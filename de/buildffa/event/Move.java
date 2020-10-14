package de.buildffa.event;

import org.bukkit.event.player.*;
import de.buildffa.ptg.util.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Move implements Listener
{
    @EventHandler
    public void onMove(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if (p.getLocation().getBlockY() < LocationManager.getHeight("DeathHeight") && !p.isDead() && p.getHealth() > 0.0) {
            p.setHealth(0.0);
        }
    }
}
