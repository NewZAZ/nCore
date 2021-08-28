package fr.newzproject.core.inventory.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Pagination {

    private final NInventory nInventory;
    private List<Integer> itemSlots;
    private List<ClickableItem> clickableItems;
    private int page;

    Pagination(NInventory nInventory) {
        this.page = 0;
        this.nInventory = nInventory;
        this.clickableItems = new ArrayList<>();
        this.itemSlots = new ArrayList<>();
    }

    public void nextPage() {
        this.setPage(this.page + 1);
    }

    public void previousPage() {
        this.setPage(this.page - 1);
    }

    public void firstPage() {
        this.setPage(0);
    }

    public void lastPage() {
        this.setPage(this.getLastPage());
    }

    public NInventory getInventory() {
        return this.nInventory;
    }

    public List<Integer> getItemSlots() {
        return this.itemSlots;
    }

    public void setItemSlots(List<Integer> ints) {
        this.itemSlots = ints;
        this.updateInventory();
    }

    public void setItemSlots(int min, int max) {
        List<Integer> itemSlots = new ArrayList<>();
        for (; min <= max; min++) itemSlots.add(min);
        this.setItemSlots(itemSlots);
    }

    public List<ClickableItem> getClickableItems() {
        return this.clickableItems;
    }

    public void setItems(List<ClickableItem> clickableItems) {
        this.clickableItems = clickableItems;
        this.updateInventory();
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        if (this.clickableItems.size() == 0) return;
        else if (this.nInventory == null) return;
        else if (this.itemSlots.size() == 0) return;

        int oldPage = this.page;
        this.page = page;
        if (!this.updateInventory()) this.page = oldPage;
    }

    public int getLastPage() {
        int m = (int) Math.ceil((double) getClickableItems().size() / getItemSlots().size()) - 1;
        return m != -1 ? m : 0;
    }

    public int getFirstPage() {
        return 0;
    }

    public boolean isLastPage() {
        return this.page == this.getLastPage();
    }

    public boolean isFirstPage() {
        return this.page == 0;
    }

    private boolean updateInventory() {
        int clickableItemSize = this.clickableItems.size();
        int itemSlotSize = this.itemSlots.size();

        int first = this.page * itemSlotSize;
        int last = (this.page + 1) * itemSlotSize;
        if (clickableItemSize <= first) return false;
        if (first < 0) return false;

        int m = 0;
        for (; first < last; first++) {
            ClickableItem clickableItem = (clickableItemSize > first) ? this.clickableItems.get(first) : ClickableItem.empty(new ItemStack(Material.AIR));
            this.nInventory.setItem(this.itemSlots.get(m), clickableItem);
            m++;
        }
        return true;
    }
}
