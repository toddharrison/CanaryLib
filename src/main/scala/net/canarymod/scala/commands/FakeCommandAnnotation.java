package net.canarymod.scala.commands;

import net.canarymod.commandsys.Command;

import java.lang.annotation.Annotation;

/**
 * A fake Command annotation, used by the scala command system
 *
 * @author Pwootage
 */
public class FakeCommandAnnotation implements Command {
    private final String[] aliases;
    private final String[] permissions;
    private final String description;
    private final String toolTip;
    private final String parent;
    private final String helpLookup;
    private final String[] searchTerms;
    private final int min;
    private final int max;
    private final String tabCompleteMethod;

    public FakeCommandAnnotation(String[] aliases, String[] permissions, String description, String toolTip, String parent, String helpLookup, String[] searchTerms, int min, int max, String tabCompleteMethod) {
        this.aliases = aliases;
        this.permissions = permissions;
        this.description = description;
        this.toolTip = toolTip;
        this.parent = parent;
        this.helpLookup = helpLookup;
        this.searchTerms = searchTerms;
        this.min = min;
        this.max = max;
        this.tabCompleteMethod = tabCompleteMethod;
    }

    @Override
    public String[] aliases() {
        return aliases;
    }

    @Override
    public String[] permissions() {
        return permissions;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String toolTip() {
        return toolTip;
    }

    @Override
    public String parent() {
        return parent;
    }

    @Override
    public String helpLookup() {
        return helpLookup;
    }

    @Override
    public String[] searchTerms() {
        return searchTerms;
    }

    @Override
    public int min() {
        return min;
    }

    @Override
    public int max() {
        return max;
    }

    @Override
    public String tabCompleteMethod() {
        return tabCompleteMethod;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return Command.class;
    }
}
