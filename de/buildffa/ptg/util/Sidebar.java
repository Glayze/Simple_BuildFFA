package de.buildffa.ptg.util;

import org.bukkit.entity.*;

import de.buildffa.ptg.BuildFFA;
import de.buildffa.stats.*;
import org.bukkit.*;
import org.bukkit.scoreboard.*;

import java.sql.SQLException;
import java.util.*;

public class Sidebar
{
    public static void setScoreboard(final Player p) {
        final Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        final Objective o1 = board.registerNewObjective("board", "dummy");
        o1.setDisplayName("§aBuildFFA");
        o1.setDisplaySlot(DisplaySlot.SIDEBAR);
        final Score zwelf = o1.getScore("§8§m-----------");
        zwelf.setScore(12);
        final Score eleven = o1.getScore("§8» §7Kills§8:");
        eleven.setScore(11);
        Score ten = null;
		try {
			ten = o1.getScore("§8➟ §1§f" + (Stats.getKills(p.getUniqueId()) + BuildFFA.kills.get(p)));
		} catch (IllegalArgumentException | IllegalStateException | SQLException e) {
			ten = o1.getScore("§8» §1§cFehler");
			e.printStackTrace();
		}
        ten.setScore(10);
        final Score nine = o1.getScore("§8§f");
        nine.setScore(9);
        final Score lolrb = o1.getScore("§8» §7Tode§8:");
        lolrb.setScore(8);
        Score seven = null;
		try {
			seven = o1.getScore("§8➟ §f" + (Stats.getDeaths(p.getUniqueId()) + BuildFFA.tode.get(p)));
		} catch (IllegalArgumentException | IllegalStateException | SQLException e) {
			seven = o1.getScore("§8» §cFehler");
			e.printStackTrace();
		}
        seven.setScore(7);
        final Score six = o1.getScore("§b§a§f");
        six.setScore(6);
        final Score five = o1.getScore("§8» §7Map§8:");
        five.setScore(5);
        String world = p.getWorld().getName();
        final Score four = o1.getScore("§8➟ §f" + world);
        four.setScore(4);
        final Score three = o1.getScore("§7§8§a");
        three.setScore(3);
        final Score two = o1.getScore("§8» §7Modus");
        two.setScore(2);
        final Score one = o1.getScore("§8➟ §fBogen");
        one.setScore(1);
        final Score zero = o1.getScore("§8§1§1");
        zero.setScore(0);
        p.setScoreboard(board);
    }
}
