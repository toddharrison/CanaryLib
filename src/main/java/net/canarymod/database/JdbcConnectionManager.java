package net.canarymod.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import net.canarymod.Canary;
import net.canarymod.config.Configuration;
import net.canarymod.config.DatabaseConfiguration;
import net.canarymod.database.exceptions.DatabaseAccessException;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Represents a connection (pool) manager for all sorts of JDBC connections.
 * In our particular case that is mysql and sqlite.
 * For sqlite, due to the minimal nature of it,
 * there need to be a separate handling.
 * TODO: Configure statement caching!
 */
public class JdbcConnectionManager {
    /**
     * Helper enum type so we can easily identify the driver type further down the code
     */
    public enum Type {
        MYSQL("com.mysql.jdbc.Driver", "mysql"),
        SQLITE("org.sqlite.JDBC", "sqlite");

        String classpath;
        String identifier;

        Type(String str, String identifier) {
            this.classpath = str;
            this.identifier = identifier;
        }

        public String getClassPath() {
            return this.classpath;
        }

        public String getIdentifier() {
            return this.identifier;
        }

        public static Type forName(String name) {
            for(Type t : Type.values()) {
                if(t.getIdentifier().equalsIgnoreCase(name)) {
                    return t;
                }
            }
            return null;
        }
    }

    // The data source pool ;)
    private ComboPooledDataSource cpds;
    // Sqlite quirk. You cannot have multiple connections on one sqlite table.
    // If you do, it results in a table deadlock.
    // To prevent it, we force one connection only
    private Connection sqliteConnection;

    private Type type;

    private static JdbcConnectionManager instance;

    /**
     * Instantiates the connection manager
     *
     * @param type the database type
     * @throws SQLException
     */
    private JdbcConnectionManager(Type type) throws SQLException {
        DatabaseConfiguration cfg = Configuration.getDbConfig();
        cpds = new ComboPooledDataSource();
        this.type = type;
        try {
            cpds.setDriverClass(type.getClassPath());
            if(type == Type.SQLITE) {
                cpds.setJdbcUrl(cfg.getDatabaseUrl(type.getIdentifier()));
            }
            else {
                cpds.setJdbcUrl(cfg.getDatabaseUrl(type.getIdentifier()));
            }
            cpds.setUser(cfg.getDatabaseUser());
            cpds.setPassword(cfg.getDatabasePassword());

            // For settings explanations see
            // http://javatech.org/2007/11/c3p0-connectionpool-configuration-rules-of-thumb/
            // https://community.jboss.org/wiki/HowToConfigureTheC3P0ConnectionPool?_sscc=t

            //connection pooling
            cpds.setAcquireIncrement(cfg.getAcquireIncrement());
            cpds.setMaxIdleTime(cfg.getMaxConnectionIdleTime());
            cpds.setMaxIdleTimeExcessConnections(cfg.getMaxExcessConnectionsIdleTime());
            cpds.setMaxPoolSize(cfg.getMaxPoolSize());
            cpds.setMinPoolSize(cfg.getMinPoolSize());
            cpds.setNumHelperThreads(cfg.getNumHelperThreads());
            cpds.setUnreturnedConnectionTimeout(cfg.getReturnConnectionTimeout());
            cpds.setIdleConnectionTestPeriod(cfg.getConnectionTestFrequency());

            //Statement pooling
            cpds.setMaxStatements(cfg.getMaxCachedStatements());
            cpds.setMaxStatementsPerConnection(cfg.getMaxCachedStatementsPerConnection());
            cpds.setStatementCacheNumDeferredCloseThreads(cfg.getNumStatementCloseThreads());

        } catch (PropertyVetoException e) {
            Canary.logDerp("Failed to configure the connection pool!", e);
        }
        //Test connection...
        //If this fails it throws an SQLException so we're notified
        Connection c = cpds.getConnection();
        c.close();
    }

    /**
     * Get the Database type.
     * @return the type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Create a new instance of this connection manager.
     *
     * @return an instance of the manager
     *
     * @throws DatabaseAccessException
     */
    private static JdbcConnectionManager getInstance() throws DatabaseAccessException {
        if(instance == null) {
            try {
                instance = new JdbcConnectionManager(Type.forName(Configuration.getServerConfig().getDatasourceType()));
            }
            catch(SQLException e) {
                throw new DatabaseAccessException("Unable to instantiate Connection Pool!", e);
            }

        }
        return instance;
    }

    /**
     * Get a connection form the connection pool.
     *
     * @return connection from the pool
     */
    public static Connection getConnection() {
        try {
            JdbcConnectionManager cman = getInstance();
            if(cman.type == Type.SQLITE) {
                if(cman.sqliteConnection != null) {
                    cman.sqliteConnection.close();
                }
                cman.sqliteConnection = cman.cpds.getConnection();
                return cman.sqliteConnection;
            }
            return cman.cpds.getConnection();
        } catch (SQLException e) {
            Canary.logSevere("Couldn't get a Connection from pool!", e);
            return null;
        } catch (DatabaseAccessException e) {
            Canary.logSevere("Couldn't get a Connection from pool!", e);
            return null;
        }
    }

    /**
     * Shut down the connection pool.
     * Should be called when the system is reloaded or goes down to prevent data loss.
     */
    public static void shutdown() {
        if(instance == null) {
            // already shut down or never instantiated (perhaps because we're running on a non-jdbc database)
            return;
        }
        instance.cpds.close();
        if(instance.sqliteConnection != null) {
            try {
                instance.sqliteConnection.close();
            }
            catch (SQLException e) {
                Canary.logWarning("SQLite connection could not be closed. Whoops!", e);
            }
        }
        instance = null;
    }
}
