package de.sinqular.lobbysystem.manager;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class HoloLocationManager {

    public static boolean getLocation() {
        File file = new File("plugins/SwipeMC/Lobby/holograms.yml");
        if(file.exists()) {
            return true;
        }
        return false;
    }
    public static void setLocation(Player p, String location) {
        File file = new File("plugins/SwipeMC/Lobby/holograms.yml");
        org.bukkit.Location loc = p.getLocation();
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        cfg.set(location + ".world", loc.getWorld().getName());
        cfg.set(location + ".x", Double.valueOf(loc.getX()));
        cfg.set(location + ".y", Double.valueOf(loc.getY()));
        cfg.set(location + ".z", Double.valueOf(loc.getZ()));
        cfg.set(location + ".yaw", Float.valueOf(loc.getYaw()));
        cfg.set(location + ".pitch", Float.valueOf(loc.getPitch()));
        try {
            cfg.save(file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public static org.bukkit.Location getLocation(Player p, String location) {
        File file = new File("plugins/SwipeMC/Lobby/holograms.yml");
        org.bukkit.Location loc = p.getLocation();
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        loc.setX(cfg.getDouble(location + ".x"));
        loc.setY(cfg.getDouble(location + ".y"));
        loc.setZ(cfg.getDouble(location + ".z"));
        loc.setYaw((float)cfg.getDouble(location + ".yaw"));
        loc.setPitch((float)cfg.getDouble(location + ".pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString(location + ".world")));
        return loc;


    }

}
