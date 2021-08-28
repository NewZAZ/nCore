package fr.newzproject.core.inventory;

import fr.newzproject.core.inventory.inventory.InventoryManager;
import fr.newzproject.core.inventory.listeners.bukkit.PlayerQuitListener;
import fr.newzproject.core.inventory.listeners.bukkit.PluginDisableListener;
import fr.newzproject.core.inventory.listeners.inventory.InventoryClickListener;
import fr.newzproject.core.inventory.listeners.inventory.InventoryCloseListener;
import fr.newzproject.core.inventory.listeners.inventory.InventoryOpenListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class InventoryAPI {

    private static InventoryAPI instance;

    private final Plugin plugin;
    private final InventoryManager inventoryManager;

    private InventoryAPI(Plugin plugin) {
        this.plugin = plugin;
        this.inventoryManager = new InventoryManager(this);

        if (InventoryAPI.instance == null) {
            InventoryAPI.instance = this;

            PluginManager pm = Bukkit.getPluginManager();
            pm.registerEvents(new InventoryClickListener(this), plugin);
            pm.registerEvents(new InventoryCloseListener(this), plugin);
            pm.registerEvents(new InventoryOpenListener(this), plugin);
            pm.registerEvents(new PlayerQuitListener(this), plugin);
            pm.registerEvents(new PluginDisableListener(this), plugin);
        }
    }

    public static InventoryAPI getInstance(Plugin plugin) {
        return InventoryAPI.instance == null ? new InventoryAPI(plugin) : InventoryAPI.instance;
    }

    public Plugin getPlugin() {
        return this.plugin;
    }

    public InventoryManager getInventoryManager() {
        return this.inventoryManager;
    }

    public InventoryCreator getInventoryCreator() {
        return new InventoryCreator(this);
    }
}
