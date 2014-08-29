package net.canarymod.user;

import net.canarymod.Canary;
import net.canarymod.api.PlayerReference;
import net.canarymod.backbone.BackboneOperators;

import java.io.*;
import java.util.List;

import static net.canarymod.Canary.log;

/**
 * Access to the backbone for operators
 *
 * @author Jason (darkdiplomat)
 */
public class OperatorsProvider {
    private BackboneOperators backboneOps;
    private List<String> ops;

    public OperatorsProvider() {
        backboneOps = new BackboneOperators();
        ops = backboneOps.loadOps();
        readOpsCfg();
    }

    /** Reload the ops from database */
    public void reload() {
        ops = backboneOps.loadOps();
        readOpsCfg();
    }

    /**
     * Reads the config/ops.cfg file if it exists and updates the database
     * with the names found in it.
     */
    private void readOpsCfg() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("config/ops.cfg")));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                }
                if (!isOpped(line)) {
                    addPlayer(line);
                }
            }
        }
        catch (FileNotFoundException e) {
            log.info("Could not find config/ops.cfg. Creating one for you...");
            File f = new File("config/ops.cfg");
            try {
                if (f.createNewFile()) {
                    PrintWriter pwriter = new PrintWriter(new FileWriter(f));
                    pwriter.println("# Note: This file is not guaranteed to be synchronous with the actual ops list in database.");
                    pwriter.println("# However, you may use it to quickly add new operators as you please.");
                    pwriter.println("# Any duplicate entries will be taken care of so don't worry.");
                    pwriter.println("# Lines starting with # are comments ;)");
                    pwriter.println("# Add one name to each line.");
                    pwriter.close();
                    log.info("You can now add ops to config/ops.cfg (one per line!). We left you a note.");
                }
            }
            catch (IOException e1) {
                log.error("Failed to write config/ops.cfg! (Probably no write-access!)", e);
            }
        }
        catch (IOException e) {
            log.error("Failed to read from config/ops.cfg!", e);
        }
    }

    /**
     * Check if a given player is opped.
     *
     * @param player
     *         the uuid/name of a player
     *
     * @return true if player is opped, false otherwise
     */
    public boolean isOpped(String player) {
        // Did UUID get passed?
        if (player.matches("[0-9a-f]{8}\\-([0-9a-f]{4}\\-){3}[0-9a-f]{12}")) {
            return ops.contains(player);
        }
        else if (Canary.getServer() != null) { // Like at start up...
            // Try to get a UUID reference from a known player
            return isOpped(Canary.getServer().matchKnownPlayer(player));
        }
        else {
            return false;
        }
    }

    public boolean isOpped(PlayerReference playerReference) {
        if (playerReference != null) {
            // Lets update to UUID if we can get a UUID
            if (ops.contains(playerReference.getName())) {
                if (playerReference.getUUIDString() != null) {
                    removePlayer(playerReference.getName());
                    addPlayer(playerReference.getUUIDString());
                }
                return true;
            }
            // UUID test it is
            return ops.contains(playerReference.getUUIDString());
        }
        return false;
    }

    /**
     * Adds a new operators entry
     *
     * @param entry
     *         the player uuid/name you want to add
     */
    public void addPlayer(String entry) {
        if (!ops.contains(entry)) {
            ops.add(entry);
            backboneOps.addOpEntry(entry);
        }
    }

    /**
     * Removes the given player from the ops list
     *
     * @param entry
     *         the player uuid/name you want to remove
     */
    public void removePlayer(String entry) {
        if (ops.contains(entry)) {
            ops.remove(entry);
            backboneOps.removeOpEntry(entry);
        }
    }

    /**
     * gets the current size of the ops list
     *
     * @return the size
     */
    public int getSize() {
        return ops.size();
    }

    /**
     * Gets an array of all Operators
     *
     * @return
     */
    public String[] getOps() {
        return ops.toArray(new String[getSize()]);
    }
}
