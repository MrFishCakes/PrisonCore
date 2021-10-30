package dev.mrfishcakes.prisoncore;

import dev.mrfishcakes.crunchy.enchantment.CustomEnchantment;
import dev.mrfishcakes.prisoncore.commands.giveCrateKey;
import dev.mrfishcakes.prisoncore.commands.makeClickableCrate;
import dev.mrfishcakes.prisoncore.enchantments.EnchantmentListener;
import dev.mrfishcakes.prisoncore.enchantments.combat.BeheadingEnchantment;
import dev.mrfishcakes.prisoncore.event.BlockBreak;
import dev.mrfishcakes.prisoncore.event.PlayerInteract;
import dev.mrfishcakes.prisoncore.registry.Registry;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class PrisonCore extends JavaPlugin {

    private static Plugin p;

    @NotNull
    public static NamespacedKey key(@NotNull String key) {
        return new NamespacedKey(PrisonCore.getPlugin(PrisonCore.class), key);
    }

    @Override
    public void onEnable() {
        p = this;
        registerEnchantments();
        registerEvents();
        registerCommands();
    }

    private void registerCommands() {
        getCommand("givekey").setExecutor(new giveCrateKey());
        getCommand("createcase").setExecutor(new makeClickableCrate());
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new BlockBreak(), this);
        pm.registerEvents(new PlayerInteract(), this);
    }

    public static Plugin getPlugin() {
        return p;
    }

    private void registerEnchantments() {
        Registry<CustomEnchantment> registry = Registry.ENCHANTMENT;

        registry.register(new BeheadingEnchantment());
        Bukkit.getPluginManager().registerEvents(new EnchantmentListener(), this);
    }

}
