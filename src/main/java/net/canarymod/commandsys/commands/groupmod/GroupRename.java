package net.canarymod.commandsys.commands.groupmod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Command to rename an existing group
 *
 * @author Chris (damagefilter)
 */
public class GroupRename implements NativeCommand {
    // group) rename <foo> <bar>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[0].equals("--help")) {
            Canary.help().getHelp(caller, "group rename");
            return;
        }
        Group group = Canary.usersAndGroups().getGroup(args[0]);
        if (group == null) {
            caller.notice(Translator.translateAndFormat("group unknown", args[0]));
            return;
        }
        Canary.usersAndGroups().renameGroup(group, args[1]);
        caller.message(Colors.YELLOW + Translator.translateAndFormat("group rename", group.getName()));
    }
}
