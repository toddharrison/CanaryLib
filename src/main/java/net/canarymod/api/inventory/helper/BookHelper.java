package net.canarymod.api.inventory.helper;

import java.util.Arrays;
import java.util.List;
import net.canarymod.api.inventory.Enchantment;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.canarymod.api.nbt.CompoundTag;
import net.canarymod.api.nbt.ListTag;
import net.canarymod.api.nbt.StringTag;

/**
 * BOOK! HELPER!
 * 
 * @author Jason (darkdiplomat)
 */
public class BookHelper extends ItemHelper {

    private final static short MAX_PAGE_LENGTH = 255; // Roughly the most characters visible on the page
    private final static byte MAX_AUTHOR_LENGTH = 16; // The max length of a player's name
    private final static byte MAX_TITLE_LENGTH = 40; // The max length of an anvil input
    private final static ListTag<StringTag> PAGES_TAG = NBT_FACTO.newListTag("pages");
    private final static StringTag PAGE_TITLE_AUTHOR = NBT_FACTO.newStringTag("page%d", "");
    private final static ListTag<CompoundTag> STORED_ENCH_TAG = NBT_FACTO.newListTag("StoredEnchantments");
    private final static CompoundTag ENCH_TAG = NBT_FACTO.newCompoundTag("ench");

    private BookHelper() {} // This class should never be constructed

    /**
     * Checks if the give book can take Meta data
     * 
     * @param book
     *            the book to check
     * @return {@code true} if Book; {@code false} if not
     */
    public static boolean isBook(Item book) {
        if (book == null) {
            return false;
        }
        switch (book.getId()) {
            case 340:
            case 386:
            case 387:
            case 403:
                return true;
            default:
                return false;
        }
    }

    /**
     * Checks if the given Book can still be written in by a player
     * 
     * @param book
     *            the book to check
     * @return {@code true} if can be written in; {@code false} if not
     */
    public static boolean isWritable(Item book) {
        return book == null ? false : book.getType() == ItemType.BookAndQuill;
    }

    /**
     * Checks if the given Book has been signed
     * 
     * @param book
     *            the book to check
     * @return {@code true} if signed; {@code false} if not
     */
    public static boolean isSigned(Item book) {
        return book == null ? false : book.getType() == ItemType.WrittenBook;
    }

    /**
     * |
     * Checks if the given Book is enchanted
     * 
     * @param book
     *            the book to check
     * @return {@code true} if enchanted; {@code false} if not
     */
    public static boolean isEnchanted(Item book) {
        return book == null ? false : book.getType() == ItemType.EnchantedBook;
    }

    /**
     * Checks if the given Book has pages
     * 
     * @param book
     *            the book to check
     * @return {@code true} if has pages; {@code false} if not
     */
    public static boolean hasPages(Item book) {
        if (book == null) {
            return false;
        }
        if (book.getType() != ItemType.BookAndQuill && book.getType() != ItemType.WrittenBook) {
            return false;
        }
        if (!book.hasDataTag()) {
            return false;
        }
        if (!book.getDataTag().containsKey("pages")) {
            return false;
        }
        return !book.getDataTag().getListTag("pages").isEmpty();
    }

    /**
     * Gets the specified page number of the Book
     * 
     * @param book
     *            the book to get a page of
     * @param page
     *            the page number to get
     * @return the text of the page or {@code null} if invalid
     */
    public static String getPage(Item book, int page) {
        if (book == null) {
            return null;
        }
        if (book.getType() != ItemType.BookAndQuill && book.getType() != ItemType.WrittenBook) {
            return null;
        }
        if (!book.hasDataTag()) {
            return null;
        }
        if (!book.getDataTag().containsKey("pages")) {
            return null;
        }
        if (!isValidPage(page, getPageCount(book))) {
            return null;
        }
        return ((StringTag) book.getDataTag().getListTag("pages").get(page)).getValue();
    }

    /**
     * Gets a String array of pages from the book
     * 
     * @param book
     *            the book to get pages of
     * @return String array of pages or null if no pages
     */
    public static String[] getPages(Item book) {
        if (book == null) {
            return null;
        }
        if (book.getType() != ItemType.BookAndQuill && book.getType() != ItemType.WrittenBook) {
            return null;
        }
        if (!book.hasDataTag()) {
            return null;
        }
        if (!book.getDataTag().containsKey("pages")) {
            return null;
        }
        if (book.getDataTag().getListTag("pages").isEmpty()) {
            return null;
        }
        ListTag<StringTag> pages_tags = book.getDataTag().getListTag("pages");
        String[] pages = new String[pages_tags.size()];
        for (int index = 0; index < getPageCount(book); index++) {
            pages[index] = pages_tags.get(index).getValue();
        }
        return pages;
    }

    /**
     * Adds pages to a writable/written book
     * 
     * @param book
     *            the Book to add pages too
     * @param pages
     *            the pages to be added
     * @return {@code true} if all pages successfully added; {@code false} if all or some pages could not be added
     */
    public static boolean addPages(Item book, String... pages) {
        if (pages == null || book == null) {
            return false;
        }
        if (book.getType() != ItemType.BookAndQuill && book.getType() != ItemType.WrittenBook) {
            return false;
        }
        if (!book.hasDataTag()) {
            return false;
        }
        if (!book.getDataTag().containsKey("pages")) {
            return false;
        }
        boolean success = true;
        for (String page : pages) {
            if (page == null) {
                continue;
            }
            StringTag toAdd = PAGE_TITLE_AUTHOR.copy();
            toAdd.setName("page" + PAGES_TAG.size());
            toAdd.setValue(correctPage(page));
            success &= book.getDataTag().getListTag("pages").add(toAdd);
        }
        return success;
    }

    /**
     * Sets a page at the specified index
     * 
     * @param book
     *            the book to set a page for
     * @param page_index
     *            the index to add the page at
     * @param page
     *            the page to be added
     */
    public static boolean setPage(Item book, int page_index, String page) {
        if (book == null || page == null) {
            return false;
        }
        if (book.getType() != ItemType.BookAndQuill && book.getType() != ItemType.WrittenBook) {
            return false;
        }
        if (!book.hasDataTag()) {
            book.setDataTag(TAG.copy());
        }
        if (!book.getDataTag().containsKey("pages")) {
            book.getDataTag().put("pages", PAGES_TAG.copy());
        }
        if (!isValidPage(page_index, getPageCount(book))) {
            return false;
        }
        ((StringTag) book.getDataTag().getListTag("pages").get(page_index)).setValue(correctPage(page));
        return true;
    }

    /**
     * Sets the pages of the book
     * 
     * @param book
     *            the book to set pages for
     * @param pages
     *            the pages to be set
     * @return {@code true} if successful; {@code false} if all or some of the pages couldn't be added
     */
    public static boolean setPages(Item book, String... pages) {
        if (book == null || pages == null || pages.length == 0) {
            return false;
        }
        if (book.getType() != ItemType.BookAndQuill && book.getType() != ItemType.WrittenBook) {
            return false;
        }
        if (!book.hasDataTag()) {
            book.setDataTag(TAG.copy());
        }
        ListTag<StringTag> pages_to_set = PAGES_TAG.copy(); // Don't bother checking, we are overriding anyways
        boolean success = true;
        for (String page : pages) {
            if (page == null) {
                continue;
            }
            StringTag toAdd = PAGE_TITLE_AUTHOR.copy();
            toAdd.setName("page" + PAGES_TAG.size());
            toAdd.setValue(correctPage(page));
            success &= pages_to_set.add(toAdd);
        }
        return success;
    }

    /**
     * Gets the page count of the book
     * 
     * @param book
     *            the book to get page count of
     * @return the number of pages or -1 if not a book or no proper tags found
     */
    public static int getPageCount(Item book) {
        if (book == null) {
            return -1;
        }
        if (book.getType() != ItemType.BookAndQuill && book.getType() != ItemType.WrittenBook) {
            return -1;
        }
        if (!book.hasDataTag()) {
            return -1;
        }
        if (!book.getDataTag().containsKey("pages")) {
            return -1;
        }
        return book.getDataTag().getListTag("pages").size();
    }

    /**
     * Checks the book for an author
     * 
     * @param book
     *            the book to check
     * @return {@code true} if has author; {@code false} if not
     */
    public static boolean hasAuthor(Item book) {
        if (book == null) {
            return false;
        }
        if (book.getType() != ItemType.BookAndQuill && book.getType() != ItemType.WrittenBook) {
            return false;
        }
        if (!book.hasDataTag()) {
            return false;
        }
        if (!book.getDataTag().containsKey("author")) {
            return false;
        }
        return !book.getDataTag().getString("author").isEmpty();
    }

    /**
     * Gets the Author of the book
     * 
     * @param book
     *            the book to get Author of
     * @return the author or null
     */
    public static String getAuthor(Item book) {
        if (book == null) {
            return null;
        }
        if (book.getType() != ItemType.BookAndQuill && book.getType() != ItemType.WrittenBook) {
            return null;
        }
        if (!book.hasDataTag()) {
            return null;
        }
        if (!book.getDataTag().containsKey("author")) {
            return null;
        }
        return book.getDataTag().getString("author");
    }

    /**
     * Sets the author of the book
     * 
     * @param book
     *            the book to set author of
     * @param author
     *            the name of the author to set
     * @return true if successful
     */
    public static boolean setAuthor(Item book, String author) {
        if (book == null || author == null) {
            return false;
        }
        if (book.getType() != ItemType.BookAndQuill && book.getType() != ItemType.WrittenBook) {
            return false;
        }
        if (!book.hasDataTag()) {
            return false;
        }
        if (!book.getDataTag().containsKey("author")) {
            return false;
        }
        StringTag authorTag = PAGE_TITLE_AUTHOR.copy();
        authorTag.setName("author");
        authorTag.setValue(correctAuthor(author));
        book.getDataTag().put("author", authorTag);
        return true;
    }

    /**
     * Checks if the book has a title
     * 
     * @param book
     *            the book to check
     * @return true if has title; false if not
     */
    public static boolean hasTitle(Item book) {
        if (book == null) {
            return false;
        }
        if (book.getType() != ItemType.BookAndQuill && book.getType() != ItemType.WrittenBook) {
            return false;
        }
        if (!book.hasDataTag()) {
            return false;
        }
        if (!book.getDataTag().containsKey("title")) {
            return false;
        }
        return !book.getDataTag().getString("title").isEmpty();
    }

    /**
     * Gets the title of a book
     * 
     * @param book
     *            the book to get title of
     * @return the title or null
     */
    public static String getTitle(Item book) {
        if (book == null) {
            return null;
        }
        if (book.getType() != ItemType.BookAndQuill && book.getType() != ItemType.WrittenBook) {
            return null;
        }
        if (!book.hasDataTag()) {
            return null;
        }
        if (!book.getDataTag().containsKey("title")) {
            return null;
        }
        return book.getDataTag().getString("title");
    }

    /**
     * Sets the title of a book
     * 
     * @param book
     *            the book to set title for
     * @param title
     *            the title to be set
     * @return true if successful or false if not
     */
    public static boolean setTitle(Item book, String title) {
        if (book == null || title == null) {
            return false;
        }
        if (book.getType() != ItemType.BookAndQuill && book.getType() != ItemType.WrittenBook) {
            return false;
        }
        if (!book.hasDataTag()) {
            return false;
        }
        if (!book.getDataTag().containsKey("title")) {
            return false;
        }
        StringTag titleTag = PAGE_TITLE_AUTHOR.copy();
        titleTag.setName("title");
        titleTag.setValue(correctTitle(title));
        book.getDataTag().put("title", titleTag);
        return true;
    }

    /**
     * Converts a Book&Quill into a Written Book
     * 
     * @param book
     *            the book to close
     * @return the new book
     */
    public static Item lockBook(Item book) {
        if (book == null) {
            return null;
        }
        if (book.getType() != ItemType.BookAndQuill) {
            return null;
        }
        book.setId(ItemType.WrittenBook.getId());
        return book;
    }

    /**
     * Converts a WrittenBook back into a Book&Quill
     * 
     * @param book
     *            the book to convert
     * @return the new Book
     */
    public static Item unlockBook(Item book) {
        if (book == null) {
            return null;
        }
        if (book.getType() != ItemType.WrittenBook) {
            return null;
        }
        book.setId(ItemType.BookAndQuill.getId());
        return book;
    }

    /**
     * Checks the book for stored enchantments
     * 
     * @param book
     *            the book to check
     * @return true if contains enchantments; false if not
     */
    public static boolean containsEnchantments(Item book) {
        if (book == null) {
            return false;
        }
        if (book.getType() != ItemType.EnchantedBook) {
            return false;
        }
        if (!book.hasDataTag()) {
            return false;
        }
        if (!book.getDataTag().containsKey("StoredEnchantments")) {
            return false;
        }
        return true;
    }

    /**
     * Sets the enchantments of the book
     * 
     * @param book
     *            the book to set enchantments of
     * @param enchantments
     *            the enchantments to set
     * @return true if successful; false if not
     */
    public static boolean setEnchantments(Item book, Enchantment... enchantments) {
        if (book == null || enchantments == null || enchantments.length == 0) {
            return false;
        }
        if (book.getType() != ItemType.EnchantedBook) {
            if (book.getType() != ItemType.Book) {
                return false;
            }
            book.setId(ItemType.EnchantedBook.getId());
        }
        if (!book.hasDataTag()) {
            book.setDataTag(TAG.copy());
        }
        boolean success = true;
        ListTag<CompoundTag> sto_ench = STORED_ENCH_TAG.copy();
        for (Enchantment ench : enchantments) {
            if (ench == null) {
                continue;
            }
            CompoundTag ench_tag = ENCH_TAG.copy();
            ench_tag.put("lvl", ench.getLevel());
            ench_tag.put("id", ench.getType().getId());
            success &= sto_ench.add(ench_tag);
        }
        book.getDataTag().put("StoredEnchantments", sto_ench);
        return success;
    }

    /**
     * Adds enchantments to the book
     * 
     * @param book
     *            the book to add enchantments to
     * @param enchantments
     *            the enchantments to be added
     * @return true if successful; false if not
     */
    public static boolean addEncahntments(Item book, Enchantment... enchantments) {
        if (book == null || enchantments == null || enchantments.length == 0) {
            return false;
        }
        if (book.getType() != ItemType.EnchantedBook) {
            if (book.getType() != ItemType.Book) {
                return false;
            }
            return setEnchantments(book, enchantments);
        }
        if (!book.hasDataTag()) {
            return setEnchantments(book, enchantments);
        }
        if (!book.getDataTag().containsKey("StoredEnchantments")) {
            return setEnchantments(book, enchantments);
        }

        boolean success = true;
        ListTag<CompoundTag> sto_ench = book.getDataTag().getListTag("StoredEnchantments");
        for (Enchantment ench : enchantments) {
            if (ench == null) {
                continue;
            }
            CompoundTag ench_tag = ENCH_TAG.copy();
            ench_tag.put("lvl", ench.getLevel());
            ench_tag.put("id", (short) ench.getType().getId());
            success &= sto_ench.add(ench_tag);
        }
        return success;
    }

    /**
     * Removes the give enchantments from the book
     * 
     * @param book
     *            the book to remove enchantments from
     * @param enchantments
     *            the enchantments to be removed
     * @return true if successful; false if not
     */
    public boolean removeEnchantments(Item book, Enchantment... enchantments) {
        if (book == null || enchantments == null || enchantments.length == 0) {
            return false;
        }
        if (book.getType() != ItemType.EnchantedBook) {
            return false;
        }
        if (!book.hasDataTag()) {
            return false;
        }
        if (!book.getDataTag().containsKey("StoredEnchantments")) {
            return false;
        }
        boolean success = true;
        ListTag<CompoundTag> sto_enchs = book.getDataTag().getListTag("StoredEnchantments");
        List<Enchantment> enchs = Arrays.asList(enchantments);
        for (int index = 0; index < sto_enchs.size(); /*Nothing as incrementing it will skip ever other one*/) {
            CompoundTag sto_ench = sto_enchs.get(index);
            for (Enchantment ench : enchs) {
                if (sto_ench.getShort("id") == ench.getType().getId() && sto_ench.getShort("lvl") == ench.getLevel()) {
                    success &= sto_enchs.remove(index) != null;
                }
            }
        }
        return success;
    }

    /**
     * Removes all enchantments from the book
     * 
     * @param book
     *            the book to remove enchantments from
     * @return true if successful; false if not
     */
    public boolean removeAllEnchantments(Item book) {
        if (book == null) {
            return false;
        }
        if (book.getType() != ItemType.EnchantedBook) {
            return false;
        }
        if (!book.hasDataTag()) {
            return false;
        }
        if (!book.getDataTag().containsKey("StoredEnchantments")) {
            return false;
        }
        book.getDataTag().remove("StoredEnchantments");
        book.setId(ItemType.Book.getId());
        return true;
    }

    private final static boolean isValidPage(int page, int page_count) {
        return page > 0 && page < page_count;
    }

    private final static String correctPage(String page) {
        return page.length() > MAX_PAGE_LENGTH ? page.substring(0, MAX_PAGE_LENGTH) : page;
    }

    private final static String correctAuthor(String author) {
        return author.length() > MAX_AUTHOR_LENGTH ? author.substring(0, MAX_AUTHOR_LENGTH) : author;
    }

    private final static String correctTitle(String title) {
        return title.length() > MAX_TITLE_LENGTH ? title.substring(0, MAX_TITLE_LENGTH) : title;
    }
}