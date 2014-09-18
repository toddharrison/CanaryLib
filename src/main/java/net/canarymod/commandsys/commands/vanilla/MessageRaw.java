package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Message Raw command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class MessageRaw extends VanillaCommandWrapper {
    // tellraw <player> <raw json message>

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "tellraw", parameters);
    }
}
