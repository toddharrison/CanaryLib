package net.canarymod.commandsys.commands.groupmod;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Command to change the parent of a group
 *
 * @author Chris (damagefilter)
 */
public class GroupParent implements NativeCommand {
    // group) rename <foo> <bar>
    public void execute(MessageReceiver caller, String[] args) {
        if (args[0].equals("--help")) {
            Canary.help().getHelp(caller, "group parent");
            return;
        }
        Group group = Canary.usersAndGroups().getGroup(args[0]);
        Group parent = Canary.usersAndGroups().getGroup(args[1]); // Must exist
        if (group == null) {
            caller.notice(Translator.translateAndFormat("group unknown", args[0]));
            return;
        }
        if (parent == null) {
            caller.notice(Translator.translateAndFormat("group unknown", args[1]));
            return;
        }
        if (parent.getWorldName() != null && group.getWorldName() != null) {
            if (!parent.getWorldName().equals(group.getWorldName())) {
                caller.notice(Translator.translateAndFormat("group parent world mismatch", parent.getName(), parent.getWorldName(), group.getWorldName()));
                return;
            }
        }
        group.setParent(parent);
        //Updating the parent group will automatically save the child groups too
        Canary.usersAndGroups().updateGroup(parent, true);
        caller.message(Colors.YELLOW + Translator.translateAndFormat("group parent changed", group.getName(), parent.getName()));
    }
}
