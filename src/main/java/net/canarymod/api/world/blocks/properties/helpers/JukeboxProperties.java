package net.canarymod.api.world.blocks.properties.helpers;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.properties.BlockBooleanProperty;

import static net.canarymod.api.world.blocks.BlockType.Jukebox;

/**
 * Jukebox properties helper
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class JukeboxProperties extends BlockProperties {
    public final static BlockBooleanProperty hasRecord = getInstanceFor(Jukebox, "has_record");

    public static Block applyRecord(Block block, boolean value) {
        return apply(block, hasRecord, value);
    }
}
