package dev.mrfishcakes.prisoncore.event;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreak implements Listener {

    @EventHandler
    public void on(BlockBreakEvent e) {
        if (e.getBlock().getType().equals(Material.GLOWSTONE)) {
            if (e.getBlock().getWorld().getName().equalsIgnoreCase("prison")) {
                if(!e.getPlayer().isOp()) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cSorry, you cannot break that block here."));
                }
            }
        } else {
            if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                e.setDropItems(false);
                Block b = e.getBlock();
                e.getPlayer().getInventory().addItem(new ItemStack(b.getType()));
            }
        }
    }
}
