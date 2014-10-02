package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Fill command wrapper
 *
 * @author Jason (darkdiplomat)
 */
public final class Fill extends VanillaCommandWrapper {
    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "fill", parameters);
    }
}
