package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;

/**
 * Command to list all permissions a player has
 *
 * @author Chris (damagefilter)
 */
public class PlayerPermissionList extends PlayermodBase {
    // groupmod permission add group value
    public void execute(MessageReceiver caller, String[] args) {

        Player[] selection = selection(caller, args, 0);
        if (isSelectionValid(selection)) {
            for (Player target : selection) {
                caller.message(ChatFormat.YELLOW + "* " + target.getName() + " *");
                target.getPermissionProvider().printPermissionsToCaller(caller);
            }
        }
        else {
            PlayerReference target = Canary.getServer().matchKnownPlayer(args[0]);
            if (target == null) {
                caller.notice(Translator.translateAndFormat("unknown player", args[0]));
                return;
            }
            target.getPermissionProvider().printPermissionsToCaller(caller);
        }
    }
}
