package net.canarymod.commandsys.commands.world;

import net.canarymod.api.MobSpawnerLogic;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.MobSpawner;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;

/**
 * MobSpawner check subcommand
 *
 * @author Jason Jones (darkdiplomat)
 */
public class MobSpawnerCheck extends MobSpawnerCommand {

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        if (canExecute(caller)) {
            Player player = (Player)caller;
            MobSpawner mobSpawner = getSpawner(player);
            if (mobSpawner != null) {
                MobSpawnerLogic logic = mobSpawner.getLogic();
                caller.message(
                        ChatFormat.GOLD + "MobSpawner @" +
                                ChatFormat.DARK_AQUA + " X: " + ChatFormat.AQUA + mobSpawner.getX() +
                                ChatFormat.DARK_AQUA + " Y: " + ChatFormat.AQUA + mobSpawner.getY() +
                                ChatFormat.DARK_AQUA + " Z: " + ChatFormat.AQUA + mobSpawner.getZ(),
                        ChatFormat.GOLD + "SpawnId: " + ChatFormat.YELLOW + logic.getSpawnId(),
                        ChatFormat.GOLD + "Delay: " + ChatFormat.YELLOW + logic.getDelay(),
                        ChatFormat.GOLD + "MinDelay: " + ChatFormat.YELLOW + logic.getMinDelay(),
                        ChatFormat.GOLD + "MaxDelay: " + ChatFormat.YELLOW + logic.getMaxDelay(),
                        ChatFormat.GOLD + "SpawnCount: " + ChatFormat.YELLOW + logic.getSpawnCount(),
                        ChatFormat.GOLD + "MaxNearBy: " + ChatFormat.YELLOW + logic.getMaxNearbyEntities(),
                        ChatFormat.GOLD + "PlayerRange: " + ChatFormat.YELLOW + logic.getRequiredPlayerRange(),
                        ChatFormat.GOLD + "SpawnRange: " + ChatFormat.YELLOW + logic.getSpawnRange()
                              );
                if (parameters.length > 0 && parameters[0].equals("raw")) {
                    caller.message("Raw NBT: " + mobSpawner.getDataTag().toString());
                }
            }
        }
    }
}
