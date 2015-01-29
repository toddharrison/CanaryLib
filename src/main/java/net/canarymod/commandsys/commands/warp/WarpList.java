package net.canarymod.commandsys.commands.warp;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.ReceiverType;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.warp.Warp;

import java.util.List;

import static net.canarymod.Canary.log;
import static net.canarymod.Translator.sendTranslatedMessage;
import static net.canarymod.Translator.sendTranslatedNotice;

/**
 * Command to list all warps
 *
 * @author Chris (damagefilter)
 */
public class WarpList implements NativeCommand {
    public void execute(MessageReceiver caller, String[] parameters) {
        if (!caller.getReceiverType().equals(ReceiverType.PLAYER)) {
            caller.notice("**** WARPS ****");

            List<Warp> warps = Canary.warps().getAllWarps();
            StringBuilder warpList = new StringBuilder();

            for (Warp warp : warps) {
                warpList.append(warp.getName()).append(", ");
            }
            if (warpList.length() > 0) {
                warpList.deleteCharAt(warpList.length() - 1);
                log.info(warpList.toString());
            }
            else {
                log.info(Translator.translate("no warps"));
            }
        }
        else {
            Player player = (Player)caller;
            sendTranslatedMessage(player, ChatFormat.YELLOW, "warps available");

            List<Warp> warps = Canary.warps().getAllWarps();
            StringBuilder warpList = new StringBuilder();

            for (Warp w : warps) {
                if (w.getOwner() != null) {
                    if (w.isPlayerHome() && w.getOwner().equals(player.getName())) {
                        warpList.append(ChatFormat.GREEN).append("(").append(Translator.translate("your home")).append(")").append(ChatFormat.WHITE).append(", ");
                    }
                    else if (!w.isPlayerHome() && w.getOwner().equals(player.getName()) || (player.isAdmin())) {
                        warpList.append(ChatFormat.GOLD).append(w.getName()).append("(").append(Translator.translate("private")).append(")").append(ChatFormat.WHITE).append(", ");
                    }
                }
                else if (w.isGroupRestricted() && w.isGroupAllowed(player.getGroup())) {
                    warpList.append(ChatFormat.YELLOW).append(w.getName()).append("(").append(Translator.translate("group")).append(")").append(ChatFormat.WHITE).append(", ");
                }
                else if (!w.isGroupRestricted()) {
                    warpList.append(w.getName()).append(", ");
                }
            }

            if (warpList.length() > 0) {
                warpList.deleteCharAt(warpList.length() - 1);
                player.message(warpList.toString().trim());
            }
            else {
                sendTranslatedNotice(player, "no warps");
            }
        }
    }
}
