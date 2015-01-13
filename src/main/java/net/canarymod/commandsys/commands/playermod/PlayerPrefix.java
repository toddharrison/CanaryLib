package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.visualillusionsent.utils.StringUtils;

/**
 * Command to change the prefix of a player
 *
 * @author Chris (damagefilter)
 */
public class PlayerPrefix extends PlayermodBase {
    // player) prefix <player> [prefix]
    public void execute(MessageReceiver caller, String[] args) {

        String prefix = null;
        if (args.length >= 2) {
            prefix = StringUtils.joinString(args, " ", 1).replaceAll("&([A-FK-Oa-fk-oRr0-9])", "\u00A7$1");
        }

        Player[] selection = selection(caller, args, 0);
        if (isSelectionValid(selection)) {
            for (Player target : selection) {
                target.setPrefix(prefix);
            }
        }
        else {
            PlayerReference target = Canary.getServer().matchKnownPlayer(args[0]);
            if (target == null) {
                caller.notice(Translator.translateAndFormat("unknown player", args[0]));
                return;
            }
            target.setPrefix(prefix);
        }
        caller.message(ChatFormat.YELLOW + Translator.translate("modify prefix set"));
    }
}
