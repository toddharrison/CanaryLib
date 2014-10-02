package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * EntityData command wrapper
 *
 * @author Jason (darkdiplomat)
 */
public final class EntityData extends VanillaCommandWrapper {
    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "entitydata", parameters);
    }
}
