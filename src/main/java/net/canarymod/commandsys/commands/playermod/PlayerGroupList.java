package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.user.Group;

/**
 * Command to list all the groups a player belongs to
 *
 * @author Chris (damagefilter)
 */
public class PlayerGroupList extends PlayermodBase {
    // player) group list <player>
    public void execute(MessageReceiver caller, String[] args) {

        Player[] selection = selection(caller, args, 0);
        if (isSelectionValid(selection)) {
            for (Player target : selection) {
                caller.message(ChatFormat.YELLOW + "* " + target.getName() + " *");
                for (Group g : target.getPlayerGroups()) {
                    caller.message(g.getName() + (g.hasParent() ? " : " + g.getParent().getName() : ""));
                }
            }
        }
        else {
            PlayerReference target = Canary.getServer().matchKnownPlayer(args[0]);

            if (target == null) {
                caller.notice(Translator.translateAndFormat("unknown player", args[0]));
                return;
            }

            for (Group g : target.getPlayerGroups()) {
                caller.message(g.getName() + (g.hasParent() ? " : " + g.getParent().getName() : ""));
            }
        }
    }
}
