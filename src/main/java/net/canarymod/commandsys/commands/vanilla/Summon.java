package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Summon command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Summon extends VanillaCommandWrapper {
    // summon <EntityName> [x] [y] [z] [dataTag] | "mobspawn", "mspawn", "spawnmob"

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "summon", parameters);
    }
}
