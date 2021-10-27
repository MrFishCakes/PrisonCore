package dev.mrfishcakes.prisoncore.event;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableMap;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;

public class PlayerDamageByPlayerEvent extends EntityDamageByEntityEvent {

    private static final HandlerList HANDLERS = new HandlerList();
    private static final Function<? super Double, Double> ZERO = Functions.constant(-0.0);

    private final ItemStack attackItem;

    public PlayerDamageByPlayerEvent(@NotNull ItemStack attackItem, @NotNull Player damager, @NotNull Player damagee, @NotNull DamageCause cause, double damage, boolean critical) {
        super(damager, damagee, cause, new EnumMap<DamageModifier, Double>(ImmutableMap.of(DamageModifier.BASE, damage)), new EnumMap<DamageModifier, Function<? super Double, Double>>(ImmutableMap.of(DamageModifier.BASE, ZERO)), critical);
        this.attackItem = attackItem;
    }

    @NotNull
    public final ItemStack getAttackItem() {
        return attackItem;
    }

    public boolean hasEnchantment(@NotNull Enchantment enchantment) {
        return attackItem != null && attackItem.containsEnchantment(enchantment);
    }

    @Override
    public final @NotNull Player getDamager() {
        return (Player) super.getDamager();
    }

    @Override
    public final @NotNull Player getEntity() {
        return (Player) super.getEntity();
    }

    @Override
    public final @NotNull EntityType getEntityType() {
        return EntityType.PLAYER;
    }

    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
