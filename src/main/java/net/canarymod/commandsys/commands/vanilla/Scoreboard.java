package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Scoreboard command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Scoreboard extends VanillaCommandWrapper {
    // scoreboard <objectives|players|teams>

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "scoreboard", parameters);
    }
}
