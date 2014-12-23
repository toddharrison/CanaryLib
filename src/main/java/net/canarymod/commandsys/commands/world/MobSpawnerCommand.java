package net.canarymod.commandsys.commands.world;

import net.canarymod.LineTracer;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.MobSpawner;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.ReceiverType;
import net.canarymod.commandsys.NativeCommand;

/**
 * MobSpawner base command
 *
 * @author Jason Jones (darkdiplomat)
 */
abstract class MobSpawnerCommand implements NativeCommand {

    final boolean canExecute(MessageReceiver receiver) {
        if (receiver.getReceiverType().equals(ReceiverType.PLAYER)) {
            return true;
        }
        receiver.notice("You need to be a player in order to use this command");
        return false;
    }

    final MobSpawner getSpawner(Player player) {
        Block spawner = new LineTracer(player).getTargetBlock();
        return spawner != null && spawner.getType().equals(BlockType.MobSpawner) ? (MobSpawner)spawner.getTileEntity() : null;
    }
}
