package io.github.osplugins.mcPhasePlus.Listeners;

import io.github.osplugins.mcPhasePlus.McPhasePlus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class SleepListener  implements Listener {
    private final McPhasePlus plugin;
    private int sleepingPlayers = 0;

    public SleepListener(McPhasePlus mc){
        this.plugin = mc;
        startDaytimeCheck();
    }
    @EventHandler
    public void CheckSleeping(PlayerBedEnterEvent e){
        if (!e.isCancelled()) {
            Player player = e.getPlayer();
            sleepingPlayers++;
            Bukkit.broadcastMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.GREEN + " is sleeping " + sleepingPlayers + "/" + Bukkit.getOnlinePlayers().size());
        }
    }
    @EventHandler
    public void CheckExitSleeping(PlayerBedLeaveEvent e){
        if (!e.isCancelled()) {
            Player player = e.getPlayer();
            sleepingPlayers--;
            long worldTime = Bukkit.getWorlds().getFirst().getTime();
            if (worldTime >= 12000) {
                Bukkit.broadcastMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.RED + " is awake " + sleepingPlayers + "/" + Bukkit.getOnlinePlayers().size());
            }
        }
    }
    //this might be excessive, but it's just for resetting sleeping players to 0 in case some issue happens
    public void startDaytimeCheck() {
        new BukkitRunnable() {
            @Override
            public void run() {
                long worldTime = Bukkit.getWorlds().getFirst().getTime();
                if (worldTime < 12000) {
                    sleepingPlayers = 0;
                }
            }
        }.runTaskTimer(plugin, 0L, 500L);
    }

}
