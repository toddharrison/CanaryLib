package net.canarymod.database.sqlite;

import net.canarymod.database.Column;
import net.canarymod.database.Column.DataType;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.JdbcConnectionManager;
import net.canarymod.database.exceptions.DatabaseAccessException;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseTableInconsistencyException;
import net.canarymod.database.exceptions.DatabaseWriteException;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

import static net.canarymod.Canary.log;

/**
 * SQLite Database
 *
 * @author Jason (darkdiplomat)
 */
public class SQLiteDatabase extends Database {

    private static SQLiteDatabase instance;
    private final String LIST_REGEX = "\u00B6";
    private final String NULL_STRING = "NULL";

    private SQLiteDatabase() {
        File path = new File("db/");

        if (!path.exists()) {
            path.mkdirs();
        }
        try {
            PreparedStatement ps = JdbcConnectionManager.getConnection().prepareStatement("PRAGMA encoding = \"UTF-8\"");
            ps.execute();
            ps.close();
        }
        catch (SQLException e) {
            log.error("Error while instantiating a new SQLiteDatabase!", e);
        }

    }

    public static SQLiteDatabase getInstance() {
        if (SQLiteDatabase.instance == null) {
            SQLiteDatabase.instance = new SQLiteDatabase();
        }
        return SQLiteDatabase.instance;
    }

    @Override
    public void insert(DataAccess data) throws DatabaseWriteException {
        if (doesEntryExist(data)) {
            return;
        }
        PreparedStatement ps = null;

        try {
            StringBuilder fields = new StringBuilder();
            StringBuilder values = new StringBuilder();
            HashMap<Column, Object> columns = data.toDatabaseEntryList();
            Iterator<Column> it = columns.keySet().iterator();

            Column column;
            while (it.hasNext()) {
                column = it.next();
                if (!column.autoIncrement()) {
                    fields.append("`").append(column.columnName()).append("`").append(",");
                    values.append("?").append(",");
                }
            }
            if (fields.length() > 0) {
                fields.deleteCharAt(fields.length() - 1);
            }
            if (values.length() > 0) {
                values.deleteCharAt(values.length() - 1);
            }
            String state = "INSERT INTO `" + data.getName() + "` (" + fields.toString() + ") VALUES(" + values.toString() + ")";
            ps = JdbcConnectionManager.getConnection().prepareStatement(state);

            int i = 1;
            for (Column c : columns.keySet()) {
                if (!c.autoIncrement()) {
                    if (c.isList()) {
                        ps.setString(i, getString((List<?>) columns.get(c)));
                    }
                    ps.setObject(i, convert(columns.get(c)));
                    i++;
                }
            }

            if (ps.executeUpdate() == 0) {
                throw new DatabaseWriteException("Error inserting SQLite: no rows updated!");
            }
        }
        catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
        catch (DatabaseTableInconsistencyException dtie) {
            log.error(dtie.getMessage(), dtie);
        }
        finally {
            close(null, ps, null);
        }

    }

    @Override
    public void update(DataAccess data, Map<String, Object> filters) throws DatabaseWriteException {
        if (!doesEntryExist(data)) {
            return;
        }
        Connection conn = JdbcConnectionManager.getConnection();
        ResultSet rs = null;

        try {
            rs = this.getResultSet(conn, data, filters, true);
            if (rs != null) {
                if (rs.next()) {
                    HashMap<Column, Object> columns = data.toDatabaseEntryList();
                    Iterator<Column> it = columns.keySet().iterator();
                    Column column;
                    while (it.hasNext()) {
                        column = it.next();
                        if (column.isList()) {
                            rs.updateObject(column.columnName(), this.getString((List<?>) columns.get(column)));
                        }
                        else {
                            rs.updateObject(column.columnName(), columns.get(column));
                        }
                    }
                    rs.updateRow();
                }
                else {
                    throw new DatabaseWriteException("Error updating DataAccess to SQLite, no such entry: " + data.toString());
                }
            }
        }
        catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
        catch (DatabaseTableInconsistencyException dtie) {
            log.error(dtie.getMessage(), dtie);
        }
        catch (DatabaseReadException e) {
            log.error(e.getMessage(), e);
        }
        finally {
            PreparedStatement st = null;
            try {
                st = rs != null && rs.getStatement() instanceof PreparedStatement ? (PreparedStatement) rs.getStatement() : null;
            }
            catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
            close(conn, st, rs);
        }
    }

    @Override
    public void remove(DataAccess dataAccess, Map<String, Object> filters) throws DatabaseWriteException {
        Connection conn = JdbcConnectionManager.getConnection();

        try {
            this.deleteRows(conn, dataAccess, filters, false);
        }
        catch (DatabaseReadException dre) {
            log.error(dre.getMessage(), dre);
        }
    }

    @Override
    public void removeAll(DataAccess dataAccess, Map<String, Object> filters) throws DatabaseWriteException {
        Connection conn = JdbcConnectionManager.getConnection();

        try {
            this.deleteRows(conn, dataAccess, filters, true);
        }
        catch (DatabaseReadException dre) {
            log.error(dre.getMessage(), dre);
        }
    }

    @Override
    public void load(DataAccess dataset, Map<String, Object> filters) throws DatabaseReadException {
        ResultSet rs = null;
        HashMap<String, Object> dataSet = new HashMap<String, Object>();
        try {
            rs = this.getResultSet(JdbcConnectionManager.getConnection(), dataset, filters, true);
            if (rs != null) {
                if (rs.next()) {
                    for (Column column : dataset.getTableLayout()) {
                        if (column.isList()) {
                            dataSet.put(column.columnName(), getList(column.dataType(), rs.getString(column.columnName())));
                        }
                        else if (column.dataType() == DataType.BOOLEAN) {
                            dataSet.put(column.columnName(), rs.getBoolean(column.columnName()));
                        }
                        else {
                            dataSet.put(column.columnName(), rs.getObject(column.columnName()));
                        }
                    }
                }
            }
        }
        catch (DatabaseReadException dre) {
            log.error(dre.getMessage(), dre);
        }
        catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
        catch (DatabaseTableInconsistencyException dtie) {
            log.error(dtie.getMessage(), dtie);
        }
        finally {
            try {
                if (rs != null) {
                    PreparedStatement st = rs.getStatement() instanceof PreparedStatement ? (PreparedStatement) rs.getStatement() : null;
                    close(null, st, rs);
                }
            }
            catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
        try {
            if (!dataSet.isEmpty()) {
                dataset.load(dataSet);
            }
        }
        catch (DatabaseAccessException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void loadAll(DataAccess typeTemplate, List<DataAccess> datasets, Map<String, Object> filters) throws DatabaseReadException {
        ResultSet rs = null;
        List<HashMap<String, Object>> stuff = new ArrayList<HashMap<String, Object>>();
        try {
            rs = this.getResultSet(JdbcConnectionManager.getConnection(), typeTemplate, filters, false);
            if (rs != null) {
                while (rs.next()) {
                    HashMap<String, Object> dataSet = new HashMap<String, Object>();
                    for (Column column : typeTemplate.getTableLayout()) {
                        if (column.isList()) {
                            dataSet.put(column.columnName(), getList(column.dataType(), rs.getString(column.columnName())));
                        }
                        else if (column.dataType() == DataType.BOOLEAN) {
                            dataSet.put(column.columnName(), rs.getBoolean(column.columnName()));
                        }
                        else {
                            dataSet.put(column.columnName(), rs.getObject(column.columnName()));
                        }
                    }
                    stuff.add(dataSet);
                }
            }

        }
        catch (DatabaseReadException dre) {
            log.error(dre.getMessage(), dre);
        }
        catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
        catch (DatabaseTableInconsistencyException dtie) {
            log.error(dtie.getMessage(), dtie);
        }
        finally {
            try {
                if (rs != null) {
                    PreparedStatement st = rs.getStatement() instanceof PreparedStatement ? (PreparedStatement) rs.getStatement() : null;
                    close(null, st, rs);
                }
            }
            catch (SQLException ex) {
                log.error(ex.getMessage(), ex);
            }
        }
        try {
            for (HashMap<String, Object> temp : stuff) {
                DataAccess newData = typeTemplate.getInstance();
                newData.load(temp);
                datasets.add(newData);
            }

        }
        catch (DatabaseAccessException dae) {
            log.error(dae.getMessage(), dae);
        }
    }

    @Override
    public void updateSchema(DataAccess schemaTemplate) throws DatabaseWriteException {
        ResultSet rs = null;

        try {
            // First check if the table exists, if it doesn't we'll skip the rest
            // of this method since we're creating it fresh.
            DatabaseMetaData metadata = JdbcConnectionManager.getConnection().getMetaData();
            rs = metadata.getTables(null, null, schemaTemplate.getName(), null);
            if (!rs.next()) {
                createTable(schemaTemplate);
            }
            else {

                LinkedList<String> toRemove = new LinkedList<String>();
                HashMap<String, Column> toAdd = new HashMap<String, Column>();
                Iterator<Column> it = schemaTemplate.getTableLayout().iterator();

                Column column;
                while (it.hasNext()) {
                    column = it.next();
                    toAdd.put(column.columnName(), column);
                }

                for (String col : getColumnNames(schemaTemplate)) {
                    if (!toAdd.containsKey(col)) {
                        toRemove.add(col);
                    }
                    else {
                        toAdd.remove(col);
                    }
                }

                for (String name : toRemove) {
                    deleteColumn(schemaTemplate.getName(), name);
                }
                for (Map.Entry<String, Column> entry : toAdd.entrySet()) {
                    insertColumn(schemaTemplate.getName(), entry.getValue());
                }
            }
        }
        catch (SQLException sqle) {
            throw new DatabaseWriteException("Error updating SQLite schema: " + sqle.getMessage());
        }
        catch (DatabaseTableInconsistencyException dtie) {
            log.error("Error updating SQLite schema." + dtie.getMessage(), dtie);
        }
        finally {
            close(null, null, rs);
        }
    }

    public void createTable(DataAccess data) throws DatabaseWriteException {
        PreparedStatement ps = null;

        try {
            StringBuilder fields = new StringBuilder();
            HashMap<Column, Object> columns = data.toDatabaseEntryList();
            Iterator<Column> it = columns.keySet().iterator();
            Column column;
            while (it.hasNext()) {
                column = it.next();
                fields.append("`").append(column.columnName()).append("` ");
                if (column.columnType().equals(Column.ColumnType.PRIMARY) && column.autoIncrement() && column.dataType() == Column.DataType.INTEGER) {
                    fields.append(" INTEGER PRIMARY KEY ASC");
                    if (it.hasNext()) {
                        fields.append(", ");
                    }
                    continue;
                }
                else {
                    fields.append(getDataTypeSyntax(column.dataType()));
                }

                if (column.columnType() == Column.ColumnType.PRIMARY) {
                    fields.append(" PRIMARY KEY");
                }
                if (column.autoIncrement()) {
                    fields.append(" AUTOINCREMENT");
                }
                if (it.hasNext()) {
                    fields.append(", ");
                }
            }
            String state = "CREATE TABLE IF NOT EXISTS `" + data.getName() + "` (" + fields.toString() + ")";
            ps = JdbcConnectionManager.getConnection().prepareStatement(state);
            if (ps.execute()) {
                log.debug("Statment Executed!");
            }
        }
        catch (SQLException ex) {
            throw new DatabaseWriteException("Error creating SQLite table '" + data.getName() + "'. " + ex.getMessage());
        }
        catch (DatabaseTableInconsistencyException ex) {
            log.error(ex.getMessage() + " Error creating SQLite table '" + data.getName() + "'. ", ex);
        }
        finally {
            close(null, ps, null);
        }
    }

    public void insertColumn(String tableName, Column column) throws DatabaseWriteException {
        PreparedStatement ps = null;

        try {
            if (column != null && !column.columnName().trim().equals("")) {
                ps = JdbcConnectionManager.getConnection().prepareStatement("ALTER TABLE `" + tableName + "` ADD `" + column.columnName() + "` " + getDataTypeSyntax(column.dataType()));
                ps.execute();
            }
        }
        catch (SQLException ex) {
            throw new DatabaseWriteException("Error adding SQLite collumn: " + column.columnName());
        }
        finally {
            close(null, ps, null);
        }

    }

    public void deleteColumn(String tableName, String columnName) throws DatabaseWriteException {
        PreparedStatement ps = null;

        try {
            if (columnName != null && !columnName.trim().equals("")) {
                ps = JdbcConnectionManager.getConnection().prepareStatement("ALTER TABLE `" + tableName + "` DROP `" + columnName + "`");
                ps.execute();
            }
        }
        catch (SQLException ex) {
            throw new DatabaseWriteException("Error deleting SQLite collumn: " + columnName);
        }
        finally {
            close(null, ps, null);
        }
    }

    public boolean doesEntryExist(DataAccess data) throws DatabaseWriteException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean toRet = false;

        try {
            StringBuilder sb = new StringBuilder();
            HashMap<Column, Object> columns = data.toDatabaseEntryList();
            Iterator<Column> it = columns.keySet().iterator();

            Column column;
            while (it.hasNext()) {
                column = it.next();
                if (!column.autoIncrement()) {
                    if (sb.length() > 0) {
                        sb.append(" AND '").append(column.columnName());
                    }
                    else {
                        sb.append("'").append(column.columnName());
                    }
                    sb.append("' = ?");
                }
            }
            ps = JdbcConnectionManager.getConnection().prepareStatement("SELECT * FROM `" + data.getName() + "` WHERE " + sb.toString());
            it = columns.keySet().iterator();

            int index = 1;
            while (it.hasNext()) {
                column = it.next();
                if (!column.autoIncrement()) {
                    ps.setObject(index, convert(columns.get(column)));
                    index++;
                }
            }
            rs = ps.executeQuery();
            if (rs != null) {
                toRet = rs.next();
            }

        }
        catch (SQLException ex) {
            throw new DatabaseWriteException(ex.getMessage() + " Error checking SQLite Entry Key in "
                    + data.toString());
        }
        catch (DatabaseTableInconsistencyException ex) {
            LogManager.getLogger().error( ex);
        }
        finally {
            close(null, ps, rs);
        }
        return toRet;
    }


    /**
     * Close a set of working data.
     * This will return all the data to the connection pool.
     * You can pass null for objects that are not relevant in your given context
     *
     * @param c
     *         the connection object
     * @param ps
     *         the prepared statement
     * @param rs
     *         the result set
     */
    private void close(Connection c, PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (c != null) {
                c.close();
            }
        }
        catch (SQLException e) {
            log.error(e.getMessage(), e);
        }

    }

    public ResultSet getResultSet(Connection conn, DataAccess data, Map<String, Object> filters, boolean limitOne) throws DatabaseReadException {
        PreparedStatement ps;
        ResultSet toRet;

        try {

            if (filters.size() > 0) {
                StringBuilder sb = new StringBuilder();
                Object[] fieldNames = filters.keySet().toArray();
                for (int i = 0; i < fieldNames.length && i < fieldNames.length; i++) {
                    sb.append("`").append(fieldNames[i]);
                    if (i + 1 < fieldNames.length) {
                        sb.append("`=? AND ");
                    }
                    else {
                        sb.append("`=?");
                    }
                }
                if (limitOne) {
                    sb.append(" LIMIT 1");
                }
                ps = conn.prepareStatement("SELECT * FROM `" + data.getName() + "` WHERE " + sb.toString());
                for (int i = 0; i < fieldNames.length && i < fieldNames.length; i++) {
                    String fieldName = String.valueOf(fieldNames[i]);
                    Column col = data.getColumnForName(fieldName);
                    if (col == null) {
                        throw new DatabaseReadException("Error fetching MySQL ResultSet in " + data.getName() + ". Column " + fieldNames[i] + " does not exist!");
                    }
                    setToStatement(i + 1, filters.get(fieldName), ps, col.dataType());
                }

            }
            else {
                if (limitOne) {
                    ps = conn.prepareStatement("SELECT * FROM `" + data.getName() + "` LIMIT 1");
                }
                else {
                    ps = conn.prepareStatement("SELECT * FROM `" + data.getName() + "`");
                }
            }
            toRet = ps.executeQuery();
        }
        catch (SQLException ex) {
            throw new DatabaseReadException("Error fetching MySQL ResultSet in " + data.getName(), ex);
        }
        catch (Exception ex) {
            throw new DatabaseReadException("Error fetching MySQL ResultSet in " + data.getName(), ex);
        }

        return toRet;
    }

    public void deleteRows(Connection conn, DataAccess data, Map<String, Object> filters, boolean limitOne) throws DatabaseReadException {
        PreparedStatement ps;
        try {
            if (filters.size() > 0) {
                StringBuilder sb = new StringBuilder();
                Object[] fieldNames = filters.keySet().toArray();
                for (int i = 0; i < fieldNames.length && i < fieldNames.length; i++) {
                    sb.append("`").append(fieldNames[i]);
                    if (i + 1 < fieldNames.length) {
                        sb.append("`=? AND ");
                    }
                    else {
                        sb.append("`=?");
                    }
                }
                if (limitOne) {
                    sb.append(" LIMIT 1");
                }
                ps = conn.prepareStatement("DELETE FROM `" + data.getName() + "` WHERE " + sb.toString());
                for (int i = 0; i < fieldNames.length && i < fieldNames.length; i++) {
                    String fieldName = String.valueOf(fieldNames[i]);
                    Column col = data.getColumnForName(fieldName);
                    if (col == null) {
                        throw new DatabaseReadException("Error deleting from SQLite table " + data.getName() + ". Column " + fieldNames[i] + " does not exist!");
                    }
                    setToStatement(i + 1, filters.get(fieldName), ps, col.dataType());
                }

            }
            else {
                if (limitOne) {
                    ps = conn.prepareStatement("DELETE FROM `" + data.getName() + "` LIMIT 1");
                }
                else {
                    // TODO: This will not work because sqlite sucks.
                    // What needs to be done is each row must be deleted, then sqlite knows a "VACUUM" command which clears the table.
                    ps = conn.prepareStatement("TRUNCATE `" + data.getName() + "`");
                }
            }
            ps.execute();
        }
        catch (SQLException ex) {
            throw new DatabaseReadException("Error deleting from SQLite table " + data.getName(), ex);
        }
        catch (Exception ex) {
            throw new DatabaseReadException("Error deleting from SQLite table " + data.getName(), ex);
        }
    }

    /**
     * Sets the given object as the given type to the given index
     * of the given PreparedStatement.
     *
     * @param index
     *         the index to set to
     * @param o
     *         the object to set
     * @param ps
     *         the prepared statement
     * @param t
     *         the DataType hint
     *
     * @throws DatabaseWriteException
     *         when an SQLException was raised or when the data type doesn't match the objects type
     */
    private void setToStatement(int index, Object o, PreparedStatement ps, Column.DataType t) throws DatabaseWriteException {
        try {
            switch (t) {
                case BYTE:
                case INTEGER:
                case FLOAT:
                case DOUBLE:
                case LONG:
                case SHORT:
                case BOOLEAN: //SQlite doesn't know boolean values, it converts it to tinyint
                    ps.setInt(index, (Integer) o);
                case STRING:
                    ps.setString(index, (String) o);
            }
        }
        catch (SQLException e) {
            throw new DatabaseWriteException("Failed to set property to prepared statement!", e);
        }
        catch (ClassCastException e) {
            throw new DatabaseWriteException("Failed to set property to prepared statement!", e);
        }

    }

    public List<String> getColumnNames(DataAccess data) {
        Statement ps = null;
        ResultSet rs = null;

        ArrayList<String> columns = new ArrayList<String>();
        String columnName;

        try {
            ps = JdbcConnectionManager.getConnection().createStatement();
            rs = ps.executeQuery("SELECT * FROM '" + data.getName() + "'");
            ResultSetMetaData rsMeta = rs.getMetaData();
            for (int index = 1; index <= rsMeta.getColumnCount(); index++) {
                columnName = rsMeta.getColumnLabel(index);
                columns.add(columnName);
            }
        }
        catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
        finally {
            close(null, null, rs);
            if (ps != null) {
                try {
                    ps.close();
                }
                catch (SQLException ex) {
                    LogManager.getLogger().error(ex);
                }
            }
        }
        return columns;
    }

    public String getDataTypeSyntax(Column.DataType type) {
        switch (type) {
            case BYTE:
                return "INTEGER";
            case INTEGER:
                return "INTEGER";
            case FLOAT:
                return "INTEGER";
            case DOUBLE:
                return "INTEGER";
            case LONG:
                return "INTEGER";
            case SHORT:
                return "INTEGER";
            case STRING:
                return "TEXT";
            case BOOLEAN:
                return "INTEGER";
        }
        return "";
    }

    /**
     * Replaces '*' character with '\\*' if the Object is a String.
     *
     * @param o
     *
     * @return
     */
    private Object convert(Object o) {
        if (o instanceof String && ((String) o).contains("*")) {
            ((String) o).replace("*", "\\*");
        }
        return o;
    }

    /**
     * Gets a Java List representation from the SQLite String.
     *
     * @param type
     * @param field
     *
     * @return
     */
    private List<Comparable<?>> getList(Column.DataType type, String field) {
        List<Comparable<?>> list = new ArrayList<Comparable<?>>();
        if (field == null) {
            return list;
        }
        switch (type) {
            case BYTE:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(Byte.valueOf(s));
                }
                break;
            case INTEGER:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(Integer.valueOf(s));
                }
                break;
            case FLOAT:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(Float.valueOf(s));
                }
                break;
            case DOUBLE:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(Double.valueOf(s));
                }
                break;
            case LONG:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(Long.valueOf(s));
                }
                break;
            case SHORT:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(Short.valueOf(s));
                }
                break;
            case STRING:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(s);
                }
                break;
            case BOOLEAN:
                for (String s : field.split(this.LIST_REGEX)) {
                    if (s.equals(NULL_STRING)) {
                        list.add(null);
                        continue;
                    }
                    list.add(Boolean.valueOf(s));
                }
                break;
        }
        return list;
    }

    /**
     * Get the database entry for a Java List.
     *
     * @param list
     *
     * @return a string representation of the passed list.
     */
    public String getString(List<?> list) {
        if (list == null) {
            return NULL_STRING;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            if (o == null) {
                sb.append(NULL_STRING);
            }
            else {
                sb.append(String.valueOf(o));
            }
            if (it.hasNext()) {
                sb.append(this.LIST_REGEX);
            }
        }
        return sb.toString();
    }
}
