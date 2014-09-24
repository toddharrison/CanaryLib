package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.OperatorsProvider;

/**
 * Op Command
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class DeOp implements NativeCommand {

    public void execute(MessageReceiver caller, String[] args) {
        OperatorsProvider opPro = Canary.ops();
        switch (caller.getReceiverType()) {
            case COMMANDBLOCK:
            case COMMANDBLOCKENTITY:
                caller.notice("Due to security concerns, DeOp command is not allowed from a CommandBlock");
                return;
            case PLAYER:
                if (!opPro.isOpped(((Player)caller))) {
                    caller.notice("You are not an Operator!");
                    return;
                }
        }
        opPro.removePlayer(args[0]); // Just assuming this worked here...
    }
}