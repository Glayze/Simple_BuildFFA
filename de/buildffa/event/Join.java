package de.buildffa.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import de.buildffa.ptg.BuildFFA;
import de.buildffa.ptg.util.*;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EnumDifficulty;
import net.minecraft.server.v1_8_R3.PacketPlayOutLogin;
import net.minecraft.server.v1_8_R3.WorldSettings.EnumGamemode;
import net.minecraft.server.v1_8_R3.WorldType;

public class Join implements Listener
{
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        final Player player = e.getPlayer();
        e.setJoinMessage((String)null);
        BuildFFAManager.onJoin(player);
        BuildFFA.kills.put(player, 0);
        BuildFFA.tode.put(player, 0);
    }
}
