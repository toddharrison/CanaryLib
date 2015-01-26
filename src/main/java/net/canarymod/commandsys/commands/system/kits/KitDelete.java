package net.canarymod.commandsys.commands.system.kits;

import net.canarymod.Canary;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.kit.Kit;

import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * /kit delete
 *
 * @author Jason Jones (darkdiplomat)
 */
public class KitDelete implements NativeCommand {

    @Override
    public void execute(MessageReceiver caller, String[] args) {
        Kit kat = Canary.kits().getKit(args[0]);
        if (kat != null) {
            Canary.kits().removeKit(kat);
            caller.notice(String.format("Kit '%s%s%s' deleted", ChatFormat.YELLOW, args[0], ChatFormat.RED));
        }
        else {
            sendTranslatedNotice(caller, "kit invalid", args[0]);
        }
    }
}
