package net.canarymod.commandsys.commands.system.whitelist;

import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

import static net.canarymod.Canary.whitelist;

/**
 * Whitelist Remove Command
 *
 * @author Jason Jones (darkdiplomat)
 */
public class WhitelistReload implements NativeCommand {

    @Override
    public void execute(MessageReceiver receiver, String[] args) {
        whitelist().reload();
        receiver.notice("Whitelist reloaded."); //TODO Better Message
    }
}
