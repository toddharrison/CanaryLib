package net.canarymod.hook.system;

import net.canarymod.hook.CancelableHook;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Called when a client ping the server
 *
 * @author greatman
 * @author Jason (darkdiplomat)
 */
public class ServerListPingHook extends CancelableHook {

    private String motd, favicon;
    private int maxPlayers, currentPlayers;

    public ServerListPingHook(String motd, int currentPlayers, int maxPlayers, String favicon) {
        this.motd = motd;
        this.maxPlayers = maxPlayers;
        this.currentPlayers = currentPlayers;
        this.favicon = favicon;
    }

    /**
     * Retrieve the MOTD that will be sent to the client
     *
     * @return The MOTD that will be sent to the client
     */
    public String getMotd() {
        return motd;
    }

    /**
     * Retrieve the maximum amount of players the server allows that will be sent to the client.
     *
     * @return The maximum amount of players
     */
    public int getMaxPlayers() {
        return maxPlayers;
    }

    /**
     * Retrieve the current amount of players connected to the server that will be sent to the client.
     *
     * @return The current amount of players
     */
    public int getCurrentPlayers() {
        return currentPlayers;
    }

    /**
     * Set the MOTD that will be sent to the client
     *
     * @param motd
     *         The new MOTD
     */
    public void setMotd(String motd) {
        this.motd = motd;
    }

    /**
     * Set the maximum amount of player the server allows that will be sent to the client. <b>Please note that this only fakes the value. It doesn't modify the real value.</b>
     *
     * @param maxPlayers
     *         The maximum amount of player the server allows
     */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * Set the current amount of players connected to the server that will be sent to the client
     *
     * @param currentPlayers
     *         the current amount of players connected to the server
     */
    public void setCurrentPlayers(int currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    /**
     * Returns the Favicon encoded string to be sent
     *
     * @return the favicon string
     */
    public String getFavicon() {
        return favicon;
    }

    /**
     * Sets the favicon encoded string.<br/>
     * The string should be formatted as data:image/png;base64,[base64encodedImage].<br/>
     *
     * @param favicon
     *         the base64 encoded PNG file
     */
    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    /**
     * Sets the server favicon from a given file path
     *
     * @param file
     *         the path to the png image file
     *
     * @throws IOException
     */
    public void setFaviconFromFile(String file) throws IOException {
        if (!file.endsWith(".png"))
            throw new IOException("Image needs to be in PNG format");
        File favicon = new File(".", file);
        if (!favicon.isFile())
            throw new IOException("Image does not exist");
        BufferedImage bufferedimage = ImageIO.read(favicon);
        if (bufferedimage.getWidth() != 64 || bufferedimage.getHeight() != 64)
            throw new IOException("Image needs to be 64x64 pixels");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bufferedimage, "PNG", bos);
        byte[] imageBytes = bos.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        this.favicon = "data:image/png;base64," + encoder.encode(imageBytes);
        bos.close();
    }
}
