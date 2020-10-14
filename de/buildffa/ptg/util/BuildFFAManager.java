package de.buildffa.ptg.util;

import org.bukkit.entity.*;

import java.sql.SQLException;
import java.util.*;
import org.bukkit.inventory.*;
import de.buildffa.ptg.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.plugin.*;
import org.bukkit.*;

public class BuildFFAManager
{
    public static ArrayList<Player> one;
    public static ArrayList<Player> two;
    public static ArrayList<Player> three;
    public static ArrayList<Player> four;
    public static ArrayList<Player> five;
    
    static {
        BuildFFAManager.one = new ArrayList<Player>();
        BuildFFAManager.two = new ArrayList<Player>();
        BuildFFAManager.three = new ArrayList<Player>();
        BuildFFAManager.four = new ArrayList<Player>();
        BuildFFAManager.five = new ArrayList<Player>();
    }
    
    public static void onJoin(final Player p) {
        onClear(p);
        onSpawn(p);
        onActionbarTitle(p);
        onLeaveItem(p);
        setSidebar();
    }
    
    public static void setStreak(final Player p) {
        if (BuildFFAManager.one.contains(p)) {
            BuildFFAManager.one.remove(p);
            BuildFFAManager.two.add(p);
            if (BuildFFAManager.five.contains(p)) {
                BuildFFAManager.five.remove(p);
            }
        }
        else if (BuildFFAManager.two.contains(p)) {
            BuildFFAManager.two.remove(p);
            BuildFFAManager.three.add(p);
        }
        else if (BuildFFAManager.three.contains(p)) {
            BuildFFAManager.three.remove(p);
            BuildFFAManager.four.add(p);
        }
        else if (BuildFFAManager.four.contains(p)) {
            BuildFFAManager.four.remove(p);
            BuildFFAManager.five.add(p);
        }
        else if (!BuildFFAManager.five.contains(p)) {
            BuildFFAManager.one.add(p);
        }
    }
    
    public static void setSidebar() {
        for (final Player all : Bukkit.getOnlinePlayers()) {
        	Bukkit.getScheduler().runTaskLater(BuildFFA.o, () -> {
        		Sidebar.setScoreboard(all);
        	}, 20*3);
				
        }
    }
    
    public static void onQuit(final Player p) {
        onClear(p);
    }
    
    public static void onClear(final Player p) {
        p.getInventory().clear();
        p.setHealth(20.0);
        p.setFoodLevel(20);
        BuildFFAManager.one.remove(p);
        BuildFFAManager.two.remove(p);
        BuildFFAManager.three.remove(p);
        BuildFFAManager.four.remove(p);
        BuildFFAManager.five.remove(p);
    }
    
    public static void onLeaveItem(final Player p) {
        p.getInventory().setItem(3, ItemActionbarManager.createItem(Material.REDSTONE_COMPARATOR, 0, "§2§lInventar Sortierung"));
        final ItemStack limeDye = new ItemStack(Material.INK_SACK, 1, (short)10);
        final ItemMeta aa = limeDye.getItemMeta();
        aa.setDisplayName("§2§lKillstreak Nachrichten §8| §aAN");
        limeDye.setItemMeta(aa);
        final ItemStack graydye = new ItemStack(Material.INK_SACK, 1, (short)DyeColor.SILVER.getData());
        final ItemMeta aaa = graydye.getItemMeta();
        aaa.setDisplayName("§2§lKillstreak Nachrichten §8| §cAUS");
        graydye.setItemMeta(aaa);
        if (BuildFFA.an.contains(p)) {
            p.getInventory().setItem(5, limeDye);
        }
        else {
            p.getInventory().setItem(5, graydye);
        }
    }
    
    public static void onSpawn(final Player p) {
        p.teleport(LocationManager.getLocation("Spawn"));
    }
    
    public static void onActionbarTitle(final Player p) {
        Bukkit.getScheduler().runTaskTimerAsynchronously((Plugin)BuildFFA.o, (Runnable)new Runnable() {
            @Override
            public void run() {
                ItemActionbarManager.sendActionbar(p, "§7Teaming §cverboten§8!");
            }
        }, 1L, 1L);
    }
    
    public static void onWheater() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)BuildFFA.o, (Runnable)new Runnable() {
            @Override
            public void run() {
                for (final Player all : Bukkit.getOnlinePlayers()) {
                    final World w = all.getWorld();
                    w.setTime(1200L);
                }
            }
        }, 20L, 20L);
    }
}
