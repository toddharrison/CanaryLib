package net.canarymod.commandsys.commands.system.reservelist;

import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

import static net.canarymod.Canary.reservelist;
import static net.canarymod.Translator.sendTranslatedMessage;

/**
 * Reserve Remove Command
 *
 * @author Jason Jones (darkdiplomat)
 */
public class ReservelistRemove implements NativeCommand {

    @Override
    public void execute(MessageReceiver caller, String[] args) {
        reservelist().removePlayer(args[0]);
        sendTranslatedMessage(caller, ChatFormat.YELLOW, "reservelist player removed", args[0]);
    }
}
