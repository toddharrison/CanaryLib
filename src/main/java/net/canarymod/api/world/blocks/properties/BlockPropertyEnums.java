package net.canarymod.api.world.blocks.properties;

/**
 * The catch-all for the enums in Blocks
 *
 * @deprecated BlockPropertyEnums is being replaced by helper classes
 * @author Jason (darkdiplomat)
 */
@Deprecated
public final class BlockPropertyEnums {

    /**
     * Covers Door, Double Plant, Slabs, Stairs, and TrapDoor
     */
    public enum BlockVerticalHalf {
        UPPER,
        LOWER;

        public static BlockVerticalHalf valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Door HingePosition
     */
    public enum DoorHingePosition {
        LEFT,
        RIGHT;

        public static DoorHingePosition valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Bed half
     */
    public enum BedHalf {
        HEAD,
        FOOT;

        public static BedHalf valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Dirt types
     */
    public enum DirtType {
        DIRT,
        COARSE_DIRT,
        PODZOL;

        public static DirtType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * DoublePlantType
     */
    public enum DoublePlantType {
        SUNFLOWER,
        SYRINGA,
        GRASS,
        FERN,
        ROSE,
        PAEONIA;

        public static DoublePlantType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * FlowerPot plant types
     */
    public enum FlowerPotPlanted {
        EMPTY,
        POPPY,
        BLUE_ORCHID,
        ALLIUM,
        HOUSTONIA,
        RED_TULIP,
        ORANGE_TULIP,
        WHITE_TULIP,
        PINK_TULIP,
        OXEYE_DAISY,
        DANDELION,
        OAK_SAPLING,
        SPRUCE_SAPLING,
        BIRCH_SAPLING,
        JUNGLE_SAPLING,
        ACACIA_SAPLING,
        DARK_OAK_SAPLING,
        MUSHROOM_RED,
        MUSHROOM_BROWN,
        DEAD_BUSH,
        FERN,
        CACTUS;

        public static FlowerPotPlanted valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Huge Mushroom parts
     */
    public enum HugeMushroomSection {
        NORTH_WEST,
        NORTH,
        NORTH_EAST,
        WEST,
        CENTER,
        EAST,
        SOUTH_WEST,
        SOUTH,
        SOUTH_EAST,
        STEM,
        ALL_INSIDE,
        ALL_OUTSIDE,
        ALL_STEM;

        public static HugeMushroomSection valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Lever orientation
     */
    public enum LeverOrientation {
        DOWN_X,
        EAST,
        WEST,
        SOUTH,
        NORTH,
        UP_Z,
        UP_X,
        DOWN_Z;

        public static LeverOrientation valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Log Axis position
     */
    public enum LogAxis {
        X,
        Y,
        Z,
        NONE;

        public static LogAxis valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Piston Extension type
     */
    public enum PistonType {
        DEFAULT,
        STICKY;

        public static PistonType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Covers Log, Planks, Leaves, WoodSlab
     */
    public enum TreeType {
        OAK,
        SPRUCE,
        BIRCH,
        JUNGLE,
        ACACIA,
        DARK_OAK;

        public static TreeType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Prismarine types
     */
    public enum PrismarineType {
        ROUGH,
        BRICKS,
        DARK;

        public static PrismarineType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Quartz types
     */
    public enum QuartzType {
        DEFAULT,
        CHISELED,
        LINES_Y,
        LINES_X,
        LINES_Z;

        public static QuartzType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Rail directions
     */
    public enum RailDirection {
        NORTH_SOUTH,
        EAST_WEST,
        ASCENDING_EAST,
        ASCENDING_WEST,
        ASCENDING_NORTH,
        ASCENDING_SOUTH,
        SOUTH_EAST,
        SOUTH_WEST,
        NORTH_WEST,
        NORTH_EAST;

        public static RailDirection valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Sandstone and Red Sandstone
     */
    public enum SandstoneType {
        DEFAULT,
        CHISELED,
        SMOOTH;

        public static SandstoneType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Silverfish block spawn types
     */
    public enum SilverfishBlockType {
        STONE,
        COBBLESTONE,
        STONEBRICK,
        MOSSY_STONEBRICK,
        CRACKED_STONEBRICK,
        CHISELED_STONEBRICK;

        public static SilverfishBlockType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Stair shapes
     */
    public enum StairsShape {
        STRAIGHT,
        INNER_LEFT,
        INNER_RIGHT,
        OUTER_LEFT,
        OUTER_RIGHT;

        public static StairsShape valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Stone types
     */
    public enum StoneType {
        STONE,
        GRANITE,
        GRANITE_SMOOTH,
        DIORITE,
        DIORITE_SMOOTH,
        ANDESITE,
        ANDESITE_SMOOTH;

        public static StoneType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * StoneBricks types
     */
    public enum StoneBrickType {
        DEFAULT,
        MOSSY,
        CRACKED,
        CHISELED;

        public static StoneBrickType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Stone slab types
     */
    public enum StoneSlabType {
        STONE,
        SAND,
        WOOD,
        COBBLESTONE,
        BRICK,
        SMOOTHBRICK,
        NETHERBRICK,
        QUARTZ;

        public static StoneSlabType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * New Stone slab types
     */
    public enum StoneSlabNewType {
        RED_SANDSTONE;

        public static StoneSlabNewType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Wall types
     */
    public enum WallType {
        NORMAL,
        MOSSY;

        public static WallType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Flower types
     */
    public enum FlowerType {
        DANDELION,
        POPPY,
        BLUE_ORCHID,
        ALLIUM,
        HOUSTONIA,
        RED_TULIP,
        ORANGE_TULIP,
        WHITE_TULIP,
        PINK_TULIP,
        OXEYE_DAISY;

        public static FlowerType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Comparator types
     */
    public enum ComparatorMode {
        COMPARE,
        SUBTRACT;

        public static ComparatorMode valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Sand types
     */
    public enum SandType {
        SAND,
        RED_SAND;

        public static SandType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }

    /**
     * Tall grass types
     */
    public enum TallGrassType {
        DEAD_BUSH,
        GRASS,
        FERN;

        public static TallGrassType valueOf(int ordinal) {
            if (ordinal < 0 || ordinal >= values().length) {
                throw new IllegalArgumentException();
            }
            return values()[ordinal];
        }
    }
}
