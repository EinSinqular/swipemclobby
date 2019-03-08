package de.sinqular.lobbysystem.listener;

import com.connorlinfoot.titleapi.TitleAPI;
import de.dytanic.cloudnet.api.CloudAPI;
import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.api.CloudLobbyAPI;
import de.sinqular.lobbysystem.api.ItemAPI;
import de.sinqular.lobbysystem.api.LobbyAPI;
import de.sinqular.lobbysystem.api.SkullAPI;
import de.sinqular.lobbysystem.display.DisplayScoreBoard;
import de.sinqular.lobbysystem.manager.HoloLocationManager;
import de.sinqular.lobbysystem.manager.LocationManager;
import de.sinqular.lobbysystem.manager.SettingManager;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class PlayerConnectionListener implements Listener {

    private final LobbySystem lobbySystem;

    public String[] lotterietext = {"§8• §eLotterie §8•",
            "§7§lJACKPOT §8» §e§l1.000.000 Coins!"};


    public PlayerConnectionListener(LobbySystem lobbySystem) {
        this.lobbySystem = lobbySystem;

    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.getInventory().clear();
        p.setFoodLevel(20);
        p.setMaxHealth(6);
        p.setHealthScale(6);
        p.setGameMode(GameMode.ADVENTURE);
        e.setJoinMessage(null);
        p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 45, 0));
        User user = lobbySystem.getBackendManager().getUser(p.getUniqueId().toString());

        LobbyAPI.spawnHologram(p, HoloLocationManager.getLocation(p, "lotterieholo"), lotterietext);

        try {
            if(!user.getUserSettings().contains(SettingManager.teleportToSpawnAtJoin)) {
                p.teleport(LocationManager.getLocation(p, "spawn"));
            } else {
                //
            }
        } catch (Exception ex) {
            p.sendMessage(LobbySystem.PREFIX + "§cDer Spawn wurde nicht gesetzt!");
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(lobbySystem, ()->{
            lobbySystem.removeMetadata(p, "userData");
            if(CloudAPI.getInstance().getServerId().equals("Lobby-1") || CloudAPI.getInstance().getServerId().equals("Lobby-2")) {
                TitleAPI.sendTitle(p, 20, 20, 20, "   §8» §bSwipeMC.net §8•", "§8• §a" + CloudAPI.getInstance().getServerId() + " §8•");
            } else if(CloudAPI.getInstance().getServerId().equals("SilentHub-1")) {
                TitleAPI.sendTitle(p, 20, 20, 20, "   §8» §bSwipeMC.net §8•", "§8• §4" + CloudAPI.getInstance().getServerId() + " §8•");
            } else if(CloudAPI.getInstance().getServerId().equals("PremiumLobby-1") || CloudAPI.getInstance().getServerId().equals("PremiumLobby-1")) {
                TitleAPI.sendTitle(p, 20, 20, 20, "   §8» §bSwipeMC.net §8•", "§8• §6" + CloudAPI.getInstance().getServerId() + " §8•");
            } else {
                TitleAPI.sendTitle(p, 20, 20, 20, "   §8» §bSwipeMC.net §8•", "§8• §cnull §8•");
            }

            Bukkit.getScheduler().scheduleSyncDelayedTask(lobbySystem, ()->{
               if(user.isTermsAccepted()) {
                   user.setLastIP(p.getAddress().toString().split(":")[0]);
                   user.setName(p.getName());
                    if(!user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                        DisplayScoreBoard.setScoreboard(p);
                    }

                   LobbyAPI.insertRank(p);
                   if(p.hasPermission("lobby.vip")) {
                       p.getInventory().setItem(0, ItemAPI.createLore(Material.COMPASS, "" , "§7•§8● §7Teleportiere dich zu den §bSpielmodis§7.", 0,"§7• §8┃ §3Navigator §8┃ §7•"));
                       p.getInventory().setItem(1, ItemAPI.createLore(Material.INK_SACK, "", "§7•§8● §7Du siehst §aalle Spieler§8.", 10, "§7• §6Spieler §8┃§7 (§aaktiviert§7)"));
                       p.getInventory().setItem(2, ItemAPI.createLore(Material.NAME_TAG, "", "§7•§8● §7Du bist §cNicht genickt§8.", 0, "§7• §5Nick §8┃§7 (§cdeaktiviert§7)"));
                       p.getInventory().setItem(4, ItemAPI.createLore(Material.GOLD_NUGGET, "", "§7•§8● §7Hier findest du die §eExtras§7.", 0, "§7• §8┃ §eExtras §8┃ §7•"));
                       p.getInventory().setItem(6, ItemAPI.createLore(Material.TNT, "", "§7•§8● §7Du bist auf einer §aNormalen Lobby§7.", 0, "§7• §5SilentHub §8┃§7 (§cdeaktiviert§7)"));
                       p.getInventory().setItem(7, ItemAPI.createItem(Material.CLAY_BALL, 0, "§7• §8┃ §cKein Gadget ausgerüstet"));
                       p.getInventory().setItem(8, SkullAPI.getFriendHead(p.getUniqueId().toString(), "§7• §8┃ §4Profil §8┃ §7•", "§7•§8● §7Hier findest du dein §dProfil§7."));
                   } else {
                       p.getInventory().setItem(0, ItemAPI.createLore(Material.COMPASS, "" , "§7•§8● §7Teleportiere dich zu den §bSpielmodis§7.", 0,"§7• §8┃ §3Navigator §8┃ §7•"));
                       p.getInventory().setItem(1, ItemAPI.createLore(Material.INK_SACK, "", "§7•§8● §7Du siehst §aalle Spieler§8.", 10, "§7• §6Spieler §8┃§7 (§aaktiviert§7)"));
                       p.getInventory().setItem(4, ItemAPI.createLore(Material.GOLD_NUGGET, "", "§7•§8● §7Hier findest du die §eExtras§7.", 0, "§7• §8┃ §eExtras §8┃ §7•"));
                       p.getInventory().setItem(7, ItemAPI.createItem(Material.CLAY_BALL, 0, "§7• §8┃ §cKein Gadget ausgerüstet"));
                       p.getInventory().setItem(8, SkullAPI.getFriendHead(p.getUniqueId().toString(), "§7• §8┃ §4Profil §8┃ §7•", "§7•§8● §7Hier findest du dein §dProfil§7."));
                   }
                   if(user.isPlayerHiding()) {
                       lobbySystem.getPlayerHider().add(p.getUniqueId().toString());
                       p.getInventory().setItem(1, ItemAPI.createLore(Material.INK_SACK, "", "§7•§8● §7Du siehst §cKeine Spieler§8.", 8, "§7• §6Spieler §8┃§7 (§cdeaktiviert§7)"));
                   }
                   lobbySystem.getPlayerHider().forEach(hiders ->{
                       Bukkit.getPlayer(UUID.fromString(hiders)).hidePlayer(p);
                   });
                   for(String gadget : user.getGetActiveGadGet()) {
                       //GadGetManager
                   }
                   if(p.hasPermission("lobby.vip")) {
                       if(user.isNicked()) {
                           p.getInventory().setItem(2, ItemAPI.createLore(Material.NAME_TAG, "", "§7•§8● §7Du bist §agenickt§8.", 0, "§7• §5Nick §8┃§7 (§aaktiviert§7)"));
                       }
                   }
                   if(p.hasPermission("lobby.vip")) {
                       if(user.isInSilentHub()) {
                           if(!CloudAPI.getInstance().getServerId().equals("SilentHub-1")) {
                               CloudLobbyAPI.sendPlayerToServer(p, "SilentHub-1");
                           }
                           p.getInventory().setItem(6, ItemAPI.createLore(Material.TNT, "", "§7•§8● §7Du bist auf der §4SilentHub§8.", 0, "§7• §5SilentHub §8┃§7 (§aaktiviert§7)"));
                       }
                   }
               } else {
                   Inventory inv = Bukkit.createInventory(null, 9*3, "§eDatenschutzerklärung");
                   inv.setItem(11, ItemAPI.createItem(Material.WOOL, 5, "§aAktzeptieren"));
                   inv.setItem(13, ItemAPI.createSkull(p.getName(), "§cSwipeMC.net/datenschutz"));
                   inv.setItem(15, ItemAPI.createItem(Material.WOOL, 14, "§cAblehnen"));

                   p.openInventory(inv);
                   return;

               }
            }, 10);
        }, 25);

    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        Player p = e.getPlayer();
        User user = lobbySystem.getBackendManager().getUser(p.getUniqueId().toString());
        user.setLastSeen(System.currentTimeMillis());
        lobbySystem.getBackendManager().updateUser(p.getUniqueId().toString());
        lobbySystem.removeMetadata(p, "userData");
        if(lobbySystem.getPlayerHider().contains(p.getUniqueId().toString())) {
            lobbySystem.getPlayerHider().remove(p.getUniqueId().toString());
        }
        if(lobbySystem.getEditPlayer().contains(p.getUniqueId().toString())) {
            lobbySystem.getEditPlayer().remove(p.getUniqueId().toString());
        }
    }
    @EventHandler
    public void onKick(PlayerKickEvent e) {
        e.setLeaveMessage(null);
        Player p = e.getPlayer();
        User user = lobbySystem.getBackendManager().getUser(p.getUniqueId().toString());
        user.setLastSeen(System.currentTimeMillis());
        lobbySystem.getBackendManager().updateUser(p.getUniqueId().toString());
        lobbySystem.removeMetadata(p, "userData");
        if(lobbySystem.getPlayerHider().contains(p.getUniqueId().toString())) {
            lobbySystem.getPlayerHider().remove(p.getUniqueId().toString());
        }
        if(lobbySystem.getEditPlayer().contains(p.getUniqueId().toString())) {
            lobbySystem.getEditPlayer().remove(p.getUniqueId().toString());
        }
    }
}
