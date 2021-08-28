package fr.newzproject.core.enchants.trigger;

import fr.newzproject.core.enchants.EventItems;
import org.bukkit.Material;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;

public class MineBlockTrigger extends EnchantTrigger<BlockBreakEvent> {

    @Override
    protected void register() {
        addListener(BlockBreakEvent.class, e -> new EventItems(e, e.getPlayer().getItemInHand()));
    }

    @Override
    public EventPriority getPriority() {
        return EventPriority.MONITOR;
    }

    @Override
    public boolean defaultAppliesTo(Material type) {
        return type.toString().endsWith("_PICKAXE");
    }
}
