package de.zagro.economy.Commands;

import de.zagro.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsAddCmd implements CommandExecutor {

    private final Economy plugin;
    public CoinsAddCmd(Economy plugin) {
        this.plugin = plugin;
    }

    // Command usage: /coinsadd <Player> <amount>
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("economy.coins.add")) {
                if (args.length != 2) {
                    player.sendMessage(ChatColor.RED + "Usage: /coinsadd <Player> <amount>");
                } else {
                    if (command.getName().equalsIgnoreCase("coinsadd")) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (!(target == null)) {
                            if (plugin.getConfig().contains(target.getUniqueId().toString())) {
                                Double coinsAdd = new Double(args[1]);
                                String targetUUID = target.getUniqueId().toString();
                                if (coinsAdd > 0) {
                                    plugin.getConfig().set(targetUUID + ".coins", (plugin.getConfig().getDouble(targetUUID + ".coins")) + Double.valueOf(args[1]));
                                    player.sendMessage(ChatColor.GOLD + target.getName() + ChatColor.GREEN + " now has " + ChatColor.GOLD + plugin.getConfig().getDouble(targetUUID + ".coins") + ChatColor.GREEN + " coins!");
                                    plugin.saveConfig();
                                }
                            }
                        } else {
                            player.sendMessage(ChatColor.GOLD + args[0] + ChatColor.RED + " is not online or doesn't exist!");
                        }
                    }
                }
            } else {
                player.sendMessage(ChatColor.RED + "You do not have the required permission!");
            }
        }
        return false;
    }
}
