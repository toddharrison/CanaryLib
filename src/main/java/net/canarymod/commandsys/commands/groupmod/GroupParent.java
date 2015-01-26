package net.canarymod.commandsys.commands.groupmod;

import net.canarymod.Canary;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

import static net.canarymod.Translator.sendTranslatedMessage;
import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Command to change the parent of a group
 *
 * @author Chris (damagefilter)
 */
public class GroupParent implements NativeCommand {
    // group) rename <foo> <bar>
    public void execute(MessageReceiver caller, String[] args) {

        Group group = Canary.usersAndGroups().getGroup(args[0]);
        Group parent = Canary.usersAndGroups().getGroup(args[1]); // Must exist
        if (group == null || !group.getName().equals(args[0])) {
            sendTranslatedNotice(caller, "group unknown", args[0]);
            return;
        }
        if (parent == null || !parent.getName().equals(args[1])) {
            sendTranslatedNotice(caller, "group unknown", args[1]);
            return;
        }
        if (parent.getWorldName() != null && group.getWorldName() != null) {
            if (!parent.getWorldName().equals(group.getWorldName())) {
                sendTranslatedNotice(caller, "group parent world mismatch", parent.getName(), parent.getWorldName(), group.getWorldName());
                return;
            }
        }
        group.setParent(parent);
        //Updating the parent group will automatically save the child groups too
        Canary.usersAndGroups().updateGroup(parent, true);
        sendTranslatedMessage(caller, ChatFormat.YELLOW, "group parent changed", group.getName(), parent.getName());
    }
}
