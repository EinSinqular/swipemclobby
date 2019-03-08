package de.sinqular.lobbysystem.listener.other;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.api.ItemAPI;
import de.sinqular.lobbysystem.api.SkullAPI;
import de.sinqular.lobbysystem.display.DisplayScoreBoard;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

public class DatenschutzListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
        if (e.getInventory().getName().equals("§eDatenschutzerklärung")) {
            e.setCancelled(true);
            if (!ItemAPI.onCheck(e)) {
                return;
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aAktzeptieren")) {
                p.sendMessage("§6Datenschutz §8» §7Du hast die §eRichtlinien Aktzeptiert!");
                p.sendMessage("§6Datenschutz §8» §aViel Spaß auf SwipeMC.net!");
                user.setTermsAccepted(true);
                p.closeInventory();
                p.getInventory().setItem(0, ItemAPI.createLore(Material.COMPASS, "" , "§7•§8● §7Teleportiere dich zu den §bSpielmodis§7.", 0,"§7• §8┃ §3Navigator §8┃ §7•"));
                p.getInventory().setItem(1, ItemAPI.createLore(Material.INK_SACK, "", "§7•§8● §7Du siehst §aalle Spieler§8.", 10, "§7• §6Spieler §8┃§7 (§aaktiviert§7)"));
                p.getInventory().setItem(4, ItemAPI.createLore(Material.GOLD_NUGGET, "", "§7•§8● §7Hier findest du die §eExtras§7.", 0, "§7• §8┃ §eExtras §8┃ §7•"));
                p.getInventory().setItem(7, ItemAPI.createItem(Material.CLAY_BALL, 0, "§7• §8┃ §cKein Gadget ausgerüstet"));
                p.getInventory().setItem(8, SkullAPI.getFriendHead(p.getUniqueId().toString(), "§7• §8┃ §4Profil §8┃ §7•", "§7•§8● §7Hier findest du dein §dProfil§7."));

                DisplayScoreBoard.setScoreboard(p);


            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAblehnen")) {
                LobbySystem.getLobbySystem().removeMetadata(p, "userData");
                p.kickPlayer("§cDu musst die Datenschutzerklärung aktzeptieren!");

            }
        }
    }
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(e.getPlayer().getUniqueId().toString());
        if(user.isTermsAccepted()) {

        } else {
            try {
                Bukkit.getScheduler().runTaskLaterAsynchronously(LobbySystem.getLobbySystem(), new Runnable() {
                    @Override
                    public void run() {
                        Inventory inv = Bukkit.createInventory(null, 9*3, "§eDatenschutzerklärung");

                        //11 13 15

                        inv.setItem(11, ItemAPI.createItem(Material.WOOL, 5, "§aAktzeptieren"));
                        inv.setItem(13, ItemAPI.createSkull(e.getPlayer().getName(), "§cSwipeMC.net/datenschutz"));
                        inv.setItem(15, ItemAPI.createItem(Material.WOOL, 14, "§cAblehnen"));
                        e.getPlayer().openInventory(inv);
                        return;
                    }
                }, 3);
            } catch (Exception ex) {

            }
        }
    }

}
