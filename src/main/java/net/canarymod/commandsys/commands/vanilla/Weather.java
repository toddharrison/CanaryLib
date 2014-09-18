package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Weather command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Weather extends VanillaCommandWrapper {
    // weather <clear|rain|thunder> [duration in seconds]

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "weather", parameters);
    }
}
