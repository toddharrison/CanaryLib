package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * ToggleDownfall command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class ToggleDownfall extends VanillaCommandWrapper {

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "toggledownfall", parameters);
    }
}
