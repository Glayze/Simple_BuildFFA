package de.buildffa.event;

import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Chat implements Listener
{
    @EventHandler
    public void onChat(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        if (e.getMessage().contains("%")) {
            e.setCancelled(true);
            p.sendMessage("§7Die §cSonderzeichen §7sind hier leider §cnicht §7erlaubt§8!");
        }
        else if (p.hasPermission("chat.admin")) {
            e.setFormat("§4Administrator §8| §4" + p.getName() + " §8» §7" + e.getMessage());
        }
        else if (p.hasPermission("chat.mod")) {
            e.setFormat("§cModerator §8| §c" + p.getName() + " §8» §7" + e.getMessage());
        }
        else if (p.hasPermission("chat.dev")) {
            e.setFormat("§bDeveloper §8| §b" + p.getName() + " §8» §7" + e.getMessage());
        }
        else if (p.hasPermission("chat.sup")) {
            e.setFormat("§3Supporter §8| §3" + p.getName() + " §8» §7" + e.getMessage());
        }
        else if (p.hasPermission("chat.builder")) {
            e.setFormat("§9Builder §8| §9" + p.getName() + " §8» §7" + e.getMessage());
        }
        else if (p.hasPermission("chat.designer")) {
            e.setFormat("§eDesign §8| §e" + p.getName() + " §8» §7" + e.getMessage());
        }
        else if (p.hasPermission("chat.youtuber")) {
            e.setFormat("§5YouTuber §8| §5" + p.getName() + " §8» §7" + e.getMessage());
        }
        else if (p.hasPermission("chat.premiumplus")) {
            e.setFormat("§ePremium§6+ §8| §e" + p.getName() + "§8» §7" + e.getMessage());
        }
        else if (p.hasPermission("chat.premium")) {
            e.setFormat("§2Premium §8| §2" + p.getName() + " §8» §7" + e.getMessage());
        }
        else {
            e.setFormat("§a " + p.getName() + " §8» §7" + e.getMessage());
        }
    }
}
