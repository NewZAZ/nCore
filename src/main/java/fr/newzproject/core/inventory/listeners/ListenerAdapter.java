package fr.newzproject.core.inventory.listeners;

import fr.newzproject.core.inventory.InventoryAPI;
import fr.newzproject.core.inventory.inventory.InventoryManager;
import org.bukkit.plugin.Plugin;

public class ListenerAdapter {

    protected final Plugin plugin;
    protected final InventoryManager inventoryManager;

    public ListenerAdapter(InventoryAPI inventoryAPI) {
        this.plugin = inventoryAPI.getPlugin();
        this.inventoryManager = inventoryAPI.getInventoryManager();
    }
}
