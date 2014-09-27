package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Reserve List Command!
 *
 * @author Jason (darkdiplomat)
 */
public class ReservelistCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] args) {
        if (args[0].equalsIgnoreCase("add")) {
            Canary.reservelist().addPlayer(args[1]);
            caller.message(Colors.YELLOW + Translator.translate("reservelist player added"));
        }
        if (args[0].equalsIgnoreCase("remove")) {
            Canary.reservelist().removePlayer(args[1]);
            caller.message(Colors.YELLOW + Translator.translate("reservelist player removed"));
        }
    }
}
