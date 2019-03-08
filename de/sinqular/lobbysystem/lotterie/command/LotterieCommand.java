package de.sinqular.lobbysystem.lotterie.command;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LotterieCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("lotterie")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if(p.hasPermission("lobby.lotterie")) {
                    if(strings.length == 0) {
                        p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cNutzung: /lotterie ->");
                        p.sendMessage("§c/Lotterie <Info | add | remove> <Chest | PChest | Tickets > <Spieler> <Stückle>");
                    } else if(strings[0].equalsIgnoreCase("info")) {
                        Player target = Bukkit.getPlayer(strings[1]);
                        try {
                            if(target != null) {
                                User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                                p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§8» §e" + target.getDisplayName());
                                p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§6Premium Kisten §8» §e" + user.getPremiumChest());
                                p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Normale Kisten §8» §e" + user.getChests());
                                p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§eTickets §8» §e" + user.getTickets());
                                p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§eCoins §8» §e" + user.getCoins());
                            } else {
                                p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cDer Spieler ist offline");
                            }
                        } catch (Exception ex) {
                            p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cGebe einen Spieler an!");
                        }
                    } else if(strings[0].equalsIgnoreCase("add")) {
                        if(strings[1].equalsIgnoreCase("chest")) {
                            Player target = Bukkit.getPlayer(strings[2]);
                            try {
                                if(target != null) {

                                    int value = Integer.valueOf(strings[3]);
                                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast §e" + target.getDisplayName() + " §a" + strings[3] + " §7Kisten gegeben");
                                    target.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§e" + p.getDisplayName() + " §7hat dir §e" + strings[3] + " kisten gegeben§7.");
                                    User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                                    int amountchest = user.getChests() + Integer.parseInt(strings[3]);
                                    user.setChests(amountchest);
                                } else {
                                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cDer Spieler ist offline");
                                }
                            } catch (Exception ex) {
                                p.sendMessage("§cGebe einen Spieler an!");
                            }
                        } else if(strings[1].equalsIgnoreCase("pchest")) {
                            Player target = Bukkit.getPlayer(strings[2]);
                            try {
                                if(target != null) {
                                    int value = Integer.valueOf(strings[3]);
                                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast §e" + target.getDisplayName() + " §a" + strings[3] + " §6Premium Kisten gegeben");
                                    target.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§e" + p.getDisplayName() + " §7hat dir §e" + strings[3] + " Premium kisten gegeben§7.");
                                    User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                                    int amountchest = user.getPremiumChest() + Integer.parseInt(strings[3]);
                                    user.setPremiumChest(amountchest);
                                } else {
                                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cDer Spieler ist offline");
                                }
                            } catch (Exception ex) {
                                p.sendMessage("§cGebe einen Spieler an!");
                            }
                        } else if(strings[1].equalsIgnoreCase("tickets")) {
                            Player target = Bukkit.getPlayer(strings[2]);
                            try {
                                if(target != null) {
                                    int value = Integer.valueOf(strings[3]);
                                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast §e" + target.getDisplayName() + " §a" + strings[3] + " §eTickets gegeben");
                                    target.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§e" + p.getDisplayName() + " §7hat dir §e" + strings[3] + " Tickets  gegeben§7.");
                                    User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                                    int amountchest = user.getTickets() + Integer.parseInt(strings[3]);
                                    user.setTickets(amountchest);
                                } else {
                                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cDer Spieler ist offline");
                                }
                            } catch (Exception ex) {
                                p.sendMessage("§cGebe einen Spieler an!");
                            }
                        }
                    }    else if(strings[0].equalsIgnoreCase("remove")) {
                        if(strings[1].equalsIgnoreCase("chest")) {
                            Player target = Bukkit.getPlayer(strings[2]);
                            try {
                                if(target != null) {
                                    int value = Integer.valueOf(strings[3]);
                                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast §e" + target.getDisplayName() + " §a" + strings[3] + " §7Kisten entzogen");
                                    target.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§e" + p.getDisplayName() + " §7hat dir §e" + strings[3] + " kisten entzogen§7.");
                                    User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                                    int amountchest = user.getChests() - Integer.parseInt(strings[3]);
                                    user.setChests(amountchest);
                                } else {
                                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cDer Spieler ist offline");
                                }
                            } catch (Exception ex) {
                                p.sendMessage("§cGebe einen Spieler an!");
                            }
                        } else if(strings[1].equalsIgnoreCase("pchest")) {
                            Player target = Bukkit.getPlayer(strings[2]);
                            try {
                                if(target != null) {
                                    int value = Integer.valueOf(strings[3]);
                                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast §e" + target.getDisplayName() + " §a" + strings[3] + " §6Premium Kisten entzogen");
                                    target.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§e" + p.getDisplayName() + " §7hat dir §e" + strings[3] + " Premium kisten entzogen§7.");
                                    User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                                    int amountchest = user.getPremiumChest() - Integer.parseInt(strings[3]);
                                    user.setPremiumChest(amountchest);
                                } else {
                                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cDer Spieler ist offline");
                                }
                            } catch (Exception ex) {
                                p.sendMessage("§cGebe einen Spieler an!");
                            }
                        } else if(strings[1].equalsIgnoreCase("tickets")) {
                            Player target = Bukkit.getPlayer(strings[2]);
                            try {
                                if(target != null) {
                                    int value = Integer.valueOf(strings[3]);
                                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast §e" + target.getDisplayName() + " §a" + strings[3] + " §eTickets Kisten entzogen");
                                    target.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§e" + p.getDisplayName() + " §7hat dir §e" + strings[3] + " Tickets kisten entzogen§7.");
                                    User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                                    int amountchest = user.getTickets() - Integer.parseInt(strings[3]);
                                    user.setTickets(amountchest);
                                } else {
                                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cDer Spieler ist offline");
                                }
                            } catch (Exception ex) {
                                p.sendMessage("§cGebe einen Spieler an!");
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
