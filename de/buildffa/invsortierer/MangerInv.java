package de.buildffa.invsortierer;

import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.configuration.file.*;
import de.buildffa.ptg.*;
import java.io.*;
import java.util.*;

public class MangerInv
{
    private static ArrayList<ItemStack> list;
    
    static {
        MangerInv.list = new ArrayList<ItemStack>();
    }
    
    public static void saveInventory(final UUID uuid, final Player p) {
        final String id = uuid.toString();
        final File file = new File("/home/BuildFFA//Inventories//" + id + ".yml");
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        Label_0132: {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    break Label_0132;
                }
                catch (IOException error) {
                    p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Es ist ein §cFehler §7beim erstellen deiner §cInventar-Datei §7aufgetreten§8! §7Versuch es erneut§8!");
                    return;
                }
            }
            cfg.set("Inventory", (Object)null);
            try {
                cfg.save(file);
            }
            catch (IOException e) {
                p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Es ist ein §cFehler §7beim editieren deiner §cInventar-Datei §7aufgetreten§8! §7Versuch es erneut§8!");
                return;
            }
        }
        final ItemStack[] contents = p.getInventory().getContents();
        for (int i = 0; i < contents.length; ++i) {
            final ItemStack item = contents[i];
            if (item != null) {
                MangerInv.list.add(item);
            }
        }
        cfg.set("Inventory", (Object)MangerInv.list);
        try {
            cfg.save(file);
        }
        catch (IOException e2) {
            if (p != null) {
                p.sendMessage(String.valueOf(BuildFFA.Prefix) + "§7Es ist ein §cFehler §7beim editieren deiner §cInventar-Datei §7aufgetreten§8! §7Versuch es erneut§8!");
            }
        }
    }
    
    public static ItemStack[] loadInventory(final UUID uuid, final Player p) {
        final String id = uuid.toString();
        final File file = new File("/home/BuildFFA//Inventories//" + id + ".yml");
        if (file.exists()) {
            final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
            final ItemStack[] contents = p.getInventory().getContents();
            final List<?> list = (List<?>)cfg.getList("Inventory");
            for (int i = 0; i < list.size(); ++i) {
                contents[i] = (ItemStack)list.get(i);
            }
            return contents;
        }
        return null;
    }
    
    public static void checkOrdner() {
        final File file = new File("/home/BuildFFA//Inventories//");
        if (!file.exists()) {
            file.mkdir();
        }
    }
    
    public static boolean checkOrdner(final UUID uuid) {
        try {
            final File file2 = new File("/home/BuildFFA//Inventories//" + uuid.toString() + ".yml");
        }
        catch (NullPointerException | IllegalArgumentException ex2) {
            final RuntimeException ex = null;
            final RuntimeException e = ex;
            return false;
        }
        final File file = new File("/home/BuildFFA//Inventories//" + uuid.toString() + ".yml");
        return file.exists();
    }
    
    public static void save(final UUID uuid, final ItemStack[] itemstack) {
        final File file = new File("/home/BuildFFA//Inventories//" + uuid.toString() + ".yml");
        new YamlConfiguration();
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set("Inventory", (Object)itemstack);
        try {
            cfg.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void restore(final Player p) {
        final YamlConfiguration c = YamlConfiguration.loadConfiguration(new File("/home/BuildFFA//Inventories//" + p.getUniqueId().toString() + ".yml"));
        @SuppressWarnings("unchecked")
		final ItemStack[] content = (ItemStack[]) ((List)c.get("Inventory")).toArray(new ItemStack[0]);
        p.getInventory().setContents(content);
    }
}
