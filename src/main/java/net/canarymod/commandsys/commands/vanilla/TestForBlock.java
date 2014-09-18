package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * TestForBlock command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class TestForBlock extends VanillaCommandWrapper {
    // testforblock <x> <y> <z> <TileName> [dataValue] [dataTag]

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "testforblock", parameters);
    }
}
