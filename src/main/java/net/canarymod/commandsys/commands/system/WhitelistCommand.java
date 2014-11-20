package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to add or remove a player from the whitelist
 *
 * @author Chris (damagefilter)
 */
public class WhitelistCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] args) {
        if (args[0].equalsIgnoreCase("add")) {
            Canary.whitelist().addPlayer(args[1]);
            caller.message(ChatFormat.YELLOW + Translator.localTranslate("whitelist player added", caller.getLocale(), args[1]));
        }
        else if (args[0].equalsIgnoreCase("remove")) {
            Canary.whitelist().removePlayer(args[1]);
            caller.message(ChatFormat.YELLOW + Translator.localTranslate("whitelist player removed", caller.getLocale(), args[1]));
        }
    }
}
