package net.canarymod.commandsys.commands.groupmod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Flushes the permission cache.
 *
 * @author Chris (damagefilter)
 */
public class GroupPermissionFlush implements NativeCommand {
    // groupmod permission flush group
    public void execute(MessageReceiver caller, String[] args) {
        Group group = Canary.usersAndGroups().getGroup(args[0]);
        if (group == null || !group.getName().equals(args[0])) {
            caller.notice(Translator.translateAndFormat("unknown group", args[0]));
            return;
        }
        group.getPermissionProvider().flushCache();
        caller.message(Colors.YELLOW + Translator.translate("modify permission cleared"));
    }
}
