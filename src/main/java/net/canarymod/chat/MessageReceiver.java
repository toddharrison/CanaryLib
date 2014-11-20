package net.canarymod.chat;

/**
 * Callback interface for commands.
 *
 * @author Willem (l4mRh4X0r)
 * @author Larry1123
 * @author Jason (darkdiplomat)
 */
public interface MessageReceiver {

    /**
     * Returns the name of this <tt>MessageReceiver</tt>.
     *
     * @return the <tt>MessageReceiver</tt>'s name.
     */
    String getName();

    /**
     * Sends a message to this <tt>MessageReceiver</tt>.
     *
     * @param message
     *         The message to send.
     */
    void notice(CharSequence message);

    /**
     * Sends a message(s) to this <tt>MessageReceiver</tt>.
     *
     * @param messages
     *         The message(s) to send.
     */
    void notice(CharSequence... messages);

    /**
     * Sends a message(s) to this <tt>MessageReceiver</tt>.
     *
     * @param messages
     *         The message(s) to send.
     */
    void notice(Iterable<? extends CharSequence> messages);

    /**
     * Sends a message to this <tt>MessageReceiver</tt>.
     *
     * @param message
     *         The message to send.
     */
    void message(CharSequence message);

    /**
     * Sends a message(s) to this <tt>MessageReceiver</tt>.
     *
     * @param messages
     *         The message(s) to send.
     */
    void message(CharSequence... messages);

    /**
     * Sends a message(s) to this <tt>MessageReceiver</tt>.
     *
     * @param messages
     *         The message(s) to send.
     */
    void message(Iterable<? extends CharSequence> messages);

    /**
     * Check if the {@link MessageReceiver} has this permission.
     * This will issue a PermissionCheck hook that means,
     * the returned result is not reliable.
     * However, this allows other Plugins to have a say in the permission lookup process.
     *
     * @param node
     *         The node to check for.
     *
     * @return <tt>true</tt> if this <tt>MessageReceiver</tt> has the given
     * permission, <tt>false</tt> otherwise.
     */
    boolean hasPermission(String node);

    /**
     * Check if a {@link MessageReceiver} has this permission.
     * This will not issue a PermissionCheck hook so the returned
     * result is reliable.
     *
     * @param permission
     *         ther permission node to check for
     *
     * @return <tt>true</tt> if this <tt>MessageReceiver</tt> has the given
     * permission, <tt>false</tt> otherwise.
     */
    boolean safeHasPermission(String permission);

    /**
     * Gets the type of MessageReceiver the instance is
     *
     * @return receiver type
     */
    ReceiverType getReceiverType();

    /**
     * Gets the locale the MessageReceiver is using in their client. This is in the format of language_region e.g. en_US
     *
     * @return the MessageReceiver's locale
     */
    String getLocale();
}
