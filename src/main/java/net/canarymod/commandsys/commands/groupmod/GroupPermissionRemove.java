package net.canarymod.commandsys.commands.groupmod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.permissionsystem.PermissionNode;
import net.canarymod.user.Group;

/**
 * Command to remove a permission node from a group
 *
 * @author Chris (damagefilter)
 */
public class GroupPermissionRemove implements NativeCommand {
    // groupmod permission add group value
    public void execute(MessageReceiver caller, String[] args) {
        Group group = Canary.usersAndGroups().getGroup(args[0]);
        if (group == null || !group.getName().equals(args[0])) {
            caller.notice(Translator.translateAndFormat("unknown group", args[0]));
            return;
        }
        PermissionNode node = PermissionNode.fromString(args[1]);
        Canary.permissionManager().removeGroupPermission(node.getName(), group);
        caller.message(Colors.YELLOW + Translator.translate("modify permission removed"));
    }
}
