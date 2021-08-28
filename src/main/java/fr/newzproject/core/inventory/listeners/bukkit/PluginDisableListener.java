package fr.newzproject.core.inventory.listeners.bukkit;

import fr.newzproject.core.inventory.InventoryAPI;
import fr.newzproject.core.inventory.inventory.NInventory;
import fr.newzproject.core.inventory.listeners.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;

import java.util.Map;

public class PluginDisableListener extends ListenerAdapter implements Listener {

    public PluginDisableListener(InventoryAPI inventoryAPI) {
        super(inventoryAPI);
    }

    @EventHandler
    public void onPluginDisable(PluginDisableEvent event) {
        if (event.getPlugin().equals(this.plugin)) {
            Map<String, NInventory> playerInventoryMap = this.inventoryManager.getPlayerInventoryMap();
            if (playerInventoryMap == null || playerInventoryMap.isEmpty()) {
                return;
            }

            for (Map.Entry<String, NInventory> entry : playerInventoryMap.entrySet()) {
                Player player = Bukkit.getPlayerExact(entry.getKey());
                NInventory nInventory = entry.getValue();
                if (player == null) {
                    return;
                }
                if (nInventory != null && playerInventoryMap.get(player.getName()) != null) {
                    nInventory.close(player);
                }
            }
        }
    }
}
