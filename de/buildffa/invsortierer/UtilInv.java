package de.buildffa.invsortierer;

import java.io.*;
import org.bukkit.configuration.file.*;
import org.bukkit.inventory.meta.*;
import java.util.*;
import org.bukkit.inventory.*;
import org.bukkit.*;

public class UtilInv
{
    public static Map<String, Inventory> banks;
    
    static {
        UtilInv.banks = new HashMap<String, Inventory>();
    }
    
    public static void saveBank(final String uuid, final Inventory inv) {
        if (!hasBank(uuid)) {
            try {
                new File("/home/BuildFFA/Inventories/" + uuid + ".yml").createNewFile();
            }
            catch (IOException ex) {}
        }
        final ItemStack[] contents = inv.getContents();
        final List<ItemStack> isl = new ArrayList<ItemStack>();
        ItemStack[] arrayOfItemStack1;
        for (int j = (arrayOfItemStack1 = contents).length, i = 0; i < j; ++i) {
            final ItemStack iso = arrayOfItemStack1[i];
            if (iso != null) {
                final ItemStack is = new ItemStack(iso);
                final ItemMeta im = is.getItemMeta();
                if (im.getDisplayName() != null) {
                    im.setDisplayName(im.getDisplayName().replace("§", "&"));
                }
                final List<String> lore = new ArrayList<String>();
                if (im.getLore() != null) {
                    for (final String s : im.getLore()) {
                        lore.add(s.replace("§", "&"));
                    }
                    im.setLore((List)lore);
                }
                is.setItemMeta(im);
                isl.add(is);
            }
        }
        final YamlConfiguration cfg = new YamlConfiguration();
        cfg.set("content", (Object)isl);
        try {
            cfg.save(new File("/home/BuildFFA/Inventories/" + uuid + ".yml"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Inventory loadBank(final String uuid) {
        final File f = new File("/home/BuildFFA/Inventories/" + uuid + ".yml");
        if (!hasBank(uuid)) {
            saveBank(uuid, Bukkit.createInventory((InventoryHolder)null, 9, "§2§lInventar Sortierung"));
        }
        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 9, "§2§lInventar Sortierung");
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);
        final ItemStack[] content = (ItemStack[]) ((List)cfg.get("content")).toArray(new ItemStack[0]);
        ItemStack[] arrayOfItemStack1;
        for (int j = (arrayOfItemStack1 = content).length, i = 0; i < j; ++i) {
            final ItemStack iso = arrayOfItemStack1[i];
            if (iso != null) {
                final ItemStack is = new ItemStack(iso);
                final ItemMeta im = is.getItemMeta();
                if (im.getDisplayName() != null) {
                    im.setDisplayName(ChatColor.translateAlternateColorCodes('&', im.getDisplayName()));
                }
                final List<String> lore = new ArrayList<String>();
                if (im.getLore() != null) {
                    for (final String s : im.getLore()) {
                        lore.add(ChatColor.translateAlternateColorCodes('&', s));
                    }
                    im.setLore((List)lore);
                }
                is.setItemMeta(im);
            }
        }
        inv.setContents(content);
        for (int i = 0; i < inv.getSize(); ++i) {
            if (inv.getItem(i) != null) {
                if (inv.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase("§aSandstein")) {
                    inv.getItem(i).getItemMeta().setDisplayName("§aSandstein");
                }
                if (inv.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase("§aKnockback-Stick")) {
                    inv.getItem(i).getItemMeta().setDisplayName("§aKnockback-Stick");
                }
            }
        }
        return inv;
    }
    
    public static Inventory getBank(final String uuid) {
        if (!UtilInv.banks.containsKey(uuid)) {
            UtilInv.banks.put(uuid, loadBank(uuid));
        }
        return UtilInv.banks.get(uuid);
    }
    
    public static boolean hasBank(final String uuid) {
        final File f = new File("/home/BuildFFA/Inventories/" + uuid + ".yml");
        return f.exists();
    }
}
