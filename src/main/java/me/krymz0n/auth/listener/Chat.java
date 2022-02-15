package me.krymz0n.auth.listener;

import com.google.common.base.CharMatcher;
import me.krymz0n.auth.Main;
import me.krymz0n.auth.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Chat implements Listener {
    private final Main plugin;

    public Chat(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent evt) {
        boolean isAscii = CharMatcher.ascii().matchesAllOf(evt.getMessage());

        if (!isAscii) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    evt.getPlayer().kickPlayer(Utils.colors(plugin.getConfig().getString("NonAsciiKickMessage")));
                    cancel();
                }
            }.runTask(plugin);
            evt.setCancelled(true);
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent evt) {
        if (plugin.getConfig().getBoolean("DisableAsciiCommand")) {
            boolean isAscii = CharMatcher.ascii().matchesAllOf(evt.getMessage());

            if (!isAscii) {
                evt.getPlayer().kickPlayer(Utils.colors(plugin.getConfig().getString("NonAsciiKickMessageCommand")));
                evt.setCancelled(true);
            }
        }
    }
}
