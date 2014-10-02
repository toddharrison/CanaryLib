package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Trigger command wrapper
 *
 * @author Jason (darkdiplomat)
 */
public final class Trigger extends VanillaCommandWrapper {
    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "trigger", parameters);
    }
}
