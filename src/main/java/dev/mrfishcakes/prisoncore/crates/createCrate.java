package dev.mrfishcakes.prisoncore.crates;

import dev.mrfishcakes.prisoncore.PrisonCore;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class createCrate {

    private String name;
    private Location loc;

    public createCrate(String name, Location l) {
        this.name = name;
        this.loc = l;
    }

    public void create() {
        loc.getBlock().setType(Material.CHEST);
        loc.getBlock().setMetadata(name, new FixedMetadataValue(PrisonCore.getPlugin(),  name));
    }

    public String getName() {
        return name;
    }

    public Location getLoc() {
        return loc;
    }
}
