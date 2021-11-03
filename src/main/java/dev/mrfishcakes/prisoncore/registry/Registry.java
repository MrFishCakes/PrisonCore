package dev.mrfishcakes.prisoncore.registry;

import dev.mrfishcakes.crunchy.enchantment.CustomEnchantment;
import dev.mrfishcakes.prisoncore.PrisonCore;
import dev.mrfishcakes.prisoncore.enchantments.EnchantmentListener;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;

public abstract class Registry<T> {

    private Registry() {}

    public static final Registry<CustomEnchantment> ENCHANTMENT = new Registry<>() {

        private final PrisonCore plugin;
        private Map<NamespacedKey, CustomEnchantment> registered;

        {
            this.plugin = PrisonCore.getPlugin(PrisonCore.class);
            this.registered = new HashMap<>();
            Bukkit.getPluginManager().registerEvents(new EnchantmentListener(), plugin);
        }

        @Override
        public void register(@NotNull CustomEnchantment enchantment) {
            if (enchantment == null) {
                throw new IllegalArgumentException("Enchantment cannot be null");
            }

            if (registered.containsKey(enchantment.getKey())) {
                plugin.getLogger().log(Level.SEVERE, String.format("Unable to register enchantment '%s' (Already Registered)",
                        enchantment.getKey().getKey()));
                return;
            }

            Bukkit.registerEnchantment(enchantment);
            registered.put(enchantment.getKey(), enchantment);
        }

        @Override
        @NotNull
        public Optional<CustomEnchantment> get(@NotNull Object value) {
            return Optional.ofNullable(registered.get(PrisonCore.key((String) value)));
        }

        @Override
        public void clear() {
            registered.clear();
            registered = null;
        }

    };

    public void register(@NotNull T object) {}

    public void unregister(@NotNull T object) {}

    @NotNull
    public abstract Optional<T> get(@NotNull Object value);

    public void clear() {}

}
