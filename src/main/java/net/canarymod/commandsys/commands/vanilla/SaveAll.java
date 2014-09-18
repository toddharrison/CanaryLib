package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Save-All command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class SaveAll extends VanillaCommandWrapper {
    // save-all

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "save-all", parameters);
    }
}
