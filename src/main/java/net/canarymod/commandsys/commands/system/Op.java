package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.OperatorsProvider;

/**
 * Op Command
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Op implements NativeCommand {

    public void execute(MessageReceiver caller, String[] args) {
        OperatorsProvider opPro = Canary.ops();
        switch (caller.getReceiverType()) {
            case COMMANDBLOCK:
            case COMMANDBLOCKENTITY:
                caller.notice("Due to security concerns, Op command is not allowed from a CommandBlock");
                return;
            case PLAYER:
                if (!opPro.isOpped(((Player)caller))) {
                    caller.notice("You are not an Operator!");
                    return;
                }
        }

        String opUUID = ToolBox.usernameToUUID(args[0]);
        if (!opPro.isOpped(opUUID)) {
            opPro.addPlayer(opUUID);
            if (opPro.isOpped(opUUID)) { // Double check that is worked
                Canary.getServer().broadcastMessageToOps(ChatFormat.GRAY + "[SERVER] Opped " + args[0]);
            }
            else {
                caller.notice("Failed to op " + args[0]);
            }
        }
        else {
            caller.notice(args[0] + " is already an Operator");
        }
    }
}
