package net.canarymod.plugin;

import net.canarymod.Canary;
import net.visualillusionsent.utils.PropertiesFile;

import java.io.File;

/**
 * Describes information about a plugin, including meta information and start/stop/load information.
 * It also contains a refrence to the loaded instance of the plugin, should there be one.
 *
 * @author Pwootage
 */
public class PluginDescriptor {
    private PropertiesFile canaryInf;
    private String path;
    private String name;
    private String version;
    private String author;
    private String language;
    private Plugin plugin;
    private IPluginLifecycle pluginLifecycle;

    public PluginDescriptor(String path) {
        this.path = path;
        reloadInf();
    }

    protected void reloadInf() {
        findAndLoadCanaryInf();
        name = canaryInf.getString("name");
        version = canaryInf.getString("version", "UNKNOWN");
        author = canaryInf.getString("author", "UNKNOWN");
        language = canaryInf.getString("language", "java");
    }

    private void findAndLoadCanaryInf() {
        File pluginFile = new File(path);
        if (pluginFile.isFile() && pluginFile.getName().matches(".+\\.(jar|zip)$")) {
            canaryInf = new PropertiesFile(pluginFile.getAbsolutePath(), "Canary.inf");
        } else if (pluginFile.isDirectory()) {
            File confFile = new File(pluginFile, "Canary.inf");
            canaryInf = new PropertiesFile(confFile.getAbsolutePath());
        }
    }

    public PropertiesFile getCanaryInf() {
        return canaryInf;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getVersion() {
        return version;
    }

    public String getPath() {
        return path;
    }

    public String getLanguage() {
        return language;
    }

    public IPluginLifecycle getPluginLifecycle() {
        return pluginLifecycle;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public Plugin getOrLoadPlugin() {
        if (plugin == null) {
            plugin = getPluginLifecycle().load();
        }
        return plugin;
    }
}
