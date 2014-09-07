package net.canarymod.plugin.lifecycle;

import net.canarymod.exceptions.InvalidPluginException;
import net.canarymod.plugin.IPluginLifecycle;
import net.canarymod.plugin.PluginDescriptor;

/**
 * Created by Christopher on 9/6/2014.
 */
public class PluginLifecycleFactory {

    public static IPluginLifecycle createLifecycle(PluginDescriptor pluginDescriptor) throws InvalidPluginException {
        String language = pluginDescriptor.getLanguage();
        if (language.equals("java")) {
            return new JavaPluginLifecycle(pluginDescriptor);
        }
        throw new InvalidPluginException("Unknown plugin language: " + language);
    }
}
