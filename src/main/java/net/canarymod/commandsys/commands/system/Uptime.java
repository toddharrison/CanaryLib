package net.canarymod.commandsys.commands.system;

import net.canarymod.ToolBox;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.visualillusionsent.utils.DateUtils;

import java.lang.management.ManagementFactory;
import java.text.MessageFormat;
import java.util.TimeZone;

/**
 * Server Uptime Command
 *
 * @author Jason (darkdiplomat)
 */
public class Uptime implements NativeCommand {

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {
        int offset = TimeZone.getDefault().getRawOffset();
        caller.notice(ChatFormat.GOLD + "Server Start: " + ChatFormat.WHITE + DateUtils.longToDateTime(ManagementFactory.getRuntimeMXBean().getStartTime()) + MessageFormat.format(" GMT{0,number,00}:{1,number,00}", offset / (60 * 60 * 1000), (offset / (60 * 1000)) % 60));
        caller.notice(ChatFormat.GOLD + "Time Alive: " + ChatFormat.WHITE + ToolBox.getTimeUntil(ManagementFactory.getRuntimeMXBean().getUptime() / 1000));
    }
}
