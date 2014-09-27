package net.canarymod.commandsys.commands.playermod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.PlayerReference;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Command to set a players group
 *
 * @author Chris (damagefilter)
 */
public class PlayerGroupSet implements NativeCommand {
    // player) group set <player> <newgroup>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "playermod group set");
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
        target.setGroup(group);
        caller.message(Colors.YELLOW + Translator.translate("modify group set"));
    }
}
