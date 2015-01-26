package net.canarymod.commandsys.commands.system.operator;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.OperatorsProvider;

import static net.canarymod.Translator.sendTranslatedNotice;

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
                sendTranslatedNotice(caller, "op no commandblock");
                return;
            case PLAYER:
                if (caller.asPlayer().isOperator()) {
                    sendTranslatedNotice(caller, "op not allowed");
                    return;
                }
        }

        String opUUID = ToolBox.usernameToUUID(args[0]);
        if (opPro.isOpped(opUUID)) {
            opPro.removePlayer(opUUID);
            if (!opPro.isOpped(opUUID)) {
                Canary.getServer().broadcastMessageToOps(ChatFormat.GRAY + "[SERVER] De-opped " + args[0]);
            }
            else {
                sendTranslatedNotice(caller, "deop failed", args[0]);
            }
        }
        else {
            sendTranslatedNotice(caller, "deop not op", args[0]);
        }
    }
}