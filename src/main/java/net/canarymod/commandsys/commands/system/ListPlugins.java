package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.ReceiverType;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.plugin.PluginDescriptor;
import net.canarymod.plugin.PluginState;

import java.util.Collection;

import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Command to list all the plugins on the server (both enabled and disabled)
 *
 * @author Chris (damagefilter)
 */
public class ListPlugins implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        String list = getReadablePluginList();
        if (list != null) {
            if (caller.getReceiverType().equals(ReceiverType.PLAYER)) {
                caller.message(ChatFormat.YELLOW.concat("Plugins: "));
                caller.message(list);
            }
            else {
                caller.notice("**** PLUGINS ****");
                caller.notice(list);
            }
        }
        else {
            sendTranslatedNotice(caller, "no plugins");
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
