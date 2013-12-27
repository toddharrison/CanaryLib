package net.canarymod.api;

import net.canarymod.chat.MessageReceiver;
import net.canarymod.user.Group;

/**
 * Command Block Logical Interface wrapping
 *
 * @author Jason (darkdiplomat)
 */
public interface CommandBlockLogic extends MessageReceiver {

    /**
     * Sets the CommandBlock's command
     *
     * @param command
     *         the command to execute when this block is activated
     */
    public void setCommand(String command);

    /**
     * Returns the CommandBlock's command
     *
     * @return the command
     */
    public String getCommand();

    /** Run this CommandBlock's command */
    public void activate();

    /**
     * Returns the group that is used to handle CommandBlock permissions.
     *
     * @return the Group
     */
    public Group getGroup();

    /**
     * Sets the group that is used to handle CommandBlock permissions,
     * but only for this command block.
     *
     * @param group
     *         the {@link Group} to give the CommandBlock
     */
    public void setGroup(Group group);
}
