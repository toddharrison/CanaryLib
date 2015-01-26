package net.canarymod.commandsys.commands.groupmod;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

import static net.canarymod.Translator.sendTranslatedMessage;
import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Command to remove a group
 *
 * @author Chris (damagefilter)
 */
public class GroupRemove implements NativeCommand {

    // group remove <name>
    public void execute(MessageReceiver caller, String[] args) {

        Group group = Canary.usersAndGroups().getGroup(args[0]);
        if (group == null || !group.getName().equals(args[0])) {
            sendTranslatedNotice(caller, "unknown group", args[0]);
            return;
        }
        if (group.getName().equalsIgnoreCase(Canary.usersAndGroups().getDefaultGroup().getName())) {
            sendTranslatedNotice(caller, "group remove default group");
            return;
        }
        // Fix players that had the said group
        for (Player player : Canary.getServer().getPlayerList()) {
            if (player.getGroup().getName().equals(group.getName())) {
                player.setGroup(group.getParent());
            }
        }
        Canary.usersAndGroups().removeGroup(group);
        sendTranslatedMessage(caller, ChatFormat.YELLOW, "group removed", group.getName());
    }
}
