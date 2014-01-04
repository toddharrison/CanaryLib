package net.canarymod.logger;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.HashMap;
import java.util.logging.Level;

/**
 * Custom Logger Level register
 *
 * @author Jason (darkdiplomat)
 * @author Larry1123
 * Updated to make use of log4j
 */
public final class CustomLevel implements Marker {

    private static final HashMap<String, CustomLevel> registered = new HashMap<String, CustomLevel>();

    private final Marker marker;

    private CustomLevel(String name) {
        marker = MarkerManager.getMarker(name);
    }

    /**
     * Registers a new CustomLevel is one does not already exist with the given name<br>
     * intValue is auto-generated starting at 1001 and going up.
     *
     * @param name
     *         the name of the CustomLevel to create
     *
     * @return the new CustomLevel
     *
     * @throws NullPointerException
     *         the name is {@code null}
     * @throws CustomLevelExistsException
     *         if a CustomLevel with the given name is already registered
     */
    public static final CustomLevel registerLevel(String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        if (registered.containsKey(name)) {
            throw new CustomLevelExistsException(name);
        }
        CustomLevel toRet = new CustomLevel(name);
        registered.put(name, toRet);
        return toRet;
    }

    /**
     * Unregisters a CustomLevel of the given name
     *
     * @param name
     *         the name of the CustomLevel to unregister
     *
     * @return the removed CustomLevel if exists; {@code null} if it does not exist
     */
    public static final CustomLevel unregisterLevel(String name) {
        if (registered.containsKey(name)) {
            return registered.remove(name);
        }
        return null;
    }

    /**
     * Gets a CustomLevel of the given name if it exists
     *
     * @param name
     *         the name of the CustomLevel to get
     *
     * @return the CustomLevel if found; {@code null} if it does not exist
     */
    public static final CustomLevel getLevel(String name) {
        if (registered.containsKey(name)) {
            return registered.get(name);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return marker.getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Marker getParent() {
        return marker.getParent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInstanceOf(Marker m) {
        return marker.isInstanceOf(m);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isInstanceOf(String name) {
        return marker.isInstanceOf(name);
    }

}
