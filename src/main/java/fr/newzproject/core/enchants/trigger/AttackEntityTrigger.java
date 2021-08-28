package fr.newzproject.core.enchants.trigger;

import fr.newzproject.core.enchants.EventItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AttackEntityTrigger extends EnchantTrigger<EntityDamageByEntityEvent> {

    @Override
    protected void register() {
        addListener(EntityDamageByEntityEvent.class, e -> {
            if (!(e.getDamager() instanceof Player)) {
                return null;
            }
            return new EventItems(e, ((Player) e.getDamager()).getItemInHand());
        });
    }

    @Override
    public EventPriority getPriority() {
        return EventPriority.MONITOR;
    }

    @Override
    public boolean defaultAppliesTo(Material type) {
        return type.toString().endsWith("_SWORD");
    }
}
