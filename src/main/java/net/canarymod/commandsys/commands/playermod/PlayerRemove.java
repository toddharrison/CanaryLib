package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;

/**
 * Command to remove player data from the database
 *
 * @author Chris (damagefilter)
 */
public class PlayerRemove extends PlayermodBase {
    // player) remove <playername>
    @Override
    public void execute(MessageReceiver caller, String[] args) {

        Player[] selection = selection(caller, args, 0);
        if (isSelectionValid(selection)) {
            for (Player target : selection) {
                target.setGroup(Canary.usersAndGroups().getDefaultGroup());
                Canary.usersAndGroups().removeUserData(target.getUUIDString());
            }
        }
        else {
            PlayerReference target = Canary.getServer().matchKnownPlayer(args[0]);
            if (target == null) {
                caller.notice(Translator.translateAndFormat("unknown player", args[0]));
                return;
            }
            target.setGroup(Canary.usersAndGroups().getDefaultGroup());
            Canary.usersAndGroups().removeUserData(target.getUUIDString());
        }
        caller.notice(Translator.translate("modify player removed"));
    }
}
