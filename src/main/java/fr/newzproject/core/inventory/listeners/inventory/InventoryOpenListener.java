package fr.newzproject.core.inventory.listeners.inventory;

import fr.newzproject.core.inventory.InventoryAPI;
import fr.newzproject.core.inventory.events.NInventoryOpenEvent;
import fr.newzproject.core.inventory.inventory.NInventory;
import fr.newzproject.core.inventory.listeners.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class InventoryOpenListener extends ListenerAdapter implements Listener {

    public InventoryOpenListener(InventoryAPI inventoryAPI) {
        super(inventoryAPI);
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            NInventory nInventory = this.inventoryManager.getPlayerInventory(player);
            if (nInventory == null) {
                return;
            }

            NInventoryOpenEvent nInventoryOpenEvent = new NInventoryOpenEvent(player, nInventory, event);
            Bukkit.getPluginManager().callEvent(nInventoryOpenEvent);
            event.setCancelled(nInventoryOpenEvent.isCancelled());
        }
    }
}
