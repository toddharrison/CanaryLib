package net.canarymod.commandsys;

import java.lang.annotation.Annotation;

/**
 * A dynamic TabComplete annotation, used by the non-java based command system
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
