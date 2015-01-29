package net.canarymod.commandsys.commands.warp;

import net.canarymod.Canary;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.ReceiverType;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.warp.Warp;

import static net.canarymod.Translator.sendTranslatedMessage;
import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Command to warp yourself to a warp
 *
 * @author Chris (damagefilter)
 */
public class WarpUse implements NativeCommand {
    public void execute(MessageReceiver caller, String[] args) {
        if (!caller.getReceiverType().equals(ReceiverType.PLAYER)) {
            sendTranslatedNotice(caller, "warp console");
        }
        else {
            Player player = (Player)caller;
            Warp target = Canary.warps().getWarp(args[0]);

            if (target != null) {
                if (target.warp(player)) {
                    sendTranslatedMessage(caller, ChatFormat.YELLOW, "warp success", target.getName());
                    return;
                }
                else {
                    sendTranslatedMessage(caller, ChatFormat.YELLOW, "warp not allowed", target.getName());
                    return;
                }
            }
            sendTranslatedNotice(caller, "warp unknown", args[0]);
        }
    }
}
