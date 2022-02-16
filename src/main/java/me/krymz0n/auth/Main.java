package me.krymz0n.auth;

import me.krymz0n.auth.command.Reload;
import me.krymz0n.auth.discord.MessageListener;
import me.krymz0n.auth.listener.Chat;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.util.Objects;

public final class Main extends JavaPlugin implements Listener {
    JDA jda;

    @Override
    public void onEnable()  {
        try {
            createJda();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
        saveDefaultConfig();
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(this, this);
        pm.registerEvents(new Chat(this), this);
        Objects.requireNonNull(getCommand("authcore")).setExecutor(new Reload(this));

    }

    public void createJda() throws LoginException, InterruptedException {
        jda = JDABuilder.createDefault(this.getConfig().getString("Token")).addEventListeners(new MessageListener()).build();

        jda.awaitReady();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
