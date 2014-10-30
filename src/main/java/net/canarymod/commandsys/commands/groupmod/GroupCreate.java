package net.canarymod.commandsys.commands.groupmod;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.Translator;
import net.canarymod.api.world.World;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.Group;

/**
 * Command to create a group
 *
 * @author Chris (damagefilter)
 */
public class GroupCreate implements NativeCommand {
    // group) add <name> [[parent] [world[:dimension]]]
    public void execute(MessageReceiver caller, String[] args) {
        if (args[args.length - 1].equals("--help")) {
            Canary.help().getHelp(caller, "groupmod add");
            return;
        }

        String worldName = null;
        Group parent = null;
        Group group = new Group();

        if (args.length == 3) { // Here we have world and group name
            // we have world and parent set!
            // We require this to get the real fqname of the world,
            // since a user could pass only the world group name which parseWorld
            // will evaluate as the NORMAL dimension.
            World world = ToolBox.parseWorld(args[2]);
            if (world == null) { // Whoops!
                caller.notice(Translator.translateAndFormat("group unknown world", args[2]));
                return;
            }
            worldName = world.getFqName();

            parent = Canary.usersAndGroups().getGroup(args[1]);
            if (parent == null || !parent.getName().equals(args[1])) {
                caller.notice(Translator.translateAndFormat("group unknown parent", args[1]));
                return;
            }
            if (!parent.getWorldName().equals(worldName)) {
                caller.notice(Translator.translateAndFormat("group parent world mismatch", parent.getName(), parent.getWorldName(), worldName));
                return;
            }
        }
        if (args.length == 2) { // This can be only a world or a group name
            parent = Canary.usersAndGroups().getGroup(args[1]);
            World world = ToolBox.parseWorld(args[1]);

            //Note: if parent is null (therefore its world too) this means, the argument is a world name
            String groupworld = parent != null ? parent.getWorldName() : null;
            if (groupworld == null) {
                worldName = world != null ? world.getFqName() : null;
                if (worldName == null) {
                    //So that's both null, all is invalid!
                    if (parent == null) { // only if this one last check fails
                        caller.notice(Translator.translateAndFormat("group unknown world", args[1]));
                        caller.notice(Translator.translateAndFormat("group unknown parent", args[1]));
                        return;
                    }
                }
            }
        }

        group.setName(args[0]);
        group.setPermissionProvider(Canary.permissionManager().getGroupsProvider(args[0], worldName));
        group.setParent(parent);
        group.setWorldName(worldName);
        Canary.usersAndGroups().addGroup(group);
        caller.message(ChatFormat.YELLOW + Translator.translateAndFormat("group created", group.getName()));
    }
}
