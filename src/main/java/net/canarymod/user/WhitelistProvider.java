package net.canarymod.user;

import net.canarymod.backbone.BackboneWhitelist;

import java.util.List;

/**
 * Access to the backbone for whitelist
 *
 * @author Chris (damagefilter)
 */
public class WhitelistProvider {
    private BackboneWhitelist backboneWhitelist;
    private List<String> whitelist;

    public WhitelistProvider() {
        backboneWhitelist = new BackboneWhitelist();
        whitelist = backboneWhitelist.loadWhitelist();
    }

    /** Reload the whitelist from database */
    public void reload() {
        whitelist = backboneWhitelist.loadWhitelist();
    }

    /**
     * Check if a given player is whitelisted.
     *
     * @param player
     *
     * @return
     */
    public boolean isWhitelisted(String player) {
        return whitelist.contains(player);
    }

    /**
     * Adds a new whitelist entry
     *
     * @param name
     */
    public void addPlayer(String name) {
        if (!whitelist.contains(name)) {
            whitelist.add(name);
            backboneWhitelist.addWhitelistEntry(name);
        }
    }

    /**
     * Removes the given player from the whitelist
     *
     * @param name
     */
    public void removePlayer(String name) {
        if (whitelist.contains(name)) {
            whitelist.remove(name);
            backboneWhitelist.removeWhitelistEntry(name);
        }
    }

    /**
     * gets the current size of the whitelist
     *
     * @return
     */
    public int getSize() {
        return whitelist.size();
    }

    public String[] getWhitelisted() {
        return whitelist.toArray(new String[getSize()]);
    }
}
