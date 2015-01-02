package net.canarymod.database;

/**
 * Location assistant DataAccess object
 *
 * @author Jason Jones (darkdiplomat)
 */
public abstract class LocationDataAccess extends PositionDataAccess {

    public LocationDataAccess(String tableName) {
        super(tableName);
    }

    public LocationDataAccess(String tableName, String tableSuffix) {
        super(tableName, tableSuffix);
    }

    // X Y Z from PositionDataAccess
    @Column(columnName = "Rotation", dataType = Column.DataType.FLOAT)
    public float rotation = 0;

    @Column(columnName = "Pitch", dataType = Column.DataType.DOUBLE)
    public float pitch = 0;

    @Column(columnName = "World", dataType = Column.DataType.STRING)
    public String world = "default";

    @Column(columnName = "Dimension", dataType = Column.DataType.STRING)
    public String dimension = "NORMAL";
}
