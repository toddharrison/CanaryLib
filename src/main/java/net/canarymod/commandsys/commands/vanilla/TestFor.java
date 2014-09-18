package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * TestFor command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class TestFor extends VanillaCommandWrapper {
    // testfor <player>

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "testfor", parameters);
    }
}
