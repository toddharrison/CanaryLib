package net.canarymod.api.world.blocks;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Static class of BlockTypes
 *
 * @author Chris (damagefilter)
 */
public final class BlockType {
    public static final BlockType Air = new BlockType(0, 0, "minecraft:air");
    public static final BlockType Stone = new BlockType(1, 0, "minecraft:stone");
    public static final BlockType Grass = new BlockType(2, 0, "minecraft:grass");
    public static final BlockType Dirt = new BlockType(3, 0, "minecraft:dirt");
    public static final BlockType Cobble = new BlockType(4, 0, "minecraft:cobblestone");
    public static final BlockType OakWood = new BlockType(5, 0, "minecraft:planks");
    public static final BlockType SpruceWood = new BlockType(5, 1, "minecraft:planks");
    public static final BlockType BirchWood = new BlockType(5, 2, "minecraft:planks");
    public static final BlockType JungleWood = new BlockType(5, 3, "minecraft:planks");
    public static final BlockType AcaciaWood = new BlockType(5, 4, "minecraft:planks");
    public static final BlockType DarkOakWood = new BlockType(5, 4, "minecraft:planks");
    public static final BlockType OakSapling = new BlockType(6, 0, "minecraft:sapling");
    public static final BlockType SpruceSapling = new BlockType(6, 1, "minecraft:sapling");
    public static final BlockType BirchSapling = new BlockType(6, 2, "minecraft:sapling");
    public static final BlockType JungleSapling = new BlockType(6, 3, "minecraft:sapling");
    public static final BlockType AcaciaSapling = new BlockType(6, 4, "minecraft:sapling");
    public static final BlockType DarkOakSapling = new BlockType(6, 5, "minecraft:sapling");
    public static final BlockType Bedrock = new BlockType(7, 0, "minecraft:bedrock");
    public static final BlockType Water = new BlockType(8, 0, "minecraft:water_flowing");
    public static final BlockType WaterFlowing = new BlockType(9, 0, "minecraft:water");
    public static final BlockType Lava = new BlockType(10, 0, "minecraft:lava_flowing");
    public static final BlockType LavaFlowing = new BlockType(11, 0, "minecraft:lava");
    public static final BlockType Sand = new BlockType(12, 0, "minecraft:sand");
    public static final BlockType Gravel = new BlockType(13, 0, "minecraft:gravel");
    public static final BlockType GoldOre = new BlockType(14, 0, "minecraft:gold_ore");
    public static final BlockType IronOre = new BlockType(15, 0, "minecraft:iron_ore");
    public static final BlockType CoalOre = new BlockType(16, 0, "minecraft:coal_ore");
    public static final BlockType OakLog = new BlockType(17, 0, "minecraft:log");
    public static final BlockType PineLog = new BlockType(17, 1, "minecraft:log");
    public static final BlockType BirchLog = new BlockType(17, 2, "minecraft:log");
    public static final BlockType JungleLog = new BlockType(17, 3, "minecraft:log");
    public static final BlockType OakLeaves = new BlockType(18, 0, "minecraft:leaves");
    public static final BlockType PineLeaves = new BlockType(18, 1, "minecraft:leaves");
    public static final BlockType BirchLeaves = new BlockType(18, 2, "minecraft:leaves");
    public static final BlockType JungleLeaves = new BlockType(18, 3, "minecraft:leaves");
    public static final BlockType Sponge = new BlockType(19, 0, "minecraft:sponge"); // THE SPONGE IS A LIE!
    public static final BlockType Glass = new BlockType(20, 0, "minecraft:glass");
    public static final BlockType LapislazuliOre = new BlockType(21, 0, "minecraft:lapis_ore");
    public static final BlockType LapisBlock = new BlockType(22, 0, "minecraft:lapis_block");
    public static final BlockType Dispenser = new BlockType(23, 0, "minecraft:dispenser");
    public static final BlockType Sandstone = new BlockType(24, 0, "minecraft:sandstone");
    public static final BlockType SandstoneOrnate = new BlockType(24, 1, "minecraft:sandstone");
    public static final BlockType SandstoneBlank = new BlockType(24, 2, "minecraft:sandstone");
    public static final BlockType NoteBlock = new BlockType(25, 0, "minecraft:noteblock");
    public static final BlockType BedBlock = new BlockType(26, 0, "minecraft:bed");
    public static final BlockType PoweredRail = new BlockType(27, 0, "minecraft:golden_rail");
    public static final BlockType DetectorRail = new BlockType(28, 0, "minecraft:detector_rail");
    public static final BlockType StickyPiston = new BlockType(29, 0, "minecraft:sticky_piston");
    public static final BlockType SpiderWeb = new BlockType(30, 0, "minecraft:web");
    public static final BlockType Shrub = new BlockType(31, 0, "minecraft:tallgrass");
    public static final BlockType TallGrass = new BlockType(31, 1, "minecraft:tallgrass");
    public static final BlockType Fern = new BlockType(31, 2, "minecraft:tallgrass");
    public static final BlockType DeadBush = new BlockType(32, 0, "minecraft:deadbush");
    public static final BlockType Piston = new BlockType(33, 0, "minecraft:piston");
    public static final BlockType PistonHead = new BlockType(34, 0, "minecraft:piston_head");
    public static final BlockType WoolWhite = new BlockType(35, 0, "minecraft:wool");
    public static final BlockType WoolOrange = new BlockType(35, 1, "minecraft:wool");
    public static final BlockType WoolMagenta = new BlockType(35, 2, "minecraft:wool");
    public static final BlockType WoolLightBlue = new BlockType(35, 3, "minecraft:wool");
    public static final BlockType WoolYellow = new BlockType(35, 4, "minecraft:wool");
    public static final BlockType WoolLightGreen = new BlockType(35, 5, "minecraft:wool");
    public static final BlockType WoolPink = new BlockType(35, 6, "minecraft:wool");
    public static final BlockType WoolGray = new BlockType(35, 7, "minecraft:wool");
    public static final BlockType WoolLightGray = new BlockType(35, 8, "minecraft:wool");
    public static final BlockType WoolCyan = new BlockType(35, 9, "minecraft:wool");
    public static final BlockType WoolPurple = new BlockType(35, 10, "minecraft:wool");
    public static final BlockType WoolBlue = new BlockType(35, 11, "minecraft:wool");
    public static final BlockType WoolBrown = new BlockType(35, 12, "minecraft:wool");
    public static final BlockType WoolDarkGreen = new BlockType(35, 13, "minecraft:wool");
    public static final BlockType WoolRed = new BlockType(35, 14, "minecraft:wool");
    public static final BlockType WoolBlack = new BlockType(35, 15, "minecraft:wool");
    public static final BlockType PistonExtended = new BlockType(36, 0, "minecraft:piston_extension");
    public static final BlockType Dandelion = new BlockType(37, 0, "minecraft:yellow_flower");

    public static final BlockType Poppy = new BlockType(38, 0, "minecraft:red_flower");
    public static final BlockType BlueOrchid = new BlockType(38, 1, "minecraft:red_flower");
    public static final BlockType Allium = new BlockType(38, 2, "minecraft:red_flower");
    public static final BlockType AzureBluet = new BlockType(38, 3, "minecraft:red_flower");
    public static final BlockType RedTulip = new BlockType(38, 4, "minecraft:red_flower");
    public static final BlockType OrangeTulip = new BlockType(38, 5, "minecraft:red_flower");
    public static final BlockType WhiteTulip = new BlockType(38, 6, "minecraft:red_flower");
    public static final BlockType PinkTulip = new BlockType(38, 7, "minecraft:red_flower");
    public static final BlockType OxeyeDaisy = new BlockType(38, 8, "minecraft:red_flower");

    public static final BlockType BrownMushroom = new BlockType(39, 0, "minecraft:brown_mushroom");
    public static final BlockType RedMushroom = new BlockType(40, 0, "minecraft:red_mushroom");
    public static final BlockType GoldBlock = new BlockType(41, 0, "minecraft:gold_block");
    public static final BlockType IronBlock = new BlockType(42, 0, "minecraft:iron_block");
    public static final BlockType DoublestepOrnateStone = new BlockType(43, 0, "minecraft:double_stone_slab");
    public static final BlockType DoublestepSandStoneTrim = new BlockType(43, 1, "minecraft:double_stone_slab");
    public static final BlockType DoublestepWood = new BlockType(43, 2, "minecraft:double_stone_slab");
    public static final BlockType DoublestepCobble = new BlockType(43, 3, "minecraft:double_stone_slab");
    public static final BlockType DoublestepBrickBlock = new BlockType(43, 4, "minecraft:double_stone_slab");
    public static final BlockType DoublestepStoneBricks = new BlockType(43, 5, "minecraft:double_stone_slab");
    public static final BlockType DoublestepNetherBrick = new BlockType(43, 6, "minecraft:double_stone_slab");
    public static final BlockType DoublestepQuartz = new BlockType(43, 9, "minecraft:double_stone_slab");
    public static final BlockType DoublestepStone = new BlockType(43, 8, "minecraft:double_stone_slab");
    public static final BlockType DoublestepSandStone = new BlockType(43, 11, "minecraft:double_stone_slab");
    public static final BlockType StepOrnateStone = new BlockType(44, 0, "minecraft:stone_slab");
    public static final BlockType StepSandStoneTrim = new BlockType(44, 1, "minecraft:stone_slab");
    public static final BlockType StepWood = new BlockType(44, 2, "minecraft:stone_slab");
    public static final BlockType StepCobble = new BlockType(44, 3, "minecraft:stone_slab");
    public static final BlockType StepBrickBlock = new BlockType(44, 4, "minecraft:stone_slab");
    public static final BlockType StepStoneBricks = new BlockType(44, 5, "minecraft:stone_slab");
    public static final BlockType StepNetherBricks = new BlockType(44, 6, "minecraft:stone_slab");
    public static final BlockType StepQuartz = new BlockType(44, 7, "minecraft:stone_slab");
    public static final BlockType StepStone = new BlockType(44, 10, "minecraft:stone_slab");
    public static final BlockType StepSandStone = new BlockType(44, 11, "minecraft:stone_slab");
    public static final BlockType BrickBlock = new BlockType(45, 0, "minecraft:brick_block");
    public static final BlockType Tnt = new BlockType(46, 0, "minecraft:tnt");
    public static final BlockType Bookshelf = new BlockType(47, 0, "minecraft:bookshelf");
    public static final BlockType MossyCobble = new BlockType(48, 0, "minecraft:mossy_cobblestone");
    public static final BlockType Obsidian = new BlockType(49, 0, "minecraft:obsidian");
    public static final BlockType Torch = new BlockType(50, 0, "minecraft:torch");
    public static final BlockType FireBlock = new BlockType(51, 0, "minecraft:fire");
    public static final BlockType MobSpawner = new BlockType(52, 0, "minecraft:mob_spawner");
    public static final BlockType WoodenStair = new BlockType(53, 0, "minecraft:oak_stairs");
    public static final BlockType Chest = new BlockType(54, 0, "minecraft:chest");
    public static final BlockType RedstoneWire = new BlockType(55, 0, "minecraft:redstone_wire");
    public static final BlockType DiamondOre = new BlockType(56, 0, "minecraft:diamond_ore");
    public static final BlockType DiamondBlock = new BlockType(57, 0, "minecraft:diamond_block");
    public static final BlockType Workbench = new BlockType(58, 0, "minecraft:crafting_table");
    public static final BlockType Crops = new BlockType(59, 0, "minecraft:wheat");
    public static final BlockType Soil = new BlockType(60, 0, "minecraft:famland");
    public static final BlockType Furnace = new BlockType(61, 0, "minecraft:furnace");
    public static final BlockType BurningFurnace = new BlockType(62, 0, "minecraft:lit_furnace");
    public static final BlockType SignPost = new BlockType(63, 0, "minecraft:standing_sign");
    public static final BlockType WoodenDoor = new BlockType(64, 0, "minecraft:wooden_door");
    public static final BlockType Ladder = new BlockType(65, 0, "minecraft:ladder");
    public static final BlockType Rail = new BlockType(66, 0, "minecraft:rail");
    public static final BlockType CobbleStair = new BlockType(67, 0, "minecraft:stone_stairs");
    public static final BlockType WallSign = new BlockType(68, 0, "minecraft:wall_sign");
    public static final BlockType Lever = new BlockType(69, 0, "minecraft:lever");
    public static final BlockType StonePlate = new BlockType(70, 0, "minecraft:stone_pressure_plate");
    public static final BlockType IronDoor = new BlockType(71, 0, "minecraft:iron_door");
    public static final BlockType WoodPlate = new BlockType(72, 0, "minecraft:wooden_pressure_plate");
    public static final BlockType RedstoneOre = new BlockType(73, 0, "minecraft:redstone_ore");
    public static final BlockType GlowingRedstoneOre = new BlockType(74, 0, "minecraft:lit_redstone_ore");
    public static final BlockType RedstoneTorchOff = new BlockType(75, 0, "minecraft:unlit_redstone_torch");
    public static final BlockType RedstoneTorchOn = new BlockType(76, 0, "minecraft:redstone_torch");
    public static final BlockType StoneButton = new BlockType(77, 0, "minecraft:stone_button");
    public static final BlockType Snow = new BlockType(78, 0, "minecraft:snow_layer");
    public static final BlockType Ice = new BlockType(79, 0, "minecraft:ice");
    public static final BlockType SnowBlock = new BlockType(80, 0, "minecraft:snow");
    public static final BlockType Cactus = new BlockType(81, 0, "minecraft:cactus");
    public static final BlockType Clay = new BlockType(82, 0, "minecraft:clay");
    public static final BlockType Reed = new BlockType(83, 0, "minecraft:reeds");
    public static final BlockType Jokebox = new BlockType(84, 0, "minecraft:jukebox");
    public static final BlockType Fence = new BlockType(85, 0, "minecraft:fence");
    public static final BlockType Pumpkin = new BlockType(86, 0, "minecraft:pumpkin");
    public static final BlockType Netherrack = new BlockType(87, 0, "minecraft:netherrack");
    public static final BlockType SoulSand = new BlockType(88, 0, "minecraft:soul_sand");
    public static final BlockType GlowStone = new BlockType(89, 0, "minecraft:glowstone");
    public static final BlockType Portal = new BlockType(90, 0, "minecraft:portal");
    public static final BlockType JackOLantern = new BlockType(91, 0, "minecraft:lit_pumpkin");
    public static final BlockType Cake = new BlockType(92, 0, "minecraft:cake");
    public static final BlockType RedstoneRepeaterOff = new BlockType(93, 0, "minecraft:unpowered_repeater");
    public static final BlockType RedstoneRepeaterOn = new BlockType(94, 0, "minecraft:powered_repeater");
    public static final BlockType WhiteGlass = new BlockType(95, 0, "minecraft:stained_glass");
    public static final BlockType OrangeGlass = new BlockType(95, 1, "minecraft:stained_glass");
    public static final BlockType MagentaGlass = new BlockType(95, 2, "minecraft:stained_glass");
    public static final BlockType LightBlueGlass = new BlockType(95, 3, "minecraft:stained_glass");
    public static final BlockType YellowGlass = new BlockType(95, 4, "minecraft:stained_glass");
    public static final BlockType LimeGlass = new BlockType(95, 5, "minecraft:stained_glass");
    public static final BlockType PinkGlass = new BlockType(95, 6, "minecraft:stained_glass");
    public static final BlockType GrayGlass = new BlockType(95, 7, "minecraft:stained_glass");
    public static final BlockType LightGrayGlass = new BlockType(95, 8, "minecraft:stained_glass");
    public static final BlockType CyanGlass = new BlockType(95, 9, "minecraft:stained_glass");
    public static final BlockType PurpleGlass = new BlockType(95, 10, "minecraft:stained_glass");
    public static final BlockType BlueGlass = new BlockType(95, 11, "minecraft:stained_glass");
    public static final BlockType BrownGlass = new BlockType(95, 12, "minecraft:stained_glass");
    public static final BlockType GreenGlass = new BlockType(95, 13, "minecraft:stained_glass");
    public static final BlockType RedGlass = new BlockType(95, 14, "minecraft:stained_glass");
    public static final BlockType BlackGlass = new BlockType(95, 15, "minecraft:stained_glass");
    public static final BlockType Trapdoor = new BlockType(96, 0, "minecraft:trapdoor");
    public static final BlockType CleanSilverFishBlock = new BlockType(97, 0, "minecraft:monster_egg");
    public static final BlockType MossySilverFishBlock = new BlockType(97, 1, "minecraft:monster_egg");
    public static final BlockType CrackdSilverFishBlock = new BlockType(97, 2, "minecraft:monster_egg");
    public static final BlockType OrnateSilverFishBlock = new BlockType(97, 3, "minecraft:monster_egg");
    public static final BlockType StoneBrick = new BlockType(98, 0, "minecraft:stonebrick");
    public static final BlockType MossyStoneBrick = new BlockType(98, 1, "minecraft:stonebrick");
    public static final BlockType CrackedStoneBrick = new BlockType(98, 2, "minecraft:stonebrick");
    public static final BlockType OrnateStoneBrick = new BlockType(98, 3, "minecraft:stonebrick");
    public static final BlockType HugeBrownMushroom = new BlockType(99, 0, "minecraft:brown_mushroom_block");
    public static final BlockType HugeRedMushroom = new BlockType(100, 0, "minecraft:red_mushroom_block");
    public static final BlockType IronBars = new BlockType(101, 0, "minecraft:iron_bars");
    public static final BlockType GlassPane = new BlockType(102, 0, "minecraft:glass_pane");
    public static final BlockType Melon = new BlockType(103, 0, "minecraft:melon_block");
    public static final BlockType PumpkinStem = new BlockType(104, 0, "minecraft:pumpkin_stem");
    public static final BlockType MelonStem = new BlockType(105, 0, "minecraft:melon_stem");
    public static final BlockType Vines = new BlockType(106, 0, "minecraft:vine");
    public static final BlockType FenceGate = new BlockType(107, 0, "minecraft:fence_gate");
    public static final BlockType BrickStair = new BlockType(108, 0, "minecraft:brick_stairs");
    public static final BlockType StoneBrickStair = new BlockType(109, 0, "minecraft:stone_brick_stairs");
    public static final BlockType Mycelium = new BlockType(110, 0, "minecraft:mycelium");
    public static final BlockType Lilypad = new BlockType(111, 0, "minecraft:waterlily");
    public static final BlockType NetherBrick = new BlockType(112, 0, "minecraft:nether_brick");
    public static final BlockType NetherBrickFence = new BlockType(113, 0, "minecraft:nether_brick_fence");
    public static final BlockType NetherBrickStair = new BlockType(114, 0, "minecraft:nether_brick_stairs");
    public static final BlockType NetherWart = new BlockType(115, 0, "minecraft:nether_wart");
    public static final BlockType EnchantmentTable = new BlockType(116, 0, "minecraft:enchanting_table");
    public static final BlockType BrewingStand = new BlockType(117, 0, "minecraft:brewing_stand");
    public static final BlockType Cauldron = new BlockType(118, 0, "minecraft:cauldron");
    public static final BlockType EndPortal = new BlockType(119, 0, "minecraft:end_portal");
    public static final BlockType EndPortalFrame = new BlockType(120, 0, "minecraft:end_portal_frame");
    public static final BlockType EndStone = new BlockType(121, 0, "minecraft:end_stone");
    public static final BlockType EnderDragonEgg = new BlockType(122, 0, "minecraft:dragon_egg");
    public static final BlockType RedstoneLampOff = new BlockType(123, 0, "minecraft:redstone_lamp");
    public static final BlockType RedstoneLampOn = new BlockType(124, 0, "minecraft:lit_redstone_lamp");
    public static final BlockType OakWoodDoubleStep = new BlockType(125, 0, "minecraft:double_wooden_slab");
    public static final BlockType SpruceWoodDoubleStep = new BlockType(125, 1, "minecraft:double_wooden_slab");
    public static final BlockType BirchWoodDoubleStep = new BlockType(125, 2, "minecraft:double_wooden_slab");
    public static final BlockType JungleWoodDoubleStep = new BlockType(125, 3, "minecraft:double_wooden_slab");
    public static final BlockType AcaciaWoodDoubleStep = new BlockType(125, 4, "minecraft:double_wooden_slab");
    public static final BlockType DarkOakWoodDoubleStep = new BlockType(125, 5, "minecraft:double_wooden_slab");
    public static final BlockType OakWoodStep = new BlockType(126, 0, "minecraft:wooden_slab");
    public static final BlockType SpruceWoodStep = new BlockType(126, 1, "minecraft:wooden_slab");
    public static final BlockType BirchWoodStep = new BlockType(126, 2, "minecraft:wooden_slab");
    public static final BlockType JungleWoodStep = new BlockType(126, 3, "minecraft:wooden_slab");
    public static final BlockType AcaciaWoodStep = new BlockType(126, 4, "minecraft:wooden_slab");
    public static final BlockType DarkOakWoodStep = new BlockType(126, 5, "minecraft:wooden_slab");
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
    // 150 not in use
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
    // 165-169 MIA
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



    private final short id;
    private final short data;
    private final String machineName;

    private static HashMap<Entry<String, Integer>, BlockType> blockTypes;

    public BlockType(int id, String machineName) {
        this(id, 0, "canarymod:"+machineName);
    }

    public BlockType(int id, String machineName, String mod) {
        this(id, 0, mod+":"+machineName);
    }

    /**
     * This will create this blockType and also add it to the BlockTypes cache,
     * if a BlockType with the same name doesn't already exist.
     * IF a BlockType with the given name already exists, nothing will happen to
     * the BlockType list, you can still use this BlockType if you need to
     *
     * @param id
     *         the block id
     * @param data
     *         the block data
     * @param machineName
     *         the block's machine name (new-style ID)
     */
    public BlockType(int id, int data, String machineName) {
        if (blockTypes == null) {
            blockTypes = new HashMap<Entry<String, Integer>, BlockType>();
        }
        if (machineName == null) {
            throw new CustomBlockTypeException("BlockType name cannot be null!");
        }
        this.id = (short) id;
        this.data = (short) data;
        this.machineName = machineName;
        Entry<String, Integer> uniqueType = new SimpleImmutableEntry<String, Integer>(machineName, data);
        if (!blockTypes.containsKey(uniqueType)) {
            @SuppressWarnings("LeakingThisInConstructor")
            BlockType ignored = blockTypes.put(uniqueType, this);
        }
        else {
            throw new CustomBlockTypeException("BlockType '" + machineName + ":" + data + "' already exists!");
        }
    }

    /**
     * Get the ID of this BlockType
     *
     * @return data
     */
    public short getData() {
        return data;
    }

    /**
     * Get the ID of this BlockType
     *
     * @return id
     */
    public short getId() {
        return id;
    }

    /**
     * Returns a "machine readable" name.
     * That is: a representation of the Block Type name
     * in lowercase letters without whitespace.
     *
     * @return machine name
     */
    public String getMachineName() {
        return machineName;
    }

    /**
     * Get a custom block type.
     * Returns null if the requested BlockType does not exist.
     *
     * @param name
     *         the machine name of the block type in question
     *
     * @return the custom {@link BlockType}
     */
    public static BlockType getCustomBlockType(String name) {
        return BlockType.getCustomBlockType(name, 0);
    }

    /**
     * Get a custom block type.
     * Returns null if the requested BlockType does not exist.
     *
     * @param name
     *         the machine name of the block type in question
     * @param data
     *         the data of the block type in question
     *
     * @return the custom {@link BlockType}
     */
    public static BlockType getCustomBlockType(String name, int data) {
        Entry<String, Integer> custom = new SimpleImmutableEntry<String, Integer>(name, data);
        if (!blockTypes.containsKey(custom)) {
            for (BlockType t : blockTypes.values()) {
                if (t.data == data && t.machineName.equalsIgnoreCase(name)) {
                    return t;
                }
            }
            return null;
        }
        return blockTypes.get(custom);
    }

    /**
     * Get the BlockType according to the given ID.
     * This will return null if there is no BlockType with this id.
     *
     * @param id
     *         the id
     *
     * @return the associated {@link BlockType} or {@code null}
     */
    public static BlockType fromId(int id) {
        for (BlockType t : blockTypes.values()) {
            if (t.id == id) {
                return t;
            }
        }
        return null;
    }

    /**
     * Get the BlockType according to the given ID and Data.
     * This will return null if there is no BlockType with this id and data.
     *
     * @param id
     *         the id
     * @param data
     *         the data value
     *
     * @return the associated {@link BlockType} or {@code null}
     */
    public static BlockType fromIdAndData(int id, int data) {
        for (BlockType t : blockTypes.values()) {
            if (t.id == id && t.data == data) {
                return t;
            }
        }
        return fromId(id); // if data has bit's set, it won't perfectly equal
    }

    /**
     * Returns a BlockType according to its name.
     * This returns null if there is no BlockType with this name.
     *
     * @param name
     *         The machine name
     *
     * @return the associated {@link BlockType} or {@code null}
     */
    public static BlockType fromString(String name) {
        for (BlockType t : blockTypes.values()) {
            if (t.machineName.equalsIgnoreCase(name)) {
                return t;
            }
        }
        return null;
    }

    /**
     * Returns a BlockType according to its name and data.
     * This returns null if there is no BlockType with this name and data.
     *
     * @param machineName
     *         The machine name
     * @param data
     *         The metadata
     *
     * @return the associated {@link BlockType} or {@code null}
     */
    public static BlockType fromStringAndData(String machineName, int data) {
        Entry<String, Integer> needle = new SimpleImmutableEntry<String, Integer>(machineName, data);
        if (!blockTypes.containsKey(needle)) {
            for (BlockType t : blockTypes.values()) {
                if (t.data == data && t.machineName.equalsIgnoreCase(machineName)) {
                    return t;
                }
            }
            return null;
        }
        return blockTypes.get(needle);
    }

    /**
     * Gets an array of all BlockTypes.
     *
     * @return all BlockTypes
     */
    public static BlockType[] values() {
        return blockTypes.values().toArray(new BlockType[blockTypes.size()]);
    }

}
