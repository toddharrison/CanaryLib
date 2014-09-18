package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Time command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Time extends VanillaCommandWrapper {
    // time <set|add> <value>

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "time", parameters);
    }
}
