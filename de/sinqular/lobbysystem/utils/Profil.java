package de.sinqular.lobbysystem.utils;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.api.player.PlayerExecutorBridge;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.api.CloudLobbyAPI;
import de.sinqular.lobbysystem.api.ItemAPI;
import de.sinqular.lobbysystem.api.SkullAPI;
import de.sinqular.lobbysystem.display.DisplayScoreBoard;
import de.sinqular.lobbysystem.manager.SettingManager;
import de.sinqular.lobbysystem.mysql.entity.User;
import de.sinqular.lobbysystem.secrets.SecretManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class Profil implements Listener {



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
            } else if (clicked.getItemMeta().getDisplayName().equalsIgnoreCase("§7• §8┃ §4Profil §8┃ §7•")) {
                Inventory inv = Bukkit.createInventory(null, 9 * 3, "§8» §cProfil");
                User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                int size = user.getUserSecrets().size() - 1;

                e.setCancelled(true);
                inv.setItem(0, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(1, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(7, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(8, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(9, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(17, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(18, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(19, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(25, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(26, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));




                inv.setItem(4, SkullAPI.getFriendHead(p.getUniqueId().toString(), "§cDein Profil","§8» §7Profil von§8: §e" + p.getDisplayName() + "" ));
                inv.setItem(10, ItemAPI.createLore(Material.COMMAND, "", "§8» §7Deine §cEinstellungen", 0, "§cEinstellungen"));
                inv.setItem(12, ItemAPI.createLore(Material.SUGAR, "", "§8» §7" + CloudAPI.getInstance().getServerId(), 0, "§eLobbys"));
                inv.setItem(14, ItemAPI.createLore(Material.INK_SACK, "§8» §7Du hast §a"  + size + " Secrets§7.", "§8» §7Hier siehst du deine §eSecrets", 10, "§aSecrets"));
                inv.setItem(16, SkullAPI.getFriendHead(p.getUniqueId().toString(), "§dFreunde", "§8» §7Es sind derzeit §e0 Freunde §aonline§7."));

                p.openInventory(inv);

            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
        if (e.getInventory().getName().equals("§8» §cProfil")) {
            e.setCancelled(true);
            if (!ItemAPI.onCheck(e)) {
                return;
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cEinstellungen")) {
                Inventory inv = Bukkit.createInventory(null, 9 * 5, "§cEinstellungen");

                inv.setItem(0, ItemAPI.createItem(Material.SIGN, 0, "§8» §eScoreboard anzeigen"));
                inv.setItem(1, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, ""));
                inv.setItem(2, ItemAPI.createItem(Material.INK_SACK, 10, "§aScoreboard anzeigen"));
                inv.setItem(3, ItemAPI.createItem(Material.INK_SACK, 1, "§cScoreboard verstecken"));
                inv.setItem(7, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));

                if (!user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                    inv.setItem(8, ItemAPI.createItem(Material.INK_SACK, 10, "§aAktiviert"));
                } else if (user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                    inv.setItem(8, ItemAPI.createItem(Material.INK_SACK, 1, "§cDeaktiviert"));
                }

                inv.setItem(9, ItemAPI.createItem(Material.COMPASS, 0, "§8» §bAnimationen anzeigen"));
                inv.setItem(10, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, ""));
                inv.setItem(11, ItemAPI.createItem(Material.INK_SACK, 10, "§aAnimation aktivieren"));
                inv.setItem(12, ItemAPI.createItem(Material.INK_SACK, 1, "§cAnimation deaktivieren"));
                inv.setItem(16, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));

                if (!user.getUserSettings().contains(SettingManager.showAnimation)) {
                    inv.setItem(17, ItemAPI.createItem(Material.INK_SACK, 10, "§aAktiviert"));
                } else if (user.getUserSettings().contains(SettingManager.showAnimation)) {
                    inv.setItem(17, ItemAPI.createItem(Material.INK_SACK, 1, "§cDeaktiviert"));
                }
                inv.setItem(18, ItemAPI.createItem(Material.INK_SACK, 5, "§8» §5Party Anfragen erlauben"));
                inv.setItem(19, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, ""));
                inv.setItem(20, ItemAPI.createItem(Material.INK_SACK, 10, "§aAnfragen erlauben"));
                inv.setItem(21, ItemAPI.createItem(Material.INK_SACK, 1, "§cAnfragen blocken"));
                inv.setItem(25, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));

                if (!user.getUserSettings().contains(SettingManager.allowPartyInvites)) {
                    inv.setItem(26, ItemAPI.createItem(Material.INK_SACK, 10, "§aAktiviert"));
                } else if (user.getUserSettings().contains(SettingManager.allowPartyInvites)) {
                    inv.setItem(26, ItemAPI.createItem(Material.INK_SACK, 1, "§cDeaktiviert"));
                }
                inv.setItem(27, ItemAPI.createItem(Material.EYE_OF_ENDER, 0, "§8» §aJoin Spawn Teleportierung"));
                inv.setItem(28, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, ""));
                inv.setItem(29, ItemAPI.createItem(Material.INK_SACK, 10, "§aTeleportierung zulassen"));
                inv.setItem(30, ItemAPI.createItem(Material.INK_SACK, 1, "§cTeleportierung blocken"));
                inv.setItem(34, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));

                if (!user.getUserSettings().contains(SettingManager.teleportToSpawnAtJoin)) {
                    inv.setItem(35, ItemAPI.createItem(Material.INK_SACK, 10, "§aAktiviert"));
                } else if (user.getUserSettings().contains(SettingManager.teleportToSpawnAtJoin)) {
                    inv.setItem(35, ItemAPI.createItem(Material.INK_SACK, 1, "§cDeaktiviert"));
                }
                inv.setItem(36, ItemAPI.createItem(Material.NOTE_BLOCK, 0, "§8» §dSounds"));
                inv.setItem(37, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, ""));
                inv.setItem(38, ItemAPI.createItem(Material.INK_SACK, 10, "§aSounds aktivieren"));
                inv.setItem(39, ItemAPI.createItem(Material.INK_SACK, 1, "§cSounds deaktivieren"));
                inv.setItem(43, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));

                if (!user.getUserSettings().contains(SettingManager.chatSounds)) {
                    inv.setItem(44, ItemAPI.createItem(Material.INK_SACK, 10, "§aAktiviert"));
                } else if (user.getUserSettings().contains(SettingManager.chatSounds)) {
                    inv.setItem(44, ItemAPI.createItem(Material.INK_SACK, 1, "§cDeaktiviert"));
                }
                p.openInventory(inv);
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§eLobbys")) {
                int LOBBY_1 = CloudAPI.getInstance().getServerInfo("Lobby-1").getOnlineCount();
                int LOBBY_2 = CloudAPI.getInstance().getServerInfo("Lobby-2").getOnlineCount();
                int PREMIUM_LOBBY_1 = CloudAPI.getInstance().getServerInfo("PremiumLobby-1").getOnlineCount();

                Inventory inv = Bukkit.createInventory(null, 9*3, "§eLobby auswählen");

                //4 10 16
                CloudPlayer player = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
                if(player.getServer().equals("PremiumLobby-1")) {
                    inv.setItem(4, ItemAPI.createLore(Material.GLOWSTONE_DUST,  "§a" + Integer.valueOf(PREMIUM_LOBBY_1) + " §7Spieler auf §ePremiumLobby-1", "§aDu bist hier", 0, "§6PremiumLobby-1"));

                    inv.setItem(10, ItemAPI.createLore(Material.SUGAR,  "§a" + Integer.valueOf(LOBBY_1) + " §7Spieler auf §eLobby-1", "§aOnline", 0, "§cLobby-1"));
                    inv.setItem(16, ItemAPI.createLore(Material.SUGAR,  "§a" + Integer.valueOf(LOBBY_2) + " §7Spieler auf §eLobby-2", "§aOnline", 0, "§cLobby-2"));

                } else  if(player.getServer().equals("Lobby-1")) {
                    inv.setItem(4, ItemAPI.createLore(Material.GLOWSTONE_DUST,  "§a" + Integer.valueOf(PREMIUM_LOBBY_1) + " §7Spieler auf §ePremiumLobby-1", "§aOnline", 0, "§6PremiumLobby-1"));

                    inv.setItem(10, ItemAPI.createLore(Material.SUGAR,  "§a" + Integer.valueOf(LOBBY_1) + " §7Spieler auf §eLobby-1", "§aDu bist hier", 0, "§cLobby-1"));
                    inv.setItem(16, ItemAPI.createLore(Material.SUGAR,  "§a" + Integer.valueOf(LOBBY_2) + " §7Spieler auf §eLobby-2", "§aOnline", 0, "§cLobby-2"));

                } else if(player.getServer().equals("Lobby-2")) {
                    inv.setItem(4, ItemAPI.createLore(Material.GLOWSTONE_DUST,  "§a" + Integer.valueOf(PREMIUM_LOBBY_1) + " §7Spieler auf §ePremiumLobby-1", "§aOnline", 0, "§6PremiumLobby-1"));

                    inv.setItem(10, ItemAPI.createLore(Material.SUGAR,  "§a" + Integer.valueOf(LOBBY_1) + " §7Spieler auf §eLobby-1", "§aOnline", 0, "§cLobby-1"));
                    inv.setItem(16, ItemAPI.createLore(Material.SUGAR,  "§a" + Integer.valueOf(LOBBY_2) + " §7Spieler auf §eLobby-2", "§aDu bist hier", 0, "§cLobby-2"));

                }
                p.openInventory(inv);

            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aSecrets")) {
                Inventory inv = Bukkit.createInventory(null, 9*2, "§aSecrets");
                if(!user.getUserSecrets().contains(SecretManager.testSecret)) {
                    inv.setItem(0, ItemAPI.createLore(Material.INK_SACK, "", "§8» §cDu hast diese Secret noch nicht", 8, "§cTest Secret"));
                } else if(user.getUserSecrets().contains(SecretManager.testSecret)) {
                    inv.setItem(0, ItemAPI.createLore(Material.INK_SACK, "", "§8» §aDu hast diese Secret gefunden", 10, "§aTest Secret"));
                }
                p.openInventory(inv);
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§dFreunde")) {
                p.chat("/fopeninv");
            }
        } else if (e.getInventory().getName().equals("§cEinstellungen")) {
            e.setCancelled(true);
            if (!ItemAPI.onCheck(e)) {
                return;
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aScoreboard anzeigen")) {
                if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                    user.getUserSettings().remove(SettingManager.displayScoreboard);
                    p.sendMessage(LobbySystem.PREFIX + "§7Du hast das §eScoreboard §aaktiviert§7.");
                    DisplayScoreBoard.setScoreboard(p);
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                    p.closeInventory();
                } else if(!user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                    p.sendMessage(LobbySystem.PREFIX + "§7Das §eScoreboard §7ist bereits §aaktiviert§7.");
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
                    p.closeInventory();
                }

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cScoreboard verstecken")) {
                if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                    p.sendMessage(LobbySystem.PREFIX + "§7Das §eScoreboard §7ist bereits §cdeaktiviert§7.");
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
                    p.closeInventory();
                } else if(!user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                    user.getUserSettings().add(SettingManager.displayScoreboard);
                    p.sendMessage(LobbySystem.PREFIX + "§7Du hast das §eScoreboard §cdeaktiviert§7.");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                    p.closeInventory();
                }


            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aAnimation aktivieren")) {
                if(user.getUserSettings().contains(SettingManager.showAnimation)) {
                    user.getUserSettings().remove(SettingManager.showAnimation);
                    p.sendMessage(LobbySystem.PREFIX + "§7Du hast die §bAnimationen §aaktiviert§7.");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                    p.closeInventory();
                } else if(!user.getUserSettings().contains(SettingManager.showAnimation)) {
                    p.sendMessage(LobbySystem.PREFIX + "§7Die §bAnimationen §7sind bereits §aaktiviert§7.");
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
                    p.closeInventory();
                }

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAnimation deaktivieren")) {
                if(user.getUserSettings().contains(SettingManager.showAnimation)) {
                    p.sendMessage(LobbySystem.PREFIX + "§7Die §bAnimationen §7sind bereits §cdeaktiviert§7.");
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
                    p.closeInventory();
                } else if(!user.getUserSettings().contains(SettingManager.showAnimation)) {
                    user.getUserSettings().add(SettingManager.showAnimation);
                    p.sendMessage(LobbySystem.PREFIX + "§7Du hast die §bAnimation §cdeaktiviert§7.");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                    p.closeInventory();
                }



            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aAnfragen erlauben")) {
                if(user.getUserSettings().contains(SettingManager.allowPartyInvites)) {
                    user.getUserSettings().remove(SettingManager.allowPartyInvites);
                    p.sendMessage(LobbySystem.PREFIX + "§7Du erlaubst nun §5Party Anfragen§7.");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                    p.closeInventory();
                } else if(!user.getUserSettings().contains(SettingManager.allowPartyInvites)) {
                    p.sendMessage(LobbySystem.PREFIX + "§7Du erlaubst bereits §5Party Anfragen§7.");
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
                    p.closeInventory();
                }

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAnfragen blocken")) {
                if(user.getUserSettings().contains(SettingManager.allowPartyInvites)) {
                    p.sendMessage(LobbySystem.PREFIX + "§7Die §5Party Anfragen §7sind bereits §cblockiert§7.");
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
                    p.closeInventory();
                } else if(!user.getUserSettings().contains(SettingManager.allowPartyInvites)) {
                    user.getUserSettings().add(SettingManager.allowPartyInvites);
                    p.sendMessage(LobbySystem.PREFIX + "§7Du erlaubst nun keine §5Party Anfragen§7 mehr.");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                    p.closeInventory();
                }


            }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aTeleportierung zulassen")) {
                if(user.getUserSettings().contains(SettingManager.teleportToSpawnAtJoin)) {
                    user.getUserSettings().remove(SettingManager.teleportToSpawnAtJoin);
                    p.sendMessage(LobbySystem.PREFIX + "§7Du wirst von nun an beim Joinen zum §eSpawn Teleportiert§7.");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                    p.closeInventory();
                } else if(!user.getUserSettings().contains(SettingManager.teleportToSpawnAtJoin)) {
                    p.sendMessage(LobbySystem.PREFIX + "§7Du wirst bereits Teleportiert.");
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
                    p.closeInventory();
                }

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cTeleportierung blocken")) {
                if (user.getUserSettings().contains(SettingManager.teleportToSpawnAtJoin)) {
                    p.sendMessage(LobbySystem.PREFIX + "§7Du wirst nicht Teleportiert.");
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
                    p.closeInventory();
                } else if (!user.getUserSettings().contains(SettingManager.teleportToSpawnAtJoin)) {
                    user.getUserSettings().add(SettingManager.teleportToSpawnAtJoin);
                    p.sendMessage(LobbySystem.PREFIX + "§7Du wirst nun §cnicht mehr §7zum §eSpawn Teleportiert§7.");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                    p.closeInventory();
                }
            }else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§aSounds aktivieren")) {
                if(user.getUserSettings().contains(SettingManager.chatSounds)) {
                    user.getUserSettings().remove(SettingManager.chatSounds);
                    p.sendMessage(LobbySystem.PREFIX + "§7Du hast die §dSounds §aaktiviert§7.");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                    p.closeInventory();
                } else if(!user.getUserSettings().contains(SettingManager.chatSounds)) {
                    p.sendMessage(LobbySystem.PREFIX + "§7Die §dSounds §7sind bereits §aaktiviert§7.");
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
                    p.closeInventory();
                }

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cSounds deaktivieren")) {
                if (user.getUserSettings().contains(SettingManager.chatSounds)) {
                    p.sendMessage(LobbySystem.PREFIX + "§7Die §dSounds §7sind bereits §cdeaktiviert§7.");
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
                    p.closeInventory();
                } else if (!user.getUserSettings().contains(SettingManager.chatSounds)) {
                    user.getUserSettings().add(SettingManager.chatSounds);
                    p.sendMessage(LobbySystem.PREFIX + "§7Du hast die §dSounds §cdeaktiviert§7.");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                    p.closeInventory();
                }
            }
        } else if (e.getInventory().getName().equals("§eLobby auswählen")) {
            e.setCancelled(true);
            if (!ItemAPI.onCheck(e)) {
                return;
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6PremiumLobby-1")) {
                CloudPlayer player = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
                PlayerExecutorBridge playerExecutorBridge = new PlayerExecutorBridge();
                if (player.getServer().equals("PremiumLobby-1")) {
                    p.sendMessage(LobbySystem.PREFIX + "§cDu bist bereits auf der PremiumLobby!");
                } else {
                    p.sendMessage(LobbySystem.PREFIX + "§7Du bist nun auf §6PremiumLobby-1");
                    CloudLobbyAPI.sendPlayerToServer(p, "PremiumLobby-1");
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLobby-1")) {
                CloudPlayer player = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
                PlayerExecutorBridge playerExecutorBridge = new PlayerExecutorBridge();
                if (player.getServer().equals("Lobby-1")) {
                    p.sendMessage(LobbySystem.PREFIX + "§cDu bist bereits auf Lobby-1!");
                } else {
                    p.sendMessage(LobbySystem.PREFIX + "§7Du bist nun auf §bLobby-1");
                    CloudLobbyAPI.sendPlayerToServer(p, "Lobby-1");
                }
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cLobby-2")) {
                CloudPlayer player = CloudAPI.getInstance().getOnlinePlayer(p.getUniqueId());
                PlayerExecutorBridge playerExecutorBridge = new PlayerExecutorBridge();
                if (player.getServer().equals("Lobby-2")) {
                    p.sendMessage(LobbySystem.PREFIX + "§cDu bist bereits auf Lobby-2!");
                } else {
                    p.sendMessage(LobbySystem.PREFIX + "§7Du bist nun auf §bLobby-2");
                    CloudLobbyAPI.sendPlayerToServer(p, "Lobby-2");
                }
            }
        }
    }
}