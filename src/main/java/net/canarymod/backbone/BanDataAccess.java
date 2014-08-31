package net.canarymod.backbone;

import net.canarymod.database.Column;
import net.canarymod.database.Column.DataType;
import net.canarymod.database.DataAccess;

/**
 * Ban Data Access
 *
 * @author Chris (damagefilter)
 */
public class BanDataAccess extends DataAccess {

    public BanDataAccess() {
        super("ban");
    }

    /** Player uuid for this ban. */
    @Column(columnName = "uuid", dataType = DataType.STRING)
    public String uuid;

    /** Player name for this ban. */
    @Column(columnName = "player", dataType = DataType.STRING)
    public String player;

    /** IP Address for this ban. */
    @Column(columnName = "ip", dataType = DataType.STRING)
    public String ip;

    /** Reason for this ban. */
    @Column(columnName = "reason", dataType = DataType.STRING)
    public String reason;

    /** Player who banned this player. */
    @Column(columnName = "banningPlayer", dataType = DataType.STRING)
    public String banningPlayer;

    /** Date to unban. */
    @Column(columnName = "unbanDate", dataType = DataType.LONG)
    public long unbanDate = -1;

    @Override
    public DataAccess getInstance() {
        return new BanDataAccess();
    }

}
