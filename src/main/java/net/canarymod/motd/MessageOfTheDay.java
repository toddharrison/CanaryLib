package net.canarymod.motd;

import net.canarymod.chat.MessageReceiver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static net.canarymod.Canary.log;

/**
 * Message of the Day container
 * <p/>
 * Loads and sends the Message of the Day to a player just joining.
 *
 * @author Jason (darkdiplomat)
 */
public class MessageOfTheDay {
    private static final List<String> motd_lines;
    private static final List<MOTDParser> motd_vars;

    static {
        motd_lines = Collections.synchronizedList(new ArrayList<String>());
        motd_vars = Collections.synchronizedList(new ArrayList<MOTDParser>());
    }

    public MessageOfTheDay() {
        try {
            loadMOTD();
        }
        catch (Exception ex) {
            log.error("Failed to read/write Message of the Day from/to the motd.txt file.", ex);
        }
    }

    private void loadMOTD() throws IOException {
        File motd_file = new File("config/motd.txt");
        if (!motd_file.exists()) {
            if (!motd_file.createNewFile()) {
                return;
            }
            PrintWriter writer = new PrintWriter(new FileWriter(motd_file));
            writer.println("# (Login) Message of the Day");
            writer.println("# See forums thread http://forums.canarymod.net/?topic=3619");
            writer.println("# for the list of default variables");
            writer.println("# or the plugins threads that add-on to the Message Of The Day");
            writer.println("# Lines may be prefixed with {permission:<[node] or ![node] or [node]&[node]&![node]>}");
            writer.println("# Examples: {permission:canary.super.administor} {permission:canary.world.build&!canary.super.administrator}");
            writer.println("# # # # #");
            writer.flush();
            writer.close();
        }
        else {
            FileInputStream fis = new FileInputStream(motd_file);
            Scanner scanner = new Scanner(fis, "UTF-8");

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.startsWith("#")) {
                    continue;
                }
                motd_lines.add(line);
            }
            scanner.close();
            fis.close();
        }
    }

    /**
     * Sends the MOTD to a {@link MessageReceiver}
     *
     * @param msgrec
     *         the {@link MessageReceiver} who will receive the MOTD
     */
    public void sendMOTD(MessageReceiver msgrec) {
        synchronized (motd_lines) {
            for (String line : motd_lines) {
                String toSend = line;
                if (toSend.matches("\\{permissions:(.)+}.+")) {
                    String perms = toSend.substring(toSend.indexOf(':') + 1, toSend.indexOf('}'));
                    permsParse:
                    {
                        if (perms.contains("&")) {
                            String[] permissions = perms.split("&");
                            multiParse:
                            {
                                for (String permission : permissions) {
                                    if (permission.charAt(0) == '!' && msgrec.hasPermission(permission.substring(1))) {
                                        break multiParse; // No good, continue to next line
                                    }
                                    else if (!msgrec.hasPermission(permission)) {
                                        break multiParse; // No good, continue to next line
                                    }
                                }
                                break permsParse; // All good, continue regular parsing
                            }
                        }
                        else if (perms.charAt(0) == '!') {
                            if (!msgrec.hasPermission(perms.substring(1))) {
                                break permsParse; // All good, continue regular parsing
                            }
                        }
                        else if (msgrec.hasPermission(perms)) {
                            break permsParse; // All good, continue regular parsing
                        }
                        continue; // No good, continue to next line
                    }
                    toSend = toSend.replace(toSend.substring(0, toSend.indexOf('}') + 1), ""); // Remove permission check substring
                }
                // Parse variables
                synchronized (motd_vars) {
                    for (MOTDParser motdp : motd_vars) {
                        try {
                            toSend = toSend.replace(motdp.key(), motdp.parse(msgrec));
                        }
                        catch (Exception ex) {
                            log.error("Failed to parse MessageOfTheDay Variable from MOTDOwner: " + motdp.getOwner().getName(), ex);
                        }
                    }
                }

                //Replace Color codes
                toSend = toSend.replaceAll("&([0-9A-FK-ORa-fk-or])", "\u00A7$1");
                msgrec.message(toSend);
            }
        }
    }

    /**
     * Registers a new {@link MessageOfTheDayListener} to be used with the MessageOfTheDay
     *
     * @param listner
     *         the {@link MessageOfTheDayListener} to be added
     * @param owner
     *         the {@link MOTDOwner} (either the server or a plugin)
     * @param force
     *         {@code true} to override existing keys if existant; {@code false} to error out on duplicate keys (recommended)
     */
    public void registerMOTDListener(final MessageOfTheDayListener listener, final MOTDOwner owner, final boolean force) {
        Method[] methods = listener.getClass().getDeclaredMethods();
        ArrayList<MOTDParser> newParsers = new ArrayList<MOTDParser>();

        for (final Method method : methods) {
            if (!method.isAnnotationPresent(MOTDKey.class)) {
                continue;
            }
            if (method.getReturnType() != String.class) {
                log.warn("You have a MOTD Listner method with invalid return type! (" + method.getName() + ") (Expected: String Got: " + method.getReturnType().getName() + ")");
                continue;
            }
            Class<?>[] params = method.getParameterTypes();
            if (params.length != 1) {
                log.warn("You have a MOTD Listner method with invalid number of arguments! (" + method.getName() + ") (Expected: 1 Got: " + params.length + ")");
                continue;
            }
            if (!MessageReceiver.class.isAssignableFrom(params[0])) {
                log.warn("You have a MOTD method with invalid argument types! - " + method.getName());
                continue;
            }
            MOTDKey meta = method.getAnnotation(MOTDKey.class);
            MOTDParser motdp = new MOTDParser(meta.key(), owner) {
                @Override
                String parse(MessageReceiver msgrec) throws Exception {
                    return (String) method.invoke(listener, new Object[]{ msgrec });
                }
            };

            // Check for duplicate keys
            for (MOTDParser parser : motd_vars) {
                if (meta.key().equals(parser.key()) && !force) {
                    log.warn(owner.getName() + " attempted to register MOTDKey: '" + meta.key() + "' but it is already registered to " + parser.getOwner().getName());
                    continue;
                }
            }
            motd_vars.add(motdp);
        }
    }

    /**
     * Removes all of a {@link MOTDOwner}'s {@link MessageOfTheDayListener} methods from the MessageOfTheDay list
     *
     * @param owner
     *         the {@link MOTDOwner} to have {@link MessageOfTheDayListener} methods removed
     */
    public void unregisterMOTDListener(MOTDOwner owner) {
        synchronized (motd_vars) {
            Iterator<MOTDParser> motdpItr = motd_vars.iterator();
            while (motdpItr.hasNext()) {
                if (motdpItr.next().getOwner() == owner) { // Yes, memory address exact
                    motdpItr.remove();
                }
            }
        }
    }

    public void reload() {
        motd_lines.clear();
        try {
            loadMOTD();
        }
        catch (Exception ex) {
            log.error("Failed to read/write Message of the Day from/to the motd.txt file.", ex);
        }
    }
}
