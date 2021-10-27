package dev.mrfishcakes.prisoncore.enchantments;

import dev.mrfishcakes.prisoncore.enchantments.combat.BeheadingEnchantment;
import dev.mrfishcakes.prisoncore.event.PlayerDamageByPlayerEvent;
import dev.mrfishcakes.prisoncore.registry.Registry;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EnchantmentListener implements Listener {

    private final BeheadingEnchantment beheadingEnchantment;

    public EnchantmentListener() {
        this.beheadingEnchantment = (BeheadingEnchantment) Registry.ENCHANTMENT.get("beheading").orElseThrow(
                () -> new IllegalArgumentException("Beheading Enchantment is not registered"));
    }

    @EventHandler(ignoreCancelled = true)
    private void onEntityDamageEntity(final EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player damager && event.getEntity() instanceof Player victim) {
            new PlayerDamageByPlayerEvent(damager.getInventory().getItemInMainHand(), damager, victim, event.getCause(),
                    event.getDamage(), event.isCritical()).callEvent();
        }
    }

    @EventHandler
    private void onPlayerDamagePlayer(final PlayerDamageByPlayerEvent event) {
        if (event.hasEnchantment(beheadingEnchantment)) {
            beheadingEnchantment.handleBeheading(event.getEntity(), event.getAttackItem().
                    getEnchantmentLevel(beheadingEnchantment));
        }
    }

}
