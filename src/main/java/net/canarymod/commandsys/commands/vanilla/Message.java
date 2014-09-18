package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Message command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Message extends VanillaCommandWrapper {
    // tell <player> <private message ...> | alias m msg

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "tell", parameters);
    }
}
