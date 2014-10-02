package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Particle command wrapper
 *
 * @author Jason (darkdiplomat)
 */
public final class Particle extends VanillaCommandWrapper {

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "particle", parameters);
    }
}
