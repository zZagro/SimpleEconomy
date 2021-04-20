package de.zagro.economy.Listener;

import de.zagro.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerInteractListener implements Listener {

    private final Economy plugin;

    public PlayerInteractListener(Economy plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {
        Player player = e.getPlayer();

        if (e.getRightClicked() != null && e.getRightClicked().getCustomName().equalsIgnoreCase("Builder")) {
            plugin.builderNPC.openInventory(player);
        }
    }
}
