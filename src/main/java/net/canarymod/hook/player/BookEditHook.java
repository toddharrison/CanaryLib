package net.canarymod.hook.player;

import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.canarymod.hook.CancelableHook;

/**
 * Called when a {@link Player} edits a Book {@link Item}
 *
 * @author Jason (darkdiplomat)
 */
public class BookEditHook extends CancelableHook {
    private final Item book;
    private final Player player;

    public BookEditHook(Item book, Player player) {
        if (book.getType() != ItemType.BookAndQuill) {
            throw new IllegalArgumentException("Item param was not of ItemType-BookAndQuill");
        }
        this.book = book;
        this.player = player;
    }

    /**
     * Gets the {@link Player} editting the Book
     *
     * @return the player doing the editting
     */
    public final Player getPlayer() {
        return player;
    }

    /**
     * Gets the Book {@link Item} being editted
     *
     * @return the book being editted
     */
    public final Item getBook() {
        return book;
    }

    /**
     * {@inheritDoc}
     */
    public final String toString() {
        return String.format("%s[Book=%s, Player=%s]", getHookName(), book, player);
    }
}
