package net.canarymod;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.api.world.position.Location;

/**
 * Traces the line of sight of an entity.
 * You can retrieve any blocks along the Line of Sight or simply the last block
 * there is within a specified range. Range defaults to 200 blocks
 *
 * @author Ho0ber
 */
public class LineTracer {
    private Location playerLoc;
    private Block currentBlock, lastBlock;
    private double rotX, rotY, viewHeight;
    private double length, step;
    private int range;
    private double xOffset, yOffset, zOffset;
    private double last_x, last_y, last_z;
    private double currentX, currentY, currentZ;

    /**
     * Constructor requiring player, uses default values
     *
     * @param in_player
     *         the {@link Player} to check Line of Sight for
     */
    public LineTracer(Player in_player) {
        Location loc = in_player.getLocation();
        loc.setRotation(in_player.getHeadRotation());
        init(loc, 300, 0.2, in_player.getEyeHeight()); // Reasonable default values
    }

    /**
     * Constructor requiring location, uses default values
     *
     * @param in_location
     *         the {@link Location} to check Line of Sight for
     */
    public LineTracer(Location in_location) {
        init(in_location, 300, 0.2, 0);
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
    public LineTracer(Player in_player, int in_range, double in_step) {
        Location loc = in_player.getLocation();
        loc.setRotation(in_player.getHeadRotation());
        init(loc, in_range, in_step, in_player.getEyeHeight());
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
    public LineTracer(Location in_location, int in_range, double in_step) {
        init(in_location, in_range, in_step, 0);
    }

    /**
     * Initialization method
     *
     * @param in_location
     *         the {@link Location} to check Line of Sight for
     * @param in_range
     *         the maximum range to check
     * @param in_step
     *         the stepping value, the amount Y to increase/decrease the further away the checks get
     * @param in_view_height
     *         the View Height to use, a {@link Player}'s view height is typically 1.62
     */
    public void init(Location in_location, int in_range, double in_step, double in_view_height) {
        playerLoc = in_location;
        viewHeight = in_view_height;
        range = in_range;
        step = in_step;
        length = 0;
        /* Convert these to real world math numbers */
        
        /* convert negative rotation values to positive */
        rotX = playerLoc.getRotation();
        rotX = rotX < 0 ? (360 + rotX) : rotX;
        /* convert minecraft pitch to degree pitch */
        rotY = playerLoc.getPitch() * -1;
        if (rotY < 0) rotY = 270 + (90 + rotY);
        
        currentX = playerLoc.getX();
        /* Add Eye Height to the Y */
        currentY = playerLoc.getY() + viewHeight;
        currentZ = playerLoc.getZ();
        last_x = currentX;
        last_y = currentY;
        last_z = currentZ;
        
    }

    /**
     * Returns the block at the cursor, or null if out of range
     *
     * @return the Target {@link Block}
     */
    public Block getTargetBlock() {
        while ((getNextBlock() != null) && (getCurBlock().getTypeId() == 0)) {
            /* do nothing, just skip to next iteration */
        }
        return getCurBlock();
    }

    /**
     * Sets the type of the block at the cursor
     *
     * @param type
     *         the {@link Block} type id
     */
    public void setTargetBlock(int type) {
        while ((getNextBlock() != null) && (getCurBlock().getTypeId() == 0)) {
            ;
        }
        if (getCurBlock() != null) {
            playerLoc.getWorld().setBlockAt(currentBlock.getX(), currentBlock.getY(), currentBlock.getZ(), (short) type);
        }
    }

    /**
     * Returns the block attached to the face at the cursor, or null if out of range
     *
     * @return the face {@link Block}
     */
    public Block getFaceBlock() {
        while ((getNextBlock() != null) && (getCurBlock().getTypeId() == 0)) {
            ;
        }
        if (getCurBlock() != null) {
            return getLastBlock();
        }
        else {
            return null;
        }
    }

    /**
     * Sets the type of the block attached to the face at the cursor
     *
     * @param type
     *         the {@link Block} type id
     */
    public void setFaceBlock(int type) {
        while ((getNextBlock() != null) && (getCurBlock().getTypeId() == 0)) {
            ;
        }
        if (getCurBlock() != null) {
            playerLoc.getWorld().setBlockAt((int)last_x, (int)last_y, (int)last_z, (short) type);
        }
    }

    /**
     * Returns STEPS forward along line of vision and returns block
     *
     * @return the next {@link Block}
     */
    public Block getNextBlock() {
        Block block = null;
        /* We already found the target block, return null */
        if (currentBlock != null && !currentBlock.isAir()) return null;
        
        somnersMadness:
        while ((length <= range) && (currentBlock == null || block == null || currentBlock.equals(block))) {
            length += step;
            yOffset = (step * Math.sin(Math.toRadians(rotY)));
            zOffset = (step * Math.cos(Math.toRadians(rotX)));
            xOffset = (step * Math.sin(Math.toRadians(rotX)));
            currentX = (-1 * xOffset) + currentX;// stuff is backwards, multiply by -1
            currentY = yOffset + currentY;
            currentZ = zOffset + currentZ;
            
            block = playerLoc.getWorld().getBlockAt(ToolBox.floorToBlock(currentX),ToolBox.floorToBlock(currentY), ToolBox.floorToBlock(currentZ));
            
            if (block != null && !block.equals(currentBlock)) {
                /* set last values to current values */
                lastBlock = currentBlock;
                currentBlock = block;
                last_x = currentX;
                last_y = currentY;
                last_z = currentZ;
                break somnersMadness;
            }
        }
        return block;
    }

    /**
     * Returns the current {@link Block} along the line of vision
     *
     * @return the current {@link Block}
     */
    public Block getCurBlock() {
        return currentBlock;
    }

    /**
     * Sets current block type id
     *
     * @param type
     *         the {@link Block} type id
     */
    public void setCurBlock(int type) {
        if (getCurBlock() != null) {
            playerLoc.getWorld().setBlockAt(ToolBox.floorToBlock(currentX), ToolBox.floorToBlock(currentY), ToolBox.floorToBlock(currentZ), (short) type);
        }
    }

    /**
     * Returns the previous block along the line of vision
     *
     * @return the last {@link Block} could be null if this is first iteration
     */
    public Block getLastBlock() {
        return lastBlock;
    }

    /**
     * Sets previous block type id
     *
     * @param type
     *         the {@link Block} type id
     */
    public void setLastBlock(int type) {
        if (getLastBlock() != null) {
            playerLoc.getWorld().setBlockAt(lastBlock.getX(), lastBlock.getY(), lastBlock.getZ(), (short) type);
        }
    }
}
