package de.sinqular.lobbysystem.utils;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.api.ItemAPI;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerHider implements Listener {

    @EventHandler
    public void onItemInteract(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        ItemStack clicked = p.getItemInHand();
        if((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if(clicked == null || !clicked.hasItemMeta()) {
                if(LobbySystem.getLobbySystem().getEditPlayer().contains(p)) {

                } else {
                    e.setCancelled(true);
                    return;
                }
            } else if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§7• §6Spieler §8┃§7 (§aaktiviert§7)")) {
                User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                e.setCancelled(true);
                for(Player all : Bukkit.getOnlinePlayers()) {
                    p.hidePlayer(all);
                    p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE2, 0.5F, 8.6F);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
                }
                p.sendMessage(LobbySystem.PREFIX + "§cDu siehst nun keine Spieler mehr§7.");
                p.getInventory().setItem(1, ItemAPI.createLore(Material.INK_SACK, "", "§7•§8● §7Du siehst §cKeine Spieler§8.", 8, "§7• §6Spieler §8┃§7 (§cdeaktiviert§7)"));
                user.setPlayerHiding(true);
                LobbySystem.getLobbySystem().getPlayerHider().add(p.getUniqueId().toString());
            } else if(clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§7• §6Spieler §8┃§7 (§cdeaktiviert§7)")) {
                e.setCancelled(true);
                User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                for(Player all : Bukkit.getOnlinePlayers()) {
                    p.showPlayer(all);

                }
                p.sendMessage(LobbySystem.PREFIX + "§aDu siehst nun alle Spieler§7.");

                p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE2, 0.5F, 8.6F);
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));

                p.getInventory().setItem(1, ItemAPI.createLore(Material.INK_SACK, "", "§7•§8● §7Du siehst §aalle Spieler§8.", 10, "§7• §6Spieler §8┃§7 (§aaktiviert§7)"));
                user.setPlayerHiding(false);
                LobbySystem.getLobbySystem().getPlayerHider().remove(p.getUniqueId().toString());
            }
        }
    }
}


