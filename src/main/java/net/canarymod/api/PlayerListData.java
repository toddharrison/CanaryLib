package net.canarymod.api;

import com.mojang.authlib.GameProfile;
import net.canarymod.api.chat.ChatComponent;
import net.canarymod.api.entity.living.humanoid.Player;

import java.util.UUID;

/**
 * The information shown in the PlayerList ("Tab List")
 *
 * @author Jason (darkdiplomat)
 */
public final class PlayerListData {
    private final PlayerListAction action;
    private GameProfile profile;
    private int ping;
    private GameMode mode;
    private ChatComponent displayName;

    /**
     * Constructs a PlayerListData with {@link net.canarymod.api.PlayerListAction} information
     *
     * @param action
     *         the {@link net.canarymod.api.PlayerListAction} this data if for
     * @param profile
     *         the {@link com.mojang.authlib.GameProfile} of the {@link Player} this ListData is for
     * @param ping
     *         the ping of the {@link Player} this ListData is for
     * @param mode
     *         the mode of the {@link Player} this ListData is for
     * @param displayName
     *         the {@link net.canarymod.api.chat.ChatComponent} used as a DisplayName in the list
     */
    public PlayerListData(PlayerListAction action, GameProfile profile, int ping, GameMode mode, ChatComponent displayName) {
        this.action = action;
        this.profile = profile;
        this.ping = ping;
        this.mode = mode;
        this.displayName = displayName;
    }

    /**
     * Constructs a PlayerListData with {@link net.canarymod.api.PlayerListAction} information
     *
     * @param action
     * @param uuid
     * @param ping
     * @param mode
     * @param displayName
     */
    public PlayerListData(PlayerListAction action, UUID uuid, int ping, GameMode mode, ChatComponent displayName) {
        // This is the constructor for doing a display name change usually, but can be used for other changes
        this(action, new GameProfile(uuid, null), ping, mode, displayName);
    }

    /**
     * Constructs a PlayerListData with {@link net.canarymod.api.PlayerListAction} information
     *
     * @param action
     *         the {@link net.canarymod.api.PlayerListAction} of this ListData
     * @param player
     *         the {@link Player} to use data from
     */
    public PlayerListData(PlayerListAction action, Player player) {
        // This is the constructor most used within the Player implementation (if done right that is)
        this.action = action;
        this.profile = player.getGameProfile();
        this.ping = player.getPing();
        this.mode = player.getMode();
        this.displayName = player.getDisplayNameComponent();
    }

    /*
     * Copy Construct
     */
    private PlayerListData(PlayerListData templ) {
        this.action = templ.action;
        this.profile = templ.profile;
        this.ping = templ.ping;
        this.mode = templ.mode;
        this.displayName = displayName != null ? displayName.clone() : null;
    }

    /**
     * Gets the {@link net.canarymod.api.PlayerListAction} of this ListData
     *
     * @return action
     */
    public final PlayerListAction getAction() {
        return action;
    }

    /**
     * Gets the {@link com.mojang.authlib.GameProfile} of this ListData
     *
     * @return profile
     */
    public final GameProfile getProfile() {
        return profile;
    }

    /**
     * Sets the {@link com.mojang.authlib.GameProfile} of this ListData
     *
     * @param profile
     *         the {@link com.mojang.authlib.GameProfile} to
     */
    public final void setProfile(GameProfile profile) {
        this.profile = profile;
    }

    /**
     * Gets the ping of the {@link net.canarymod.api.entity.living.humanoid.Player} this ListData is for
     *
     * @return ping
     */
    public final int getPing() {
        return ping;
    }

    /**
     * Sets the ping of this ListData
     *
     * @param ping
     *         the ping to be set
     */
    public final void setPing(int ping) {
        this.ping = ping;
    }

    /**
     * Gets the {@link net.canarymod.api.GameMode} of the {@link net.canarymod.api.entity.living.humanoid.Player} this ListData is for
     *
     * @return gamemode
     */
    public final GameMode getMode() {
        return mode;
    }

    /**
     * Sets the {@link net.canarymod.api.GameMode} of this ListData
     *
     * @param mode
     *         the {@link net.canarymod.api.GameMode}
     */
    public final void setMode(GameMode mode) {
        this.mode = mode;
    }

    /**
     * Checks if the {@link net.canarymod.api.chat.ChatComponent} display name is not null
     *
     * @return {@code true} if not null; {@code false} if null
     */
    public final boolean displayNameSet() {
        return this.displayName != null;
    }

    public final ChatComponent getDisplayName() {
        return this.displayName;
    }

    public final void setDisplayName(ChatComponent displayName) {
        this.displayName = displayName;
    }

    public PlayerListData copy() {
        return new PlayerListData(this);
    }

    public final String toString() {
        return String.format("PlayerListData[Action=%s, Profile=%s, Ping=%d, Mode=%s, DisplayName=%s]", action, profile, ping, mode, displayName);
    }
}
