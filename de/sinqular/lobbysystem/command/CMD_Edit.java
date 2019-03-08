package de.sinqular.lobbysystem.command;

import de.dytanic.cloudnet.api.CloudAPI;
import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.api.CloudLobbyAPI;
import de.sinqular.lobbysystem.api.ItemAPI;
import de.sinqular.lobbysystem.api.SkullAPI;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CMD_Edit implements CommandExecutor {


    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("edit")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if(p.hasPermission("lobbysystem.edit")) {
                    if(strings.length == 0) {
                        if(!LobbySystem.getLobbySystem().getEditPlayer().contains(p)) {
                            LobbySystem.getLobbySystem().getEditPlayer().add(p);
                            p.sendMessage(LobbySystem.PREFIX + "§7Du kannst die Map nun bearbeiten");
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1 );
                            p.setGameMode(GameMode.CREATIVE);
                            p.getInventory().clear();
                        } else {
                            LobbySystem.getLobbySystem().getEditPlayer().remove(p);
                            p.sendMessage(LobbySystem.PREFIX + "§7Du kannst die Map nun §cnicht §7mehr beabrieten");
                            p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1 );
                            p.setGameMode(GameMode.ADVENTURE);

                            User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());

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
                                LobbySystem.getLobbySystem().getPlayerHider().add(p.getUniqueId().toString());
                                p.getInventory().setItem(1, ItemAPI.createLore(Material.INK_SACK, "", "§7•§8● §7Du siehst §cKeine Spieler§8.", 8, "§7• §6Spieler §8┃§7 (§cdeaktiviert§7)"));
                            }
                            LobbySystem.getLobbySystem().getPlayerHider().forEach(hiders ->{
                                Bukkit.getPlayer(UUID.fromString(hiders)).hidePlayer(p);
                            });
                            for(String gadget : user.getGetActiveGadGet()) {
                                //GadGetManager
                            }
                            if(p.hasPermission("lobby.vip")) {
                                if(user.isNicked()) {
                                    p.getInventory().setItem(2, ItemAPI.createLore(Material.NAME_TAG, "", "§7•§8● §7Du bist §agenickt§8.", 0, "§7• §5Nick §8┃§7 (§caktiviert§7)"));
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




                        }
                    } else {
                        Player target = Bukkit.getPlayer(strings[0]);
                        if(target != null) {
                            if(!LobbySystem.getLobbySystem().getEditPlayer().contains(target)) {
                                LobbySystem.getLobbySystem().getEditPlayer().add(target);
                                p.sendMessage(LobbySystem.PREFIX + "§e" + target.getDisplayName() + " §chat nun Edit Rechte!");
                                target.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cDu kannst nun die Map bearbeiten!");
                            } else {
                                LobbySystem.getLobbySystem().getEditPlayer().remove(p);
                            }
                        } else {
                            p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cSpieler ist offline");
                        }
                    }
                } else {
                    p.sendMessage(LobbySystem.NO_PERMISSION);
                }
            }
        }
        return false;
    }
}
