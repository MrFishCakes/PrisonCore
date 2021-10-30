package dev.mrfishcakes.prisoncore.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {

    //Events for crates

    @EventHandler
    public void on(PlayerInteractEvent e) {
        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_AIR)) {
            return;
        } else if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) ) {
            if (e.getClickedBlock().hasMetadata("Vote")) {
                if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage("You clicked Vote crate.");
                }
            } else {
                return;
            }
        }
    }
}
