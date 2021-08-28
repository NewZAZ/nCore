package fr.newzproject.core.inventory;

import fr.newzproject.core.inventory.inventory.NInventory;
import org.bukkit.ChatColor;
import org.bukkit.event.inventory.InventoryType;

public class InventoryCreator {

    private final InventoryAPI inventoryAPI;

    private String title = "";
    private InventoryType inventoryType = InventoryType.CHEST;
    private int size = 6;
    private boolean closable = true;
    private String id = "";
    private boolean clickable = false;

    InventoryCreator(InventoryAPI inventoryAPI) {
        this.inventoryAPI = inventoryAPI;
    }

    public InventoryCreator setTitle(String title) {
        this.title = title;
        return this;
    }

    public InventoryCreator setSize(int size) {
        this.size = size;
        return this;
    }

    public InventoryCreator setId(String id) {
        this.id = id;
        return this;
    }

    public InventoryCreator setClosable(boolean closable) {
        this.closable = closable;
        return this;
    }

    public InventoryCreator setInventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
        return this;
    }

    public InventoryCreator setClickable(boolean clickable) {
        this.clickable = clickable;
        return this;
    }

    public NInventory create() {
        return new NInventory(this.inventoryAPI, ChatColor.translateAlternateColorCodes('&', this.title), this.inventoryType, this.size, this.id, this.closable, this.clickable);
    }
}
