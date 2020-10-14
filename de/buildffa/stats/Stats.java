package de.buildffa.stats;

import org.bukkit.entity.*;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.*;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Stats
{

	   public static void setKills(UUID uuid, int kills) throws SQLException {
	        PreparedStatement ps;
	        if (existsPlayer(uuid)) {
	            ps = MySQL.conn.prepareStatement("UPDATE BuildFFA SET Kills='" + kills + "' WHERE UUID='" + uuid + "'");
	            ps.executeUpdate();
	            ps.close();
	        } else {
	            ps = MySQL.conn.prepareStatement("INSERT INTO BuildFFA(UUID,Kills,Deaths) VALUES ('" + uuid + "','" + kills + "','" + getDeaths(uuid) + "')");
	            ps.executeUpdate();
	            ps.close();
	        }

	    }
	    
	    public static void setPoints(UUID uuid, int points) throws SQLException {
	        PreparedStatement ps;
	        if (existsPlayer(uuid)) {
	            ps = MySQL.conn.prepareStatement("UPDATE BuildFFA SET POINTS='" + points + "' WHERE UUID='" + uuid + "'");
	            ps.executeUpdate();
	            ps.close();
	        } else {
	            ps = MySQL.conn.prepareStatement("INSERT INTO BuildFFA(UUID,POINTS,Deaths) VALUES ('" + uuid + "','" + points + "','" + getDeaths(uuid) + "')");
	            ps.executeUpdate();
	            ps.close();
	        }

	    }


	    public static void setDeaths(UUID uuid, int deaths) throws SQLException {
	        PreparedStatement ps;
	        if (existsPlayer(uuid)) {
	            ps = MySQL.conn.prepareStatement("UPDATE BuildFFA SET Deaths='" + deaths + "' WHERE UUID='" + uuid + "'");
	            ps.executeUpdate();
	            ps.close();
	        } else {
	            ps = MySQL.conn.prepareStatement("INSERT INTO BuildFFA(UUID,Kills,Deaths) VALUES ('" + uuid + "','" + getKills(uuid) + "','" + deaths + "')");
	            ps.executeUpdate();
	            ps.close();
	        }

	    }

	    public static int getKills(UUID uuid) throws SQLException {
	        int kills = 0;
	        PreparedStatement ps = MySQL.conn.prepareStatement("SELECT * FROM BuildFFA WHERE UUID='" + uuid + "'");

	        for (ResultSet result = ps.executeQuery(); result.next(); kills = result.getInt(2)) {
	        }

	        return kills;
	    }
	    
	    public static int getPoints(UUID uuid) throws SQLException {
	        int points = 0;
	        PreparedStatement ps = MySQL.conn.prepareStatement("SELECT * FROM BuildFFA WHERE UUID='" + uuid + "'");

	        for (ResultSet result = ps.executeQuery(); result.next(); points = result.getInt(4)) {
	        }

	        return points;
	    }


	    public static int getDeaths(UUID uuid) throws SQLException {
	        int deaths = 0;
	        PreparedStatement ps = MySQL.conn.prepareStatement("SELECT * FROM BuildFFA WHERE UUID='" + uuid + "'");

	        ResultSet result;
	        for (result = ps.executeQuery(); result.next(); deaths = result.getInt(3)) {
	        }

	        result.close();
	        ps.close();
	        return deaths;
	    }

	    public static boolean existsPlayer(UUID uuid) throws SQLException {
	        PreparedStatement ps = MySQL.conn.prepareStatement("SELECT * FROM BuildFFA WHERE UUID='" + uuid + "'");
	        ResultSet result = ps.executeQuery();

	        do {
	            if (!result.next()) {
	                result.close();
	                ps.close();
	                return false;
	            }
	        } while (result == null);

	        return true;
	    }

	    public static int getRank(String UUID) {
	        int rank = -1;

	        try {
	            PreparedStatement ps = MySQL.conn.prepareStatement("SELECT * FROM BuildFFA ORDER BY Points DESC");
	            ResultSet result = ps.executeQuery();

	            while (result.next()) {
	                String uuid2 = result.getString("UUID");
	                if (uuid2.equalsIgnoreCase(UUID)) {
	                    rank = result.getRow();
	                    break;
	                }
	            }

	            result.close();
	            ps.close();
	        } catch (Exception var5) {
	            var5.printStackTrace();
	        }

	        return rank;
	    }
	
}
