package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Debug command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Debug extends VanillaCommandWrapper {

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "debug", parameters);
    }
}
