package dev.mrfishcakes.prisoncore.event;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {

    //Events for crates

    @EventHandler
    public void on(PlayerInteractEvent e) {
        if (e.getClickedBlock().hasMetadata("Vote")) {
            if (e.getClickedBlock().getType().equals(Material.CHEST)) {
                e.setCancelled(true);
                e.getPlayer().sendMessage("You clicked Vote crate.");
            }
        }
    }
}
