package fr.newzproject.core.enchants;

import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

public class EventItems {

    private Event event;
    private ItemStack[] before;
    private ItemStack[] after;

    /**
     * @param event The event
     * @param before The array of items before the event executes
     * @param after The array of items after the event executes
     */
    public EventItems(Event event, ItemStack[] before, ItemStack[] after) {
        if (before == null) {
            before = new ItemStack[after == null ? 1 : after.length];
        }
        if (after == null) {
            after = new ItemStack[before.length];
        }
        this.before = before;
        this.after = after;
        this.event = event;
    }

    /**
     * @param event The event
     * @param before The item before the event executes
     * @param after The item after the event executes
     */
    public EventItems(Event event, ItemStack before, ItemStack after) {
        this(event, new ItemStack[] {before}, new ItemStack[] {after});
    }

    /**
     * @param event The event
     * @param after The array of items related to the event
     */
    public EventItems(Event event, ItemStack[] after) {
        this(event, new ItemStack[after.length], after);
    }

    /**
     * @param event The event
     * @param after The item related to the event
     */
    public EventItems(Event event, ItemStack after) {
        this(event, new ItemStack[] {null}, new ItemStack[] {after});
    }

    /**
     * @return The array of items before the event executes
     */
    public ItemStack[] getBefore() {
        return before;
    }

    /**
     * @return The array of items after the event executes
     */
    public ItemStack[] getAfter() {
        return after;
    }

    /**
     * @return The event
     */
    public Event getEvent() {
        return event;
    }
}
