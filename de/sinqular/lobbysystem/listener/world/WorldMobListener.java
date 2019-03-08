package de.sinqular.lobbysystem.listener.world;

import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class WorldMobListener implements Listener {

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent e) {
        if(e.getEntity() instanceof ArmorStand) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

}
