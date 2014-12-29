package net.canarymod.backbone;

import net.canarymod.config.Configuration;
import net.canarymod.database.Column;
import net.canarymod.database.Column.DataType;
import net.canarymod.database.DataAccess;

import java.util.List;

/**
 * Warp Data Access
 *
 * @author Chris (damagefilter)
 */
public class WarpDataAccess extends DataAccess {

    public WarpDataAccess() {
        super(Configuration.getDbConfig().getWarpsTableName());
    }

    /** Name of this warp. */
    @Column(columnName = "name", dataType = DataType.STRING)
    public String name;

    /** Is this warp a player home? */
    @Column(columnName = "isPlayerHome", dataType = DataType.BOOLEAN)
    public boolean isPlayerHome;

    /** Owner of this warp. */
    @Column(columnName = "owner", dataType = DataType.STRING)
    public String owner;

    /** groups that can use this warp. */
    @Column(columnName = "groups", dataType = DataType.STRING, isList = true)
    public List<String> groups;

    /** Serialised location of this warp. */
    @Column(columnName = "location", dataType = DataType.STRING)
    public String location; // serialised location

    @Override
    public DataAccess getInstance() {
        return new WarpDataAccess();
    }
}
