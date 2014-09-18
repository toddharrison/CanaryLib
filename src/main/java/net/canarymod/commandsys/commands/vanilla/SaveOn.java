package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Save-On command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class SaveOn extends VanillaCommandWrapper {
    // save-on

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "save-on", parameters);
    }
}
