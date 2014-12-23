package net.canarymod.commandsys.commands.system.whitelist;

import net.canarymod.Translator;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

import static net.canarymod.Canary.whitelist;

/**
 * Whitelist Remove Command
 *
 * @author Jason Jones (darkdiplomat)
 */
public class WhitelistRemove implements NativeCommand {

    @Override
    public void execute(MessageReceiver receiver, String[] args) {
        whitelist().removePlayer(args[0]);
        receiver.message(ChatFormat.YELLOW + Translator.localTranslate("whitelist player removed", receiver.getLocale(), args[0]));
    }
}
