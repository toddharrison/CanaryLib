package net.canarymod.commandsys.commands.system.whitelist;

import net.canarymod.Translator;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.PropertiesFile;

/**
 * Whitelist Enable (On) Command
 *
 * @author Jason Jones (darkdiplomat)
 */
public class WhitelistEnable implements NativeCommand {

    @Override
    public void execute(MessageReceiver receiver, String[] args) {
        PropertiesFile srvcfg = Configuration.getServerConfig().getFile();
        srvcfg.setBoolean("whitelist-enabled", true);
        srvcfg.save();
        receiver.message(ChatFormat.YELLOW + Translator.localTranslate("whitelist enabled", receiver.getLocale()));
    }
}
