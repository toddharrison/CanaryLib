package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * TestForBlocks command wrapper
 *
 * @author Jason (darkdiplomat)
 */
public final class TestForBlocks extends VanillaCommandWrapper {
    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "testforblocks", parameters);
    }
}
