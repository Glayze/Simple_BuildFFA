package de.buildffa.ptg.util;

import org.bukkit.entity.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.*;
import org.bukkit.enchantments.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.ItemStack;

import java.util.*;
import org.bukkit.*;
import org.bukkit.Material;

import net.minecraft.server.v1_8_R3.*;
import java.lang.reflect.*;

public class ItemActionbarManager
{
    public static void sendTitle1(final Player player, final Integer fadeIn, final Integer stay, final Integer fadeOut, String title, String subtitle) {
        final PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        final PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, (IChatBaseComponent)null, (int)fadeIn, (int)stay, (int)fadeOut);
        connection.sendPacket((Packet)packetPlayOutTimes);
        if (subtitle != null) {
            subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
            subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
            final IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
            final PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
            connection.sendPacket((Packet)packetPlayOutSubTitle);
        }
        if (title != null) {
            title = title.replaceAll("%player%", player.getDisplayName());
            title = ChatColor.translateAlternateColorCodes('&', title);
            final IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
            final PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
            connection.sendPacket((Packet)packetPlayOutTitle);
        }
    }
    
    public static void sendTitle(final Player p, final int fadeIn, final int stay, final int fadeOut, final String subtitle) {
        sendTitle1(p, fadeIn, stay, fadeOut, subtitle, null);
    }
    
    public static void sendActionbar(final Player p, final String message) {
        final IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}");
        final PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)bar);
    }
    
    public static ItemStack createItem(final Material material, final int anzahl, final int subid, final String displayname) {
        final short newsubid = (short)subid;
        final ItemStack i = new ItemStack(material, anzahl, newsubid);
        final ItemMeta m = i.getItemMeta();
        m.setDisplayName(displayname);
        i.setItemMeta(m);
        return i;
    }
    
    public static ItemStack createItem(final int id, final int anzahl, final String displayname) {
        final ItemStack i = new ItemStack(id, anzahl);
        final ItemMeta m = i.getItemMeta();
        m.setDisplayName(displayname);
        i.setItemMeta(m);
        return i;
    }
    
    public static ItemStack createDurability(final Material material, final int anzahl, final int subid, final String displayname, final int durabilty) {
        final short neuesubid = (short)subid;
        final ItemStack i = new ItemStack(material, anzahl, neuesubid);
        final ItemMeta m = i.getItemMeta();
        m.setDisplayName(displayname);
        i.setDurability((short)durabilty);
        i.setItemMeta(m);
        return i;
    }
    
    public static ItemStack createHeadItem(final String owner, final String displayname) {
        final ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        final SkullMeta sm = (SkullMeta)i.getItemMeta();
        sm.setOwner(owner);
        sm.setDisplayName(displayname);
        i.setItemMeta((ItemMeta)sm);
        return i;
    }
    
    public static ItemStack createEnchantment(final Material material, final int anzahl, final int subid, final String displayname, final Enchantment enchantment, final int enchantmentID) {
        final short neuesubid = (short)subid;
        final ItemStack i = new ItemStack(material, anzahl, neuesubid);
        final ItemMeta m = i.getItemMeta();
        m.setDisplayName(displayname);
        m.addEnchant(enchantment, enchantmentID, true);
        i.setItemMeta(m);
        return i;
    }
    
    public static ItemStack createLeatherBoots(final String displayname, final Color color, final Enchantment enchantment, final int enchantmentID) {
        final ItemStack i = new ItemStack(Material.LEATHER_BOOTS, 1);
        final LeatherArmorMeta im = (LeatherArmorMeta)i.getItemMeta();
        im.setDisplayName(displayname);
        im.setColor(color);
        im.addEnchant(enchantment, enchantmentID, true);
        i.setItemMeta((ItemMeta)im);
        return i;
    }
    
    public static ItemStack createNormalLeatherBoots(final String displayname, final Color color) {
        final ItemStack i = new ItemStack(Material.LEATHER_BOOTS, 1);
        final LeatherArmorMeta im = (LeatherArmorMeta)i.getItemMeta();
        im.setDisplayName(displayname);
        im.setColor(color);
        i.setItemMeta((ItemMeta)im);
        return i;
    }
    
    public static ItemStack createGlowLeatherBoots(final String displayname, final Color color) {
        final ItemStack i = new ItemStack(Material.LEATHER_BOOTS, 1);
        final LeatherArmorMeta im = (LeatherArmorMeta)i.getItemMeta();
        im.setDisplayName(displayname);
        im.setColor(color);
        im.addEnchant(Enchantment.DURABILITY, 0, true);
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        i.setItemMeta((ItemMeta)im);
        return i;
    }
    
    public static ItemStack createItem(final Material mat, final int subid, final String DisplayName) {
        final ItemStack item = new ItemStack(mat, 1, (short)subid);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(DisplayName);
        item.setItemMeta(meta);
        return item;
    }
    
    public static ItemStack createLeatherHelmet(final String displayname, final Color color, final Enchantment enchantment, final int enchantmentID) {
        final ItemStack i = new ItemStack(Material.LEATHER_HELMET, 1);
        final LeatherArmorMeta im = (LeatherArmorMeta)i.getItemMeta();
        im.setDisplayName(displayname);
        im.addEnchant(enchantment, enchantmentID, true);
        im.setColor(color);
        i.setItemMeta((ItemMeta)im);
        return i;
    }
    
    public static ItemStack createLeatherChest(final String displayname, final Color color, final Enchantment enchantment, final int enchantmentID) {
        final ItemStack i = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
        final LeatherArmorMeta lam = (LeatherArmorMeta)i.getItemMeta();
        lam.setDisplayName(displayname);
        lam.addEnchant(enchantment, enchantmentID, true);
        lam.setColor(color);
        i.setItemMeta((ItemMeta)lam);
        return i;
    }
    
    public static ItemStack createLeatherleggings(final String displayname, final Color color, final Enchantment enchantment, final int enchantmentID) {
        final ItemStack i = new ItemStack(Material.LEATHER_LEGGINGS, 1);
        final LeatherArmorMeta lam = (LeatherArmorMeta)i.getItemMeta();
        lam.setDisplayName(displayname);
        lam.addEnchant(enchantment, enchantmentID, true);
        lam.setColor(color);
        i.setItemMeta((ItemMeta)lam);
        return i;
    }
    
    public static ItemStack addLore(final String Display, final Material mat, final String lores, final int Anzahl) {
        final ItemStack is = new ItemStack(mat, Anzahl);
        final ItemMeta im = is.getItemMeta();
        im.setDisplayName(Display);
        final List<String> lore = new ArrayList<String>();
        lore.add(lores);
        im.setLore((List)lore);
        is.setItemMeta(im);
        return is;
    }
    
    public static ItemStack addGlow(final String Display, final int id, final String lores, final int Anzahl) {
        final ItemStack is = new ItemStack(id, Anzahl);
        final ItemMeta im = is.getItemMeta();
        im.setDisplayName(Display);
        final List<String> lore = new ArrayList<String>();
        lore.add(lores);
        im.setLore((List)lore);
        im.addEnchant(Enchantment.DURABILITY, 0, true);
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        is.setItemMeta(im);
        return is;
    }
    
    public static ItemStack Skull(final String Display, final Material m, final String lores, final int Anzahl, final short Shorts, final String Owner) {
        final ItemStack is = new ItemStack(m, Anzahl, (short)3);
        final SkullMeta im = (SkullMeta)Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
        im.setOwner(Owner);
        im.setDisplayName(Display);
        final List<String> lore = new ArrayList<String>();
        lore.add(lores);
        im.setLore((List)lore);
        is.setItemMeta((ItemMeta)im);
        return is;
    }
    
    public static ItemStack coloredArmor(final String Display, final Material m, final String lores, final int Anzahl, final short Shorts, final int RGB1, final int RGB2, final int RGB3) {
        final ItemStack istack52 = new ItemStack(m, Anzahl, Shorts);
        final LeatherArmorMeta istackMeta52 = (LeatherArmorMeta)istack52.getItemMeta();
        istackMeta52.setColor(Color.fromRGB(RGB1, RGB2, RGB3));
        istackMeta52.setDisplayName(Display);
        final List<String> lore = new ArrayList<String>();
        lore.add(lores);
        istackMeta52.setLore((List)lore);
        istack52.setItemMeta((ItemMeta)istackMeta52);
        return istack52;
    }
    
    public static ItemStack addGlowMitMehrerenLores(final String Display, final Material material, final String[] lores, final int Anzahl) {
        final ItemStack is = new ItemStack(material, Anzahl);
        final ItemMeta im = is.getItemMeta();
        im.setDisplayName(Display);
        final List<String> lore = new ArrayList<String>();
        for (final String Text : lores) {
            lore.add(Text);
        }
        im.setLore((List)lore);
        im.addEnchant(Enchantment.DURABILITY, 0, true);
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        is.setItemMeta(im);
        return is;
    }
    
    public static ItemStack createItemMitMehrerenLores(final Material material, final int anzahl, final int subid, final String displayname, final String[] lores) {
        final short newsubid = (short)subid;
        final ItemStack is = new ItemStack(material, anzahl, newsubid);
        final ItemMeta im = is.getItemMeta();
        im.setDisplayName(displayname);
        final List<String> lore = new ArrayList<String>();
        for (final String Text : lores) {
            lore.add(Text);
        }
        im.setLore((List)lore);
        im.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        is.setItemMeta(im);
        return is;
    }
    
    @Deprecated
    public static void sendTitle(final Player player, final Integer fadeIn, final Integer stay, final Integer fadeOut, final String message) {
        sendTitle1(player, fadeIn, stay, fadeOut, message, null);
    }
    
    @Deprecated
    public static void sendSubtitle(final Player player, final Integer fadeIn, final Integer stay, final Integer fadeOut, final String message) {
        sendTitle1(player, fadeIn, stay, fadeOut, null, message);
    }
    
    @Deprecated
    public static void sendFullTitle(final Player player, final Integer fadeIn, final Integer stay, final Integer fadeOut, final String title, final String subtitle) {
        sendTitle1(player, fadeIn, stay, fadeOut, title, subtitle);
    }
    
    public static void sendTitle(final Player player, final Integer fadeIn, final Integer stay, final Integer fadeOut, String title, String subtitle) {
        final PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        final PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, (IChatBaseComponent)null, (int)fadeIn, (int)stay, (int)fadeOut);
        connection.sendPacket((Packet)packetPlayOutTimes);
        if (subtitle != null) {
            subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
            subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
            final IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
            final PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
            connection.sendPacket((Packet)packetPlayOutSubTitle);
        }
        if (title != null) {
            title = title.replaceAll("%player%", player.getDisplayName());
            title = ChatColor.translateAlternateColorCodes('&', title);
            final IChatBaseComponent titleMain = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
            final PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleMain);
            connection.sendPacket((Packet)packetPlayOutTitle);
        }
    }
    
    public static void sendTabTitle(final Player player, String header, String footer) {
        if (header == null) {
            header = "";
        }
        header = ChatColor.translateAlternateColorCodes('&', header);
        if (footer == null) {
            footer = "";
        }
        footer = ChatColor.translateAlternateColorCodes('&', footer);
        header = header.replaceAll("%player%", player.getDisplayName());
        footer = footer.replaceAll("%player%", player.getDisplayName());
        final PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
        final IChatBaseComponent tabTitle = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + header + "\"}");
        final IChatBaseComponent tabFoot = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + footer + "\"}");
        final PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter(tabTitle);
        try {
            final Field field = headerPacket.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(headerPacket, tabFoot);
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
        finally {
            connection.sendPacket((Packet)headerPacket);
        }
        connection.sendPacket((Packet)headerPacket);
    }
}
