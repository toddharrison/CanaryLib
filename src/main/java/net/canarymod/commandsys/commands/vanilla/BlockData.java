package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * BlockData command wrapper
 *
 * @author Jason (darkdiplomat)
 */
public final class BlockData extends VanillaCommandWrapper {

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "blockdata", parameters);
    }
}
