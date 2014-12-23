package net.canarymod.plugin.lifecycle;

import com.google.common.collect.Maps;
import net.canarymod.Canary;
import net.canarymod.exceptions.InvalidPluginException;
import net.canarymod.plugin.PluginDescriptor;
import net.canarymod.plugin.PluginLifecycle;

import java.util.HashMap;

/**
 * Factory to create {@link net.canarymod.plugin.PluginLifecycle} instances based on Canary.inf
 * (inside a {@link net.canarymod.plugin.PluginDescriptor}
 *
 * @author Pwootage
 * @author Jason (darkdiplomat)
 */
public class PluginLifecycleFactory {
    private static final HashMap<String, Class<? extends PluginLifecycle>> aPluginsLife = Maps.newHashMap();

    /**
     * Construct a plugin lifecycle for a descriptor
     *
     * @param pluginDescriptor
     *         Descriptor of plugin
     *
     * @return Lifecycle for descriptor
     *
     * @throws InvalidPluginException
     *         If this type of plugin is not known how to handle
     */
    public static PluginLifecycle createLifecycle(PluginDescriptor pluginDescriptor) throws InvalidPluginException {
        String language = pluginDescriptor.getLanguage();
        if (!aPluginsLife.containsKey(language)) {
            throw new InvalidPluginException("Unknown plugin language: " + language);
        }
        try {
            return aPluginsLife.get(language).getConstructor(PluginDescriptor.class).newInstance(pluginDescriptor);
        }
        catch (Exception ex) {
            throw new InvalidPluginException("Error while creating new PluginLifeCycle for Language: " + language);
        }
    }

    /**
     * Registers a new PluginLifecycle class
     *
     * @param language
     *         the language of the PluginLifecycle
     * @param lifecycle
     *         the lifecycle class
     *
     * @throws InvalidPluginLifecycleException
     *         if a lifecycle if improper or already registered
     */
    public static void registerLifecycle(String language, Class<? extends PluginLifecycle> lifecycle) throws InvalidPluginLifecycleException {
        if (language == null || language.trim().isEmpty()) {
            throw new InvalidPluginLifecycleException("Language cannot be set to null and cannot be empty");
        }
        if (lifecycle == null) {
            throw new InvalidPluginLifecycleException("LifeCycle class cannot be null");
        }
        if (aPluginsLife.containsKey(language)) {
            throw new InvalidPluginLifecycleException("Language for name '" + language + "' is already registered.");
        }
        Canary.log.debug("Registering new PluginLifeCycle language with name: " + language);
        aPluginsLife.put(language, lifecycle);
    }
}
