package net.canarymod.backbone;

import net.canarymod.database.Column;
import net.canarymod.database.Column.DataType;
import net.canarymod.database.DataAccess;

/**
 * Reserve List Data Access
 *
 * @author Jason (darkdiplomat)
 */
public class ReservelistDataAccess extends DataAccess {

    public ReservelistDataAccess() {
        super("reservelist");
    }

    /** Playername for this reservelist entry */
    @Column(columnName = "player", dataType = DataType.STRING)
    public String player;

    @Override
    public DataAccess getInstance() {
        return new ReservelistDataAccess();
    }
}
