package net.canarymod.util;

import net.canarymod.api.nbt.BaseTag;

/**
 * Json to/from NBT Utility
 * <p/>
 * Instance of this should be retrieved using {@link net.canarymod.Canary#jsonNBT()}
 *
 * @author Jason Jones (darkdiplomat)
 */
public interface JsonNBTUtility {

    public BaseTag jsonToNBT(String rawJson);

    public String baseTagToJSON(BaseTag baseTag);
}
