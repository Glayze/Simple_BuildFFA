package de.buildffa.event;

import org.bukkit.event.block.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.plugin.*;

import de.buildffa.ptg.*;

import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Block implements Listener
{
    @EventHandler
    public void onPlaceLadder(final BlockPlaceEvent e) {
        final Player p = e.getPlayer();
        final List<Location> locs = new ArrayList<Location>();
        final List<Material> materials = new ArrayList<Material>();
        final List<Byte> bytes = new ArrayList<Byte>();
        final org.bukkit.block.Block b = e.getBlock();
        if (p.getItemInHand().getType() == Material.WEB) {
            locs.add(b.getLocation());
            materials.add(b.getType());
            bytes.add(b.getData());
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)BuildFFA.o, (Runnable)new Runnable() {
                @Override
                public void run() {
                    for (final Location loc : locs) {
                        for (final Material material : materials) {
                            for (final Byte by : bytes) {
                                final org.bukkit.block.Block block = loc.getBlock();
                                block.setType(Material.AIR);
                                block.setData((byte)by);
                                block.getWorld().playEffect(block.getLocation(), Effect.FLAME, 3);
                            }
                        }
                    }
                }
            }, 40L);
        }
    }
    
    @EventHandler
    public void onPlace(final BlockPlaceEvent e) {
        final Player p = e.getPlayer();
        final List<Location> locs = new ArrayList<Location>();
        final List<Material> materials = new ArrayList<Material>();
        final List<Byte> bytes = new ArrayList<Byte>();
        final org.bukkit.block.Block b = e.getBlock();
        if (p.getItemInHand().getType() == Material.SANDSTONE) {
            locs.add(b.getLocation());
            materials.add(b.getType());
            bytes.add(b.getData());
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)BuildFFA.o, (Runnable)new Runnable() {
                @Override
                public void run() {
                    for (final Location loc : locs) {
                        for (final Material material : materials) {
                            for (final Byte by : bytes) {
                                final org.bukkit.block.Block block = loc.getBlock();
                                block.setType(Material.RED_SANDSTONE);
                                block.setData((byte)by);
                                block.getWorld().playEffect(block.getLocation(), Effect.FLAME, 3);
                                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)BuildFFA.o, (Runnable)new Runnable() {
                                    @Override
                                    public void run() {
                                        for (final Location loc : locs) {
                                            for (final Material material : materials) {
                                                for (final Byte by : bytes) {
                                                    final org.bukkit.block.Block block = loc.getBlock();
                                                    block.setType(Material.AIR);
                                                    block.setData((byte)by);
                                                    block.getWorld().playEffect(block.getLocation(), Effect.FLAME, 3);
                                                }
                                            }
                                        }
                                    }
                                }, 80L);
                            }
                        }
                    }
                }
            }, 40L);
        }
    }
}
