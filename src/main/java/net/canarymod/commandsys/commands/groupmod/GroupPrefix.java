package net.canarymod.commandsys.commands.groupmod;

import net.canarymod.Canary;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;
import net.visualillusionsent.utils.StringUtils;

import static net.canarymod.Translator.sendTranslatedMessage;
import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Command to change the prefix of a group
 *
 * @author Chris (damagefilter)
 */
public class GroupPrefix implements NativeCommand {
    // group) prefix <group> <prefix>
    public void execute(MessageReceiver caller, String[] args) {

        Group group = Canary.usersAndGroups().getGroup(args[0]);
        if (group == null || !group.getName().equals(args[0])) {
            sendTranslatedNotice(caller, "unknown group", args[0]);
            return;
        }
        String prefix = null;
        if (args.length >= 2) {
            prefix = StringUtils.joinString(args, " ", 1).replaceAll("&([A-FK-Oa-fk-oRr0-9])", "\u00A7$1");
        }
        group.setPrefix(prefix);
        Canary.usersAndGroups().updateGroup(group, false);
        sendTranslatedMessage(caller, ChatFormat.YELLOW, "modify prefix set");
    }
}
