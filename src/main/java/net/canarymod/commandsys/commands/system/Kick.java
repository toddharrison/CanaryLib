package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.ReceiverType;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.hook.player.KickHook;
import net.visualillusionsent.utils.StringUtils;

/**
 * Command to kick a player from the server
 *
 * @author Brian (WWOL)
 */
public class Kick implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        boolean isPlayerCommandBlock = caller.getReceiverType() != ReceiverType.SERVER;

        Player[] targets = Canary.playerSelector().matchPlayers(caller, parameters[0]);
        Player target = Canary.getServer().matchPlayer(parameters[0]);
        if (targets != null) {
            for (Player player : targets) {
                if (isPlayerCommandBlock) {
                    Player c = (Player)caller;
                    if (!c.getGroup().hasControlOver(player.getGroup())) {
                        continue;
                    }
                }
                if (player != null) {
                    String reason = Translator.translateAndFormat("kick message", new Object[]{ caller.getName() });
                    if (parameters.length >= 2) {
                        reason = StringUtils.joinString(parameters, " ", 1);
                    }
                    new KickHook(player, caller, reason).call();
                    player.kickNoHook(reason);
                    caller.notice(Translator.translateAndFormat("kick kicked", new Object[]{ player.getName() }));
                }
            }
        }
        else if (target != null) {
            if (isPlayerCommandBlock) {
                Player c = (Player)caller;
                if (!c.getGroup().hasControlOver(target.getGroup())) {
                    caller.notice("nope!"); //TODO Better Message
                    return;
                }
            }
            String reason = Translator.translateAndFormat("kick message", new Object[]{ caller.getName() });
            if (parameters.length >= 2) {
                reason = StringUtils.joinString(parameters, " ", 1);
            }
            new KickHook(target, caller, reason).call();
            target.kickNoHook(reason);
            caller.notice(Translator.translateAndFormat("kick kicked", new Object[]{ target.getName() }));
        }
        else {
            caller.notice(Translator.translate("kick failed") + " " + Translator.translateAndFormat("unknown player", new Object[]{ parameters[1] }));
        }
    }
}
