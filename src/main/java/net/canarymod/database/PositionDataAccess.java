package net.canarymod.database;

/**
 * Position assistant DataAccess object
 *
 * @author Jason Jones (darkdiplomat)
 */
public abstract class PositionDataAccess extends DataAccess {

    public PositionDataAccess(String tableName) {
        super(tableName);
    }

    public PositionDataAccess(String tableName, String tableSuffix) {
        super(tableName, tableSuffix);
    }

    @Column(columnName = "PosX", dataType = Column.DataType.DOUBLE)
    public double posX = Double.MIN_VALUE;

    @Column(columnName = "PosY", dataType = Column.DataType.DOUBLE)
    public double posY = 0;

    @Column(columnName = "PosZ", dataType = Column.DataType.DOUBLE)
    public double posZ = 0;
}
