package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Title command wrapper
 *
 * @author Jason (darkdiplomat)
 */
public final class Title extends VanillaCommandWrapper {

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "title", parameters);
    }
}
