package fr.newzproject.core.inventory.inventory;

import fr.newzproject.core.inventory.InventoryAPI;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {

    private InventoryAPI inventoryAPI;
    private final Plugin plugin;
    private final Map<String, NInventory> playerInventoryMap = new HashMap<>();

    public InventoryManager(InventoryAPI inventoryAPI) {
        this.inventoryAPI = inventoryAPI;
        this.plugin = inventoryAPI.getPlugin();
    }

    public Map<String, NInventory> getPlayerInventoryMap() {
        return this.playerInventoryMap;
    }

    public NInventory getPlayerInventory(String playerName) {
        return this.playerInventoryMap.get(playerName);
    }

    public NInventory getPlayerInventory(Player player) {
        return this.getPlayerInventory(player.getName());
    }

    public void setPlayerInventory(String playerName, NInventory hInventory) {
        this.playerInventoryMap.put(playerName, hInventory);
    }

    public void setPlayerInventory(Player player, NInventory hInventory) {
        this.setPlayerInventory(player.getName(), hInventory);
    }

    public String getId(String playerName) {
        NInventory hInventory = this.getPlayerInventory(playerName);
        return hInventory != null ? hInventory.getId() : null;
    }

    public String getId(Player player) {
        return this.getId(player.getName());
    }
}
