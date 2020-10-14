package de.buildffa.cmd;

import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;

import de.buildffa.ptg.*;
import de.buildffa.ptg.util.*;

import org.bukkit.craftbukkit.v1_8_R3.entity.*;
import net.minecraft.server.v1_8_R3.*;
import net.minecraft.server.v1_8_R3.Entity;

public class CMD_set implements CommandExecutor
{
    public boolean onCommand(final CommandSender arg0, final Command arg1, final String arg2, final String[] arg3) {
        final Player p = (Player)arg0;
        if (p.hasPermission("buildffa.set")) {
            if (arg3.length == 0) {
                p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§a/set give ");
                p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§a/set spawn ");
                p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§a/set deathhigh ");
                p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§a/set spawnhigh ");
            }
            else if (arg3[0].equalsIgnoreCase("give")) {
                p.sendMessage(">");
                p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§aSpawn gesetzt");
                p.sendMessage(">");
                LocationManager.setHeight(p, "give");
            }
            else if (arg3[0].equalsIgnoreCase("deathhigh")) {
                LocationManager.setHeight(p, "DeathHeight");
                p.sendMessage(">");
                p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§aDeathHeigt gesetzt");
                p.sendMessage(">");
            }
            else if (arg3[0].equalsIgnoreCase("spawnhigh")) {
                LocationManager.setHeight(p, "SpawnHeight");
                p.sendMessage(">");
                p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§aSpawnHigh gesetzt");
                p.sendMessage(">");
            }
            else if (arg3[0].equalsIgnoreCase("spawn")) {
                p.sendMessage(">");
                p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§aSpawn gesetzt");
                p.sendMessage(">");
                LocationManager.setLocation(p, "Spawn");
            }
        }
        return false;
    }
}
