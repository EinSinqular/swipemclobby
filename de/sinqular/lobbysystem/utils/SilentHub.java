package de.sinqular.lobbysystem.utils;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.api.CloudLobbyAPI;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SilentHub implements Listener {

    @EventHandler
    public void onItemInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack clicked = p.getItemInHand();
        if((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if(clicked == null || !clicked.hasItemMeta()) {
                if(LobbySystem.getLobbySystem().getEditPlayer().contains(p)) {
                } else {
                    e.setCancelled(true);
                    return;
                }
            } else if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§7• §5SilentHub §8┃§7 (§cdeaktiviert§7)")) {
                e.setCancelled(true);
                User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                user.setInSilentHub(true);
                CloudLobbyAPI.sendPlayerToServer(p, "SilentHub-1");

            } else if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§7• §5SilentHub §8┃§7 (§aaktiviert§7)")) {
                e.setCancelled(true);
                User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                user.setInSilentHub(false);
                CloudLobbyAPI.sendPlayerToServer(p, "Lobby-1");
            }
        }
    }
}


