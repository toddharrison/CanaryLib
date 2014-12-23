package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to view the Message Of The Day
 *
 * @author Chris (damagefilter)
 */
public class Motd implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller == null) {
            throw new CommandException(Translator.translateAndFormat("unknown messagereceiver", "null"));
        }
        Canary.motd().sendMOTD(caller);
    }
}
