package net.canarymod.api.world;

import java.util.HashMap;

/**
 * Static class of WorldTypes
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public class WorldType {
    public static final WorldType DEFAULT = new WorldType("DEFAULT");
    public static final WorldType SUPERFLAT = new WorldType("FLAT");
    public static final WorldType DEFAULT_1_1 = new WorldType("DEFAULT_1_1");
    public static final WorldType LARGEBIOMES = new WorldType("LARGEBIOMES");
    public static final WorldType AMPLIFIED = new WorldType("AMPLIFIED");
    public static final WorldType CUSTOMIZED = new WorldType("CUSTOMIZED");
    public static final WorldType DEBUG = new WorldType("debug_all_block_states");

    private String string;
    private static HashMap<String, WorldType> types;

    private WorldType(String string) {
        if (types == null) {
            types = new HashMap<String, WorldType>();
        }
        this.string = string;
        types.put(string, this);
    }

    /**
     * Register a new WorldType.
     *
     * @param name
     *
     * @return
     */
    public static boolean registerWorldType(String name) {
        if (types.containsKey(name)) {
            return false;
        }
        types.put(name, new WorldType(name));
        return true;
    }

    @Override
    public String toString() {
        return string;
    }

    /**
     * get a worldType from string.
     * This may return null if the requested WorldType does not exist!
     *
     * @param string
     *
     * @return
     */
    public static WorldType fromString(String string) {
        for (String n : types.keySet()) {
            if (n.equals(string.toUpperCase())) {
                return types.get(n);
            }
        }
        if (string.toUpperCase().equals("DEBUG")) { // That one weird one...
            return DEBUG;
        }
        return null;
    }

    /**
     * Gets an array of WorldType names
     *
     * @return WorldType names
     */
    public static String[] getTypeNames() {
        return types.keySet().toArray(new String[types.size()]);
    }
}
