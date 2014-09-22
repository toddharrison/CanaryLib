package net.canarymod.hook.world;

import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;

/**
 * Called when a block "grows"
 *
 * @author Jason Jones (darkdiplomat)
 */
public class BlockGrowHook extends CancelableHook {
    private final Block original, growth;

    public BlockGrowHook(Block original, Block growth) {
        this.original = original;
        this.growth = growth;
    }

    public Block getOriginal() {
        return original;
    }

    public Block getGrowth() {
        return growth;
    }

    public String toString() {
        return String.format("%s[Original: %s, Growth: %s]", getHookName(), original, growth);
    }
}
