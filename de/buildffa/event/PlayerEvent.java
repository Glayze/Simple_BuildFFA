package de.buildffa.event;

import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.block.*;
import org.bukkit.event.player.*;

import de.buildffa.ptg.util.*;

public class PlayerEvent implements Listener
{
    public static ArrayList<Player> build;
    
    static {
        PlayerEvent.build = new ArrayList<Player>();
    }
    
    @EventHandler
    public void onFood(final FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
    
    @EventHandler
    public void onBlockPlace(final BlockPlaceEvent e) {
        if (e.getPlayer().getLocation().getBlockY() > LocationManager.getHeight("SpawnHeight")) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onBlockBreak(final BlockBreakEvent e) {
        if (!PlayerEvent.build.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onDropItem(final PlayerDropItemEvent e) {
        if (!PlayerEvent.build.contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
}
