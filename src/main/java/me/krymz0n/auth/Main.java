package me.krymz0n.auth;

import me.krymz0n.auth.command.Reload;
import me.krymz0n.auth.listener.Chat;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(this, this);
        pm.registerEvents(new Chat(this), this);
        Objects.requireNonNull(getCommand("authcore")).setExecutor(new Reload(this));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
