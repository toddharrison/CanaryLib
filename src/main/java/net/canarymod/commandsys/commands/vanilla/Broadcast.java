package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Broadcast command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Broadcast extends VanillaCommandWrapper {
    // say <message ...> | alias broadcast

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "say", parameters);
    }
}
