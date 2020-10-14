package de.buildffa.event;

import org.bukkit.event.weather.*;
import org.bukkit.event.*;

public class Wheater implements Listener
{
    @EventHandler
    public void onWheater(final WeatherChangeEvent e) {
        e.setCancelled(true);
    }
}
