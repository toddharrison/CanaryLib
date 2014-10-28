package net.canarymod;

import com.google.common.collect.ArrayListMultimap;
import net.canarymod.plugin.Plugin;

/**
 * Canary Class Watcher
 * <p/>
 * Designed to allow plugins to share classes between each other.<br>
 * NOTE: If a class isn't loaded, this will load the class from the first ClassLoader that has the class.<br>
 * It could be an incorrect class or improperly initialized. Plugin devs should program with this in mind.
 *
 * @author Jason (darkdiplomat)
 */
final class CanaryClassWatcher {
    private final ArrayListMultimap<CanaryClassLoader, Class<?>> loadedClasses = ArrayListMultimap.create();

    /**
     * Finds a loaded {@link Class} from any of the {@link Plugin}'s {@link CanaryClassLoader}
     *
     * @param looking
     *         the {@link net.canarymod.CanaryClassLoader} looking for a {@link java.lang.Class}
     * @param name
     *         the name of the {@link Class}
     *
     * @return the {@link Class} if found; {@code null} otherwise
     */
    synchronized final Class<?> findLoadedClass(CanaryClassLoader looking, String name) {
        for (Class<?> cls : loadedClasses.values()) {
            if (cls.getName().equals(name)) {
                return cls;
            }
        }
        return loadClass(looking, name); // ClassNotFound, attempt to load it
    }

    /**
     * Loads a {@link Class} from the first loader that contains the class
     *
     * @param looking
     *         the {@link net.canarymod.CanaryClassLoader} looking for a {@link java.lang.Class}
     * @param name
     *         the name of the {@link Class} to be loaded
     *
     * @return the {@link Class} if found; {@code null} otherwise
     */
    private synchronized Class<?> loadClass(CanaryClassLoader looking, String name) {
        String nameTemp = name.replace('.', '/').concat(".class");
        for (CanaryClassLoader loader : loadedClasses.keySet()) {
            if (loader == looking) {
                continue; // We already know that this loader has failed
            }
            if (loader.getResource(nameTemp) != null) {
                try {
                    Class<?> cls = loader.loadClass(name);
                    addClass(loader, cls);
                    return cls;
                }
                catch (ClassNotFoundException e) {
                    // Realistically this shouldn't happen here since we pre-checked if the jar has the resource
                    // But then again it is Java
                }
            }
        }
        return null;
    }

    /**
     * Adds a {@link Class} to the list of loaded classes
     *
     * @param loader
     *         the {@link CanaryClassLoader} the {@link Class} is from
     * @param cls
     *         the {@link Class} to be added
     */
    synchronized final void addClass(CanaryClassLoader loader, Class<?> cls) {
        loadedClasses.put(loader, cls);
    }

    /**
     * Removes a {@link CanaryClassLoader} and all associated {@link Class}es
     *
     * @param loader
     *         the {@link CanaryClassLoader} to remove
     */
    synchronized final void removeLoader(CanaryClassLoader loader) {
        if (loadedClasses.containsKey(loader)) {
            loadedClasses.asMap().remove(loader);
        }
    }
}
