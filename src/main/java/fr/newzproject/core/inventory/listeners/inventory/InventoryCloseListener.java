package fr.newzproject.core.inventory.listeners.inventory;

import fr.newzproject.core.inventory.InventoryAPI;
import fr.newzproject.core.inventory.events.NInventoryCloseEvent;
import fr.newzproject.core.inventory.inventory.NInventory;
import fr.newzproject.core.inventory.listeners.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener extends ListenerAdapter implements Listener {

    public InventoryCloseListener(InventoryAPI inventoryAPI) {
        super(inventoryAPI);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();
            NInventory nInventory = this.inventoryManager.getPlayerInventory(player);

            if (nInventory == null) {
                return;
            }

            if (nInventory.isClosable()) {
                NInventoryCloseEvent nInventoryCloseEvent = new NInventoryCloseEvent(player, nInventory, event);
                Bukkit.getPluginManager().callEvent(nInventoryCloseEvent);
                if (nInventoryCloseEvent.isCancelled() && this.plugin != null) {
                    Bukkit.getScheduler().runTaskLater(this.plugin, () -> nInventory.open(player), 1);
                    return;
                }

                if (nInventory.closeChecker != null) {
                    nInventory.closeChecker.accept(event);
                }

                this.inventoryManager.getPlayerInventoryMap().remove(player.getName());
                if (this.plugin != null) {
                    Bukkit.getScheduler().runTaskLater(this.plugin, player::updateInventory, 1);
                }
            } else {
                if (this.plugin != null) {
                    Bukkit.getScheduler().runTaskLater(this.plugin, () -> nInventory.open(player), 1);
                }
            }
        }
    }
}
