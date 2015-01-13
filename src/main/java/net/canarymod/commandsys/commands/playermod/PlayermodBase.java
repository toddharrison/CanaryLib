package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to show help for the playermod command
 *
 * @author Chris (damagefilter)
 */
public class PlayermodBase implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (parameters.length == 0) {
            Canary.help().getHelp(caller, "playermod");
        }
        if (parameters.length == 1 && parameters[0].equals("--help")) {
            Canary.help().getHelp(caller, "playermod");
        }
    }

    protected Player[] selection(MessageReceiver caller, String[] parameters, int index) {
        return Canary.playerSelector().matchPlayers(caller, parameters[index]);
    }

    protected boolean isSelectionValid(Player[] selection) {
        return selection != null && selection.length > 0;
    }
}
