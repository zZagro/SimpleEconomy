package de.zagro.economy.Commands;

import de.zagro.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCmd implements CommandExecutor {

    private final Economy plugin;
    public CoinsCmd(Economy plugin) {
        this.plugin = plugin;
    }

    int coins;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String playerUUID = player.getUniqueId().toString();
            if (player.hasPermission("economy.coins")) {
                player.sendMessage(ChatColor.GOLD + "Coins: " + ChatColor.GREEN + plugin.getConfig().getDouble(playerUUID + ".coins"));
            } else {
                player.sendMessage(ChatColor.RED + "You do not have the required permission!");
            }
        }
        return false;
    }
}
