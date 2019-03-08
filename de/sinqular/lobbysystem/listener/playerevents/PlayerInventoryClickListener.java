package de.sinqular.lobbysystem.listener.playerevents;

import de.sinqular.lobbysystem.LobbySystem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PlayerInventoryClickListener implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(!LobbySystem.getLobbySystem().getEditPlayer().contains(p)) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }
}
