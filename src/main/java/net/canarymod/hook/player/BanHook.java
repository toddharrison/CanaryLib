package net.canarymod.hook.player;

import net.canarymod.api.PlayerReference;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.hook.Hook;

/**
 * Ban hook. Contains information about an issued Ban
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 */
public final class BanHook extends Hook {
    private PlayerReference banned;
    private MessageReceiver moderator;
    private String ip;
    private String reason;
    private boolean ipban;
    private long bantime;

    public BanHook(PlayerReference banned, String ip, MessageReceiver moderator, String reason, long bantime) {
        this.banned = banned;
        this.moderator = moderator;
        this.reason = reason;
        this.ip = ip;
        this.ipban = ip != null;
        this.bantime = bantime;
    }

    /**
     * Get the {@link PlayerReference} that has been banned.
     * This might be null if the banned player is offline/not currently on the server
     *
     * @return banned if not ipban, null otherwise
     */
    public PlayerReference getBannedPlayer() {
        return banned;
    }

    /**
     * Checks if this is an IP ban
     *
     * @return true if is IP ban
     */
    public boolean isIpBan() {
        return ipban;
    }

    /**
     * Gets the banned IP address
     *
     * @return ip if is ipban, null otherwise
     */
    public String getIp() {
        return ip;
    }

    /**
     * Get the {@link MessageReceiver} that has issued the ban.
     *
     * @return moderator
     */
    public MessageReceiver getModerator() {
        return moderator;
    }

    /**
     * Gets the reason for the ban
     *
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * Return the ban time as unix timestamp.
     * This is the amount of seconds this ban lasts
     *
     * @return the bantime, -1 if ban is a perma-ban
     */
    public long getBantime() {
        return bantime;
    }

    @Override
    public final String toString() {
        return String.format("%s[Banned=%s, Moderator=%s, isIPBan=%b, Reason=%s, BanTime=%s]", getHookName(), banned, moderator, ipban, reason, bantime);
    }
}
