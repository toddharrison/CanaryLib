package net.canarymod;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.position.Location;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator wrapper for a LineTracer
 */
public class BlockIterator implements Iterator<Block> {
    private final LineTracer tracer;
    private boolean alreadyAdvanced;

    /**
     * Constructor requiring player, uses default values
     *
     * @param in_player
     *         the {@link Player} to check Line of Sight for
     */
    public BlockIterator(Player in_player) {
        this.tracer = new LineTracer(in_player);
    }

    /**
     * Constructor requiring location, uses default values
     *
     * @param in_location
     *         the {@link Location} to check Line of Sight for
     */
    public BlockIterator(Location in_location) {
        this.tracer = new LineTracer(in_location);
    }

    /**
     * Constructor requiring player, max range, and a stepping value
     *
     * @param in_player
     *         the {@link Player} to check Line of Sight for
     * @param in_range
     *         the maximum range to check
     * @param in_step
     *         the stepping value, the amount Y to increase/decrease the further away the checks get
     */
    public BlockIterator(Player in_player, int in_range, double in_step) {
        this.tracer = new LineTracer(in_player, in_range, in_step);
    }

    /**
     * Constructor requiring location, max range, and a stepping value
     *
     * @param in_location
     *         the {@link Location} to check Line of Sight for
     * @param in_range
     *         the maximum range to check
     * @param in_step
     *         the stepping value, the amount Y to increase/decrease the further away the checks get
     */
    public BlockIterator(Location in_location, int in_range, double in_step) {
        this.tracer = new LineTracer(in_location, in_range, in_step);
    }

    /**
     * Creats a BlockIterator from a {@link net.canarymod.LineTracer}
     *
     * @param tracer
     *         the LineTracer to use with this BlockIterator
     */
    public BlockIterator(LineTracer tracer) {
        this.tracer = tracer;
    }

    /**
     * Checks if there are more {@link net.canarymod.api.world.blocks.Block}s in line
     *
     * @return {@code true} if the is a next block
     */
    @Override
    public boolean hasNext() {
        if (alreadyAdvanced) { 
            /* we've already done this check, so check the curent LineTracer block */
            return tracer.getCurBlock() != null;
        }
        alreadyAdvanced = true; // Need to track if we have advanced or not
        return tracer.getNextBlock() != null;
    }

    /**
     * Gets the next {@link net.canarymod.api.world.blocks.Block} in line
     *
     * @return next block
     *
     * @throws NoSuchElementException
     *         if there are no more blocks
     */
    @Override
    public Block next() {
        if (alreadyAdvanced) { // If hasNext was checked, then we need to check the current block
            alreadyAdvanced = false; //We are no longer advanced
            if (tracer.getCurBlock() == null) {
                throw new NoSuchElementException("No more blocks");
            }
            return tracer.getCurBlock();
        }
        else if (tracer.getNextBlock() == null) {
            throw new NoSuchElementException("No more blocks");
        }
        return tracer.getNextBlock();
    }

    /**
     * Sets the current block to Air, effectively removing it from the world
     */
    @Override
    public void remove() {
        tracer.getCurBlock().setType(BlockType.Air);
    }
}
