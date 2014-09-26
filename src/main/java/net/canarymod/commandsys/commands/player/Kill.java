package net.canarymod.commandsys.commands.player;

import net.canarymod.Canary;
import net.canarymod.Translator;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;

/**
 * Command to kill yourself or another player
 *
 * @author Chris (damagefilter)
 */
public class Kill implements NativeCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if ((caller instanceof Player)) {
            player((Player) caller, parameters);
        }
        else {
            console(caller, parameters);
        }
    }

    private void console(MessageReceiver caller, String[] args) {
        if (args.length == 0) {
            caller.notice(Translator.translate("kill console"));
        }
        else {
            doAction(caller, args);
        }
    }

    private void player(Player player, String[] args) {
        if (args.length == 0) {
            player.notice(Translator.translate("player suicide"));
            player.kill();
        }
        else if (player.hasPermission("canary.command.player.kill.other")) {
            doAction((MessageReceiver) player, args);
        }
    }

    private void doAction(MessageReceiver caller, String[] args) {
        Player[] targets = Canary.playerSelector().matchPlayers(caller, args[0]);
        if (targets != null) {
            for (Player target : targets) {
                if (target != null) {
                    target.kill();
                    caller.notice(Translator.translateAndFormat("killed other", new Object[]{target.getName()}));
                }
                else {
                    caller.notice(Translator.translate("not killed") + " " + Translator.translateAndFormat("unknown player", new Object[]{args[0]}));
                }
            }
            return;
        }

        Player target = Canary.getServer().matchPlayer(args[0]);
        if (target != null) {
            target.kill();
            caller.notice(Translator.translateAndFormat("killed other", new Object[]{target.getName()}));
        }
        else {
            caller.notice(Translator.translate("not killed") + " " + Translator.translateAndFormat("unknown player", new Object[]{args[0]}));
        }
    }

}
