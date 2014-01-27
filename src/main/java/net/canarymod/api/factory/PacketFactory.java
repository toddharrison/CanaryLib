package net.canarymod.api.factory;

import net.canarymod.api.DataWatcher;
import net.canarymod.api.chat.ChatComponent;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.XPOrb;
import net.canarymod.api.entity.hanging.Painting;
import net.canarymod.api.entity.living.LivingBase;
import net.canarymod.api.entity.living.humanoid.Human;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.packet.InvalidPacketConstructionException;
import net.canarymod.api.packet.Packet;
import net.canarymod.api.potion.PotionEffect;
import net.canarymod.api.statistics.Stat;
import net.canarymod.api.world.Chunk;
import net.canarymod.api.world.position.Position;
import net.canarymod.api.world.position.Vector3D;

import java.util.List;
import java.util.Map;

/**
 * Packet manufacturing Factory
 * <p/>
 * See <a href="http://wiki.vg/Protocol">wiki.vg/Protocol</a> for information about Packets
 * <p/>
 * Some implemented packets may not function as intended or at all.
 *
 * @author Jason (darkdiplomat)
 */
public interface PacketFactory {

    /**
     * Creates Packets to be sent later
     * <p/>
     * NOTE: Not all packets can be constructed<br/>
     * All non-constructable packets will throw an {@link InvalidPacketConstructionException} or similar exception
     *
     * @param id
     *         the Packet ID
     * @param args
     *         the arguments for Packet Construction.
     *
     * @return the new Packet
     *
     * @throws InvalidPacketConstructionException
     *         should a Packet not be able to be constructed properly
     * @see http://wiki.vg/Protocol
     */
    Packet createPacket(int id, Object... args) throws InvalidPacketConstructionException;

    /**
     * Creates a Chat {@link Packet}
     *
     * @param chatComponent
     *         the {@link net.canarymod.api.chat.ChatComponent}
     *
     * @return a new Chat packet or {@code null} if an error occurred
     */
    Packet chat(ChatComponent chatComponent); // 2

    /**
     * Creates a Update Time {@link Packet}
     * <p/>
     * Time is based on ticks, where 20 ticks happen every second. There are 24000 ticks in a day, making Minecraft days exactly 20 minutes long.<br/>
     * The time of day is based on the timestamp modulo 24000. 0 is sunrise, 6000 is noon, 12000 is sunset, and 18000 is midnight.<br/>
     * The default SMP server increments the time by 20 every second.
     *
     * @param worldAge
     *         the age of the world
     * @param time
     *         the time of the world
     *
     * @return a new Update Time packet or {@code null} if an error occurred
     */
    Packet updateTime(long worldAge, long time); // 3

    /**
     * Creates a EntityEquipment {@link Packet}
     *
     * @param entityID
     *         the {@link Entity} ID
     * @param slot
     *         Equipment slot: 0=held, 1-4=armor slot (1 - boots, 2 - leggings, 3 - chestplate, 4 - helmet)
     * @param item
     *         the {@link Item} in the slot
     *
     * @return new EntityEquipment {@link Packet} or {@code null} if an error occurred
     */
    Packet entityEquipment(int entityID, int slot, Item item); // 4

    /**
     * Creates a Spawn Position {@link Packet}
     * <p/>
     * Sent by the server after login to specify the coordinates of the spawn point (the point at which players spawn at, and which the compass points to).<br/>
     * It can be sent at any time to update the point compasses point at.
     *
     * @param x
     *         the X coordinate
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     *
     * @return new Spawn Position {@link Packet} or {@code null} if an error occurred
     */
    Packet spawnPosition(int x, int y, int z); // 5

    /**
     * Creates a Update Health {@link Packet}
     * <p/>
     * Sent by the server to update/set the health of the player it is sent to.<br/>
     * Food saturation acts as a food "overcharge". Food values will not decrease while the saturation is over zero.<br/>
     * Players logging in automatically get a saturation of 5.0.<br/>
     * Eating food increases the saturation as well as the food bar.<br/>
     *
     * @param health
     *         the player's health
     * @param foodLevel
     *         the player's food level
     * @param saturation
     *         the player's food saturation
     *
     * @return new Update Health {@link Packet} or {@code null} if an error occurred
     */
    Packet updateHealth(float health, int foodLevel, float saturation); // 6

    //Packet respawn(int, World)

    /**
     * Creates a Player Position and Look {@link Packet}
     *
     * @param x
     *         the X coordinate
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     * @param yaw
     *         the Yaw rotation
     * @param pitch
     *         the Pitch rotation
     * @param onGround
     *         {@code true} for on ground; {@code false} for not
     *
     * @return new PlayerLookMove {@link Packet} or {@code null} if an error occurred
     */
    Packet playerPositionLook(double x, double y, double z, float yaw, float pitch, boolean onGround); // 8

    /**
     * Creates a HeldItemChange {@link Packet}
     * <p/>
     * Sent when the player changes the slot selection
     *
     * @param slot
     *         The slot which the player has selected (0-8)
     *
     * @return new HeldItemChange {@link Packet} or {@code null} if an error occurred
     */
    Packet heldItemChange(int slot); // 9

    /**
     * Creates a UseBed {@link Packet}
     * <p/>
     * This packet tells that a player goes to bed.<br/>
     * The client with the matching Entity ID will go into bed mode.<br/>
     * This Packet is sent to all nearby players including the one sent to bed.
     *
     * @param player
     *         the {@link Player} going to bed
     * @param x
     *         Bed headboard X as block coordinate
     * @param y
     *         Bed headboard Y as block coordinate
     * @param z
     *         Bed headboard Z as block coordinate
     *
     * @return new UseBed {@link Packet} or {@code null} if an error occurred
     */
    Packet useBed(Player player, int x, int y, int z); // 10

    /**
     * Creates a Animation {@link Packet}
     * <p/>
     * Sent whenever an player should change animation.<br/>
     *
     * @param player
     *         the {@link Player}
     * @param animation
     *         the animation id 0: No animation 1: Swing arm 2: Damage animation 3: Leave bed 5: Eat food 6: Critical effect 7: Magic critical effect
     *
     * @return new Animation {@link Packet} or {@code null} if an error occurred
     */
    Packet animation(Player player, int animation); // 11

    /**
     * Creates a SpawnPlayer {@link Packet}
     *
     * @param human
     *         the {@link Human} to spawn (either a NPC or Player)
     *
     * @return new SpawnPlayer {@link Packet} or {@code null} if an error occurred
     */
    Packet spawnPlayer(Human human); // 12

    /**
     * Creates a CollectItem {@link Packet}
     * <p/>
     * Sent by the server when someone picks up an item lying on the ground - its sole purpose appears to be the animation of the item flying towards you.<br/>
     * It doesn't destroy the entity in the client memory (0x1D does that), and it doesn't add it to your inventory (0x67 does that).<br/>
     * The server only checks for items to be picked up after each Player Position and Player Position & Look packet sent by the client.
     *
     * @param entityItemID
     *         the ID of the EntityItem
     * @param collectorID
     *         the ID of the Player
     *
     * @return new CollectItem {@link Packet} or {@code null} if an error occurred
     */
    Packet collectItem(int entityItemID, int collectorID); // 13

    /**
     * Creates a SpawnObject {@link Packet}
     *
     * @param entity
     *         the entity being spawned
     * @param objectID
     *         See <a href="http://wiki.vg/Entities#Objects">wiki.vg/Entities#Objects</a>
     *
     * @return new SpawnObjectVehicle {@link Packet} or {@code null} if an error occurred
     */
    Packet spawnObject(Entity entity, int objectID); // 14

    /**
     * Creates a SpawnObject {@link Packet}
     *
     * @param entity
     *         the entity being spawned
     * @param objectID
     *         See <a href="http://wiki.vg/Entities#Objects">wiki.vg/Entities#Objects</a>
     * @param throwerID
     *         the EntityID of the Thrower (if applicable); Otherwise see <a href="http://wiki.vg/Object_Data">wiki.vg/Object_Data</a>
     *
     * @return new SpawnObjectVehicle {@link Packet} or {@code null} if an error occurred
     */
    Packet spawnObject(Entity entity, int objectID, int throwerID); //14

    /**
     * Creates a SpawnMob {@link Packet}
     *
     * @param livingbase
     *         the {@link LivingBase} to spawn
     *
     * @return new SpawnMob {@link Packet} or {@code null} if an error occurred
     */
    Packet spawnMob(LivingBase livingbase); // 15

    /**
     * Creates a SpawnPainting {@link Packet}
     *
     * @param painting
     *         the {@link Painting} to spawn
     *
     * @return new SpawnPainting {@link Packet} or {@code null} if an error occurred
     */
    Packet spawnPainting(Painting painting); // 16

    /**
     * Creates a SpawnXPOrb {@link Packet}
     *
     * @param xporb
     *         the {@link XPOrb} to spawn
     *
     * @return new SpawnXPOrb {@link Packet} or {@code null} if an error occurred
     */
    Packet spawnXPOrb(XPOrb xporb); // 17

    /**
     * Creates a EntityVelocity {@link Packet}
     * <p/>
     * Velocity is believed to be in units of 1/8000 of a block per server tick (50ms); for example, -1343 would move (-1343 / 8000) = −0.167875 blocks per tick (or −3,3575 blocks per second).
     *
     * @param entityID
     *         the entity ID
     * @param motX
     *         the X-wise motion
     * @param motY
     *         the Y-wise motion
     * @param motZ
     *         the Z-wise motion
     *
     * @return new EntityVelocity {@link Packet} or {@code null} if an error occurred
     */
    Packet entityVelocity(int entityID, double motX, double motY, double motZ); // 18

    /**
     * Creates a DestroyEntities {@link Packet}
     * <p/>
     * Sent by the server when an list of Entities is to be destroyed on the client.
     *
     * @param ids
     *         the IDs of the Entities to destroy
     *
     * @return new DestoryEntities {@link Packet} or {@code null} if an error occurred
     */
    Packet destroyEntities(int... ids); // 19

    /**
     * Creates a EntityRelativeMove {@link Packet}
     * <p/>
     * This packet is sent by the server when an entity moves less then 4 blocks; if an entity moves more than 4 blocks Entity Teleport should be sent instead.<br/>
     * This packet allows at most four blocks movement in any direction, because byte range is from -128 to 127.<br/>
     * Movement is an offset of Absolute Int; to convert relative move to block coordinate offset, divide by 32.
     *
     * @param entityID
     *         the entity ID
     * @param x
     *         the X block coordinate offset
     * @param y
     *         the Y block coordinate offset
     * @param z
     *         the Z block coordinate offset
     *
     * @return new EntityRelativeMove {@link Packet} or {@code null} if an error occurred
     */
    Packet entityRelativeMove(int entityID, byte x, byte y, byte z); // 21

    /**
     * Creates a EntityLook {@link Packet}
     * <p/>
     * This packet is sent by the server when an entity rotates. Example: "Yaw" field 64 means a 90 degree turn.
     *
     * @param entityID
     *         the entity ID
     * @param yaw
     *         the yaw offset
     * @param pitch
     *         the pitch offset
     *
     * @return new EntityLook {@link Packet} or {@code null} if an error occurred
     */
    Packet entityLook(int entityID, byte yaw, byte pitch); // 22

    /**
     * Creates a EntityLookRelativeMove {@link Packet}
     * <p/>
     * Since a byte range is limited from -128 to 127, and movement is offset of Absolute Int, this packet allows at most four blocks movement in any direction. (-128/32 == -4)
     *
     * @param entityID
     *         the entityID
     * @param x
     *         the X block coordinate offset
     * @param y
     *         the Y block coordinate offset
     * @param z
     *         the Z block coordinate offset
     * @param yaw
     *         the yaw offset
     * @param pitch
     *         the pitch offset
     *
     * @return new EntityLookRelativeMove {@link Packet} or {@code null} if an error occurred
     */
    Packet entityLookRelativeMove(int entityID, byte x, byte y, byte z, byte yaw, byte pitch); // 23

    /**
     * Creates an EntityTeleport {@link Packet}
     *
     * @param entity
     *         the {@link Entity} teleporting
     *
     * @return new EntityTeleport {@link Packet} or {@code null} if an error occurred
     */
    Packet entityTeleport(Entity entity); // 24

    /**
     * Creates a EntityTeleport {@link Packet}
     *
     * @param entityID
     *         the EntityID
     * @param x
     *         the X coordinate
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     * @param yaw
     *         the yaw offset
     * @param pitch
     *         the pitch offset
     *
     * @return new EntityTeleport {@link Packet} or {@code null} if an error occurred
     */
    Packet entityTeleport(int entityID, int x, int y, int z, byte yaw, byte pitch); // 24

    /**
     * Creates a new EntityStatus {@link Packet}
     *
     * @param entityID
     *         the entity ID
     * @param status
     *         2: Entity hurt <br/>
     *         3: Entity dead <br/>
     *         6: Wolf taming <br/>
     *         7: Wolf tamed <br/>
     *         8: Wolf shaking water off itself <br/>
     *         9: (of self) Eating accepted by server <br/>
     *         10: Sheep eating grass <br/>
     *         11: Iron Golem handing over a rose <br/>
     *         12: Spawn "heart" particles near a villager <br/>
     *         13: Spawn particles indicating that a villager is angry and seeking revenge <br/>
     *         14: Spawn happy particles near a villager <br/>
     *         15: Spawn a "magic" particle near the Witch <br/>
     *         16: Zombie converting into a villager by shaking violently <br/>
     *         17: A firework exploding <br/>
     *
     * @return new EntityStatus {@link Packet} or {@code null} if an error occurred
     */
    Packet entityStatus(int entityID, byte status); // 25

    /**
     * Creates a AttachEntity {@link Packet}
     *
     * @param leashId
     *         the Leash ID
     * @param attaching
     *         the entity being attached
     * @param vehicle
     *         the entity being attached to
     *
     * @return new AttachEntity {@link Packet} or {@code null} if an error occurred
     */
    Packet attachEntity(int leashId, Entity attaching, Entity vehicle); // 27

    /**
     * Creates a EntityMetaData {@link Packet}
     *
     * @param entityID
     *         the entity ID
     * @param watcher
     *         the {@link DataWatcher} data
     *
     * @return new EntityMetaData {@link Packet} or {@code null} if an error occurred
     */
    Packet entityMetaData(int entityID, DataWatcher watcher); // 28

    /**
     * Creates a EntityEffect {@link Packet}
     *
     * @param entityID
     *         the Entity ID
     * @param effect
     *         the {@link PotionEffect}
     *
     * @return new EntityEffect {@link Packet} or {@code null} if an error occurred
     */
    Packet entityEffect(int entityID, PotionEffect effect); // 29

    /**
     * Creates a RemoveEntityEffect {@link Packet}
     *
     * @param entityID
     *         the Entity ID
     * @param effect
     *         the {@link PotionEffect}
     *
     * @return new RemoveEntityEffect {@link Packet} or {@code null} if an error occurred
     */
    Packet removeEntityEffect(int entityID, PotionEffect effect); // 30

    /**
     * Creates a SetExperience {@link Packet}
     *
     * @param bar
     *         Used for drawing the experience bar - value is between 0 and 1.
     * @param level
     *         the Experience Level
     * @param totalXp
     *         the Total Experience
     *
     * @return new SetExperience {@link Packet} or {@code null} if an error occurred
     */
    Packet setExperience(float bar, int level, int totalXp); // 31

    /**
     * Creates a ChunkData {@link Packet}
     *
     * @param chunk
     *         the {@link Chunk}
     * @param initialize
     *         {@code true} to include initializing; {@code false}
     * @param bitflag
     *         unknown function at this time, either 0x0 or 0x1 observed
     *
     * @return new ChunkData {@link Packet} or {@code null} if an error occurred
     */
    Packet chunkData(Chunk chunk, boolean initialize, int bitflag); // 33

    /**
     * Creates a MultiBlockChange {@link Packet}
     *
     * @param size
     *         the amount of blocks changed
     * @param chunkBlocks
     *         the chunk Block Coordinates compressed into a single short per block
     *         Assuming the condition: x << 12 & 15; z << 8 & 15; y & 255;
     * @param chunk
     *         the {@link Chunk} being changed
     *
     * @return new MultiBlockChange {@link Packet} or {@code null} if an error occurred
     */
    Packet multiBlockChange(int size, short[] blocks, Chunk chunk); //34

    /**
     * Creates a BlockChange {@link Packet}
     *
     * @param x
     *         the Block X Coordinate
     * @param y
     *         the Block Y Coordinate
     * @param z
     *         the Block Z Coordinate
     * @param typeId
     *         the Block type
     * @param data
     *         the Block Data
     *
     * @return new BlockChange {@link Packet} or {@code null} if an error occurred
     */
    Packet blockChange(int x, int y, int z, int typeId, int data); //35

    /**
     * Creates a BlockAction {@link Packet}
     *
     * @param x
     *         the Block X Coordinate
     * @param y
     *         the Block Y Coordinate
     * @param z
     *         the Block Z Coordinate
     * @param targetId
     *         the target Block ID
     * @param stat1
     *         see wiki link below
     * @param stat2
     *         see wiki link below
     *
     * @return new BlockAction {@link Packet} or {@code null} if an error occurred
     *
     * @see http://wiki.vg/Block_Actions
     */
    Packet blockAction(int x, int y, int z, int targetId, int stat1, int stat2); // 36

    /**
     * Creates a BlockBreakAnimation {@link Packet}
     *
     * @param entityId
     *         the Player entity ID
     * @param x
     *         the Block X coordinate
     * @param y
     *         the Block Y coordinate
     * @param z
     *         the Block Z coordinate
     * @param state
     *         the break state (0-7)
     *
     * @return new BlockBreakAnimation {@link Packet} or {@code null} if an error occurred
     */
    Packet blockBreakAnimation(int entityId, int x, int y, int z, int state); // 37

    /**
     * Creates a MapChunkBulk {@link Packet}
     *
     * @param chunks
     *         the list of {@link Chunk}(s)
     *
     * @return new MapChunkBulk {@link Packet} or {@code null} if an error occurred
     */
    Packet mapChunkBulk(List<Chunk> chunks); // 38

    /**
     * Creates a Explosion {@link Packet}
     *
     * @param explodeX
     *         the X coordinate
     * @param explodeY
     *         the Y coordinate
     * @param explodeZ
     *         the Z coordinate
     * @param power
     *         the Explosion size
     * @param affectedPositions
     *         the effected coordinates
     * @param playerVelocity
     *         the player's velocity
     *
     * @return new Explosion {@link Packet} or {@code null} if an error occurred
     */
    Packet explosion(double explodeX, double explodeY, double explodeZ, float power, List<Position> affectedPositions, Vector3D playerVelocity); // 39

    /**
     * Creates a Effect {@link Packet}
     *
     * @param sfxID
     *         the sound/particle effect id
     * @param x
     *         the X coordinate
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     * @param aux
     *         the Auxiliary data
     * @param disableRelVol
     *         {@code true} to disable Relative Volume; {@code false} for don't
     *
     * @return new Effect {@link Packet} or {@code null} if an error occurred
     *
     * @see http://wiki.vg/Protocol#Sound_Or_Particle_Effect_.280x3D.29
     */
    Packet effect(int sfxID, int x, int y, int z, int aux, boolean disableRelVol); // 40

    /**
     * Creates a SoundEffect {@link Packet}
     *
     * @param name
     *         name of the effect
     * @param x
     *         the X coordinate
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     * @param volume
     *         the volume (0.0F - 1.0F)
     * @param pitch
     *         the pitch (63 max)
     *
     * @return new SoundEffect {@link Packet} or {@code null} if an error occurred
     */
    Packet soundEffect(String name, double x, double y, double z, float volume, float pitch); // 41

    /**
     * Creates a Particles {@link Packet}
     * <p/>
     * Information unknown at this time
     *
     * @param name
     * @param f1
     * @param f2
     * @param f3
     * @param f4
     * @param f5
     * @param f6
     * @param f7
     * @param i1
     *
     * @return
     */
    Packet particles(String name, float f1, float f2, float f3, float f4, float f5, float f6, float f7, int i1); // 42

    /**
     * Creates a GameStateChange {@link Packet}
     *
     * @param state
     *         0: Invalid Bed <br/>
     *         1: Begin raining <br/>
     *         2: End raining <br/>
     *         3: Change game mode <br/>
     *         4: Enter credits <br/>
     * @param mode
     *         Used only when state = 3; GameMode 0 1 2
     *
     * @return new ChangeGameState {@link Packet} or {@code null} if an error occurred
     */
    Packet changeGameState(int state, int mode); // 43

    /**
     * Creates a SpawnGlobalEntity {@link Packet}
     * <p/>
     * Currently only LightningBolts use this packet
     *
     * @param entity
     *         the entity to spawn
     *
     * @return new SpawnGlobalEntity
     */
    Packet spawnGlobalEntity(Entity entity); // 44

    /**
     * Creates a OpenWindow {@link Packet}
     * <p/>
     * Would probably be best to use {@link Player#openInventory(net.canarymod.api.inventory.Inventory)}
     *
     * @param windowId
     *         The window Id
     * @param type
     *         the Window(Inventory) Type
     * @param title
     *         the Window title
     * @param slots
     *         the number of slots (typically divisible by 9)
     * @param useTitle
     *         whether to use the given title, or use it for translation
     *
     * @return new OpenWindow {@link Packet}
     *
     * @see http://wiki.vg/Inventory#Windows
     */
    Packet openWindow(int windowId, int type, String title, int slots, boolean useTitle); // 45

    /**
     * Creates a CloseWindow {@link Packet}
     *
     * @param windowId
     *         the Window ID
     *
     * @return new CloseWindow {@link Packet}
     */
    Packet closeWindow(int windowId); // 46

    /**
     * Creates a SetSlot {@link Packet}
     *
     * @param windowId
     *         the Window ID
     * @param slotId
     *         the Slot ID
     * @param item
     *         the {@link Item} to set
     *
     * @return new SetSlot {@link Packet}
     */
    Packet setSlot(int windowId, int slotId, Item item); // 47

    /**
     * Creates a SetWindowItems {@link Packet}
     *
     * @param windowId
     *         the Window ID
     * @param items
     *         the List of Items to be set; nulls are allowed
     *
     * @return new SetWindowItems {@link Packet}
     */
    Packet setWindowItems(int windowId, List<Item> items); // 48

    /**
     * Creates a UpdateWindowProperty {@link Packet}
     *
     * @param windowId
     *         the Window ID
     * @param bar
     *         the Progress Bar type
     * @param value
     *         the Progress Bar value
     *
     * @return new UpdateWindowProperty {@link Packet}
     *
     * @see http://wiki.vg/Protocol#Update_Window_Property_.280x69.29
     */
    Packet updateWindowProperty(int windowId, int bar, int value); // 49

    /**
     * Creates a UpdateSign {@link Packet}
     *
     * @param x
     *         the X coordinate of the Sign
     * @param y
     *         the Y coordinate of the Sign
     * @param z
     *         the Z coordinate of the Sign
     * @param text
     *         the array of text
     *
     * @return new UpdateSign {@link Packet}
     */
    Packet updateSign(int x, int y, int z, String[] text); // 51

    /**
     * Creates an Maps {@link Packet}<br/>
     * Sent to specify complex data on an item; currently used only for maps.
     * <p/>
     * Maps: If the first byte of the text is 0, the next two bytes are X start and Y start and the rest of the bytes are the colors in that column.<br/>
     * If the first byte of the text is 1, the rest of the bytes are in groups of three: (data, x, y).<br/>
     * The lower half of the data is the type (always 0 under vanilla) and the upper half is the direction.
     *
     * @param mapID
     *         the Map ID (#)
     * @param data
     *         the data to specify
     *
     * @return new Maps {@link Packet}
     */
    Packet maps(short mapID, byte[] data); // 52

    /**
     * Creates an UpdateTileEntity {@link Packet}
     *
     * @param x
     *         the X coordinate
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     * @param action
     *         the action taken (1: MobSpawner Entites set)
     * @param compoundTag
     *         NBT Data to apply
     *
     * @return new UpdateTileEntity {@link Packet}
     */
    Packet updateTileEntity(int x, int y, int z, int action, CompoundTag compoundTag); // 53

    /**
     * Creates a SignEditorOpen {@link Packet}
     *
     * @param x
     *         the X coordinate
     * @param y
     *         the Y coordinate
     * @param z
     *         the Z coordinate
     *
     * @return new SignEditorOpen {@link Packet}
     */
    Packet signEditorOpen(int x, int y, int z); // 54

    /**
     * Creates a Statistics {@link Packet}
     *
     * @param stats
     *         map of stats
     *
     * @return new Statistics {@link Packet}
     *
     * @see http://www.minecraftwiki.net/wiki/Statistics
     */
    Packet statistics(Map<Stat, Integer> stats); // 55

    /**
     * Creates a playerListItem {@link Packet}
     *
     * @param name
     *         the Player's name
     * @param connected
     *         {@code true} for connected; {@code false} for not (Note: False will remove the player from the player list)
     * @param ping
     *         the Player's Ping
     *
     * @return new playerListItem {@link Packet}
     */
    Packet playerListItem(String name, boolean connected, int ping); // 56
}
