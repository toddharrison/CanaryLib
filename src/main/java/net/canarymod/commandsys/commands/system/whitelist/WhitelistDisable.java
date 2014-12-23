package net.canarymod.commandsys.commands.system.whitelist;

import net.canarymod.Translator;
import net.canarymod.chat.ChatFormat;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.NativeCommand;
import net.canarymod.config.Configuration;
import net.visualillusionsent.utils.PropertiesFile;

/**
 * Whitelist Disable (Off) Command
 *
 * @author Jason Jones (darkdiplomat)
 */
public class WhitelistDisable implements NativeCommand {

    @Override
    public void execute(MessageReceiver receiver, String[] args) {
        PropertiesFile srvcfg = Configuration.getServerConfig().getFile();
        srvcfg.setBoolean("whitelist-enabled", false);
        srvcfg.save();
        receiver.message(ChatFormat.YELLOW + Translator.localTranslate("whitelist disabled", receiver.getLocale()));
    }
}
