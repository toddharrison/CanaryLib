package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.ToolBox;
import net.canarymod.api.PlayerReference;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

import java.util.UUID;

/**
 * Operators List command
 *
 * @author Jason Jones (darkdiplomat)
 */
public class OpList implements NativeCommand {

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        caller.message(ChatFormat.GRAY + "**** OPERATORS ****");
        caller.message(createList());
    }

    private String createList() {
        String[] operators = Canary.ops().getOps();
        StringBuilder sb = new StringBuilder();

        for (String operator : operators) {
            String name = operator;
            if (ToolBox.isUUID(operator)) {
                PlayerReference reference = Canary.getServer().matchKnownPlayer(UUID.fromString(operator));
                if (reference != null) {
                    name = reference.getName();
                }
            }
            sb.append(name).append(", ");
        }
        if (sb.length() > 0) {
            sb.delete(sb.length() - 2, sb.length() - 1);
        }
        return sb.toString();
    }
}
