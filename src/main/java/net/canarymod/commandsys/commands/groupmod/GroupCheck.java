package net.canarymod.commandsys.commands.groupmod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Command to check the values of a group
 *
 * @author Chris (damagefilter)
 */
public class GroupCheck implements NativeCommand {
    // groupmod check <group>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[0].equals("--help")) {
            Canary.help().getHelp(caller, "groupmod check");
            return;
        }
        Group g = Canary.usersAndGroups().getGroup(args[0]);
        if (g == null || !g.getName().equals(args[0])) {
            caller.notice(Translator.translateAndFormat("unknown group", args[0]));
            return;
        }
        caller.message("Name: " + g.getName());
        caller.message("ID: " + g.getId());
        caller.message("Prefix: " + ChatFormat.MARKER + g.getPrefix() + g.getPrefix());
        caller.message("Parent: " + (g.hasParent() ? g.getParent().getName() : Translator.translate("no")));
    }
}
