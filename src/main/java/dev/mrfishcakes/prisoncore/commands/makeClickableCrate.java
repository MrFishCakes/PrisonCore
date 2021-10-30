package dev.mrfishcakes.prisoncore.commands;

import dev.mrfishcakes.prisoncore.crates.createCrate;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class makeClickableCrate implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("createcase")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Sorry, you cannot do this.");
                return true;
            }
            Player player = (Player) sender;
            if (!player.hasPermission("prison.createcrate")) {
                player.sendMessage("No permissions.");
                return true;
            }
            if (args.length == 0) {
                player.sendMessage("Sorry, wrong input. /createcase <Name>");
                return true;
            }
            String name = args[0];
            createCrate crate = new createCrate(name, player.getLocation());
            crate.create();
            player.sendMessage("You have created a crate with the name: " + name + "!");
        }
        return false;
    }
}
