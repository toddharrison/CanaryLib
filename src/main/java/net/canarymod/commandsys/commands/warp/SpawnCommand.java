package net.canarymod.commandsys.commands.warp;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.position.Location;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to teleport yourself or someoneelse to spawn
 *
 * @author Chris (damagefilter)
 */
public class SpawnCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player) caller, parameters);
        }
        else {
            console(caller, parameters);
        }
    }

    private void console(MessageReceiver caller, String[] args) {
        if (args.length < 3) {
            caller.notice(Translator.translate("spawn failed console"));
        }
        else {
            Player player = Canary.getServer().matchPlayer(args[2]);
            World w = Canary.getServer().getWorld(args[1]);

            if (player != null && w != null) {
                player.teleportTo(getFirsetAirLocation(w));
                caller.notice(Translator.translateAndFormat("spawn success other", player.getName()));
            }
            else {
                caller.notice(Translator.translate("spawn failed console"));
            }
        }

    }

    private void player(Player player, String[] args) {
        if (args.length == 1) {
            player.teleportTo(getFirsetAirLocation(player.getWorld()));
            player.message(ChatFormat.YELLOW + Translator.translate("spawn success"));
        }
        else if (args.length == 2) {
            World w = Canary.getServer().getWorld(args[1]);

            if (w == null) {
                player.notice(Translator.translate("spawn failed"));
            }
            else {
                player.teleportTo(getFirsetAirLocation(w));
                player.message(ChatFormat.YELLOW + Translator.translate("spawn success"));
            }
        }
        else {
            World w = Canary.getServer().getWorld(args[1]);
            Player target = Canary.getServer().matchPlayer(args[2]);

            if (target != null && w != null) {
                target.teleportTo(getFirsetAirLocation(w));
                player.message(ChatFormat.YELLOW + Translator.translateAndFormat("spawn success other", player.getName()));
            }
        }
    }

    private Location getFirsetAirLocation(World world) {
        Location loc = world.getSpawnLocation();
        Block block = world.getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        if (block.isAir()) {
            block = world.getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());
        }
        while (!block.isAir())
        {
            for (int y = block.getY() + 1; y < world.getHeight(); y++) {
                block = world.getBlockAt(loc.getBlockX(), y, loc.getBlockZ());
                if (block.isAir()) {
                    break;
                }
            }
            block = world.getBlockAt(block.getX(), block.getY() + 1, block.getZ());
        }
        block.getLocation().setY(block.getLocation().getY() - 1);
        return block.getLocation();
    }
}
