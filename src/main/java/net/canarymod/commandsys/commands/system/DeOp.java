package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.TextFormat;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.OperatorsProvider;

/**
 * DeOp Command
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

        String opUUID = ToolBox.usernameToUUID(args[0]);
        if (opPro.isOpped(opUUID)) {
            opPro.removePlayer(opUUID);
            if (!opPro.isOpped(opUUID)) {
                Canary.getServer().broadcastMessageToOps(TextFormat.LIGHT_GRAY + "[SERVER] De-opped " + args[0]);
            }
            else {
                caller.notice("Failed to deop " + args[0]);
            }
        }
        else {
            caller.notice(args[0] + " was not an operator.");
        }
    }
}