package fr.newzproject.core.inventory.listeners.bukkit;

import fr.newzproject.core.inventory.InventoryAPI;
import fr.newzproject.core.inventory.inventory.NInventory;
import fr.newzproject.core.inventory.listeners.ListenerAdapter;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener extends ListenerAdapter implements Listener {

    public PlayerQuitListener(InventoryAPI inventoryAPI) {
        super(inventoryAPI);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        NInventory nInventory = this.inventoryManager.getPlayerInventory(player);

        if (nInventory != null) {
            this.inventoryManager.getPlayerInventoryMap().remove(player.getName());
        }
    }
}
