package net.canarymod.commandsys.commands.warp;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.UnknownWorldException;
import net.canarymod.api.world.World;
import net.canarymod.api.world.WorldManager;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.position.Location;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.config.Configuration;

/**
 * Command to teleport yourself or someoneelse to spawn
 *
 * @author Chris (damagefilter)
 */
public class SpawnCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player)caller, parameters);
        }
        else {
            console(caller, parameters);
        }
    }

    private void console(MessageReceiver caller, String[] args) {
        try {
            if (args.length < 3) {
                caller.notice(Translator.translate("spawn failed console"));
            }
            else {
                WorldManager worldManager = Canary.getServer().getWorldManager();
                if (!worldManager.worldExists(args[1])) {
                    caller.notice(Translator.translate("spawn failed"));
                    return;
                }
                World w = worldManager.getWorld(args[1], Configuration.getWorldConfig(args[1]).allowWarpAutoLoad());

                Player player = Canary.getServer().matchPlayer(args[2]);
                if (player != null && w != null) {
                    Location loc = w.getSpawnLocation();
                    loc.setY(w.getHighestBlockAt(loc.getBlockX(), loc.getBlockZ()));
                    player.teleportTo(loc);
                    caller.notice(Translator.translateAndFormat("spawn success other", player.getName()));
                }
                else {
                    caller.notice(Translator.translate("spawn failed console"));
                }
            }
        }
        catch (UnknownWorldException exception) {
            caller.notice(Translator.translate("spawn failed"));
        }
    }

    private void player(Player player, String[] args) {
        try {
            if (args.length == 1) {
                Location loc = player.getWorld().getSpawnLocation();
                loc.setY(player.getWorld().getHighestBlockAt(loc.getBlockX(), loc.getBlockZ()));
                player.teleportTo(loc);
                player.message(ChatFormat.YELLOW + Translator.translate("spawn success"));
            }
            else if (args.length == 2) {
                WorldManager worldManager = Canary.getServer().getWorldManager();
                if (!worldManager.worldExists(args[1])) {
                    player.notice(Translator.translate("spawn failed"));
                    return;
                }
                World w = worldManager.getWorld(args[1], Configuration.getWorldConfig(args[1]).allowWarpAutoLoad());

                if (w == null) {
                    player.notice(Translator.translate("spawn failed"));
                }
                else {
                    Location loc = w.getSpawnLocation();
                    loc.setY(w.getHighestBlockAt(loc.getBlockX(), loc.getBlockZ()));
                    player.teleportTo(loc);
                    player.message(ChatFormat.YELLOW + Translator.translate("spawn success"));
                }
            }
            else {
                WorldManager worldManager = Canary.getServer().getWorldManager();
                if (!worldManager.worldExists(args[1])) {
                    player.notice(Translator.translate("spawn failed"));
                    return;
                }
                World w = worldManager.getWorld(args[1], Configuration.getWorldConfig(args[1]).allowWarpAutoLoad());
                Player target = Canary.getServer().matchPlayer(args[2]);

                if (target != null && w != null) {
                    Location loc = w.getSpawnLocation();
                    loc.setY(w.getHighestBlockAt(loc.getBlockX(), loc.getBlockZ()));
                    target.teleportTo(loc);
                    player.message(ChatFormat.YELLOW + Translator.translateAndFormat("spawn success other", player.getName()));
                }
                else {
                    player.notice(Translator.translate("spawn failed"));
                }
            }
        }
        catch (UnknownWorldException exception) {
            player.notice(Translator.translate("spawn failed"));
        }
    }

    private Location getFirsetAirLocation(World world) {
        Location loc = world.getSpawnLocation();
        Block block = world.getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        if (block.isAir()) {
            block = world.getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ());
        }
        while (!block.isAir()) {
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
