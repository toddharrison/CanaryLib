package net.canarymod.api.world.blocks;

import com.google.common.collect.HashBiMap;

/**
 * An enumeration of "vanilla" Minecraft blocks
 *
 * @author Jason Jones (darkdiplomat)
 */
public enum VanillaBlock {

    AIR("air"),
    STONE("stone:0"),
    GRANITE("stone:1"),
    POLISHEDGRANITE("stone:2"),
    DIORITE("stone:3"),
    POLISHEDDIORITE("stone:4"),
    ANDESITE("stone:5"),
    POLISHEDANDESITE("stone:6"),
    GRASS("grass"),
    DIRT("dirt:0"),
    COARSEDIRT("dirt:1"),
    PODZOL("dirt:2"),
    COBBLE("cobblestone"),
    OAKPLANKS("planks:0"),
    SPRUCEPLANKS("planks:1"),
    BIRCHPLANKS("planks:2"),
    JUNGLEPLANKS("planks:3"),
    ACACIAPLANKS("planks:4"),
    DARKOAKPLANKS("planks:5"),
    OAKSAPLING("sapling:0"),
    SPRUCESAPLING("sapling:1"),
    BIRCHSAPLING("sapling:2"),
    JUNGLESAPLING("sapling:3"),
    ACACIASAPLING("sapling:4"),
    DARKOAKSAPLING("sapling:5"),
    BEDROCK("bedrock"),
    FLOWINGWATER("flowing_water"),
    WATER("water"),
    FLOWINGLAVA("flowing_lava"),
    LAVA("lava"),
    SAND("sand:0"),
    REDSAND("sand:1"),
    GRAVEL("gravel"),
    GOLDORE("gold_ore"),
    IRONORE("iron_ore"),
    COALORE("coal_ore"),
    OAKLOG("log:0"),
    SPRUCELOG("log:1"),
    BIRCHLOG("log:2"),
    JUNGLELOG("log:3"),
    OAKLEAVES("leaves:0"),
    SPRUCELEAVES("leaves:1"),
    BIRCHLEAVES("leaves:2"),
    JUNGLELEAVES("leaves:3"),
    SPONGE("sponge"),
    GLASS("glass"),
    LAPISORE("lapis_ore"),
    LAPISBLOCK("lapis_block"),
    DISPENSER("dispenser"),
    SANDSTONE("sandstone:0"),
    SANDSTONEORNATE("sandstone:1"),
    SANDSTONESMOOTH("sandstone:2"),
    NOTEBLOCK("noteblock"),
    BED("bed"),
    POWEREDRAIL("golden_rail"),
    DETECTORRAIL("detector_rail"),
    STICKYPISTON("sticky_piston"),
    WEB("web"),
    SHRUB("tallgrass:0"), // Dead Bush?
    TALLGRASS("tallgrass:1"),
    FERN("tallgrass:2"),
    DEADBUSH("deadbush"),
    PISTON("piston"),
    PISTONHEAD("piston_head"),
    WHITEWOOL("wool:0"),
    ORANGEWOOL("wool:1"),
    MAGENTAWOOL("wool:2"),
    LIGHTBLUEWOOL("wool:3"),
    YELLOWOOL("wool:4"),
    LIMEWOOL("wool:5"),
    PINKWOOL("wool:6"),
    GRAYWOOL("wool:7"),
    LIGHTGRAYWOOL("wool:8"), // Internally its labeled "silver" but thats not really common naming
    CYANWOOL("wool:9"),
    PURPLEWOOL("wool:10"),
    BLUEWOOL("wool:11"),
    BROWNWOOL("wool:12"),
    GREENWOOL("wool:13"),
    REDWOOL("wool:14"),
    BLACKWOOL("wool:15"),
    PISTONEXTENSION("piston_extension"),
    DANDELION("yellow_flower"),
    POPPY("red_flower:0"),
    BLUEORCHID("red_flower:1"),
    ALLIUM("red_flower:2"),
    AZUREBLUET("red_flower:3"),
    REDTULIP("red_flower:4"),
    ORANGETULIP("red_flower:5"),
    WHITETULIP("red_flower:6"),
    PINKTULIP("red_flower:7"),
    OXEYEDAISY("red_flower:8"),
    BROWNMUSHROOM("brown_mushroom"),
    REDMUSHROOM("red_mushroom"),
    GOLDBLOCK("gold_block"),
    IRONBLOCK("iron_block"),
    DOUBLESTONESLAB("double_stone_slab:0"),
    DOUBLESANDSTONESLAB("double_stone_slab:1"),
    DOUBLEWOODSLAB("double_stone_slab:2"),
    DOUBLECOBBLESLAB("double_stone_slab:3"),
    DOUBLEBRICKSLAB("double_stone_slab:4"),
    DOUBLESTONEBRICKSLAB("double_stone_slab:5"),
    DOUBLENETHERBRICKSLAB("double_stone_slab:6"),
    DOUBLEQUARTZSLAB("double_stone_slab:7"),
    STONESLAB("stone_slab:0"),
    SANDSTONESLAB("stone_slab:1"),
    WOODSLAB("stone_slab:2"),
    COBBLESLAB("stone_slab:3"),
    BRICKSLAB("stone_slab:4"),
    STONEBRICKSSLAB("stone_slab:5"),
    NETHERBRICKSSLAB("stone_slab:6"),
    QUARTZSLAB("stone_slab:7"),
    BRICKS("brick_block"),
    TNT("tnt"),
    BOOKSHELF("bookshelf"),
    MOSSYCOBBLE("mossy_cobblestone"),
    OBSIDIAN("obsidian"),
    TORCH("torch"),
    FIRE("fire"),
    MOBSPAWNER("mob_spawner"),
    OAKSTAIRS("oak_stairs"),
    CHEST("chest"),
    REDSTONEWIRE("redstone_wire"),
    DIAMONDORE("diamond_ore"),
    DIAMONDBLOCK("diamond_block"),
    WORKBENCH("crafting_table"),
    WHEAT("wheat"),
    FARMLAND("farmland"),
    FURNACE("furnace"),
    LITFURNACE("lit_furnace"),
    STANDINGSIGN("standing_sign"),
    WOODENDOOR("wooden_door"),
    LADDER("ladder"),
    RAIL("rail"),
    STONESTAIRS("stone_stairs"),
    WALLSIGN("wall_sign"),
    LEVER("lever"),
    STONEPRESSUREPLATE("stone_pressure_plate"),
    IRONDOOR("iron_door"),
    WOODENPRESSUREPLATE("wooden_pressure_plate"),
    REDSTONEORE("redstone_ore"),
    LITREDSTONEORE("lit_redstone_ore"),
    UNLITREDSTONETORCH("unlite_redstone_torch"),
    REDSTONETORCH("redstone_torch"),
    STONEBUTTON("stone_button"),
    SNOWLAYER("snow_layer"),
    ICE("ice"),
    SNOW("snow"),
    CACTUS("cactus"),
    CLAY("clay"),
    REED("reeds"),
    JUKEBOX("jukebox"),
    FENCE("fence"),
    PUMPKIN("pumpkin"),
    NETHERRACK("netherrack"),
    SOULSAND("soul_sand"),
    GLOWSTONE("glowstone"),
    PORTAL("portal"),
    JACKOLANTERN("lit_pumpkin"),
    CAKE("cake"),
    REDSTONEREPEATEROFF("unpowered_repeater"),
    REDSTONEREPEATERON("powered_repeater"),
    WHITESTAINEDGLASS("stained_glass:0"),
    ORANGESTAINEDGLASS("stained_glass:1"),
    MAGENTASTAINEDGLASS("stained_glass:2"),
    LIGHTBLUESTAINEDGLASS("stained_glass:3"),
    YELLOWSTAINEDGLASS("stained_glass:4"),
    LIMESTAINEDGLASS("stained_glass:5"),
    PINKSTAINEDGLASS("stained_glass:6"),
    GRAYSTAINEDGLASS("stained_glass:7"),
    LIGHTGRAYSTAINEDGLASS("stained_glass:8"),
    CYANSTAINEDGLASS("stained_glass:9"),
    PURPLESTAINEDGLASS("stained_glass:10"),
    BLUESTAINEDGLASS("stained_glass:11"),
    BROWNSTAINEDGLASS("stained_glass:12"),
    GREENSTAINEDGLASS("stained_glass:13"),
    REDSTAINEDGLASS("stained_glass:14"),
    BLACKSTAINEDGLASS("stained_glass:15"),
    TRAPDOOR("trapdoor"),
    STONEMONSTEREGG("monster_egg:0"),
    COBBLEMONSTEREGG("monster_egg:1"),
    STONEBRICKMONSTEREGG("monster_egg:2"),
    MOSSYSTONEBRICKMONSTEREGG("monster_egg:3"),
    CRACKEDSTONEBRICKMONSTEREGG("monster_egg:4"),
    ORNATESTONEBRICKMONSTEREGG("monster_egg:5"),
    STONEBRICK("stonebrick:0"),
    MOSSYSTONEBRICK("stonebrick:1"),
    CRACKEDSTONEBRICK("stonebrick:2"),
    ORNATESTONEBRICK("stonebrick:3"),
    HUGEBROWNMUSHROOM("brown_mushroom_block"),
    HUGEREDMUSHROOM("red_mushroom_block"),
    IRONBARS("iron_bars"),
    GLASSPANE("glass_pane"),
    MELON("melon_block"),
    PUMPKINSTEM("pumpkin_stem"),
    MELONSTEM("melon_stem"),
    VINE("vine"),
    FENCEGATE("fence_gate"),
    BRICKSTAIRS("brick_stairs"),
    STONEBRICKSTAIRS("stone_brick_stairs"),
    MYCELIUM("mycelium"),
    LILYPAD("waterlily"),
    NETHERBRICK("nether_brick"),
    NETHERBRICKFENCE("nether_brick_fence"),
    NETHERBRICKSTAIRS("nether_brick_stairs"),
    NETHERWART("nether_wart"),
    ENCHANTMENTTABLE("enchanting_table"),
    BREWINGSTAND("brewing_stand"),
    CAULDRON("cauldron"),
    ENDPORTAL("end_portal"),
    ENDPORTALFRAME("end_portal_frame"),
    ENDSTONE("end_stone"),
    ENDERDRAGONEGG("dragon_egg"),

    /*
    public static final BlockType RedstoneLampOff = new BlockType(123, 0, "minecraft:redstone_lamp");
    public static final BlockType RedstoneLampOn = new BlockType(124, 0, "minecraft:lit_redstone_lamp");
    public static final BlockType DoubleOakWoodSlab = new BlockType(125, 0, "minecraft:double_wooden_slab");
    public static final BlockType DoubleSpruceWoodSlab = new BlockType(125, 1, "minecraft:double_wooden_slab");
    public static final BlockType DoubleBirchWoodSlab = new BlockType(125, 2, "minecraft:double_wooden_slab");
    public static final BlockType DoubleJungleWoodSlab = new BlockType(125, 3, "minecraft:double_wooden_slab");
    public static final BlockType DoubleAcaciaWoodSlab = new BlockType(125, 4, "minecraft:double_wooden_slab");
    public static final BlockType DoubleDarkOakWoodSlab = new BlockType(125, 5, "minecraft:double_wooden_slab");
    public static final BlockType OakWoodSlab = new BlockType(126, 0, "minecraft:wooden_slab");
    public static final BlockType SpruceWoodSlab = new BlockType(126, 1, "minecraft:wooden_slab");
    public static final BlockType BirchWoodSlab = new BlockType(126, 2, "minecraft:wooden_slab");
    public static final BlockType JungleWoodSlab = new BlockType(126, 3, "minecraft:wooden_slab");
    public static final BlockType AcaciaWoodSlab = new BlockType(126, 4, "minecraft:wooden_slab");
    public static final BlockType DarkOakWoodSlab = new BlockType(126, 5, "minecraft:wooden_slab");
    public static final BlockType CocoaPlant = new BlockType(127, 0, "minecraft:cocoa");
    public static final BlockType SandstoneStair = new BlockType(128, 0, "minecraft:sandstone_stairs");
    public static final BlockType EmeraldOre = new BlockType(129, 0, "minecraft:emerald_ore");
    public static final BlockType EnderChest = new BlockType(130, 0, "minecraft:ender_chest");
    public static final BlockType TripwireHook = new BlockType(131, 0, "minecraft:tripwire_hook");
    public static final BlockType Tripwire = new BlockType(132, 0, "minecraft:tripwire");
    public static final BlockType EmeraldBlock = new BlockType(133, 0, "minecraft:emerald_block");
    public static final BlockType PineWoodStair = new BlockType(134, 0, "minecraft:spruce_stairs");
    public static final BlockType BirchWoodStair = new BlockType(135, 0, "minecraft:birch_stairs");
    public static final BlockType JungleWoodStair = new BlockType(136, 0, "minecraft:jungle_stairs");
    public static final BlockType CommandBlock = new BlockType(137, 0, "minecraft:command_block");
    public static final BlockType Beacon = new BlockType(138, 0, "minecraft:beacon");
    public static final BlockType CobblestoneWall = new BlockType(139, 0, "minecraft:cobblestone_wall");
    public static final BlockType MossyCobbleWall = new BlockType(139, 1, "minecraft:cobblestone_wall");
    public static final BlockType Flowerpot = new BlockType(140, 0, "minecraft:flower_pot");
    public static final BlockType Carrots = new BlockType(141, 0, "minecraft:carrots");
    public static final BlockType Potatoes = new BlockType(142, 0, "minecraft:potatoes");
    public static final BlockType WoodenButton = new BlockType(143, 0, "minecraft:wooden_button");
    public static final BlockType SkeletonHead = new BlockType(144, 0, "minecraft:skull");
    public static final BlockType WitherSkeletonHead = new BlockType(144, 1, "minecraft:skull");
    public static final BlockType ZombieHead = new BlockType(144, 2, "minecraft:skull");
    public static final BlockType HumanHead = new BlockType(144, 3, "minecraft:skull");
    public static final BlockType CreeperHead = new BlockType(144, 4, "minecraft:skull");
    public static final BlockType Anvil = new BlockType(145, 0, "minecraft:anvil");
    public static final BlockType TrappedChest = new BlockType(146, 0, "minecraft:trapped_chest");
    public static final BlockType LightWeightedPressurePlate = new BlockType(147, 0, "minecraft:light_weighted_pressure_plate");
    public static final BlockType HeavyWeightedPressurePlate = new BlockType(148, 0, "minecraft:heavy_weighted_pressure_plate");
    public static final BlockType RedstoneComparator = new BlockType(149, 0, "minecraft:unpowered_comparator");
    public static final BlockType RedstoneComparatorPowered = new BlockType(150, 0, "minecraft:powered_comparator");
    public static final BlockType DaylightSensor = new BlockType(151, 0, "minecraft:daylight_detector");
    public static final BlockType RedstoneBlock = new BlockType(152, 0, "minecraft:redstone_block");
    public static final BlockType NetherQuartzOre = new BlockType(153, 0, "minecraft:quartz_ore");
    public static final BlockType Hopper = new BlockType(154, 0, "minecraft:hopper");
    public static final BlockType QuartzBlock = new BlockType(155, 0, "minecraft:quartz_block");
    public static final BlockType OrnateQuartzBlock = new BlockType(155, 1, "minecraft:quartz_block");
    public static final BlockType QuartzPillarVertical = new BlockType(155, 2, "minecraft:quartz_block");
    public static final BlockType QuartzPillarHorizontal = new BlockType(155, 3, "minecraft:quartz_block");
    public static final BlockType QuartzPillarCap = new BlockType(155, 4, "minecraft:quartz_block");
    public static final BlockType QuartzStairs = new BlockType(156, 0, "minecraft:quartz_stairs");
    public static final BlockType ActivatorRail = new BlockType(157, 0, "minecraft:activator_rail");
    public static final BlockType Dropper = new BlockType(158, 0, "minecraft:dropper");
    public static final BlockType WhiteStainedClay = new BlockType(159, 0, "minecraft:stained_hardened_clay");
    public static final BlockType OrangeStainedClay = new BlockType(159, 1, "minecraft:stained_hardened_clay");
    public static final BlockType MagentaStainedClay = new BlockType(159, 2, "minecraft:stained_hardened_clay");
    public static final BlockType LightBlueStainedClay = new BlockType(159, 3, "minecraft:stained_hardened_clay");
    public static final BlockType YellowStainedClay = new BlockType(159, 4, "minecraft:stained_hardened_clay");
    public static final BlockType LimeStainedClay = new BlockType(159, 5, "minecraft:stained_hardened_clay");
    public static final BlockType PinkStainedClay = new BlockType(159, 6, "minecraft:stained_hardened_clay");
    public static final BlockType GrayStainedClay = new BlockType(159, 7, "minecraft:stained_hardened_clay");
    public static final BlockType LightGrayStainedClay = new BlockType(159, 8, "minecraft:stained_hardened_clay");
    public static final BlockType CyanStainedClay = new BlockType(159, 9, "minecraft:stained_hardened_clay");
    public static final BlockType PurpleStainedClay = new BlockType(159, 10, "minecraft:stained_hardened_clay");
    public static final BlockType BlueStainedClay = new BlockType(159, 11, "minecraft:stained_hardened_clay");
    public static final BlockType BrownStainedClay = new BlockType(159, 12, "minecraft:stained_hardened_clay");
    public static final BlockType GreenStainedClay = new BlockType(159, 13, "minecraft:stained_hardened_clay");
    public static final BlockType RedStainedClay = new BlockType(159, 14, "minecraft:stained_hardened_clay");
    public static final BlockType BlackStainedClay = new BlockType(159, 15, "minecraft:stained_hardened_clay");
    public static final BlockType WhiteGlassPane = new BlockType(160, 0, "minecraft:stained_glass_pane");
    public static final BlockType OrangeGlassPane = new BlockType(160, 1, "minecraft:stained_glass_pane");
    public static final BlockType MagentaGlassPane = new BlockType(160, 2, "minecraft:stained_glass_pane");
    public static final BlockType LightBlueGlassPane = new BlockType(160, 3, "minecraft:stained_glass_pane");
    public static final BlockType YellowGlassPane = new BlockType(160, 4, "minecraft:stained_glass_pane");
    public static final BlockType LimeGlassPane = new BlockType(160, 5, "minecraft:stained_glass_pane");
    public static final BlockType PinkGlassPane = new BlockType(160, 6, "minecraft:stained_glass_pane");
    public static final BlockType GrayGlassPane = new BlockType(160, 7, "minecraft:stained_glass_pane");
    public static final BlockType LightGrayGlassPane = new BlockType(160, 8, "minecraft:stained_glass_pane");
    public static final BlockType CyanGlassPane = new BlockType(160, 9, "minecraft:stained_glass_pane");
    public static final BlockType PurpleGlassPane = new BlockType(160, 10, "minecraft:stained_glass_pane");
    public static final BlockType BlueGlassPane = new BlockType(160, 11, "minecraft:stained_glass_pane");
    public static final BlockType BrownGlassPane = new BlockType(160, 12, "minecraft:stained_glass_pane");
    public static final BlockType GreenGlassPane = new BlockType(160, 13, "minecraft:stained_glass_pane");
    public static final BlockType RedGlassPane = new BlockType(160, 14, "minecraft:stained_glass_pane");
    public static final BlockType BlackGlassPane = new BlockType(160, 15, "minecraft:stained_glass_pane");
    public static final BlockType AcaciaLeaves = new BlockType(161, 0, "minecraft:leaves2");
    public static final BlockType DarkOakLeaves = new BlockType(161, 1, "minecraft:leaves2");
    public static final BlockType AcaciaLog = new BlockType(162, 0, "minecraft:log2");
    public static final BlockType DarkOakLog = new BlockType(162, 1, "minecraft:log2");
    public static final BlockType AcaciaStairs = new BlockType(163, 0, "minecraft:acacia_stairs");
    public static final BlockType DarkOakStairs = new BlockType(164, 0, "minecraft:dark_oak_stairs");
    public static final BlockType SlimeBlock = new BlockType(165, 0, "minecraft:slime");
    public static final BlockType Barrier = new BlockType(166, 0, "minecraft:barrier");
    public static final BlockType IronTrapDoor = new BlockType(167, 0, "minecraft:iron_trapdoor");
    public static final BlockType Prismarine = new BlockType(168, 0, "minecraft:prismarine");
    public static final BlockType PrismarineBricks = new BlockType(168, 1, "minecraft:prismarine");
    public static final BlockType DarkPrismarine = new BlockType(168, 2, "minecraft:prismarine");
    public static final BlockType SeaLanturn = new BlockType(169, 0, "minecraft:sea_lantern");
    public static final BlockType HayBale = new BlockType(170, 0, "minecraft:hay_block");
    public static final BlockType WhiteCarpet = new BlockType(171, 0, "minecraft:carpet");
    public static final BlockType OrangeCarpet = new BlockType(171, 1, "minecraft:carpet");
    public static final BlockType MagentaCarpet = new BlockType(171, 2, "minecraft:carpet");
    public static final BlockType LightBlueCarpet = new BlockType(171, 3, "minecraft:carpet");
    public static final BlockType YellowCarpet = new BlockType(171, 4, "minecraft:carpet");
    public static final BlockType LimeCarpet = new BlockType(171, 5, "minecraft:carpet");
    public static final BlockType PinkCarpet = new BlockType(171, 6, "minecraft:carpet");
    public static final BlockType GrayCarpet = new BlockType(171, 7, "minecraft:carpet");
    public static final BlockType LightGrayCarpet = new BlockType(171, 8, "minecraft:carpet");
    public static final BlockType CyanCarpet = new BlockType(171, 9, "minecraft:carpet");
    public static final BlockType PurpleCarpet = new BlockType(171, 10, "minecraft:carpet");
    public static final BlockType BlueCarpet = new BlockType(171, 11, "minecraft:carpet");
    public static final BlockType BrownCarpet = new BlockType(171, 12, "minecraft:carpet");
    public static final BlockType GreenCarpet = new BlockType(171, 13, "minecraft:carpet");
    public static final BlockType RedCarpet = new BlockType(171, 14, "minecraft:carpet");
    public static final BlockType BlackCarpet = new BlockType(171, 15, "minecraft:carpet");
    public static final BlockType HardenedClay = new BlockType(172, 0, "minecraft:hardened_clay");
    public static final BlockType CoalBlock = new BlockType(173, 0, "minecraft:coal_block");
    public static final BlockType PackedIce = new BlockType(174, 0, "minecraft:packed_ice");
    public static final BlockType Sunflower = new BlockType(175, 0, "minecraft:double_plant");
    public static final BlockType Lilac = new BlockType(175, 1, "minecraft:double_plant");
    public static final BlockType DoubleGrass = new BlockType(175, 2, "minecraft:double_plant");
    public static final BlockType LargeFern = new BlockType(175, 3, "minecraft:double_plant");
    public static final BlockType RoseBush = new BlockType(175, 4, "minecraft:double_plant");
    public static final BlockType Peony = new BlockType(175, 5, "minecraft:double_plant");
    public static final BlockType StandingBanner = new BlockType(176, 0, "minecraft:standing_banner");
    public static final BlockType WallBanner = new BlockType(177, 0, "minecraft:wall_banner");
    public static final BlockType DaylightSensorInverted = new BlockType(178, 0, "minecraft:daylight_detector_inverted");
    public static final BlockType RedSandstone = new BlockType(179, 0, "minecraft:red_sandstone");
    public static final BlockType RedSandstoneOrnate = new BlockType(179, 1, "minecraft:red_sandstone");
    public static final BlockType RedSandstoneBlank = new BlockType(179, 2, "minecraft:red_sandstone");
    public static final BlockType RedSandstoneStairs = new BlockType(180, 0, "minecraft:red_sandstone_stairs");
    public static final BlockType RedSandstoneSlab = new BlockType(181, 0, "minecraft:stone_slab2");
    public static final BlockType DoubleRedSandstoneSlab = new BlockType(182, 0, "minecraft:double_stone_slab2");
    public static final BlockType SpruceFenceGate = new BlockType(183, 0, "minecraft:spruce_fence_gate");
    public static final BlockType BirchFenceGate = new BlockType(184, 0, "minecraft:birch_fence_gate");
    public static final BlockType JungleFenceGate = new BlockType(185, 0, "minecraft:jungle_fence_gate");
    public static final BlockType DarkOakFenceGate = new BlockType(186, 0, "minecraft:dark_oak_fence_gate");
    public static final BlockType AcaciaFenceGate = new BlockType(187, 0, "minecraft:acacia_fence_gate");
    public static final BlockType SpruceFence = new BlockType(188, 0, "minecraft:spruce_fence");
    public static final BlockType BirchFence = new BlockType(189, 0, "minecraft:birch_fence");
    public static final BlockType JungleFence = new BlockType(190, 0, "minecraft:jungle_fence");
    public static final BlockType DarkOakFence = new BlockType(191, 0, "minecraft:dark_oak_fence");
    public static final BlockType AcaciaFence = new BlockType(192, 0, "minecraft:acacia_fence");
    public static final BlockType SpruceDoor = new BlockType(193, 0, "minecraft:spruce_door");
    public static final BlockType BirchDoor = new BlockType(194, 0, "minecraft:birch_door");
    public static final BlockType JungleDoor = new BlockType(195, 0, "minecraft:jungle_door");
    public static final BlockType AcaciaDoor = new BlockType(196, 0, "minecraft:acacia_door");
    public static final BlockType DarkOakDoor = new BlockType(197, 0, "minecraft:dark_oak_door");
    */

    /**
     * All NON-VANILLA blocks safety translation
     */
    NULL("NULL");

    private final String mapping;

    private VanillaBlock(String mapping) {
        this.mapping = "minecraft:".concat(mapping);
    }

    /**
     * Gets the matching {@link net.canarymod.api.world.blocks.BlockType} for the {@code VanillaBlock}
     *
     * @return matching {@code VanillaBlock} or {@code null} if not match is found
     */
    public BlockType getType() {
        if (this.equals(NULL)) {
            return null;
        }
        return mappings.inverse().get(this);
    }

    /**
     * Converts a {@link net.canarymod.api.world.blocks.BlockType} to a {@code VanillaBlocks}
     *
     * @param type
     *         the {@link net.canarymod.api.world.blocks.BlockType} to convert
     *
     * @return the matching {@code VanillaBlock}
     */
    public static VanillaBlock enumerate(BlockType type) {
        if (type == null) {
            return NULL;
        }
        VanillaBlock match = mappings.get(type);
        return match == null ? NULL : match;
    }

    /**
     * Mapping of BlockType to VanillaBlocks
     */
    private static final HashBiMap<BlockType, VanillaBlock> mappings = HashBiMap.create();

    /**
     * Initialize map
     */
    static {
        for (VanillaBlock value : values()) {
            if (value.equals(NULL)) {
                continue;
            }
            mappings.put(BlockType.fromString(value.mapping), value);
        }
    }
}
