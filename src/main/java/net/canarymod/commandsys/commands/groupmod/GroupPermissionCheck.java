package net.canarymod.commandsys.commands.groupmod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.permissionsystem.PermissionNode;
import net.canarymod.user.Group;

import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Command to check if a group has a permission node
 *
 * @author Chris (damagefilter)
 */
public class GroupPermissionCheck implements NativeCommand {
    // groupmod permission check group value
    public void execute(MessageReceiver caller, String[] args) {
        Group group = Canary.usersAndGroups().getGroup(args[0]);
        if (group == null || !group.getName().equals(args[0])) {
            sendTranslatedNotice(caller, "unknown group", args[0]);
            return;
        }
        PermissionNode node = PermissionNode.fromString(args[1]);
        if (group.getPermissionProvider().pathExists(node.getName())) {
            if (group.hasPermission(node.getName())) {
                caller.message(ChatFormat.GREEN + node.getName() + ": true");
            }
            else {
                caller.message(ChatFormat.RED + node.getName() + ": false");
            }
        }
        else {
            if (group.hasPermission(node.getName())) {
                caller.message(ChatFormat.GREEN + node.getName() + ": true");
            }
            else {
                caller.message(ChatFormat.YELLOW + node.getName() + ": " + Translator.translate("no"));
            }
        }
    }
}
