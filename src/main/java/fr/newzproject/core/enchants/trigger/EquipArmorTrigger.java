package fr.newzproject.core.enchants.trigger;

import fr.newzproject.core.enchants.EventItems;
import fr.newzproject.core.enchants.events.PlayerChangedArmorEvent;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class EquipArmorTrigger extends EnchantTrigger<PlayerChangedArmorEvent> {

    @Override
    protected void register() {
        addListener(PlayerChangedArmorEvent.class, e -> new EventItems(e, e.getPreviousArmor(), e.getNewArmor()));
        addListener(PlayerJoinEvent.class, e ->
                new EventItems(new PlayerChangedArmorEvent(e.getPlayer(), null, e.getPlayer().getInventory().getArmorContents()),
                        null, e.getPlayer().getInventory().getArmorContents()));
        addListener(PlayerQuitEvent.class, e ->
                new EventItems(new PlayerChangedArmorEvent(e.getPlayer(), e.getPlayer().getInventory().getArmorContents(), new ItemStack[4]),
                        e.getPlayer().getInventory().getArmorContents(), null));
    }

    @Override
    public boolean defaultAppliesTo(Material type) {
        String str = type.toString();
        return str.endsWith("_BOOTS") || str.endsWith("_CHESTPLATE") || str.endsWith("_LEGGINGS") || str.endsWith("_HELMET");
    }
}
