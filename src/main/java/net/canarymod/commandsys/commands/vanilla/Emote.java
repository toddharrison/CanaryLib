package net.canarymod.commandsys.commands.vanilla;

import net.canarymod.chat.MessageReceiver;

/**
 * Emote command wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class Emote extends VanillaCommandWrapper {
    // emote <action...> | alias me

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        passOn(caller, "emote", parameters);
    }
}
