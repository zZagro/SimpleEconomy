package de.zagro.economy.Listener;

import de.zagro.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final Economy plugin;
    public JoinListener(Economy plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!plugin.getConfig().contains(player.getUniqueId().toString())) {
            plugin.getConfig().set(player.getUniqueId().toString() + ".coins", 0);
        }

        e.setJoinMessage(ChatColor.GOLD + player.getName() + ChatColor.GREEN + " joined the server!");

        plugin.sM.setScoreboard(player);

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            plugin.sM.setScoreboard(player);
        }, 0, 20);

        plugin.saveConfig();
    }
}
