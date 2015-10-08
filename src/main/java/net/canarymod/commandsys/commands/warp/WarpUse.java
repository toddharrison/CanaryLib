package net.canarymod.commandsys.commands.warp;

import static net.canarymod.Translator.*;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.ReceiverType;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.warp.Warp;

/**
 * Command to warp yourself to a warp
 *
 * @author Chris (damagefilter)
 */
public class WarpUse implements NativeCommand {
    @Override
    public void execute(final MessageReceiver caller, final String[] args) {
        if (!caller.getReceiverType().equals(ReceiverType.PLAYER)) {
            sendTranslatedNotice(caller, "warp console");
        } else {
            final Player player = (Player) caller;
            final Warp target = Canary.warps().getWarp(args[0]);

            if (target != null) {
                if (target.warp(player)) {
                    sendTranslatedMessage(caller, ChatFormat.YELLOW, "warp success",
                            target.getName());
                    return;
                } else {
                    sendTranslatedMessage(caller, ChatFormat.YELLOW, "warp not allowed",
                            target.getName());
                    return;
                }
            }
            sendTranslatedNotice(caller, "warp unknown", args[0]);
        }
    }
}
