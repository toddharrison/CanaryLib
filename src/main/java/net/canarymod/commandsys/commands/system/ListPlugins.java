package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.plugin.PluginDescriptor;
import net.canarymod.plugin.PluginState;

import java.util.Collection;

/**
 * Command to list all the plugins on the server (both enabled and disabled)
 *
 * @author Chris (damagefilter)
 */
public class ListPlugins implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player)caller);
        }
        else {
            console(caller);
        }
    }

    private void console(MessageReceiver caller) {
        String list = getReadablePluginList();

        caller.notice("**** PLUGINS ****");
        if (list != null) {
            caller.notice(list);
        }
        else {
            caller.notice(Translator.translate("no plugins"));
        }
    }

    private void player(Player player) {
        String list = getReadablePluginList();

        player.message(ChatFormat.YELLOW + "Plugins: ");
        if (list != null) {
            player.message(list);
        }
        else {
            player.notice(Translator.translate("no plugins"));
        }
    }

    private String getReadablePluginList() {
        Collection<PluginDescriptor> descriptors = Canary.pluginManager().getPluginDescriptors();
        StringBuilder sb = new StringBuilder();
        for (PluginDescriptor plugin : descriptors) {
            if (plugin.getCurrentState() == PluginState.ENABLED) {
                sb.append(ChatFormat.GREEN).append(plugin.getName()).append(ChatFormat.WHITE).append(", ");
            }
            else {
                sb.append(ChatFormat.RED).append(plugin.getName()).append(ChatFormat.WHITE).append(", ");
            }
        }
        String str = sb.toString();
        String list;
        if (str.length() > 1) {
            list = str.substring(0, str.length() - 1);
        }
        else {
            list = null;
        }
        return list;
    }
}
