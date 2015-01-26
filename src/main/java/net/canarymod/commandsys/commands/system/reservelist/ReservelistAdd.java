package net.canarymod.commandsys.commands.system.reservelist;

import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

import static net.canarymod.Canary.reservelist;
import static net.canarymod.Translator.sendTranslatedMessage;

/**
 * ReserveList Add Command
 *
 * @author Jason Jones (darkdiplomat)
 */
public class ReservelistAdd implements NativeCommand {

    @Override
    public void execute(MessageReceiver caller, String[] args) {
        reservelist().addPlayer(args[0]);
        sendTranslatedMessage(caller, ChatFormat.YELLOW, "reservelist player added", args[0]);
    }
}
