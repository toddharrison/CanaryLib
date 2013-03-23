package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.api.Server;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CanaryCommand;
import net.canarymod.commandsys.CommandException;

public class Home extends CanaryCommand {

    public Home() {
        super("canary.command.home", "Teleport home or to someone elses home", "Usage: /home [playername]", 1, 2);
    }

    @Override
    protected void execute(MessageReceiver caller, String[] parameters) {
        if(caller instanceof Server) {
            console(caller);
        }
        else if(caller instanceof Player) {
            player((Player)caller, parameters);
        }
        else {
            throw new CommandException("Unknown MessageReceiver: "+caller.getClass().getSimpleName());
        }
    }
    
    private void console(MessageReceiver caller) {
        caller.notice("You are already home.");
    }
    
    private void player(Player player, String[] args) {
        if(args.length == 1) {
            if(player.hasHome()) {
                player.notice("Going home");
                player.teleportTo(player.getHome());
            }
            else {
                player.notice("You have no home set. Use /sethome to create your own home.");
            }
        }
        else {
            if(player.hasPermission("canary.command.home.other")) {
                Player target = Canary.getServer().matchPlayer(args[1]);
                if(target != null) {
                    if(target.hasHome()) {
                        player.notice("Going to "+target.getName()+"'s home");
                        player.teleportTo(target.getHome());
                    }
                    else {
                        player.notice(target.getName() + " has no home yet.");
                    }
                }
                else {
                    player.notice("Player "+args[1]+" does not exist.");
                }
            }
        }
    }

}
