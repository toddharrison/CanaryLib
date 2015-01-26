package net.canarymod.commandsys.commands.groupmod;

import net.canarymod.Canary;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

import static net.canarymod.Translator.sendTranslatedMessage;
import static net.canarymod.Translator.sendTranslatedNotice;

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
            sendTranslatedNotice(caller, "unknown group", args[0]);
            return;
        }
        group.getPermissionProvider().flushCache();
        sendTranslatedMessage(caller, ChatFormat.YELLOW, "modify permission cleared");
    }
}
