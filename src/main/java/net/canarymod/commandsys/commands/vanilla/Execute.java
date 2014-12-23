package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Execute command wrapper
 *
 * @author Jason (darkdiplomat)
 */
public final class Execute extends VanillaCommandWrapper {

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "execute", parameters);
    }
}
