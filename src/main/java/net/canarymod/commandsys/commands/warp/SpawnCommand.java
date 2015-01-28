package net.canarymod.commandsys.commands.warp;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.UnknownWorldException;
import net.canarymod.api.world.World;
import net.canarymod.api.world.position.Location;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.ReceiverType;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.config.Configuration;
import net.canarymod.hook.player.TeleportHook;

import static net.canarymod.Translator.sendTranslatedMessage;
import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Command to teleport yourself or someoneelse to spawn
 *
 * @author Chris (damagefilter)
 */
public class SpawnCommand implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller.getReceiverType().equals(ReceiverType.PLAYER)) {
            player(caller.asPlayer(), parameters);
        }
        else {
            console(caller, parameters);
        }
    }

    private void console(MessageReceiver caller, String[] args) {
        try {
            if (args.length < 3) {
                sendTranslatedNotice(caller, "spawn failed console");
            }
            else {
                String fqName = ToolBox.parseWorldName(args[1]);
                World w = ToolBox.parseWorld(fqName, Configuration.getWorldConfig(fqName).allowWarpAutoLoad());
                Player player = Canary.getServer().matchPlayer(args[2]);

                if (w == null) {
                    sendTranslatedNotice(caller, "unknown world", args[1]);
                }
                else if (player != null) {
                    Location loc = w.getSpawnLocation();
                    loc.setY(w.getHighestBlockAt(loc.getBlockX(), loc.getBlockZ()));
                    player.teleportTo(loc, TeleportHook.TeleportCause.COMMAND);
                    sendTranslatedNotice(caller, "spawn success other", player.getName());
                }
                else {
                    sendTranslatedNotice(caller, "spawn failed console");
                }
            }
        }
        catch (UnknownWorldException exception) {
            sendTranslatedNotice(caller, "unknown world", args[1]);
        }
    }

    private void player(Player player, String[] args) {
        try {
            if (args.length == 1) {
                Location loc = player.getWorld().getSpawnLocation();
                loc.setY(player.getWorld().getHighestBlockAt(loc.getBlockX(), loc.getBlockZ()));
                player.teleportTo(loc, TeleportHook.TeleportCause.COMMAND);
                sendTranslatedMessage(player, ChatFormat.YELLOW, "spawn success");
            }
            else if (args.length == 2) {
                String fqName = ToolBox.parseWorldName(args[1]);
                World w = ToolBox.parseWorld(fqName, Configuration.getWorldConfig(fqName).allowWarpAutoLoad());

                if (w == null) {
                    sendTranslatedNotice(player, "unknown world", args[1]);
                }
                else {
                    Location loc = w.getSpawnLocation();
                    loc.setY(w.getHighestBlockAt(loc.getBlockX(), loc.getBlockZ()));
                    player.teleportTo(loc, TeleportHook.TeleportCause.COMMAND);
                    sendTranslatedMessage(player, ChatFormat.YELLOW, "spawn success");
                }
            }
            else {
                String fqName = ToolBox.parseWorldName(args[1]);
                World w = ToolBox.parseWorld(fqName, Configuration.getWorldConfig(fqName).allowWarpAutoLoad());
                Player target = Canary.getServer().matchPlayer(args[2]);

                if (w == null) {
                    sendTranslatedNotice(player, "unknown world", args[1]);
                }
                else if (target != null) {
                    Location loc = w.getSpawnLocation();
                    loc.setY(w.getHighestBlockAt(loc.getBlockX(), loc.getBlockZ()));
                    target.teleportTo(loc, TeleportHook.TeleportCause.COMMAND);
                    sendTranslatedMessage(player, ChatFormat.YELLOW, "spawn success other", target.getName());
                }
                else {
                    sendTranslatedNotice(player, "spawn failed");
                }
            }
        }
        catch (UnknownWorldException exception) {
            sendTranslatedNotice(player, "unknown world", args[1]);
        }
    }
}
