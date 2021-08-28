package fr.newzproject.core.enchants.events;

import fr.newzproject.core.NCore;
import fr.newzproject.core.misc.Task;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerChangedHeldItemEvent extends PlayerEvent {

    private static HandlerList handlers = new HandlerList();

    static {
        register();
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    private static void register() {
        Task.syncRepeating(NCore.getInstance(), () -> Bukkit.getOnlinePlayers().forEach(PlayerChangedHeldItemEvent::check), 1, 1);
    }

    private static void check(Player player) {
        ItemStack held = player.getItemInHand();
        Task.syncDelayed(NCore.getInstance(), () -> {
            ItemStack now = player.getItemInHand();
            if ((held == null && now != null) || !held.equals(now)) {
                Bukkit.getPluginManager().callEvent(new PlayerChangedHeldItemEvent(player, held, now));
            }
        }, 1);
    }

    private ItemStack oldItem;
    private ItemStack newItem;

    /**
     * Constructs a new PlayerChangedHeldItemEvent
     * @param player The Player who changed their held item
     * @param oldItem The item they were previously holding
     * @param newItem The item they are now holding
     */
    public PlayerChangedHeldItemEvent(Player player, ItemStack oldItem, ItemStack newItem) {
        super(player);
        this.oldItem = oldItem;
        this.newItem = newItem;
    }

    /**
     * @return The item the player was holding previously
     */
    public ItemStack getPreviousItem() {
        return oldItem;
    }

    /**
     * @return The item the player is now holding
     */
    public ItemStack getNewItem() {
        return newItem;
    }

    /**
     * @return The player
     */

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
}
