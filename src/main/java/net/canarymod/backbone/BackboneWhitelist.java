package net.canarymod.backbone;

import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static net.canarymod.Canary.log;

/**
 * Backbone to the whitelist system. This contains NO logic, it is only the data
 * source access!
 *
 * @author Chris (damagefilter)
 */
public class BackboneWhitelist extends Backbone {

    private static WhitelistDataAccess schema = new WhitelistDataAccess();

    public BackboneWhitelist() {
        super(Backbone.System.WHITELIST);
        try {
            Database.get().updateSchema(new WhitelistDataAccess());
        }
        catch (DatabaseWriteException e) {
            log.error("Failed to update database schema", e);
        }
    }

    /**
     * Checks if the player is whitelisted
     *
     * @param player
     *         the player's name to check
     *
     * @return {@code true} if whitelisted; {@code false} otherwise
     */
    public boolean isWhitelisted(String player) {
        WhitelistDataAccess data = new WhitelistDataAccess();

        try {
            HashMap<String, Object> filter = new HashMap<String, Object>();
            filter.put("player", player);
            Database.get().load(data, filter);
        }
        catch (DatabaseReadException e) {
            log.error(e.getMessage(), e);
        }
        return data.hasData();
    }

    /**
     * Add a new whitelist entry
     *
     * @param player
     *         the player's name
     */
    public void addWhitelistEntry(String player) {
        if (isWhitelisted(player)) {
            return;
        }
        WhitelistDataAccess data = new WhitelistDataAccess();

        data.player = player;
        try {
            Database.get().insert(data);
        }
        catch (DatabaseWriteException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Removes a player from the whitelist
     *
     * @param subject
     *         the player's name
     */
    public void removeWhitelistEntry(String subject) {
        try {
            HashMap<String, Object> filter = new HashMap<String, Object>();
            filter.put("player", subject);
            Database.get().remove(schema, filter);
        }
        catch (DatabaseWriteException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Load and return all recorded bans
     *
     * @return An array list of all recorded ban instances.
     */
    public List<String> loadWhitelist() {
        List<String> whiteList = new ArrayList<String>();
        List<DataAccess> dataList = new ArrayList<DataAccess>();

        try {
            Database.get().loadAll(schema, dataList, new HashMap<String, Object>());
            for (DataAccess da : dataList) {
                WhitelistDataAccess data = (WhitelistDataAccess) da;
                whiteList.add(data.player);
            }
        }
        catch (DatabaseReadException e) {
            log.error(e.getMessage(), e);
        }
        return whiteList;
    }
}
