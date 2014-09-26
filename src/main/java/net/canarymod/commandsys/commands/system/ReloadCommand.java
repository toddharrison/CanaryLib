package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to reload the server (config, perms player data, etc.)
 *
 * @author Chris (damagefilter)
 * @see Canary#reload()
 */
public class ReloadCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        caller.notice(Translator.translate("reload reloading"));
        Canary.instance().reload();
        caller.notice(Translator.translate("reload reloading done"));
    }
}
