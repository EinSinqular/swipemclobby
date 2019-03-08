package de.sinqular.lobbysystem.runnables;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.manager.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;

public class ParticlesRunnable {

    public static void startSpawnScheduler() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbySystem.getLobbySystem(), ()->{
            for(Player all : Bukkit.getOnlinePlayers()) {
                all.getWorld().playEffect(LocationManager.getLocation(all, "spawn"), Effect.FLYING_GLYPH, 1);
                all.getWorld().playEffect(LocationManager.getLocation(all, "spawn"), Effect.FLYING_GLYPH, 1);
                all.getWorld().playEffect(LocationManager.getLocation(all, "spawn"), Effect.FLYING_GLYPH, 1);
                all.getWorld().playEffect(LocationManager.getLocation(all, "spawn"), Effect.FLYING_GLYPH, 1);
                all.getWorld().playEffect(LocationManager.getLocation(all, "spawn"), Effect.FLYING_GLYPH, 1);
                all.getWorld().playEffect(LocationManager.getLocation(all, "spawn"), Effect.FIREWORKS_SPARK, 1);
            }
        }, 5, 5);
    }
}
