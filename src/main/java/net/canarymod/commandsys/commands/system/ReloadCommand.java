package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Command to reload the server (config, perms player data, etc.)
 *
 * @author Chris (damagefilter)
 * @see Canary#reload()
 */
public class ReloadCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        sendTranslatedNotice(caller, "reload reloading");
        Canary.instance().reload();
        sendTranslatedNotice(caller, "reload reloading done");
    }
}
