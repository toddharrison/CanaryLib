package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * ReplaceItem command wrapper
 *
 * @author Jason (darkdiplomat)
 */
public final class ReplaceItem extends VanillaCommandWrapper {
    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "replaceitem", parameters);
    }
}
