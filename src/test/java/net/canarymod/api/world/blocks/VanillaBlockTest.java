package net.canarymod.api.world.blocks;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * VanillaBlock translations test
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class VanillaBlockTest {

    @Test
    public void testVanillaBlockTranslations() {
        assertEquals(VanillaBlock.AIR, VanillaBlock.enumerate(BlockType.Air));
        assertEquals(VanillaBlock.STONE, VanillaBlock.enumerate(BlockType.Stone));
        assertEquals(VanillaBlock.GRANITE, VanillaBlock.enumerate(BlockType.Granite));
    }
}
