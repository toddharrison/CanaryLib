package net.canarymod.api.entity.living.humanoid;

import net.canarymod.api.NetServerHandler;
import net.canarymod.api.PlayerListEntry;
import net.canarymod.api.PlayerReference;
import net.canarymod.api.chat.ChatComponent;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.packet.Packet;
import net.canarymod.api.world.blocks.Sign;
import net.canarymod.api.world.position.Direction;
import net.canarymod.api.world.position.Location;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.hook.player.TeleportHook.TeleportCause;

/**
 * Player wrapper
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public interface Player extends Human, MessageReceiver, PlayerReference {

    /** Initializes or re-initializes the permissions, groups and prefix data for this player */
    public void initPlayerData();

    /**
     * Sends a message as the Player
     *
     * @param message
     *         the message to send as the Player
     */
    public void chat(String message);

    /**
     * Get the spawn position for the player
     *
     * @return spawn position
     */
    public Location getSpawnPosition();

    /**
     * Sets the spawn position
     *
     * @param spawn
     *         the {@link Location} to spawn at
     */
    public void setSpawnPosition(Location spawn);

    /**
     * Make this player execute the given command
     *
     * @param command
     *         the String array arguments
     *
     * @return true if the command executed successfully, false otherwise
     */
    public boolean executeCommand(String[] command);

    /**
     * Send player a packet. This will throw exception if packet is invalid!
     *
     * @param packet
     *         the {@link Packet} to send
     */
    public void sendPacket(Packet packet);

    /**
     * Get the NetServerHandler for this player
     *
     * @return {@link NetServerHandler}
     */
    public NetServerHandler getNetServerHandler();

    /**
     * Check if a player has this permission.
     * This will not issue a PermissionCheck hook so the returned
     * result is reliable.
     */
    public boolean safeHasPermission(String permission);

    /**
     * Get player enderchest inventory
     *
     * @return enderchest inventory
     */
    public Inventory getEnderChestInventory();

    /**
     * Teleport to this location with specified cause
     * <p/>
     * If other Teleport methods are called, cause default to PLUGIN
     *
     * @param location
     *         the {@link Location} to teleport to
     * @param cause
     *         the {@link TeleportCause}]
     */
    public void teleportTo(Location location, TeleportCause cause);

    /**
     * Kick this player
     *
     * @param reason
     *         the string reasoning
     */
    public void kick(String reason);

    /**
     * Kick this player without a hook call
     *
     * @param reason
     *         the string reasoning
     */
    public void kickNoHook(String reason);

    /**
     * Get the cardinal direction this player is looking at
     *
     * @return direction
     */
    public Direction getCardinalDirection();

    /**
     * Gets the Player's ping
     *
     * @return the ping
     */
    public int getPing();

    /**
     * Gets a {@link PlayerListEntry} for the Player
     * <p/>
     * The initially set name is {@link Player#getDisplayName()}
     *
     * @param shown
     *         whether it should be shown by default
     *
     * @return {@link PlayerListEntry} for the Player
     *
     * @see PlayerListEntry
     */
    public PlayerListEntry getPlayerListEntry(boolean shown);

    /**
     * Sends a {@link PlayerListEntry} to the Player
     * <p/>
     * NOTE: The server needs to have PlayerList enabled in the configuration
     *
     * @param plentry
     *         the {@link PlayerListEntry} to send
     *
     * @see PlayerListEntry
     */
    public void sendPlayerListEntry(PlayerListEntry plentry);

    /**
     * Checks if the player is asleep
     *
     * @return true if player is in bed, false otherwise
     */
    public boolean isSleeping();

    /**
     * Check if this player is deeply sleeping,
     * that is: Player is in bed and the screen has fully faded out.
     *
     * @return true if fully asleep, false otherwise
     */
    public boolean isDeeplySleeping();

    /** Refreshes mode */
    public void refreshCreativeMode();

    /** Sends update of PlayerCapabilities to player */
    public void updateCapabilities();

    /**
     * Sends the WindowOpenPacket to the Player with the specified Inventory
     * <p/>
     * NOTE: Some inventories may not work remotely
     *
     * @param inventory
     *         the {@link Inventory} to have opened
     */
    public void openInventory(Inventory inventory);

    /** Creates a fake Workbench and sends the WindowOpenPacket to the Player */
    public void createAndOpenWorkbench();

    /** Creates a fake Anvil and sends the WindowOpenPacket to the Player */
    public void createAndOpenAnvil();

    /**
     * Creates a fake EnchantmentTable and then sends the WindowOpenPacket to the Player
     *
     * @param bookshelves
     *         **Currently not implemented** Sets the number of bookshelves to fake around the enchantment table
     */
    public void createAndOpenEnchantmentTable(int bookshelves);

    /**
     * Opens the {@link Sign} edit window.
     * <p/>
     * NOTE: For changes to take effect, the {@link Sign} needs to be editable, or the Player set as owner
     *
     * @param sign
     *         the {@link Sign} to edit
     */
    public void openSignEditWindow(Sign sign);

    /**
     * Closes an open Inventory GUI if one is presently open.<br/>
     * This should also call the close method for that inventory.
     */
    public void closeWindow();

    /**
     * Gets the locale the player is using in their client. This is in the format of language_region e.g. en_US
     *
     * @return the player's locale
     */
    public String getLocale();

    /**
     * Sends a {@link net.canarymod.api.chat.ChatComponent} to the {@code Player}
     *
     * @param chatComponent
     *         the {@link net.canarymod.api.chat.ChatComponent} to send
     */
    public void sendChatComponent(ChatComponent chatComponent);

    /**
     * Gets the previously known IP of the {@code Player} if found
     *
     * @return previous IP if found
     */
    public String getPreviousIP();
}
