package net.canarymod.commandsys.commands.system.kits;

import net.canarymod.Canary;
import net.canarymod.chat.TextFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.kit.Kit;

/**
 * /kit delete
 *
 * @author Jason Jones (darkdiplomat)
 */
public class KitDelete implements NativeCommand {

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        Kit kat = Canary.kits().getKit(parameters[0]);
        if (kat != null) {
            Canary.kits().removeKit(kat);
            caller.notice(String.format("Kit '%s%s%s' deleted", TextFormat.YELLOW, parameters[0], TextFormat.RED));
        }
        else {
            caller.notice("Error 404: Kit Not Found...");
        }
    }
}
