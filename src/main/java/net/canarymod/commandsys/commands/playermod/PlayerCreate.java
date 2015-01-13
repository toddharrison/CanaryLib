package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.user.Group;

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
            caller.notice(Translator.translateAndFormat("unknown group", args[1]));
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
        caller.message(ChatFormat.YELLOW + Translator.translate("modify group set"));
    }
}
