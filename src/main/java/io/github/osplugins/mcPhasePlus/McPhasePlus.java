package io.github.osplugins.mcPhasePlus;

import io.github.osplugins.mcPhasePlus.Listeners.SleepListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class McPhasePlus extends JavaPlugin {
    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new SleepListener(this),this);
        console.sendMessage(ChatColor.AQUA+"MCPhasePlus Started successfully");
    }

    @Override
    public void onDisable() {
        console.sendMessage(ChatColor.RED+"MCPhasePlus shutting down");
    }
}
