package net.canarymod.backbone;

import net.canarymod.Canary;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Backbone to the reservelist system. This contains NO logic, it is only the data
 * source access!
 *
 * @author Jason (darkdiplomat)
 */
public class BackboneReservelist extends Backbone {

    private ReservelistDataAccess schema = new ReservelistDataAccess();
    public BackboneReservelist() {
        super(Backbone.System.RESERVELIST);
        try {
            Database.get().updateSchema(new WhitelistDataAccess());
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace("Failed to update database schema", e);
        }
    }

    /**
     * Checks if the player has a reserved slot
     *
     * @param player
     *         the player's name to check
     *
     * @return {@code true} if slot reserved; {@code false} otherwise
     */
    public boolean isSlotReserved(String player) {
        WhitelistDataAccess data = new WhitelistDataAccess();

        try {
            HashMap<String, Object> filter = new HashMap<String, Object>();
            filter.put("player", player);
            Database.get().load(data, filter);
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        return data.hasData();
    }

    /**
     * Add a new reservelist entry
     *
     * @param player
     *         the player's name
     */
    public void addSlotReservation(String player) {
        if (isSlotReserved(player)) {
            return;
        }
        ReservelistDataAccess data = new ReservelistDataAccess();

        data.player = player;
        try {
            Database.get().insert(data);
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }

    /**
     * Removes a player from the reservelist
     *
     * @param subject
     *         the player's name
     */
    public void removeReservelistEntry(String subject) {
        try {
            HashMap<String, Object> filter = new HashMap<String, Object>();
            filter.put("player", subject);
            Database.get().remove(schema, filter);
        }
        catch (DatabaseWriteException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
    }

    /**
     * Load and return all recorded reservelist entries
     *
     * @return An array list of all recorded reserve entries.
     */
    public List<String> loadReservelist() {
        List<String> reservelist = new ArrayList<String>();
        List<DataAccess> dataList = new ArrayList<DataAccess>();

        try {
            Database.get().loadAll(schema, dataList, new HashMap<String, Object>());
            for (DataAccess da : dataList) {
                ReservelistDataAccess data = (ReservelistDataAccess) da;
                reservelist.add(data.player);
            }
        }
        catch (DatabaseReadException e) {
            Canary.logStacktrace(e.getMessage(), e);
        }
        return reservelist;
    }
}
