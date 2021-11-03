package dev.mrfishcakes.prisoncore;

import dev.mrfishcakes.prisoncore.enchantments.combat.BeheadingEnchantment;
import dev.mrfishcakes.prisoncore.registry.Registry;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class PrisonCore extends JavaPlugin {

    @NotNull
    public static NamespacedKey key(@NotNull String key) {
        return new NamespacedKey(PrisonCore.getPlugin(PrisonCore.class), key);
    }

    @Override
    public void onEnable() {
        registerEnchantments();
    }

    private void registerEnchantments() {
        Registry.ENCHANTMENT.register(new BeheadingEnchantment());
    }

}
