package net.canarymod.commandsys;

import java.lang.annotation.Annotation;

/**
 * Copyright (C) 2014 Visual Illusions Entertainment
 * All Rights Reserved.
 *
 * @author Jason Jones (darkdiplomat)
 */
public class DynamicTabCompleteAnnotation implements TabComplete {
    private final String[] commands;

    public DynamicTabCompleteAnnotation(String... commands) {
        this.commands = commands;
    }

    @Override
    public String[] commands() {
        return commands;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return TabComplete.class;
    }
}
