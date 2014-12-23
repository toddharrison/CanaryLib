package net.canarymod.commandsys.commands.system;

import net.canarymod.api.PlayerReference;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.plugin.Plugin;

/**
 * Extra data to be inserted with PlayerInformation
 *
 * @author Jason Jones (darkdiplomat)
 */
public abstract class PlayerInfoAddition {
    private final Plugin plugin;

    /**
     * Constructs a new PlayerInfoAddition and automatically adds it to the {@link net.canarymod.commandsys.commands.system.PlayerInformation} command
     *
     * @param plugin
     */
    public PlayerInfoAddition(Plugin plugin) {
        if (plugin == null) {
            throw new IllegalArgumentException("Plugin cannot be null");
        }
        this.plugin = plugin;
        PlayerInformation.addInfoAddition(this);
    }

    /**
     * Checks if this PlayerInfoAddition is still valid
     *
     * @return {@code true} if valid; {@code false} if not
     */
    public final boolean canApply() {
        return !plugin.isDisabled();
    }

    /**
     * Checks if this PlayerInfoAddtion is for a given {@link net.canarymod.plugin.Plugin}
     *
     * @param plugin
     *         the plugin to check
     *
     * @return {@code true} if is for given plugin; {@code false} if not
     */
    public final boolean isFor(Plugin plugin) {
        return plugin.equals(this.plugin);
    }

    /**
     * Override this method to return the data you wish it have added to the {@link net.canarymod.commandsys.commands.system.PlayerInformation} command
     *
     * @param caller
     *         the {@link net.canarymod.chat.MessageReceiver} making the call to the command
     * @param subject
     *         the {@link net.canarymod.api.PlayerReference} who's information is being looked at
     *
     * @return The information to be displayed to the caller. Do not send the caller this data directly.
     */
    public abstract String applyData(MessageReceiver caller, PlayerReference subject);
}
