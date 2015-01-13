package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.user.Group;

/**
 * Command to remove a group from a player (not main group)
 *
 * @author Chris (damagefilter)
 */
public class PlayerGroupRemove extends PlayermodBase {
    // player) group remove <player> <group>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod group remove");
            return;
        }

        Group group = Canary.usersAndGroups().getGroup(args[1]);
        if (group == null || !group.getName().equals(args[1])) {
            caller.notice(Translator.translateAndFormat("unknown group", args[1]));
            return;
        }

        Player[] selection = selection(caller, args, 0);
        if (isSelectionValid(selection)) {
            boolean success = false;
            for (Player target : selection) {
                success |= target.removeGroup(group);
            }
            if (success) {
                caller.message(ChatFormat.YELLOW + Translator.translate("modify group removed"));
            }
            else {
                caller.message(ChatFormat.YELLOW + Translator.translate("modify group removed failed"));
            }
        }
        else {
            PlayerReference target = Canary.getServer().matchKnownPlayer(args[0]);

            if (target == null) {
                caller.notice(Translator.translateAndFormat("unknown player", args[0]));
                return;
            }

            if (target.removeGroup(group)) {
                caller.message(ChatFormat.YELLOW + Translator.translate("modify group removed"));
            }
            else {
                caller.message(ChatFormat.YELLOW + Translator.translate("modify group removed failed"));
            }
        }
    }
}
