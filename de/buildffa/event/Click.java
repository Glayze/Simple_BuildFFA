package de.buildffa.event;

import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Click implements Listener
{
    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        try {
            if (!e.getClickedInventory().getName().equalsIgnoreCase("§2§lInventar Sortierung")) {
                e.setCancelled(true);
            }
        }
        catch (Exception ex) {}
    }
}
