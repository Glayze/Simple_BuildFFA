package de.buildffa.stats;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;


public class MySQL {

	public static Connection conn;
	public static String host;
	public static String username;
	public static String passwort;
	public static String database;
	public static String port;
	
	public static void connect() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + username + "&password=" + passwort + "&autoReconnect=true");
			Bukkit.getConsoleSender().sendMessage("[MySQL] Die Verbindung HAT ERFOLG!");
		} catch(SQLException var) {
			var.printStackTrace();
			Bukkit.getConsoleSender().sendMessage("[MySQL] Die Verbindung IST FEHLGESCHLAGEN!");
		}
	}
	
	public static void createTable() {
		if(conn != null) {
			try {
				conn.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS BuildFFA(UUID varchar(100),Kills int(100), Deaths int (100), Points int (100))");
			} catch(SQLException var) {
				var.printStackTrace();
			}
		}
	}
	
    public static void close() {
        if (conn != null) {
            try {
                conn.close();
                Bukkit.getConsoleSender().sendMessage("§cDie Verbindung mit MySQL wurde geschlossen!");
            } catch (SQLException var1) {
               var1.printStackTrace();
            }
        }
    }
	
    public static File getMySQLFile() {
        return new File("plugins/BuildFFA", "MySQL.yml");
    }

    public static FileConfiguration getMySQLFileConfiguration() {
        return YamlConfiguration.loadConfiguration(getMySQLFile());
    }

    public static void setStandardMySQL() {
        FileConfiguration cfg = getMySQLFileConfiguration();
        cfg.options().copyDefaults(true);
        cfg.addDefault("username", "root");
        cfg.addDefault("password", "password");
        cfg.addDefault("database", "localhost");
        cfg.addDefault("host", "localhost");
        cfg.addDefault("port", "3306");
        
        try {
            cfg.save(getMySQLFile());
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public static void readMySQL() {
        FileConfiguration cfg = getMySQLFileConfiguration();
        username = cfg.getString("username");
        passwort = cfg.getString("password");
        database = cfg.getString("database");
        host = cfg.getString("host");
        port = cfg.getString("port");
    }
	
}
