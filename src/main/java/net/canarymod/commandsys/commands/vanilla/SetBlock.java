package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * SetBlock command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class SetBlock extends VanillaCommandWrapper {
    // setblock <x> <y> <z> <TileName> [dataValue] [oldBlockHandling] [dataTag]

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "setblock", parameters);
    }
}
