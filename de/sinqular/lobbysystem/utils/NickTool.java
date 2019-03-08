package de.sinqular.lobbysystem.utils;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.api.ItemAPI;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class NickTool implements Listener {

    @EventHandler
    public void onItemInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack clicked = p.getItemInHand();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if (clicked == null || !clicked.hasItemMeta()) {
                if (LobbySystem.getLobbySystem().getEditPlayer().contains(p)) {

                } else {
                    e.setCancelled(true);
                    return;
                }
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§7• §5Nick §8┃§7 (§cdeaktiviert§7)")) {
                e.setCancelled(true);
                User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                p.getInventory().setItem(2, ItemAPI.createLore(Material.NAME_TAG, "", "§7•§8● §7Du bist §agenickt§8.", 0, "§7• §5Nick §8┃§7 (§aaktiviert§7)"));
                user.setNicked(true);
                p.sendMessage("§8» §5NickSystem §8• §7Du hast den automatischen Nicknamen §aaktiviert§7.");
            } else if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§7• §5Nick §8┃§7 (§aaktiviert§7)")) {
                e.setCancelled(true);
                User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                p.getInventory().setItem(2, ItemAPI.createLore(Material.NAME_TAG, "", "§7•§8● §7Du bist §cNicht genickt§8.", 0, "§7• §5Nick §8┃§7 (§cdeaktiviert§7)"));
                user.setNicked(false);
                p.sendMessage("§8» §5NickSystem §8• §7Du hast den automatischen Nicknamen §cdeaktiviert§7.");
            }
        }
    }

}
