package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.user.Group;

import static net.canarymod.Translator.sendTranslatedMessage;
import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Command to add a player (for permissions, groups, etc.) to the database
 *
 * @author Chris (damagefilter)
 */
public class PlayerCreate extends PlayermodBase {

    // player) add <playername> <groupname>
    public void execute(MessageReceiver caller, String[] args) {

        Group g = Canary.usersAndGroups().getGroup(args[1]);
        if (g == null || !g.getName().equals(args[1])) {
            sendTranslatedNotice(caller, "unknown group", args[0]);
            return;
        }

        Player[] selection = selection(caller, args, 0);
        if (isSelectionValid(selection)) {
            for (Player target : selection) {
                target.setGroup(g);
            }
        }
        else {
            Player target = Canary.getServer().matchPlayer(args[0]);
            if (target == null) {
                Canary.usersAndGroups().addOfflinePlayer(args[0], g.getName());
            }
            else {
                target.setGroup(g);
            }
        }
        sendTranslatedMessage(caller, ChatFormat.YELLOW, "modify group set");
    }
}
