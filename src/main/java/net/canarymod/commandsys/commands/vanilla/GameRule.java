package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * GameRule command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class GameRule extends VanillaCommandWrapper {
    // gamerule <rule name> [value]

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "gamerule", parameters);
    }
}
