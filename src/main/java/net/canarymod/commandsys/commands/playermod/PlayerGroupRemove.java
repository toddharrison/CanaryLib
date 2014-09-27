package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Command to remove a group from a player (not main group)
 *
 * @author Chris (damagefilter)
 */
public class PlayerGroupRemove implements NativeCommand {
    // player) group remove <player> <group>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod group remove");
            return;
        }
        PlayerReference target = Canary.getServer().matchKnownPlayer(args[0]);
        Group group = Canary.usersAndGroups().getGroup(args[1]);
        if (group == null) {
            caller.notice(Translator.translateAndFormat("unknown group", args[1]));
            return;
        }
        if (target == null) {
            caller.notice(Translator.translateAndFormat("unknown player", args[0]));
            return;
        }

        if (target.removeGroup(group)) {
            caller.message(Colors.YELLOW + Translator.translate("modify group removed"));
        }
        else {
            caller.message(Colors.YELLOW + Translator.translate("modify group removed failed"));
        }
    }
}
