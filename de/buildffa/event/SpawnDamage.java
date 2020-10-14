package de.buildffa.event;

import org.bukkit.event.entity.*;

import de.buildffa.ptg.util.*;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class SpawnDamage implements Listener
{
    @EventHandler
    public void onDamage(final EntityDamageEvent e) {
        if (e.getEntity().getLocation().getBlockY() > LocationManager.getHeight("SpawnHeight")) {
            if (e.getEntity() instanceof Player) {
                e.setCancelled(true);
            }
        }
        else {
            final boolean b = e.getEntity() instanceof Player;
        }
        if (e.getCause().equals((Object)EntityDamageEvent.DamageCause.FALL)) {
            e.setCancelled(true);
        }
    }
}
