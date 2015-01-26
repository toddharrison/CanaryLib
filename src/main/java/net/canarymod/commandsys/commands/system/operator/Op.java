package net.canarymod.commandsys.commands.system.operator;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.user.OperatorsProvider;

import static net.canarymod.Translator.sendTranslatedNotice;

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
                sendTranslatedNotice(caller, "op no commandblock");
                return;
            case PLAYER:
                if (!caller.asPlayer().isOperator()) {
                    sendTranslatedNotice(caller, "op not allowed");
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
                sendTranslatedNotice(caller, "op failed", args[0]);
            }
        }
        else {
            sendTranslatedNotice(caller, "op already", args[0]);
        }
    }
}
