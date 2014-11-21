package net.canarymod.api.world.blocks;

import net.canarymod.api.DyeColor;
import net.canarymod.api.inventory.helper.BannerPattern;

import java.util.List;

/**
 * Banner TileEntity wrapper
 *
 * @author Jason Jones (darkdiplomat)
 */
public interface Banner extends TileEntity {

    /**
     * Sets the base color of the banner
     *
     * @param color
     *         the {@link net.canarymod.api.DyeColor} to set
     */
    void setColor(DyeColor color);

    /**
     * Gets the base color of the banner
     *
     * @return the {@link net.canarymod.api.DyeColor} of the banner
     */
    DyeColor getColor();

    /**
     * Gets a list of applied {@link BannerPattern}(s)
     *
     * @return patterns list
     */
    List<BannerPattern> getPatterns();

    /**
     * Sets the list of applied {@link BannerPattern}(s)
     *
     * @param patterns
     *         the patterns to apply
     */
    void setPatterns(List<BannerPattern> patterns);

    /**
     * Attempts to add a {@link net.canarymod.api.inventory.helper.BannerPattern} to the Banner
     *
     * @param pattern
     *         the pattern to add
     *
     * @return {@code true} if successful
     */
    boolean addPattern(BannerPattern pattern);

    /**
     * Attempts to remove a {@link net.canarymod.api.inventory.helper.BannerPattern} from the Banner
     *
     * @param pattern
     *         the pattern to remove
     *
     * @return {@code true} if successful
     */
    boolean removePattern(BannerPattern pattern);
}
