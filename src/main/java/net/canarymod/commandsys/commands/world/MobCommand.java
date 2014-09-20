package net.canarymod.commandsys.commands.world;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.CommandBlock;
import net.canarymod.api.world.position.Location;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Mob Command shared methods
 */
abstract class MobCommand implements NativeCommand {

    protected Location location(MessageReceiver caller) {
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

    protected World callerWorld(MessageReceiver caller) {
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
