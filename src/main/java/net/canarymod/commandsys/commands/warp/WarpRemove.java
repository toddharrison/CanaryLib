package net.canarymod.commandsys.commands.warp;

import net.canarymod.Canary;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.warp.Warp;

import static net.canarymod.Translator.sendTranslatedMessage;
import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Command to remove a warp
 *
 * @author Chris (damagefilter)
 */
public class WarpRemove implements NativeCommand {
    public void execute(MessageReceiver caller, String[] args) {
        if (Canary.warps().warpExists(args[0])) {
            Warp target = Canary.warps().getWarp(args[0]);
            Canary.warps().removeWarp(target);
            sendTranslatedMessage(caller, ChatFormat.YELLOW, "warp removed", target.getName());
        }
        else {
            sendTranslatedNotice(caller, "warp unknown", args[0]);
        }
    }
}
