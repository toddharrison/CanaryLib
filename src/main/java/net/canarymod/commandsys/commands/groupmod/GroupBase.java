package net.canarymod.commandsys.commands.groupmod;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to show help for the groupmod command
 *
 * @author Chris (damagefilter)
 */
public class GroupBase implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (parameters.length == 0) {
            Canary.help().getHelp(caller, "groupmod");
        }
        if (parameters.length == 1 && parameters[0].equals("--help")) {
            Canary.help().getHelp(caller, "groupmod");
        }
    }
}
