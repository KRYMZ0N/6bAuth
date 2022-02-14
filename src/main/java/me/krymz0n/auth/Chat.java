package me.krymz0n.auth;

import com.google.common.base.CharMatcher;
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
                    evt.getPlayer().kickPlayer(ChatColor.RED + "You can not use non-ascii characters in your message!");
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
                evt.getPlayer().kickPlayer(ChatColor.RED + "You can not use non-ascii characters in your Command!");
                evt.setCancelled(true);
            }
        }
    }
}
