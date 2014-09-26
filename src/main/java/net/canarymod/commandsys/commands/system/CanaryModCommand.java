package net.canarymod.commandsys.commands.system;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.TextFormat;
import net.canarymod.commandsys.NativeCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Command to get information about CanaryMod
 *
 * @author Jason (darkdiplomat)
 */
public class CanaryModCommand implements NativeCommand {
    private final static List<String> information;

    static {
        ArrayList<String> temp = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();
        temp.add(builder.append(TextFormat.LIGHT_RED).append("---- ").append(TextFormat.ORANGE).append(Canary.getImplementationTitle()).append(" ").append(Canary.getImplementationVersion()).append(TextFormat.LIGHT_RED).append(" ----").toString());
        builder.delete(0, builder.length());
        temp.add(builder.append(TextFormat.ORANGE).append("Project Leaders: ").append(TextFormat.WHITE).append("damagefilter, darkdiplomat").toString());
        builder.delete(0, builder.length());
        temp.add(builder.append(TextFormat.ORANGE).append("Lead Programmer: ").append(TextFormat.WHITE).append("darkdiplomat").toString());
        builder.delete(0, builder.length());
        temp.add(builder.append(TextFormat.ORANGE).append("Programmers: ").append(TextFormat.WHITE).append("14mRh4X0r, EHudB, Larry1123, MossyBlog, Pwootage, somners").toString());
        builder.delete(0, builder.length());
        temp.add(builder.append(TextFormat.ORANGE).append("With Contributions by: ").append(TextFormat.WHITE).append("ickyacky, Shadow386, gregthegeek, WWOL, joskuijpers, greatman, nosefish, YLivay, BluXDragon, Hidendra, Tux2, and NiccosSystem").toString());
        builder.delete(0, builder.length());
        temp.add(builder.append(TextFormat.ORANGE).append("WebSite: ").append(TextFormat.WHITE).append("http://canarymod.net").toString());
        builder.delete(0, builder.length());
        temp.add(builder.append(TextFormat.ORANGE).append("GitHub: ").append(TextFormat.WHITE).append("http://git.io/tuPNQw").toString());
        builder.delete(0, builder.length());
        temp.add(builder.append(TextFormat.LIGHT_RED).append("  Copyright (c) 2012-2014 ").append(TextFormat.ORANGE).append("CanaryMod Team").toString());
        builder.delete(0, builder.length());
        temp.add(builder.append(TextFormat.LIGHT_RED).append("  Under the management of ").append(TextFormat.ORANGE).append("PlayBlack and Visual Illusions Ent.").toString());
        builder.delete(0, builder.length());
        temp.add(builder.append(TextFormat.LIGHT_RED).append("  Licensed under the BSD 3-Clause License  ").toString());
        builder.delete(0, builder.length());
        temp.add(builder.append(TextFormat.BLUE).append("  CanaryMod is NOT affiliated with, endorsed, or sponsored by Mojang AB ").toString());
        information = Collections.unmodifiableList(temp);
    }

    public void execute(MessageReceiver caller, String[] args) {
        for (String msg : information) {
            caller.message(msg);
        }
    }

}
