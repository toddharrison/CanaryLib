package net.canarymod.backbone;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.api.world.position.Location;
import net.canarymod.database.DataAccess;
import net.canarymod.database.Database;
import net.canarymod.database.exceptions.DatabaseReadException;
import net.canarymod.database.exceptions.DatabaseWriteException;
import net.canarymod.user.Group;
import net.canarymod.warp.Warp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static net.canarymod.Canary.log;

/**
 * Backbone to the warps system This contains NO logic, it is only the data
 * source access!
 *
 * @author Chris
 */
public class BackboneWarps extends Backbone {

    private static WarpDataAccess schema = new WarpDataAccess();

    public BackboneWarps() {
        super(Backbone.System.WARPS);
        try {
            Database.get().updateSchema(new WarpDataAccess());
        }
        catch (DatabaseWriteException e) {
            log.error("Failed to update database schema", e);
        }
    }

    private boolean warpExists(Warp warp) {
        WarpDataAccess data = new WarpDataAccess();

        try {
            HashMap<String, Object> filter = new HashMap<String, Object>();
            filter.put("name", warp.getName());
            Database.get().load(data, filter);
        }
        catch (DatabaseReadException e) {
            log.error(e.getMessage(), e);
        }
        return data.hasData();
    }

    /**
     * Creates a groups array.
     * IMPORTANT NOTE: This requires the groups backbone to be loaded already!
     *
     * @param groups
     *         the {@link Group}(s)
     *
     * @return group array
     */
    private Group[] makeGroupArray(List<String> groups) {
        Group[] data = new Group[groups.size()];

        for (int i = 0; i < groups.size(); ++i) {
            data[i] = Canary.usersAndGroups().getGroup(groups.get(i));
        }
        return data;
    }

    /**
     * Add a new Warp to the list of Warps.
     *
     * @param warp
     *         Warp instance to add to the data source.
     */
    public void addWarp(Warp warp) {
        if (warpExists(warp)) {
            updateWarp(warp);
            return;
        }
        WarpDataAccess data = new WarpDataAccess();

        data.groups = warp.getGroupsAsString();
        data.isPlayerHome = warp.isPlayerHome();
        data.location = warp.getLocation().toString();
        data.name = warp.getName();
        data.owner = warp.getOwner();

        try {
            Database.get().insert(data);
        }
        catch (DatabaseWriteException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Remove a Warp from the data source
     *
     * @param warp
     *         Warp instance to remove from the data source.
     */
    public void removeWarp(Warp warp) {
        try {
            HashMap<String, Object> filter = new HashMap<String, Object>();
            filter.put("name", warp.getName());
            filter.put("location", warp.getLocation().toString());
            Database.get().remove(schema, filter);
        }
        catch (DatabaseWriteException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Update a Warp
     *
     * @param warp
     *         Warp instance to update to the data source.
     */
    public void updateWarp(Warp warp) {
        WarpDataAccess data = new WarpDataAccess();

        data.groups = warp.getGroupsAsString();
        data.isPlayerHome = warp.isPlayerHome();
        data.location = warp.getLocation().toString();
        data.name = warp.getName();
        data.owner = warp.getOwner();
        try {
            HashMap<String, Object> filter = new HashMap<String, Object>();
            filter.put("name", warp.getName());
            Database.get().update(data, filter);
        }
        catch (DatabaseWriteException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * Load and return all warps
     *
     * @return An ArrayList containing all loaded Warp instances.
     */
    public List<Warp> loadWarps() {
        List<Warp> warps = new ArrayList<Warp>();
        List<DataAccess> daos = new ArrayList<DataAccess>();

        try {
            Database.get().loadAll(schema, daos, new HashMap<String, Object>());
            for (DataAccess dao : daos) {
                WarpDataAccess data = (WarpDataAccess) dao;
                Group[] groups = makeGroupArray(data.groups);
                String owner = ToolBox.stringToNull(data.owner);
                String name = data.name;
                boolean playerHome = data.isPlayerHome;
                Location loc = Location.fromString(data.location);
                Warp warp;

                if (owner != null) {
                    warp = new Warp(loc, name, owner, playerHome);
                }
                else if (groups != null && groups.length > 0) {
                    warp = new Warp(loc, groups, name);
                }
                else {
                    // assume this is a public warp
                    warp = new Warp(loc, name);
                }
                warps.add(warp);
            }
        }
        catch (DatabaseReadException e) {
            log.error(e.getMessage(), e);
        }

        return warps;
    }
}
