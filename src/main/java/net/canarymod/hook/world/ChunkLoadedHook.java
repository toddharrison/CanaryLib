package net.canarymod.hook.world;

import net.canarymod.api.world.Chunk;
import net.canarymod.api.world.World;
import net.canarymod.hook.Hook;

/**
 * Chunk created hook
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public final class ChunkLoadedHook extends Hook {

    private World world;
    private Chunk chunk;
    private boolean newchunk;

    public ChunkLoadedHook(Chunk chunk, World world, boolean newchunk) {
        this.world = world;
        this.chunk = chunk;
        this.newchunk = newchunk;
    }

    /**
     * Gets the {@link Chunk}
     *
     * @return The {@link Chunk}.
     */
    public Chunk getChunk() {
        return chunk;
    }

    /**
     * Gets the world this chunk is a part of.
     *
     * @return the world
     */
    public World getWorld() {
        return world;
    }

    /**
     * Gets whether this chunk was a freshly created chunk
     *
     * @return {@code true} if new; {@code false} otherwise
     */
    public boolean isNew() {
        return newchunk;
    }

    @Override
    public final String toString() {
        return String.format("%s[World=%s, Chunk=%s, New=%b]", getHookName(), world, chunk, newchunk);
    }
}
