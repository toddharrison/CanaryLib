package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Stats command wrapper
 *
 * @author Jason (darkdiplomat)
 */
public final class Stats extends VanillaCommandWrapper {
    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "stats", parameters);
    }
}
