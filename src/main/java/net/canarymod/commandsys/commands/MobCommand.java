package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.living.animal.Tameable;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.CommandBlock;
import net.canarymod.api.world.position.Location;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Mob stats and clearing
 *
 * @author Jason (darkdiplomat)
 */
public class MobCommand implements NativeCommand {

    // /mob count [world]
    // /mob clear [flags=[(-h)(p)(t)(u)(a)]] [r=<radius>] [w=world] Note: (-h = not hostiles, p = passives, t = tamed, u = utility (Boats/Minecarts/ect...) a = all)
    public void execute(MessageReceiver caller, String[] parameters) {
        if (parameters[1].equals("clear")) {
            boolean hostiles = true, passives = false, tamed = false, utility = false, all = false;
            long radius = 20L;
            World world = callerWorld(caller);
            for (String arg : parameters) {
                if (arg.startsWith("flags=")) {
                    String flags = arg.replace("flags=", "");
                    if (flags.contains("-h")) {
                        hostiles = false;
                    }
                    if (flags.contains("p")) {
                        passives = true;
                    }
                    if (flags.contains("t")) {
                        tamed = true;
                    }
                    if (flags.contains("a")) {
                        all = true;
                    }
                    if (flags.contains("u")) {
                        utility = true;
                    }
                }
                else if (arg.startsWith("r=")) {
                    try {
                        radius = Long.parseLong(arg.replace("r=", ""));
                    }
                    catch (NumberFormatException nfe) {
                        caller.notice("Invalid Radius (" + arg + "). Defaulting to 20");
                    }
                    if (radius < -1 || radius == 0) {
                        radius = 20;
                        caller.notice("Invalid Radius (" + arg + "). Defaulting to 20");
                    }
                }
                else if (arg.startsWith("w=")) {
                    String wrld = arg.replace("w=", "");
                    if (!wrld.matches("[\\w]+_(END|NORMAL|NETHER)")) {
                        caller.notice("You need to add the dimension qualifier to the world name... ie: default_NORMAL");
                        return;
                    }
                    else if (!Canary.getServer().getWorldManager().worldExists(wrld)) {
                        caller.notice("No world by the name " + wrld);
                        return;
                    }
                    else if (!Canary.getServer().getWorldManager().worldIsLoaded(wrld)) {
                        caller.notice("The world is not currently loaded");
                        return;
                    }
                    world = Canary.getServer().getWorld(wrld);
                }
            }
            // TODO: Move this to someplace that it can be used other than here
            int mobCount = 0, passivesCount = 0, utilityCount = 0, tameCount = 0;
            for (Entity entity : world.getTrackedEntities()) {
                if (entity.isPlayer()) continue;
                if (radius == -1 || entity.getLocation().getDistance(location(caller)) <= radius) {
                    if (entity.isMob() && (hostiles || all)) {
                        entity.destroy();
                        mobCount++;
                    }
                    else if (entity.isAnimal() && (passives || all)) {
                        if (entity instanceof Tameable) {
                            if (tamed || all) {
                                entity.destroy();
                                tameCount++;
                            }
                        }
                        else {
                            entity.destroy();
                            passivesCount++;
                        }
                    }
                    else if (entity.isLiving() && !entity.isMob() && (passives || all)) {
                        entity.destroy();
                        passivesCount++;
                    }
                    else if (!entity.isLiving() && (utility || all)) {
                        entity.destroy();
                        utilityCount++;
                    }
                }
            }
            caller.notice(String.format("Destroyed %d Mobs, %d Passives, %d Tamed, %d Utility (%d Total).", mobCount, passivesCount, tameCount, utilityCount, (mobCount + passivesCount + tameCount + utilityCount)));
        }
    }

    private Location location(MessageReceiver caller) {
        if (caller instanceof Player) {
            return ((Player) caller).getLocation();
        }
        else if (caller instanceof CommandBlock) {
            return ((CommandBlock) caller).getBlock().getLocation();
        }
        else {
            return new Location(Canary.getServer().getDefaultWorld(), 0, 64, 0, 0.0F, 0.0F);
        }
    }

    private World callerWorld(MessageReceiver caller) {
        if (caller instanceof Player) {
            return ((Player) caller).getWorld();
        }
        else if (caller instanceof CommandBlock) {
            return ((CommandBlock) caller).getBlock().getWorld();
        }
        else {
            return Canary.getServer().getDefaultWorld();
        }
    }
}
