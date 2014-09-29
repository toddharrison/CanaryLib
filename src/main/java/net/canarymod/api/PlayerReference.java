package net.canarymod.api;

import net.canarymod.api.statistics.Achievement;
import net.canarymod.api.statistics.Stat;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Position;
import net.canarymod.permissionsystem.PermissionProvider;
import net.canarymod.user.Group;

import java.util.UUID;

/**
 * The reference that brings {@link net.canarymod.api.OfflinePlayer} and {@link net.canarymod.api.entity.living.humanoid.Player} together
 *
 * @author Jason (darkdiplomat)
 */
public interface PlayerReference {

    /**
     * Get the permission provider that is responsible for the player
     *
     * @return PermissionProvider
     */
    public PermissionProvider getPermissionProvider();

    /**
     * Get this players main group
     *
     * @return {@link Group}
     */
    public Group getGroup();

    /**
     * get this players prefix
     *
     * @return {@link String}
     */
    public String getPrefix();

    /**
     * Check if there is an online player for this offline player
     *
     * @return {@code true} if online; {@code false} otherwise
     */
    public boolean isOnline();

    /**
     * Check if this player has the given permission
     *
     * @param path
     *         the permission path
     *
     * @return {@code true} if has permission; {@code false} otherwise
     */
    public boolean hasPermission(String path);

    /**
     * Set this players group
     *
     * @param group
     *         the {@link net.canarymod.user.Group} to set
     */
    public void setGroup(Group group);

    /**
     * Add a group to this players sub groups
     *
     * @param group
     *         the {@link Group} to add
     */
    public void addGroup(Group group);

    /**
     * Remove this group from the player.<br>
     * You can NOT delete the default group.
     *
     * @param g
     *         the {@link Group} to remove
     *
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean removeGroup(Group g);

    /**
     * Remove a group with this name from the player.<br>
     * You can NOT delete the default group.
     *
     * @param g
     *         the name of the {@link Group} to remove
     *
     * @return {@code true} if successful; {@code false} otherwise
     */
    public boolean removeGroup(String g);

    /**
     * Check if player is in the given group
     *
     * @param group
     *         the {@link Group} to check
     * @param parents
     *         {@code true} if you want to take parent groups into account; {@code false} otherwise
     *
     * @return {@code true} if the player is in the group, {@code false} otherwise
     */
    public boolean isInGroup(Group group, boolean parents);

    /**
     * Check if player is in the given group
     *
     * @param group
     *         the name of the group to check
     * @param parents
     *         {@code true} if you want to take parent groups into account; {@code false} otherwise
     *
     * @return {@code true} if the player is in the group; {@code false} otherwise
     */
    public boolean isInGroup(String group, boolean parents);

    /**
     * Set this players prefix
     *
     * @param prefix
     *         the prefix to be set
     */
    public void setPrefix(String prefix);

    /**
     * get the world the player logged off in
     *
     * @return {@link net.canarymod.api.world.World}
     */
    public World getWorld();

    /**
     * get the position the player logged off at
     *
     * @return {@link net.canarymod.api.world.position.Position}
     */
    public Position getPosition();

    /**
     * Get this offline players name
     *
     * @return name
     */
    public String getName();

    /**
     * Get the UUID of this Player
     *
     * @return UUID
     */
    public UUID getUUID();

    /**
     * Gets the UUID of this Player as a string
     *
     * @return UUID as string
     */
    public String getUUIDString();

    /**
     * Check if this player is muted
     *
     * @return {@code true} if muted; {@code false} otherwise
     */
    public boolean isMuted();

    /**
     * Mute or unmute this offline player
     *
     * @param muted
     *         {@code true} to mute; {@code false} to unmute
     */
    public void setMuted(boolean muted);

    /**
     * Get all of this players groups
     *
     * @return Group array
     */
    public Group[] getPlayerGroups();

    /**
     * Gets the date and time the Player first joined (or when the player.dat was created) formatted as dd-MMM-yyyy HH:mm:ss
     *
     * @return first joined
     */
    public String getFirstJoined();

    /**
     * Gets the time played in seconds
     *
     * @return time played
     */
    public long getTimePlayed();

    /**
     * Gets the {@link GameMode} for the Player
     *
     * @return the Player's {@link GameMode}
     */
    public GameMode getMode();

    /**
     * Gets the {@link GameMode} ID for the Player
     *
     * @return 0 for Survival; 1 for Creative; 2 for Adventure
     */
    public int getModeId();

    /**
     * Sets the Player's {@link GameMode}
     *
     * @param mode
     *         the {@link GameMode} to set
     */
    public void setMode(GameMode mode);

    /**
     * Sets the Player's {@link GameMode}
     *
     * @param mode
     *         0 for Survival; 1 for Creative; 2 for Adventure
     */
    public void setModeId(int mode);

    /**
     * Check if this player has the admin flag set
     *
     * @return {@code true} if is Admin; {@code false} otherwise
     */
    public boolean isAdmin();

    /**
     * Check if this player can modify the world
     *
     * @return {@code true} if can Build; {@code false} otherwise
     */
    public boolean canBuild();

    /**
     * Set whether this player can modify the world
     *
     * @param canModify
     *         {@code true} if can build; {@code false} if not
     */
    public void setCanBuild(boolean canModify);

    /**
     * Check if this player can bypass permission checks
     *
     * @return {@code true} if can ignore restrictions; {@code false} otherwise
     */
    public boolean canIgnoreRestrictions();

    /**
     * Set whether this player can bypass permission checks
     *
     * @param canIgnore
     *         {@code true} if can ignore restrictions; {@code false} if not
     */
    public void setCanIgnoreRestrictions(boolean canIgnore);

    /**
     * Add to the level of food exhaustion of a player
     *
     * @param exhaustion
     *         the exhaustion to add
     */
    public void addExhaustion(float exhaustion);

    /**
     * Set the food exhaustion level to the specified value
     *
     * @param exhaustion
     *         the exhaustion to set
     */
    public void setExhaustion(float exhaustion);

    /**
     * Retrieve the current exhaustion level for this player
     *
     * @return exhaustion level
     */
    public float getExhaustionLevel();

    /**
     * Set this players food level
     *
     * @param hunger
     *         the hunger to set
     */
    public void setHunger(int hunger);

    /**
     * Get this players food level
     *
     * @return the food level.
     */
    public int getHunger();

    /**
     * Add experience to the player
     *
     * @param experience
     *         the experience amount
     */
    public void addExperience(int experience);

    /**
     * Remove experience from the player
     *
     * @param experience
     *         the experience amount
     */
    public void removeExperience(int experience);

    /**
     * Get experience points for this player
     *
     * @return experience
     */
    public int getExperience();

    /**
     * Set the experience of this player
     *
     * @param xp
     *         the experience amount
     */
    public void setExperience(int xp);

    /**
     * Get the current level of this player.
     *
     * @return level
     */
    public int getLevel();

    /**
     * Explicitly override this players level.
     *
     * @param level
     *         the new player level
     */
    public void setLevel(int level);

    /**
     * Add the specific amount of levels to the player.
     *
     * @param level
     *         amount of levels to add
     */
    public void addLevel(int level);

    /**
     * Explicitly remove the amount of levels from the player.
     *
     * @param level
     *         amount of levels to remove
     */
    public void removeLevel(int level);

    /**
     * Set this players home location
     *
     * @param loc
     *         The new home location.
     */
    public void setHome(Location loc);

    /**
     * Get this players home location
     *
     * @return home {@link Location}
     */
    public Location getHome();

    /**
     * Check if this player has a home location set
     *
     * @return {@code true} if home is set; {@code false} if not
     */
    public boolean hasHome();

    /**
     * Returns all allowed IPs for this player as Array
     *
     * @return allowed ips
     */
    public String[] getAllowedIPs();

    /**
     * Get the IP for this player (or last known IP)
     *
     * @return ip address
     */
    public String getIP();

    /**
     * Gets the timestamp of the last time the player was online
     *
     * @return last seen timestamp
     */
    public String getLastJoined();

    /**
     * Get this player's health.
     *
     * @return health
     */
    public float getHealth();

    /**
     * Set this player's health.
     *
     * @param health
     *         the health to be set
     */
    public void setHealth(float health);

    /**
     * Increase this player's health. This does not set but add the amount of
     * health
     *
     * @param health
     *         to increase the health with (negative values decrease)
     */
    public void increaseHealth(float health);

    /**
     * Sets the value of a given {@link net.canarymod.api.statistics.Stat}
     *
     * @param stat
     *         the {@link net.canarymod.api.statistics.Stat} to set value of
     * @param value
     *         the value to be set
     */
    public void setStat(Stat stat, int value);

    /**
     * Increases the value of a given {@link net.canarymod.api.statistics.Stat}
     *
     * @param stat
     *         the {@link net.canarymod.api.statistics.Stat} to increase value of
     * @param value
     *         the amount to increase
     */
    public void increaseStat(Stat stat, int value);

    /**
     * Decrease the value of a given {@link net.canarymod.api.statistics.Stat}
     *
     * @param stat
     *         the {@link net.canarymod.api.statistics.Stat} to decrease value of
     * @param value
     *         the amount to decrease
     */
    public void decreaseStat(Stat stat, int value);

    /**
     * Gets the value of a {@link net.canarymod.api.statistics.Stat}
     *
     * @param stat
     *         the {@link net.canarymod.api.statistics.Stat} to get value of
     *
     * @return stat value
     */
    public int getStat(Stat stat);

    /**
     * Checks if the {@link net.canarymod.api.statistics.Achievement} has been awarded
     *
     * @param achievement
     *         the {@link net.canarymod.api.statistics.Achievement} to check
     *
     * @return {@code true} if has achievement; {@code false} if not
     */
    public boolean hasAchievement(Achievement achievement);

    /**
     * Removes an awarded {@link net.canarymod.api.statistics.Achievement} and any child achievements
     *
     * @param achievement
     *         the {@link net.canarymod.api.statistics.Achievement} to be removed
     */
    public void removeAchievement(Achievement achievement);

    /**
     * Awards an {@link net.canarymod.api.statistics.Achievement} and any parent achievements requried
     *
     * @param achievement
     *         the {@link net.canarymod.api.statistics.Achievement} to be awarded
     */
    public void awardAchievement(Achievement achievement);
}
