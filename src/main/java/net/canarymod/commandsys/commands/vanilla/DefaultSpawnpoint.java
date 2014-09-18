package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Default GameMode command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class DefaultSpawnpoint extends VanillaCommandWrapper {
    // setworldspawn [<x> <y> <z>]

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "setworldspawn", parameters);
    }
}
