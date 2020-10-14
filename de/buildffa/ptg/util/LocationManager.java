package de.buildffa.ptg.util;

import org.bukkit.configuration.file.*;
import java.io.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class LocationManager
{
    public static File folder;
    public static File file;
    public static YamlConfiguration cfg;
    
    static {
        LocationManager.folder = new File("plugins/BuildFFA/");
        LocationManager.file = new File("plugins/BuildFFA/loc.yml");
        LocationManager.cfg = YamlConfiguration.loadConfiguration(LocationManager.file);
    }
    
    public static void saveFile() {
        try {
            LocationManager.cfg.save(LocationManager.file);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void setupFiles() {
        if (!LocationManager.folder.exists()) {
            LocationManager.folder.mkdir();
        }
        if (!LocationManager.file.exists()) {
            try {
                LocationManager.file.createNewFile();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        saveFile();
    }
    
    public static void setHeight(final Player p, final String name) {
        LocationManager.cfg.set("Locations." + name + ".Y", (Object)p.getLocation().getBlockY());
        saveFile();
    }
    
    public static void setLocation(final Player p, final String name) {
        final double x = p.getLocation().getBlockX() + 0.5;
        final double y = p.getLocation().getBlockY();
        final double z = p.getLocation().getBlockZ() + 0.5;
        final double yaw = Math.round(p.getLocation().getYaw() * 22.5) / 22.5;
        final double pitch = Math.round(p.getLocation().getPitch() * 22.5) / 22.5;
        final String worldName = p.getWorld().getName();
        LocationManager.cfg.set("Locations." + name + ".X", (Object)x);
        LocationManager.cfg.set("Locations." + name + ".Y", (Object)y);
        LocationManager.cfg.set("Locations." + name + ".Z", (Object)z);
        LocationManager.cfg.set("Locations." + name + ".Yaw", (Object)yaw);
        LocationManager.cfg.set("Locations." + name + ".Pitch", (Object)pitch);
        LocationManager.cfg.set("Locations." + name + ".worldName", (Object)worldName);
        saveFile();
    }
    
    public static int getHeight(final String name) {
        if (LocationManager.cfg.get("Locations." + name + ".Y") != null) {
            return LocationManager.cfg.getInt("Locations." + name + ".Y");
        }
        return 0;
    }
    
    public static Location getLocation(final String name) {
        if (LocationManager.cfg.get("Locations." + name + ".worldName") != null) {
            final double x = LocationManager.cfg.getDouble("Locations." + name + ".X");
            final double y = LocationManager.cfg.getInt("Locations." + name + ".Y");
            final double z = LocationManager.cfg.getDouble("Locations." + name + ".Z");
            final double yaw = LocationManager.cfg.getDouble("Locations." + name + ".Yaw");
            final double pitch = LocationManager.cfg.getDouble("Locations." + name + ".Pitch");
            final String worldName = LocationManager.cfg.getString("Locations." + name + ".worldName");
            return new Location(Bukkit.getWorld(worldName), x, y, z, (float)yaw, (float)pitch);
        }
        return null;
    }
}
