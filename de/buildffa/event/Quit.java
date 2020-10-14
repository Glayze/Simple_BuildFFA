package de.buildffa.event;

import org.bukkit.event.player.*;

import de.buildffa.ptg.BuildFFA;
import de.buildffa.ptg.util.*;
import de.buildffa.stats.Stats;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Quit implements Listener
{
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        Player player = event.getPlayer();
        if (BuildFFA.kills.get(player) != null) {
            try {
                Stats.setKills(player.getUniqueId(), Stats.getKills(player.getUniqueId()) + BuildFFA.kills.get(player));
                BuildFFA.kills.remove(player);
            } catch (Exception var5) {
            }
        }

        if (BuildFFA.tode.get(player) != null) {
            try {
                Stats.setDeaths(player.getUniqueId(), Stats.getDeaths(player.getUniqueId()) + BuildFFA.tode.get(player));
                BuildFFA.tode.remove(player);
            } catch (Exception var4) {
            }
        }

    }

    @EventHandler
    public void onQuit(PlayerKickEvent event) {
        event.setLeaveMessage(null);
        Player player = event.getPlayer();
        if (BuildFFA.kills.get(player) != null) {
            try {
                Stats.setKills(player.getUniqueId(), Stats.getKills(player.getUniqueId()) + BuildFFA.kills.get(player));
                BuildFFA.kills.remove(player);
            } catch (Exception var5) {
            }
        }

        if (BuildFFA.tode.get(player) != null) {
            try {
                Stats.setDeaths(player.getUniqueId(), Stats.getDeaths(player.getUniqueId()) + BuildFFA.tode.get(player));
                BuildFFA.tode.remove(player);
            } catch (Exception var4) {
            }
        }

    }
}
