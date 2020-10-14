package de.buildffa.invsortierer;

import org.bukkit.event.block.*;
import org.bukkit.*;
import net.md_5.bungee.api.*;
import net.md_5.bungee.api.ChatColor;

import org.bukkit.enchantments.*;
import de.buildffa.ptg.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.inventory.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import de.buildffa.ptg.util.*;
import org.bukkit.plugin.*;
import org.bukkit.event.player.*;

public class InvEvent implements Listener
{
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final ItemStack limeDye = new ItemStack(Material.INK_SACK, 1, (short)10);
        final ItemMeta aa = limeDye.getItemMeta();
        aa.setDisplayName("§2§lKillstreak Nachrichten §8| §aAN");
        limeDye.setItemMeta(aa);
        final ItemStack graydye = new ItemStack(Material.INK_SACK, 1, (short)DyeColor.SILVER.getData());
        final ItemMeta aaa = graydye.getItemMeta();
        aaa.setDisplayName("§2§lKillstreak Nachrichten §8| §cAUS");
        graydye.setItemMeta(aaa);
        try {
            if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getInventory().getItemInHand().getType() == Material.REDSTONE_COMPARATOR) {
                e.setCancelled(true);
                final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 9, "§2§lInventar Sortierung");
                p.getInventory().clear();
                if (MangerInv.checkOrdner(p.getUniqueId())) {
                    final Inventory openinv = UtilInv.getBank(p.getUniqueId().toString());
                    for (int i = 0; i < openinv.getSize(); ++i) {
                        if (openinv.getItem(i) != null) {
                            final ItemStack itemstack = openinv.getItem(i);
                            final ItemMeta meta = itemstack.getItemMeta();
                            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemstack.getItemMeta().getDisplayName()));
                            itemstack.setItemMeta(meta);
                        }
                    }
                    p.openInventory(openinv);
                }
                else {
                    inv.setItem(1, ItemActionbarManager.createEnchantment(Material.STICK, 1, 0, "§aKnockback-Stick", Enchantment.KNOCKBACK, 1));
                    inv.setItem(0, ItemActionbarManager.createEnchantment(Material.GOLD_SWORD, 1, 0, "§aGoldschwert", Enchantment.DURABILITY, 500));
                    ItemStack bow = ItemActionbarManager.createEnchantment(Material.BOW, 1, 0, "§aBogen", Enchantment.DURABILITY, 500);
                    ItemMeta itemMeta = bow.getItemMeta();
                    itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
                    bow.setItemMeta(itemMeta);
                    inv.setItem(2, bow);
                    inv.setItem(3, ItemActionbarManager.createItem(Material.WEB, 2, 0, "§aCobwebs"));
                    inv.setItem(4, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(5, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(6, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(7, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(8, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    UtilInv.saveBank(p.getUniqueId().toString(), inv);
                    for (int j = 0; j < inv.getSize(); ++j) {
                        if (inv.getItem(j) != null) {
                            final ItemStack itemstack2 = inv.getItem(j);
                            final ItemMeta meta2 = itemstack2.getItemMeta();
                            meta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemstack2.getItemMeta().getDisplayName()));
                            itemstack2.setItemMeta(meta2);
                        }
                    }
                    p.openInventory(inv);
                }
            }
        }
        catch (Exception ex) {}
        try {
            if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2§lKillstreak Nachrichten §8| §aAN")) {
                e.setCancelled(true);
                BuildFFA.aus.add(p);
                BuildFFA.an.remove(p);
                p.closeInventory();
                BuildFFAManager.onLeaveItem(p);
                p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Du erhältst nun §ckeine §7Killstreak Nachrichten mehr§8!");
            }
        }
        catch (Exception ex2) {}
        try {
            if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2§lKillstreak Nachrichten §8| §cAUS")) {
                e.setCancelled(true);
                BuildFFA.aus.remove(p);
                BuildFFA.an.add(p);
                p.closeInventory();
                p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Du erhältst §anun §7Killstreak Nachrichten§8!");
                BuildFFAManager.onLeaveItem(p);
            }
        }
        catch (Exception ex3) {}
    }
    
    @EventHandler
    public void onClose(final InventoryCloseEvent e) {
        final Player p = (Player)e.getPlayer();
        if (e.getInventory().getName().equalsIgnoreCase("§2§lInventar Sortierung")) {
            final Inventory inv = e.getInventory();
            boolean bol = false;
            for (int i = 0; i < p.getInventory().getSize(); ++i) {
                if (p.getInventory().getItem(i) != null) {
                    p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Deine Sortierung ist §cunvollständig§8!");
                    inv.setItem(1, ItemActionbarManager.createEnchantment(Material.STICK, 1, 0, "§aKnockback-Stick", Enchantment.KNOCKBACK, 1));
                    inv.setItem(0, ItemActionbarManager.createEnchantment(Material.GOLD_SWORD, 1, 0, "§aGoldschwert", Enchantment.DURABILITY, 500));
                    ItemStack bow = ItemActionbarManager.createEnchantment(Material.BOW, 1, 0, "§aBogen", Enchantment.DURABILITY, 500);
                    ItemMeta itemMeta = bow.getItemMeta();
                    itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
                    bow.setItemMeta(itemMeta);
                    inv.setItem(2, bow);
                    inv.setItem(3, ItemActionbarManager.createItem(Material.WEB, 2, 0, "§aCobwebs"));
                    inv.setItem(4, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(5, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(6, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(7, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(8, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    p.getInventory().clear();
                    final ItemStack limeDye = new ItemStack(Material.INK_SACK, 1, (short)10);
                    final ItemMeta aa = limeDye.getItemMeta();
                    aa.setDisplayName("§2§lKillstreak Nachrichten §8| §aAN");
                    limeDye.setItemMeta(aa);
                    final ItemStack graydye = new ItemStack(Material.INK_SACK, 1, (short)DyeColor.SILVER.getData());
                    final ItemMeta aaa = graydye.getItemMeta();
                    aaa.setDisplayName("§2§lKillstreak Nachrichten §8| §AUS");
                    graydye.setItemMeta(aaa);
                    if (p.getLocation().getBlockY() != LocationManager.getHeight("give")) {
                        Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)BuildFFA.o, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                p.getInventory().clear();
                                BuildFFAManager.onLeaveItem(p);
                            }
                        }, 6L);
                        UtilInv.saveBank(p.getUniqueId().toString(), inv);
                        bol = true;
                        return;
                    }
                    BuildFFA.setJoinInv(p);
                }
                if (!inv.contains(Material.WEB, 1)) {
                    p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Deine Sortierung ist §cunvollständig§8!");
                    inv.setItem(1, ItemActionbarManager.createEnchantment(Material.STICK, 1, 0, "§aKnockback-Stick", Enchantment.KNOCKBACK, 1));
                    inv.setItem(0, ItemActionbarManager.createEnchantment(Material.GOLD_SWORD, 1, 0, "§aGoldschwert", Enchantment.DURABILITY, 500));
                    ItemStack bow = ItemActionbarManager.createEnchantment(Material.BOW, 1, 0, "§aBogen", Enchantment.DURABILITY, 500);
                    ItemMeta itemMeta = bow.getItemMeta();
                    itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
                    bow.setItemMeta(itemMeta);
                    inv.setItem(2, bow);
                    inv.setItem(3, ItemActionbarManager.createItem(Material.WEB, 2, 0, "§aCobwebs"));
                    inv.setItem(4, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(5, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(6, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(7, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(8, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    
                    p.getInventory().clear();
                    final ItemStack limeDye = new ItemStack(Material.INK_SACK, 1, (short)10);
                    final ItemMeta aa = limeDye.getItemMeta();
                    aa.setDisplayName("§2§lKillstreak Nachrichten §8| §cAN");
                    limeDye.setItemMeta(aa);
                    final ItemStack graydye = new ItemStack(Material.INK_SACK, 1, (short)DyeColor.SILVER.getData());
                    final ItemMeta aaa = graydye.getItemMeta();
                    aaa.setDisplayName("§2§lKillstreak Nachrichten §8| §cAUS");
                    graydye.setItemMeta(aaa);
                    if (p.getLocation().getBlockY() != LocationManager.getHeight("give")) {
                        BuildFFA.an.contains(p);
                        Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)BuildFFA.o, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                p.getInventory().clear();
                            }
                        }, 6L);
                        UtilInv.saveBank(p.getUniqueId().toString(), inv);
                        bol = true;
                    }
                }
                if (!inv.contains(Material.GOLD_SWORD, 1)) {
                    p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Deine Sortierung ist §cunvollständig§8!");
                    inv.setItem(1, ItemActionbarManager.createEnchantment(Material.STICK, 1, 0, "§aKnockback-Stick", Enchantment.KNOCKBACK, 1));
                    inv.setItem(0, ItemActionbarManager.createEnchantment(Material.GOLD_SWORD, 1, 0, "§aGoldschwert", Enchantment.DURABILITY, 500));
                    ItemStack bow = ItemActionbarManager.createEnchantment(Material.BOW, 1, 0, "§aBogen", Enchantment.DURABILITY, 500);
                    ItemMeta itemMeta = bow.getItemMeta();
                    itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
                    bow.setItemMeta(itemMeta);
                    inv.setItem(2, bow);
                    inv.setItem(3, ItemActionbarManager.createItem(Material.WEB, 2, 0, "§aCobwebs"));
                    inv.setItem(4, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(5, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(6, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(7, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(8, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    
                    p.getInventory().clear();
                    final ItemStack limeDye = new ItemStack(Material.INK_SACK, 1, (short)10);
                    final ItemMeta aa = limeDye.getItemMeta();
                    aa.setDisplayName("§2§lKillstreak Nachrichten §8| §cAN");
                    limeDye.setItemMeta(aa);
                    final ItemStack graydye = new ItemStack(Material.INK_SACK, 1, (short)DyeColor.SILVER.getData());
                    final ItemMeta aaa = graydye.getItemMeta();
                    aaa.setDisplayName("§2§lKillstreak Nachrichten §8| §cAUS");
                    graydye.setItemMeta(aaa);
                    if (p.getLocation().getBlockY() != LocationManager.getHeight("give")) {
                        BuildFFA.an.contains(p);
                        Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)BuildFFA.o, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                p.getInventory().clear();
                            }
                        }, 6L);
                        UtilInv.saveBank(p.getUniqueId().toString(), inv);
                        bol = true;
                    }
                }
                
                if (!inv.contains(Material.STICK, 1)) {
                    p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Deine Sortierung ist §cunvollständig§8!");
                    inv.setItem(1, ItemActionbarManager.createEnchantment(Material.STICK, 1, 0, "§aKnockback-Stick", Enchantment.KNOCKBACK, 1));
                    inv.setItem(0, ItemActionbarManager.createEnchantment(Material.GOLD_SWORD, 1, 0, "§aGoldschwert", Enchantment.DURABILITY, 500));
                    ItemStack bow = ItemActionbarManager.createEnchantment(Material.BOW, 1, 0, "§aBogen", Enchantment.DURABILITY, 500);
                    ItemMeta itemMeta = bow.getItemMeta();
                    itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
                    bow.setItemMeta(itemMeta);
                    inv.setItem(2, bow);                   
                    inv.setItem(3, ItemActionbarManager.createItem(Material.WEB, 2, 0, "§aCobwebs"));
                    inv.setItem(4, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(5, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(6, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(7, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(8, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    
                    p.getInventory().clear();
                    final ItemStack limeDye = new ItemStack(Material.INK_SACK, 1, (short)10);
                    final ItemMeta aa = limeDye.getItemMeta();
                    aa.setDisplayName("§2§lKillstreak Nachrichten | §aAN");
                    limeDye.setItemMeta(aa);
                    final ItemStack graydye = new ItemStack(Material.INK_SACK, 1, (short)DyeColor.SILVER.getData());
                    final ItemMeta aaa = graydye.getItemMeta();
                    aaa.setDisplayName("§2§lKillstreak Nachrichten | §cAUS");
                    graydye.setItemMeta(aaa);
                    if (p.getLocation().getBlockY() == LocationManager.getHeight("give")) {
                        BuildFFA.setJoinInv(p);
                    }
                    else {
                        BuildFFA.an.contains(p);
                        Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)BuildFFA.o, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                p.getInventory().clear();
                                BuildFFAManager.onLeaveItem(p);
                            }
                        }, 6L);
                        UtilInv.saveBank(p.getUniqueId().toString(), inv);
                        bol = true;
                    }
                }
                if (!inv.contains(Material.BOW, 1)) {
                    p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Deine Sortierung ist §cunvollständig§8!");
                    inv.setItem(1, ItemActionbarManager.createEnchantment(Material.STICK, 1, 0, "§aKnockback-Stick", Enchantment.KNOCKBACK, 1));
                    inv.setItem(0, ItemActionbarManager.createEnchantment(Material.GOLD_SWORD, 1, 0, "§aGoldschwert", Enchantment.DURABILITY, 500));
                    ItemStack bow = ItemActionbarManager.createEnchantment(Material.BOW, 1, 0, "§aBogen", Enchantment.DURABILITY, 500);
                    ItemMeta itemMeta = bow.getItemMeta();
                    itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
                    bow.setItemMeta(itemMeta);
                    inv.setItem(2, bow);
                    inv.setItem(3, ItemActionbarManager.createItem(Material.WEB, 2, 0, "§aCobwebs"));
                    inv.setItem(4, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(5, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(6, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(7, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(8, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    
                    p.getInventory().clear();
                    final ItemStack limeDye = new ItemStack(Material.INK_SACK, 1, (short)10);
                    final ItemMeta aa = limeDye.getItemMeta();
                    aa.setDisplayName("§§2§lKillstreak Nachrichten §8| §aAN");
                    limeDye.setItemMeta(aa);
                    final ItemStack graydye = new ItemStack(Material.INK_SACK, 1, (short)DyeColor.SILVER.getData());
                    final ItemMeta aaa = graydye.getItemMeta();
                    aaa.setDisplayName("§2§lKillstreak Nachrichten §8| §cAUS");
                    graydye.setItemMeta(aaa);
                    if (p.getLocation().getBlockY() == LocationManager.getHeight("give")) {
                        BuildFFA.setJoinInv(p);
                    }
                    else {
                        BuildFFA.an.contains(p);
                        Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)BuildFFA.o, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                p.getInventory().clear();
                                BuildFFAManager.onLeaveItem(p);
                            }
                        }, 6L);
                        UtilInv.saveBank(p.getUniqueId().toString(), inv);
                        bol = true;
                    }
                }
                
               
                if (!inv.contains(Material.SANDSTONE, 320)) {
                    p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Deine Sortierung ist §cunvollständig§8!");
                    inv.setItem(1, ItemActionbarManager.createEnchantment(Material.STICK, 1, 0, "§aKnockback-Stick", Enchantment.KNOCKBACK, 1));
                    inv.setItem(0, ItemActionbarManager.createEnchantment(Material.GOLD_SWORD, 1, 0, "§aGoldschwert", Enchantment.DURABILITY, 500));
                    ItemStack bow = ItemActionbarManager.createEnchantment(Material.BOW, 1, 0, "§aBogen", Enchantment.DURABILITY, 500);
                    ItemMeta itemMeta = bow.getItemMeta();
                    itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
                    bow.setItemMeta(itemMeta);
                    inv.setItem(2, bow);
                    inv.setItem(3, ItemActionbarManager.createItem(Material.WEB, 2, 0, "§aCobwebs"));
                    inv.setItem(4, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(5, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(6, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(7, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                    inv.setItem(8, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
                                   
                    p.getInventory().clear();
                    final ItemStack limeDye = new ItemStack(Material.INK_SACK, 1, (short)10);
                    final ItemMeta aa = limeDye.getItemMeta();
                    aa.setDisplayName("§2§lKillstreak Nachrichten §8| §aAN");
                    limeDye.setItemMeta(aa);
                    final ItemStack graydye = new ItemStack(Material.INK_SACK, 1, (short)DyeColor.SILVER.getData());
                    final ItemMeta aaa = graydye.getItemMeta();
                    aaa.setDisplayName("§2§lKillstreak Nachrichten §8| §cAUS");
                    graydye.setItemMeta(aaa);
                    if (p.getLocation().getBlockY() == LocationManager.getHeight("give")) {
                        BuildFFA.setJoinInv(p);
                    }
                    else {
                        BuildFFA.an.contains(p);
                        Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)BuildFFA.o, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                p.getInventory().clear();
                                BuildFFAManager.onLeaveItem(p);
                            }
                        }, 6L);
                        UtilInv.saveBank(p.getUniqueId().toString(), inv);
                        bol = true;
                    }
                }
            }
            if (bol) {
                return;
            }
            UtilInv.saveBank(p.getUniqueId().toString(), inv);
            p.getInventory().clear();
            Bukkit.getScheduler().runTaskLater((Plugin)BuildFFA.o, (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.getInventory().setContents(UtilInv.getBank(p.getUniqueId().toString()).getContents());
                    for (int i = 0; i < p.getInventory().getSize(); ++i) {
                        if (p.getInventory().getItem(i) != null) {
                            final ItemStack itemstack = p.getInventory().getItem(i);
                            final ItemMeta meta = itemstack.getItemMeta();
                            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemstack.getItemMeta().getDisplayName()));
                            itemstack.setItemMeta(meta);
                        }
                    }
                    p.updateInventory();
                    p.getInventory().clear();
                    final ItemStack limeDye = new ItemStack(Material.INK_SACK, 1, (short)10);
                    final ItemMeta aa = limeDye.getItemMeta();
                    aa.setDisplayName("§2§lKillstreak Nachrichten §8| §aAN");
                    limeDye.setItemMeta(aa);
                    final ItemStack graydye = new ItemStack(Material.INK_SACK, 1, (short)DyeColor.SILVER.getData());
                    final ItemMeta aaa = graydye.getItemMeta();
                    aaa.setDisplayName("§2§lKillstreak Nachrichten §8| §cAUS");
                    if (p.getLocation().getBlockY() == LocationManager.getHeight("give")) {
                        BuildFFA.setJoinInv(p);
                    }
                    else {
                        graydye.setItemMeta(aaa);
                        BuildFFA.an.contains(p);
                        Bukkit.getScheduler().runTaskLaterAsynchronously((Plugin)BuildFFA.o, (Runnable)new Runnable() {
                            @Override
                            public void run() {
                                p.getInventory().clear();
                                BuildFFAManager.onLeaveItem(p);
                            }
                        }, 6L);
                    }
                }
            }, 1L);
            p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Deine Sortierung wurde §agespeichert§8!");
        }
    }
    
    private static ItemStack createItem(final Material mat, final int anzahl, final String name) {
        final ItemStack i = new ItemStack(mat, anzahl);
        final ItemMeta m = i.getItemMeta();
        m.setDisplayName(name);
        i.setItemMeta(m);
        return i;
    }
    
    private static ItemStack createEnchantedItem(final Material mat, final int anzahl, final String name, final Enchantment en, final int power) {
        final ItemStack i = new ItemStack(mat, anzahl);
        final ItemMeta m = i.getItemMeta();
        m.setDisplayName(name);
        m.addEnchant(en, power, true);
        i.setItemMeta(m);
        return i;
    }
    
    @EventHandler
    public void Onmove(PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if (p.getLocation().getBlockY() == LocationManager.getHeight("give")) {
            p.closeInventory();
            BuildFFA.setJoinInv(p);
        }
    }
}
