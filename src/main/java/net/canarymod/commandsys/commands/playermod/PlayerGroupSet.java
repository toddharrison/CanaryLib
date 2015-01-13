package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.user.Group;

/**
 * Command to set a players group
 *
 * @author Chris (damagefilter)
 */
public class PlayerGroupSet extends PlayermodBase {

    // player) group set <player> <newgroup>
    public void execute(MessageReceiver caller, String[] args) {

        Group group = Canary.usersAndGroups().getGroup(args[1]);
        if (!group.getName().equals(args[1])) { // Incase default group was picked up
            caller.notice(Translator.translateAndFormat("unknown group", args[1]));
            return;
        }

        Player[] selection = selection(caller, args, 0);
        if (isSelectionValid(selection)) {
            for (Player target : selection) {
                target.setGroup(group);
            }
        }
        else {
            PlayerReference target = Canary.getServer().matchKnownPlayer(args[0]);

            if (target == null) {
                caller.notice(Translator.translateAndFormat("unknown player", args[0]));
                return;
            }
            target.setGroup(group);
        }
        caller.message(ChatFormat.YELLOW + Translator.translate("modify group set"));
    }
}
