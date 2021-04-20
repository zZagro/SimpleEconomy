package de.zagro.economy.utils;

import de.zagro.economy.Economy;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {

    private final Economy plugin;

    public ScoreboardManager(Economy plugin) {
        this.plugin = plugin;
    }

    String ipName = "test.net";
    String rank;

    public void setScoreboard(Player player) {
        rank = PlaceholderAPI.setPlaceholders(player, "%vault_prefix%");

        org.bukkit.scoreboard.ScoreboardManager sm = Bukkit.getScoreboardManager();
        Scoreboard sb = sm.getNewScoreboard();

        Objective o = sb.registerNewObjective("Scoreboard", "dummy");
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName(ChatColor.DARK_AQUA + "Scoreboard");

        Score blank1 = o.getScore(" ");
        blank1.setScore(2);
        Score blank2 = o.getScore("  ");
        blank2.setScore(4);
        Score blank3 = o.getScore("   ");
        blank3.setScore(6);
        Score blank4 = o.getScore("    ");
        blank4.setScore(8);

        Score ip = o.getScore(ChatColor.GREEN + ipName);
        ip.setScore(1);
        Score coins = o.getScore(ChatColor.GOLD + "Coins: " + plugin.getConfig().getDouble(player.getUniqueId().toString() + ".coins"));
        coins.setScore(3);
        Score playerRank = o.getScore(ChatColor.GOLD + "Rank: " + rank);
        playerRank.setScore(5);
        Score playerCount = o.getScore(ChatColor.GREEN + "Players: " + Bukkit.getServer().getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
        playerCount.setScore(7);

        player.setScoreboard(sb);
    }
}
