package de.buildffa.event;

import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Interact implements Listener
{
    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        try {
            if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getInventory().getItemInHand().getType() == Material.REDSTONE) {
                p.kickPlayer("");
                e.setCancelled(true);
            }
        }
        catch (Exception ex) {}
    }
}
