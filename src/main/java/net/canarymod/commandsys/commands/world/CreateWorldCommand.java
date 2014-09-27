package net.canarymod.commandsys.commands.world;

import net.canarymod.Canary;
import net.canarymod.api.world.DimensionType;
import net.canarymod.api.world.WorldManager;
import net.canarymod.api.world.WorldType;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Create World Command
 *
 * @author Jason (darkdiplomat)
 */
public final class CreateWorldCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        try {
            WorldManager manage = Canary.getServer().getWorldManager();
            String name = parameters[0];
            DimensionType dType = DimensionType.NORMAL;
            WorldType wType = WorldType.DEFAULT;
            long seed = System.currentTimeMillis(); // The Mojang random seed secret revealed!

            if (parameters.length > 1) {
                if (parameters[1].matches("\\d+")) {
                    seed = Long.parseLong(parameters[2]);
                }
                else {
                    seed = parameters[1].hashCode();
                }
            }
            if (parameters.length > 2) {
                if (parameters[2].matches("\\d")) {
                    dType = DimensionType.fromId(Integer.parseInt(parameters[2]));
                }
                else {
                    dType = DimensionType.fromName(parameters[2].toUpperCase());
                }
            }
            if (dType == null) {
                caller.notice("Dimension Type is non-existant");
                return;
            }
            if (parameters.length > 3) {
                wType = WorldType.fromString(parameters[3]);
            }
            if (wType == null) {
                caller.notice("World Type is non-existant");
                return;
            }

            caller.notice("Please wait while the world is generated...");
            manage.createWorld(name, seed, dType, wType);
            caller.notice("World Created!");

        }
        catch (Exception ex) {
            caller.notice("Failed to create world. Check console for errors.");
        }
    }
}
