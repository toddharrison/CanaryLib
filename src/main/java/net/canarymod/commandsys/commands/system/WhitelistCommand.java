package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

import static net.canarymod.Canary.whitelist;
/**
 * Command to add or remove a player from the whitelist
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class WhitelistCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] args) {
        if (args[0].equalsIgnoreCase("add")) {
            whitelist().addPlayer(args[1]);
            caller.message(ChatFormat.YELLOW + Translator.localTranslate("whitelist player added", caller.getLocale(), args[1]));
        }
        else if (args[0].equalsIgnoreCase("remove")) {
            whitelist().removePlayer(args[1]);
            caller.message(ChatFormat.YELLOW + Translator.localTranslate("whitelist player removed", caller.getLocale(), args[1]));
        }
        else if (args[0].equalsIgnoreCase("show")) {
            caller.message(ChatFormat.AQUA + "**** WHITELISTED ****");
            StringBuilder sb = new StringBuilder();
            for (String uuid : whitelist().getWhitelisted()) {
                PlayerReference reference = Canary.getServer().matchKnownPlayer(uuid);
                if (reference != null) {
                    sb.append(reference.getName()).append(", ");
                }
                else {
                    sb.append(uuid).append(" (Name Unknown)").append(", ");
                }
            }

            if (sb.length() > 0) {
                sb.delete(sb.length() - 2, sb.length() - 1);
            }
            else {
                sb.append(ChatFormat.RED).append("NONE");
            }
            caller.message(sb.toString());
        }
    }
}
