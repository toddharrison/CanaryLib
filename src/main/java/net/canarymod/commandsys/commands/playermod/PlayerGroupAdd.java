package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Command to add a group to a player
 *
 * @author Jason (darkdiplomat)
 */
public class PlayerGroupAdd implements NativeCommand {
    // player group add <player> <newgroup>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod group add");
            return;
        }
        PlayerReference target = Canary.getServer().matchKnownPlayer(args[0]);
        Group group = Canary.usersAndGroups().getGroup(args[1]);
        if (group == null || !group.getName().equals(args[1])) {
            caller.notice(Translator.translateAndFormat("unknown group", args[1]));
            return;
        }
        if (target == null) {
            caller.notice(Translator.translateAndFormat("unknown player", args[0]));
            return;
        }
        if (!target.isInGroup(group, true)) {
            target.addGroup(group);
            caller.message(ChatFormat.YELLOW + Translator.translate("modify group add"));
        }
    }
}
