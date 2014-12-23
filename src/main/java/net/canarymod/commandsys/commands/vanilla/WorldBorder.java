package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * WorldBorder command wrapper
 *
 * @author Jason (darkdiplomat)
 */
public final class WorldBorder extends VanillaCommandWrapper {
    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "worldborder", parameters);
    }
}
