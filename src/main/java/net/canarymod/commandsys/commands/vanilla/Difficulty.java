package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Difficulty command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Difficulty extends VanillaCommandWrapper {
    // difficulty <new difficulty> [world]

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "difficulty", parameters);
    }
}
