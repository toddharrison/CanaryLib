package net.canarymod.util;

import com.mojang.authlib.GameProfile;
import net.canarymod.api.nbt.BaseTag;
import net.canarymod.api.nbt.CompoundTag;

/**
 * Json to/from NBT Utility
 * <p/>
 * Instance of this should be retrieved using {@link net.canarymod.Canary#jsonNBT()}
 *
 * @author Jason Jones (darkdiplomat)
 */
public interface JsonNBTUtility {

    BaseTag jsonToNBT(String rawJson);

    String baseTagToJSON(BaseTag baseTag);

    GameProfile gameProfileFromNBT(CompoundTag tag);

    CompoundTag gameProfileToNBT(GameProfile profile);
}
