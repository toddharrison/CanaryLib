package net.canarymod.plugin.lifecycle;

import net.canarymod.plugin.IPluginLifecycle;

/**
 * Created by Christopher on 9/6/2014.
 */
public class PluginLifecycleFactory {
    public static IPluginLifecycle getLifecycleForLanguage(String language) {
        if (language.equals("java")) {
            //TODO
        }
        return null;
    }
}
