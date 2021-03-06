package net.canarymod.api.world.position;

import net.canarymod.ToolBox;
import net.canarymod.api.world.blocks.BlockFace;
import net.canarymod.database.PositionDataAccess;

/**
 * Position is a x, y, z triple
 *
 * @author Chris (damagefilter)
 */
public class Position implements Cloneable {
    protected double x, y, z;

    public Position(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Position() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    /**
     * Copy constructor copies the primitives
     *
     * @param templ
     */
    public Position(Position templ) {
        this.x = templ.x;
        this.y = templ.y;
        this.z = templ.z;
    }

    /**
     * Retrieve X component of Vector
     *
     * @return double x
     */
    public double getX() {
        return x;
    }

    /**
     * Returns a reliable block ordinate
     *
     * @return
     */
    public int getBlockX() {
        return ToolBox.floorToBlock(x);
    }

    /**
     * Set x component with native double
     *
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set x component with a int2double conversion
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Retrieve Y component of Vector
     *
     * @return double y
     */
    public double getY() {
        return y;
    }

    /**
     * Returns a reliable block ordinate
     *
     * @return
     */
    public int getBlockY() {
        return ToolBox.floorToBlock(y);
    }

    /**
     * Set y component with native double
     *
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Set y component with a int2double conversion
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Retrieve Z component of Vector
     *
     * @return double z
     */
    public double getZ() {
        return z;
    }

    /**
     * Returns a reliable block ordinate
     *
     * @return
     */
    public int getBlockZ() {
        return ToolBox.floorToBlock(z);
    }

    /**
     * Set y component with native double
     *
     * @param z
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Set z component with a int2double conversion
     *
     * @param z
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * Transforms this position 1 block in the given {@link BlockFace} direction
     *
     * @param face
     *         the {@link BlockFace} used to move the position
     */
    public void transform(BlockFace face) {
        switch (face) {
            case TOP:
                ++this.y;
                break;
            case BOTTOM:
                --this.y;
                break;
            case NORTH: // -Z
                --this.z;
                break;
            case SOUTH: // +Z
                ++this.z;
                break;
            case WEST: // -X
                --this.x;
                break;
            case EAST: // +X
                ++this.x;
                break;
            default:
                break;
        }
    }

    /**
     * Moves the position a specified amount in the X direction
     *
     * @param x
     *         the amount to move X-wise
     */
    public void moveX(int x) {
        this.x += x;
    }

    /**
     * Moves the position a specified amount in the X direction
     *
     * @param x
     *         the amount to move X-wise
     */
    public void moveX(double x) {
        this.x += x;
    }

    /**
     * Moves the position a specified amount in the Y direction
     *
     * @param y
     *         the amount to move Y-wise
     */
    public void moveY(int y) {
        this.y += y;
    }

    /**
     * Moves the position a specified amount in the Y direction
     *
     * @param y
     *         the amount to move Y-wise
     */
    public void moveY(double y) {
        this.y += y;
    }

    /**
     * Moves the position a specified amount in the Z direction
     *
     * @param z
     *         the amount to move Z-wise
     */
    public void moveZ(int z) {
        this.z += z;
    }

    /**
     * Moves the position a specified amount in the Z direction
     *
     * @param z
     *         the amount to move Z-wise
     */
    public void moveZ(double z) {
        this.z += z;
    }

    /**
     * Moves the position a specified amount in all directions
     *
     * @param x
     *         the amount to move X-wise
     * @param y
     *         the amount to move Y-wise
     * @param z
     *         the amount to move Z-wise
     */
    public void move(int x, int y, int z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    /**
     * Moves the position a specified amount in all directions
     *
     * @param x
     *         the amount to move X-wise
     * @param y
     *         the amount to move Y-wise
     * @param z
     *         the amount to move Z-wise
     */
    public void move(double x, double y, double z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public PositionDataAccess toDataAccess(PositionDataAccess pda) {
        pda.posX = this.x;
        pda.posY = this.y;
        pda.posZ = this.z;
        return pda;
    }

    public static Position fromDataAccess(PositionDataAccess pda) {
        Position pos = new Position(0, 0, 0);
        pos.setX(pda.posX);
        pos.setY(pda.posY);
        pos.setZ(pda.posZ);
        return pos;
    }

    /**
     * Checks if another object equals this one
     *
     * @param obj
     *
     * @return whether the other object has the same values for x,y,z
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }
        Position other = (Position)obj;

        return other.getX() == this.x && other.getY() == this.y && other.getZ() == this.z;
    }

    /**
     * Return a hashcode for this object
     */
    @Override
    public int hashCode() {
        int hash = 3;

        hash = (int)(hash + x);
        hash = (int)(hash + y);
        hash = (int)(hash + z);
        return hash;
    }

    public String toString() {
        return String.format("%.4f:%.4f:%.4f", this.x, this.y, this.z);
    }

    @Override
    public Position clone() throws CloneNotSupportedException {
        // Since we have only primitive/immutable fields, calling super.clone is fine
        return (Position)super.clone();
    }

    public Position copy() {
        try {
            return this.clone();
        }
        catch (CloneNotSupportedException e) {
            // it is supported...
        }
        return new Position(this);
    }
}
