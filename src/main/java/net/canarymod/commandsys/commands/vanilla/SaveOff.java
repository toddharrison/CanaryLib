package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Save-Off command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class SaveOff extends VanillaCommandWrapper {
    // save-off

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "save-off", parameters);
    }
}
