package fr.newzproject.core.inventory.events;

import fr.newzproject.core.inventory.inventory.NInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class NInventoryOpenEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final Player player;
    private final NInventory nInventory;
    private final InventoryOpenEvent event;
    private boolean cancelled = false;

    public NInventoryOpenEvent(Player player, NInventory nInventory, InventoryOpenEvent event) {
        this.player = player;
        this.nInventory = nInventory;
        this.event = event;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public Player getPlayer() {
        return this.player;
    }

    public NInventory getInventory() {
        return this.nInventory;
    }

    public InventoryOpenEvent getOpenEvent() {
        return this.event;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.cancelled = b;
    }

    public HandlerList getHandlers() {
        return handlers;
    }
}
