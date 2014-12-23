package net.canarymod.commandsys.commands.groupmod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.ChatFormat;
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

        Group group = Canary.usersAndGroups().getGroup(args[0]);
        if (group == null || !group.getName().equals(args[0])) {
            caller.notice(Translator.translateAndFormat("group unknown", args[0]));
            return;
        }
        Canary.usersAndGroups().renameGroup(group, args[1]);
        caller.message(ChatFormat.YELLOW + Translator.translateAndFormat("group rename", group.getName()));
    }
}
