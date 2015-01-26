package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.api.PlayerReference;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.user.Group;

import static net.canarymod.Translator.sendTranslatedMessage;
import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Command to add a group to a player
 *
 * @author Jason (darkdiplomat)
 */
public class PlayerGroupAdd extends PlayermodBase {
    // player group add <player> <newgroup>
    public void execute(MessageReceiver caller, String[] args) {

        Group group = Canary.usersAndGroups().getGroup(args[1]);
        if (group == null || !group.getName().equals(args[1])) {
            sendTranslatedNotice(caller, "unknown group", args[0]);
            return;
        }

        Player[] selection = selection(caller, args, 0);
        if (isSelectionValid(selection)) {
            for (Player target : selection) {
                if (!target.isInGroup(group, true)) {
                    target.addGroup(group);
                }
            }
        }
        else {
            PlayerReference target = Canary.getServer().matchKnownPlayer(args[0]);

            if (target == null) {
                sendTranslatedNotice(caller, "unknown player", args[0]);
                return;
            }

            if (!target.isInGroup(group, true)) {
                target.addGroup(group);
            }
        }
        sendTranslatedMessage(caller, ChatFormat.YELLOW, "modify group add");
    }
}
