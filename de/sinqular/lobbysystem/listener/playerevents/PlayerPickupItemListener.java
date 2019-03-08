package de.sinqular.lobbysystem.listener.playerevents;

import de.sinqular.lobbysystem.LobbySystem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItemListener implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        if(!LobbySystem.getLobbySystem().getEditPlayer().contains(e.getPlayer())) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

}
