package de.zagro.economy;

import de.zagro.economy.Commands.*;
import de.zagro.economy.Listener.JoinListener;
import de.zagro.economy.Listener.PlayerInteractListener;
import de.zagro.economy.utils.ScoreboardManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Economy extends JavaPlugin {

    private static Economy plugin;

    public ScoreboardManager sM = new ScoreboardManager(this);

    FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            getServer().getPluginManager().registerEvents(new JoinListener(this), this);
            sM = new ScoreboardManager(this);
        } else {
            getLogger().warning("Could not find PlaceholderAPI! This plugin is required.");
            getServer().getPluginManager().disablePlugin(this);
        }

        this.getCommand("coins").setExecutor(new CoinsCmd(this));
        this.getCommand("coinsadd").setExecutor(new CoinsAddCmd(this));
        this.getCommand("coinsset").setExecutor(new CoinsSetCmd(this));
        this.getCommand("coinsremove").setExecutor(new CoinsRemoveCmd(this));

        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);

        loadConfig();
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public void loadConfig() {
        config.options().copyDefaults(true);
        saveConfig();
    }

    private static Economy getPlugin() {
        return plugin;
    }
}