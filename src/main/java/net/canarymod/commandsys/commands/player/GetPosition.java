package net.canarymod.commandsys.commands.player;

import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to get your own position (x, y, z and rotation)
 *
 * @author Chris (damagefilter)
 */
public class GetPosition implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Player) {
            player((Player)caller);
        }
        else {
            console(caller);
        }
    }

    private void console(MessageReceiver caller) {
        caller.notice(Translator.translate("getpos console") + "(" + ChatFormat.OBFUSCATED + String.format("Altitude: %dkm ", Long.MAX_VALUE) + ChatFormat.RESET + ")");
    }

    private void player(Player player) {
        player.message(ChatFormat.GOLD + " X: " + ChatFormat.GRAY + player.getX());
        player.message(ChatFormat.GOLD + " Y: " + ChatFormat.GRAY + player.getY());
        player.message(ChatFormat.GOLD + " Z: " + ChatFormat.GRAY + player.getZ());
        player.message(ChatFormat.GOLD + "Rotation: " + ChatFormat.GRAY + player.getRotation() + ChatFormat.GOLD + " Pitch: " + ChatFormat.GRAY + player.getPitch());

        double degrees = ((player.getRotation() - 90) % 360);

        if (degrees < 0) {
            degrees += 360.0;
        }

        player.notice(Translator.translate("compass") + " " + Translator.translate(player.getCardinalDirection().toString()) + " (" + (Math.round(degrees * 10) / 10.0) + ")");
    }
}
