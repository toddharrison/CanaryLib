package net.canarymod.commandsys.commands.system.whitelist;

import net.canarymod.Translator;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

import static net.canarymod.Canary.whitelist;

/**
 * Whitelst Add Command
 *
 * @author Jason Jones (darkdiplomat)
 */
public class WhitelistAdd implements NativeCommand {

    @Override
    public void execute(MessageReceiver receiver, String[] args) {
        whitelist().addPlayer(args[0]);
        receiver.message(ChatFormat.YELLOW + Translator.localTranslate("whitelist player added", receiver.getLocale(), args[0]));
    }
}
