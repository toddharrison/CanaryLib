package net.canarymod.commandsys.commands.groupmod;

import net.canarymod.Canary;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Command to list all the groups defined
 *
 * @author Chris (damagefilter)
 */
public class GroupList implements NativeCommand {
    // group) list
    public void execute(MessageReceiver caller, String[] args) {
        if (args.length > 0 && args[0].equals("--help")) {
            Canary.help().getHelp(caller, "groupmod list");
            return;
        }
        for (Group g : Canary.usersAndGroups().getGroups()) {
            StringBuilder line = new StringBuilder();
            line.append(ChatFormat.YELLOW).append("Name: ").append(ChatFormat.WHITE).append(g.getName());
            if (g.hasParent()) {
                line.append(", ").append(ChatFormat.YELLOW).append("Parent: ").append(ChatFormat.WHITE).append(g.getParent().getName());
            }
            caller.message(line.toString());
        }
    }
}
