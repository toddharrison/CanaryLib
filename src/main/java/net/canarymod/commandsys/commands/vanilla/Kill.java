package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Kill command wrapper
 *
 * @author Almog (Swift)
 */
public final class Kill extends VanillaCommandWrapper {

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "kill", parameters);
    }
}
