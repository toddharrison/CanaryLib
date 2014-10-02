package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Clone command wrapper
 *
 * @author Jason (darkdiplomat)
 */
public final class Clone extends VanillaCommandWrapper {

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "clone", parameters);
    }
}
