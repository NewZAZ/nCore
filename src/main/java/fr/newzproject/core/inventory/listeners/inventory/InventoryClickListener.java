package fr.newzproject.core.inventory.listeners.inventory;

import fr.newzproject.core.inventory.InventoryAPI;
import fr.newzproject.core.inventory.events.NInventoryClickEvent;
import fr.newzproject.core.inventory.inventory.ClickableItem;
import fr.newzproject.core.inventory.inventory.NInventory;
import fr.newzproject.core.inventory.listeners.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.function.Consumer;

public class InventoryClickListener extends ListenerAdapter implements Listener {

    public InventoryClickListener(InventoryAPI inventoryAPI) {
        super(inventoryAPI);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClick().equals(ClickType.UNKNOWN) || event.getClickedInventory() == null) {
            event.setCancelled(true);
            return;
        }

        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            NInventory nInventory = this.inventoryManager.getPlayerInventory(player);

            if (nInventory == null) {
                return;
            } else if (!nInventory.isClickable() && event.getClickedInventory().equals(player.getInventory())) {
                event.setCancelled(true);
                return;
            } else if (nInventory.isClickable() && event.getClickedInventory().equals(player.getInventory())) {
                return;
            }

            NInventoryClickEvent nInventoryClickEvent = new NInventoryClickEvent(player, nInventory, event);
            Bukkit.getPluginManager().callEvent(nInventoryClickEvent);
            if (nInventoryClickEvent.isCancelled()) {
                event.setCancelled(true);
                return;
            }

            ClickableItem clickableItem = nInventory.getItem(event.getSlot());
            if (clickableItem != null) {
                event.setCancelled(true);
                Consumer<InventoryClickEvent> clickEventConsumer = clickableItem.getClick();
                if (clickEventConsumer != null) {
                    clickEventConsumer.accept(event);
                }
            }
        }
    }
}
