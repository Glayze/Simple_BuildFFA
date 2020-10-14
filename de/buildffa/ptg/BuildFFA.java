package de.buildffa.ptg;

import org.bukkit.plugin.java.*;
import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.plugin.*;
import de.buildffa.event.*;
import de.buildffa.cmd.*;
import org.bukkit.command.*;
import org.bukkit.enchantments.*;
import de.buildffa.ptg.util.*;
import de.buildffa.stats.MySQL;
import de.buildffa.invsortierer.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import org.bukkit.inventory.meta.*;

public class BuildFFA extends JavaPlugin
{
    public static String Prefix;
    public static BuildFFA o;
    public static HashMap<Player, Integer> kills;
    public static HashMap<Player, Integer> tode;
    public static ArrayList<Player> an;
    public static ArrayList<Player> aus;
    
    static {
        BuildFFA.Prefix = "§8[§2§lBuild§a§lFFA§8] ";
        BuildFFA.an = new ArrayList<Player>();
        BuildFFA.aus = new ArrayList<Player>();
    }
    
    public void onEnable() {
        BuildFFA.o = this;
        BuildFFAManager.onWheater();
        LocationManager.setupFiles();
        kills = new HashMap();
        tode = new HashMap();
        this.getServer().getPluginManager().registerEvents((Listener)new Block(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new PlayerEvent(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new Join(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new Quit(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new Move(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new Death(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new InvEvent(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new Interact(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new Click(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new Wheater(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new SpawnDamage(), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new Chat(), (Plugin)this);
        this.getCommand("set").setExecutor((CommandExecutor)new CMD_set());
        this.getCommand("stats").setExecutor((CommandExecutor)new CMD_stats());
        BuildFFAManager.onWheater();
        MySQL.setStandardMySQL();
        MySQL.readMySQL();
        MySQL.connect();
        MySQL.createTable();
        Bukkit.getConsoleSender().sendMessage("§aDas Plugin wurde erfolgreich geladen!");
    }
    
    
    public void onDisable() {
    	MySQL.close();
    }
    
    public static void setJoinInv(final Player p) {
        p.getInventory().clear();
        p.getInventory().setArmorContents((ItemStack[])null);
        p.setLevel(0);
        p.setHealth(20.0);
        p.setFoodLevel(20);
        ItemStack bow = ItemActionbarManager.createEnchantment(Material.BOW, 1, 0, "§aBogen", Enchantment.DURABILITY, 500);
        ItemMeta itemMeta = bow.getItemMeta();
        itemMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
        bow.setItemMeta(itemMeta);
        p.getInventory().setItem(1, ItemActionbarManager.createEnchantment(Material.STICK, 1, 0, "§aKnockback-Stick", Enchantment.KNOCKBACK, 1));
        p.getInventory().setItem(0, ItemActionbarManager.createEnchantment(Material.GOLD_SWORD, 1, 0, "§aGoldschwert", Enchantment.DURABILITY, 500));
        p.getInventory().setItem(2, bow);
        p.getInventory().setItem(3, ItemActionbarManager.createItem(Material.WEB, 2, 0, "§aCobwebs"));
        p.getInventory().setItem(4, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
        p.getInventory().setItem(5, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
        p.getInventory().setItem(6, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
        p.getInventory().setItem(7, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
        p.getInventory().setItem(8, ItemActionbarManager.createItem(Material.SANDSTONE, 64, 0, "§aSandstein"));
        p.updateInventory();
        UtilInv.saveBank(p.getUniqueId().toString(), (Inventory)p.getInventory());
        if (UtilInv.getBank(p.getUniqueId().toString()) != null) {
            p.getInventory().clear();
            p.getInventory().setContents(UtilInv.getBank(p.getUniqueId().toString()).getContents());
            for (int i = 0; i < p.getInventory().getSize(); ++i) {
                if (p.getInventory().getItem(i) != null) {
                    p.updateInventory();
                    final ItemStack itemstack = p.getInventory().getItem(i);
                    final ItemMeta meta = itemstack.getItemMeta();
                    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemstack.getItemMeta().getDisplayName()));
                    itemstack.setItemMeta(meta);
                    p.updateInventory();
                }
            }
        }
        p.getInventory().setHelmet(ItemActionbarManager.createEnchantment(Material.LEATHER_HELMET, 1, 0, "§eHelm", Enchantment.DURABILITY, 500));
        p.getInventory().setChestplate(ItemActionbarManager.createEnchantment(Material.CHAINMAIL_CHESTPLATE, 1, 0, "§eBrustplatte", Enchantment.DURABILITY, 500));
        p.getInventory().setLeggings(ItemActionbarManager.createEnchantment(Material.LEATHER_LEGGINGS, 1, 0, "§eHose", Enchantment.DURABILITY, 500));
        p.getInventory().setBoots(ItemActionbarManager.createEnchantment(Material.LEATHER_BOOTS, 1, 0, "§eSchuhe", Enchantment.DURABILITY, 500));
        p.getInventory().setItem(9, ItemActionbarManager.createItem(Material.ARROW, 1, 0, "§aPfeil"));
        p.updateInventory();
    }
}
