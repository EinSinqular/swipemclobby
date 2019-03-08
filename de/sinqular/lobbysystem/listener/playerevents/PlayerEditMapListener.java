package de.sinqular.lobbysystem.listener.playerevents;

import de.sinqular.lobbysystem.LobbySystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlayerEditMapListener implements Listener {

    @EventHandler
    public void onBuild(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if(LobbySystem.getLobbySystem().getEditPlayer().contains(p)) {
            e.setCancelled(false);
            return;
        } else {
            e.setCancelled(true);

        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if(!LobbySystem.getLobbySystem().getEditPlayer().contains(e.getPlayer())) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }

}
