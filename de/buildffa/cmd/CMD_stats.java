package de.buildffa.cmd;

import org.bukkit.command.*;

import java.sql.SQLException;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;

import de.buildffa.ptg.*;
import de.buildffa.ptg.util.*;
import de.buildffa.stats.Stats;

import org.bukkit.craftbukkit.v1_8_R3.entity.*;
import net.minecraft.server.v1_8_R3.*;
import net.minecraft.server.v1_8_R3.Entity;

public class CMD_stats implements CommandExecutor
{
	@SuppressWarnings("deprecation")
    public boolean onCommand(final CommandSender arg0, final Command arg1, final String arg2, final String[] args) {
        final Player p = (Player)arg0;
        if(args.length == 0) {
        	p.sendMessage("");
            p.sendMessage("§8» §cStats");
        	p.sendMessage("");    
        	try {
        		double kills;
                double deaths;
                double kd;
                int kills2 = Stats.getKills(p.getUniqueId());
                if (BuildFFA.kills.get(p) != null) {
                    kills2 = Stats.getKills(p.getUniqueId()) + (Integer) BuildFFA.kills.get(p);
                }
                
                int tode = Stats.getDeaths(p.getUniqueId());
                if (BuildFFA.tode.get(p) != null) {
                    tode = Stats.getDeaths(p.getUniqueId()) + (Integer) BuildFFA.tode.get(p);
                }
                
                kills = (double) kills2;
                deaths = (double) tode;
                if(deaths == 0.0D) {
                	p.sendMessage("§8• §aKD§8: §c " + kills);
                } else {
                	kd = kills / deaths;
                	double gerundetekd = Double.valueOf((double) Math.round(kd * 100.0D) / 100.0D);
                	p.sendMessage("§8• §aKD§8: §c " + gerundetekd);
                }
                
    
	        	p.sendMessage("§8• §aPlatz§8: §c" + Stats.getRank(p.getUniqueId().toString()));                
	        	p.sendMessage("§8• §aKills§8: §c" + kills2);
	        	p.sendMessage("§8• §aTode§8: §c" + tode);
	        	p.sendMessage("");
    		} catch (SQLException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        }else if(args.length == 1) {
			OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
    		try {
				if(!Stats.existsPlayer(target.getUniqueId()) || target == null) {
					p.sendMessage("§8[§2§lBuildFFA§8] §7Dieser §aSpieler §7war noch nie auf diesem Netzwerk§8!");
					return false;
				}
				
			} catch (SQLException e) {
				p.sendMessage("§8[§2§lBuildFFA§8] §7Dieser §aSpieler §7war noch nie auf diesem Netzwerk§8!");
				
				e.printStackTrace();
				
			}
        	try {
	        	p.sendMessage("§8» §cStats von §a" + target.getName());
	        	p.sendMessage("");
        		double kills;
                double deaths;
                double kd;
                int kills2 = Stats.getKills(target.getUniqueId());
                
                int tode = Stats.getDeaths(target.getUniqueId());
                
                kills = (double) kills2;
                deaths = (double) tode;
                if(deaths == 0.0D) {
                	p.sendMessage("§8• §aKD§8: §c " + kills);
                } else {
                	kd = kills / deaths;
                	double gerundetekd = Double.valueOf((double) Math.round(kd * 100.0D) / 100.0D);
                	p.sendMessage("§8• §aKD§8: §c " + gerundetekd);
                }
        		
	        	p.sendMessage("§8• §aPlatz§8: §c" + Stats.getRank(target.getUniqueId().toString()));
	        	p.sendMessage("§8• §aKills§8: §c" + kills2);
	        	p.sendMessage("§8• §aTode§8: §c" + tode);
	        	p.sendMessage("");
    		} catch (SQLException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
        }else if(args.length != 1 && args.length != 2) {
        	p.sendMessage("§8/§astats §8[§2SPIELER§8]");
        }
        return false;
    }
}
