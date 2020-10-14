package de.buildffa.event;

import org.bukkit.event.entity.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;

import de.buildffa.ptg.*;
import de.buildffa.ptg.util.*;
import de.buildffa.stats.*;

import java.sql.SQLException;
import java.util.*;
import org.bukkit.event.*;

public class Death implements Listener
{
    @EventHandler
    public void onDeath(final PlayerDeathEvent e) {
        e.setDeathMessage((String)null);
        final Player p = e.getEntity();
        final Player t = p.getKiller();
        Integer deaths = BuildFFA.tode.get(p);
        if (deaths == null) {
            BuildFFA.tode.remove(p);
            BuildFFA.tode.put(p, 1);
        } else {
            BuildFFA.tode.remove(p);
            BuildFFA.tode.put(p, deaths + 1);
        }
        BuildFFAManager.onClear(p);
        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)BuildFFA.o, (Runnable)new Runnable() {
            @Override
            public void run() {
                p.spigot().respawn();
                p.setCanPickupItems(false);
                p.teleport(LocationManager.getLocation("Spawn"));
                p.getInventory().clear();
                BuildFFAManager.onLeaveItem(p);
            }
        }, 1L);
        e.getDrops().clear();
        if (t == null) {
            p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Du bist §cgestorben§8.");
            BuildFFAManager.setSidebar();
        }
        else {
            Integer kills = BuildFFA.kills.get(t);
            if (kills == null) {
                BuildFFA.kills.remove(t);
                BuildFFA.kills.put(t, 1);
            } else {
                BuildFFA.kills.remove(t);
                BuildFFA.kills.put(t, kills + 1);
            }
            BuildFFAManager.setStreak(t);
            t.setLevel(t.getLevel() + 1);
            t.setHealth(20.0D);
            if (t.getLevel() == 5 
            		|| t.getLevel() == 10
            		|| t.getLevel() == 15
            		|| t.getLevel() == 20
            		|| t.getLevel() == 25
            		|| t.getLevel() == 30
            		|| t.getLevel() == 35
            		) {
                for (final Player all : Bukkit.getOnlinePlayers()) {
                    if (BuildFFA.an.contains(all)) {
                        all.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Killstreak §a " + t.getDisplayName() + " §c" + t.getLevel());
                    }
                }
            }
            t.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Du hast §a" + p.getDisplayName() + " §7getötet§8!");
            p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Du wurdest von §a" + t.getDisplayName() + " §7getötet§8!");
        }
        BuildFFAManager.setSidebar();
    }
}
