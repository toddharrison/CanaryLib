package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.user.Group;

/**
 * Command to check if a player is in a group
 *
 * @author Chris (damagefilter)
 */
public class PlayerGroupCheck extends PlayermodBase {
    // player) group check <player> <group>
    public void execute(MessageReceiver caller, String[] args) {

        Player[] selection = selection(caller, args, 0);
        if (isSelectionValid(selection)) {
            for (Player target : selection) {
                for (Group g : target.getPlayerGroups()) {
                    if (g.getName().equals(args[1])) {
                        caller.message(ChatFormat.GREEN + args[0] + ": " + Translator.translate("yes"));
                        return;
                    }
                }
                caller.message(ChatFormat.RED + args[0] + ": " + Translator.translate("no"));
            }
        }
        else {
            PlayerReference target = Canary.getServer().matchKnownPlayer(args[0]);
            if (target == null) {
                caller.notice(Translator.translateAndFormat("unknown player", args[0]));
                return;
            }

            for (Group g : target.getPlayerGroups()) {
                if (g.getName().equals(args[1])) {
                    caller.message(ChatFormat.GREEN + args[0] + ": " + Translator.translate("yes"));
                    return;
                }
            }
            caller.message(ChatFormat.RED + args[0] + ": " + Translator.translate("no"));
        }
    }
}
