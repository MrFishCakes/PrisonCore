package dev.mrfishcakes.prisoncore.enchantments.combat;

import dev.mrfishcakes.crunchy.enchantment.CustomEnchantment;
import dev.mrfishcakes.prisoncore.PrisonCore;
import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.Random;
import java.util.Set;

import static org.bukkit.Material.*;

public class BeheadingEnchantment extends CustomEnchantment {

    private static final Set<Material> ITEMS = EnumSet.of(WOODEN_SWORD, STONE_SWORD, IRON_SWORD, GOLDEN_SWORD,
            DIAMOND_SWORD, NETHERITE_SWORD, WOODEN_AXE, STONE_AXE, IRON_AXE, GOLDEN_AXE, DIAMOND_AXE,
            NETHERITE_AXE);

    private final Random random;

    public BeheadingEnchantment() {
        super(PrisonCore.key("beheading_enchantment"));
        this.random = new Random();
    }

    public void handleBeheading(Player attackee, int level) {
        if (level > 0 && random.nextDouble() <= (level % 100) * 10) {
            ItemStack itemStack = new ItemStack(PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

            skullMeta.displayName(Component.text(attackee.getName() + (attackee.getName().endsWith("s") ?
                    "'" : "'s"), NamedTextColor.RED).append(Component.text(" Skull", NamedTextColor.WHITE)));
            skullMeta.setPlayerProfile(attackee.getPlayerProfile());
            itemStack.setItemMeta(skullMeta);

            attackee.getWorld().dropItemNaturally(attackee.getLocation(), itemStack);
        }
    }

    @Override
    public @NotNull Component displayName() {
        return Component.text("Beheading");
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public @NotNull EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.WEAPON;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item) {
        return ITEMS.contains(item.getType());
    }

    @Override
    public @NotNull EnchantmentRarity getRarity() {
        return EnchantmentRarity.VERY_RARE;
    }

}
