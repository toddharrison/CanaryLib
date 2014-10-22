package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.exceptions.InvalidPluginException;
import net.canarymod.exceptions.PluginLoadFailedException;
import net.canarymod.logger.Logman;

/**
 * Command to enable, disable or reload plugins
 *
 * @author Chris (damagefilter)
 */
public class PluginCommand implements NativeCommand {
    private final boolean disable;
    private final boolean reload;
    private final boolean permanent = false;

    public PluginCommand(boolean disable, boolean reload) {
        this.reload = reload;
        this.disable = reload ? false : disable;
    }

    public void execute(MessageReceiver caller, String[] parameters) {
        String plugin = parameters[0];

        if (reload) {
            reload(caller, plugin);
        }
        else {
            if (disable) {
                disable(caller, plugin, permanent);
            }
            else {
                enable(caller, plugin);
            }
        }
    }

    private void reload(MessageReceiver caller, String plugin) {
        try {
            if (Canary.pluginManager().reloadPlugin(plugin)) {
                caller.notice(Translator.translateAndFormat("plugin reloaded", plugin));
            }
            else {
                caller.notice(Translator.translateAndFormat("plugin reloaded fail", plugin));
            }
        }
        catch (PluginLoadFailedException e) {
            caller.notice(Translator.translateAndFormat("plugin enabled fail", plugin));
            Logman.getLogman("pluginCommand").error("Failed to load plugin", e);
        }
        catch (InvalidPluginException e) {
            caller.notice(Translator.translateAndFormat("plugin enabled fail", plugin));
            Logman.getLogman("pluginCommand").error("Failed to load plugin", e);
        }
    }

    private void enable(MessageReceiver caller, String plugin) {
        try {
            if (Canary.pluginManager().enablePlugin(plugin)) {
                caller.notice(Translator.translateAndFormat("plugin enabled", plugin));
            }
            else {
                caller.notice(Translator.translateAndFormat("plugin enabled fail", plugin));
            }
        }
        catch (PluginLoadFailedException e) {
            caller.notice(Translator.translateAndFormat("plugin enabled fail", plugin));
            Logman.getLogman("pluginCommand").error("Failed to load plugin", e);
        }
    }

    private void disable(MessageReceiver caller, String plugin, boolean permanent) {
        if (Canary.pluginManager().disablePlugin(plugin)) {
            //TODO: Permanent flag in canary
            if (permanent) {
//                Canary.manager().moveToDisabled(plugin);
            }
            caller.notice(Translator.translateAndFormat("plugin disabled", plugin));
        }
        else {
            caller.notice(Translator.translateAndFormat("plugin disabled fail", plugin));
        }
    }

    /**
     * Check if we have a permanent disable/enable requests
     *
     * @param params
     *
     * @return
     */
    private boolean getPermanentParameter(String[] params) {
        return params[params.length - 2].equalsIgnoreCase("-p");
    }

}
