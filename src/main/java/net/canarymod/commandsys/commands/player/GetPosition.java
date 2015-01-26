package net.canarymod.commandsys.commands.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.ReceiverType;
import net.canarymod.commandsys.NativeCommand;

import static net.canarymod.Translator.sendTranslatedNotice;
import static net.canarymod.Translator.translate;

/**
 * Command to get your own position (x, y, z and rotation)
 *
 * @author Chris (damagefilter)
 */
public class GetPosition implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller.getReceiverType().equals(ReceiverType.PLAYER)) {
            parse(caller.asPlayer());
        }
        else {
            sendTranslatedNotice(caller, "getpos console", (ChatFormat.OBFUSCATED.toString() + Long.MAX_VALUE + ChatFormat.RESET + "km"));
        }
    }

    private void parse(Player player) {
        player.message(ChatFormat.GOLD + " X: " + ChatFormat.GRAY + player.getX());
        player.message(ChatFormat.GOLD + " Y: " + ChatFormat.GRAY + player.getY());
        player.message(ChatFormat.GOLD + " Z: " + ChatFormat.GRAY + player.getZ());
        player.message(ChatFormat.GOLD + "Rotation: " + ChatFormat.GRAY + player.getRotation() + ChatFormat.GOLD + " Pitch: " + ChatFormat.GRAY + player.getPitch());

        double degrees = ((player.getRotation() - 90) % 360);

        if (degrees < 0) {
            degrees += 360.0;
        }

        player.notice(translate("compass") + " " + translate(player.getCardinalDirection().toString()) + " (" + (Math.round(degrees * 10) / 10.0) + ")");
    }
}
