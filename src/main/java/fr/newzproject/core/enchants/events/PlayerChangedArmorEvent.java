package fr.newzproject.core.enchants.events;

import fr.newzproject.core.NCore;
import fr.newzproject.core.misc.Task;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class PlayerChangedArmorEvent extends PlayerEvent {

    private static HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }

    static {
        register();
    }

    private static void register() {
        Task.syncRepeating(NCore.getInstance(), () -> Bukkit.getOnlinePlayers().forEach(PlayerChangedArmorEvent::check), 1, 1);
    }

    private static void check(Player player) {
        ItemStack[] armor = player.getInventory().getArmorContents().clone();
        Task.syncDelayed(NCore.getInstance(), () -> {
            ItemStack[] newArmor = player.getInventory().getArmorContents();
            for (int i = 0; i < armor.length; i++) {
                if (armor[i] == null && newArmor[i] == null) {
                    continue;
                }
                if ((armor[i] == null) || (newArmor[i] == null) || !armor[i].equals(newArmor[i])) {
                    Bukkit.getPluginManager().callEvent(new PlayerChangedArmorEvent(player, armor, newArmor));
                }
            }
        }, 1);
    }

    private ItemStack[] previous;
    private ItemStack[] current;

    /**
     * Constructs a new PlayerChangedArmorEvent
     * @param player The Player who changed their armor
     * @param previous The armor the Player was previously wearing
     * @param current The armor the Player is now wearing
     */
    public PlayerChangedArmorEvent(Player player, ItemStack[] previous, ItemStack[] current) {
        super(player);
        this.previous = previous;
        this.current = current;
    }

    /**
     * @return The armor the Player was previously wearing
     */
    public ItemStack[] getPreviousArmor() {
        return previous;
    }

    /**
     * @return The armor the Player is now wearing
     */
    public ItemStack[] getNewArmor() {
        return current;
    }


    public HandlerList getHandlers() {
        return handlers;
    }
}
