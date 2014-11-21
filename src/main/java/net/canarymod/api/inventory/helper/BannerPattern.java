package net.canarymod.api.inventory.helper;

import net.canarymod.Canary;
import net.canarymod.api.DyeColor;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.nbt.NBTTagType;

/**
 * Container helper for Banner pattern shape and colors
 *
 * @author Jason Jones (darkdiplomat)
 */
public final class BannerPattern {
    private final DyeColor color;
    private final BannerPatterns pattern;

    public BannerPattern(DyeColor color, BannerPatterns pattern) {
        this.color = color;
        this.pattern = pattern;
    }

    public DyeColor getColor() {
        return color;
    }

    public BannerPatterns getPattern() {
        return pattern;
    }

    public CompoundTag asCompoundTag() {
        CompoundTag toRet = Canary.factory().getNBTFactory().newCompoundTag("Pattern");
        toRet.put("color", color.getDyeColorCode());
        toRet.put("pattern", pattern.name().toLowerCase());
        return toRet;
    }

    /**
     * Creates a BannerPattern from a {@link CompoundTag}
     *
     * @param tag
     *         the tag to grab data from, the tag should be the one from within the Patterns list tag
     *
     * @return a new BannerPattern
     *
     * @throws java.lang.IllegalArgumentException
     *         if the tag given does not contain the proper Banner data
     */
    public static BannerPattern fromCompoundTag(CompoundTag tag) {
        if (tag.containsKey("color", NBTTagType.ANY_NUMERIC) && tag.containsKey("pattern", NBTTagType.STRING)) {
            return new BannerPattern(DyeColor.fromDyeColorCode(tag.getInt("Color")), BannerPatterns.fromString(tag.getString("Pattern")));
        }
        throw new IllegalArgumentException("Tag does not contain Banner data");
    }

    public final boolean equals(Object obj) {
        return obj instanceof BannerPattern && ((BannerPattern)obj).color.equals(this.color) && ((BannerPattern)obj).pattern.equals(this.pattern);
    }
}
