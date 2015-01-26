package net.canarymod.commandsys.commands.player;

import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.ReceiverType;
import net.canarymod.commandsys.NativeCommand;

import static net.canarymod.Translator.sendTranslatedNotice;
import static net.canarymod.Translator.translate;

/**
 * Command to get your own rotation (cardinal direction and degrees yaw)
 *
 * @author Chris (damagefilter)
 */
public class Compass implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller.getReceiverType().equals(ReceiverType.PLAYER)) {
            double degrees = (caller.asPlayer().getRotation() - 180) % 360;

            if (degrees < 0) {
                degrees += 360.0;
            }

            caller.notice(translate("compass") + " " + translate(caller.asPlayer().getCardinalDirection().toString()) + " (" + (Math.round(degrees * 10) / 10.0) + ")");
        }
        else {
            sendTranslatedNotice(caller, "compass console");
        }
    }
}
