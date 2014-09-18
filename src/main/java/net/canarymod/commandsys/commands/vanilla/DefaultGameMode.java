package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Default GameMode command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class DefaultGameMode extends VanillaCommandWrapper {
    // defaultgamemode <gamemode>

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "defaultgamemode", parameters);
    }
}
