package de.sinqular.lobbysystem.lotterie.listener;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.api.FireworkAPI;
import de.sinqular.lobbysystem.api.ItemAPI;
import de.sinqular.lobbysystem.display.DisplayScoreBoard;
import de.sinqular.lobbysystem.lotterie.LotterieData;
import de.sinqular.lobbysystem.lotterie.chestopening.Animator;
import de.sinqular.lobbysystem.lotterie.chestopening.ChestLoot;
import de.sinqular.lobbysystem.lotterie.chestopening.IAnimator;
import de.sinqular.lobbysystem.lotterie.inv.LotterieInventory;
import de.sinqular.lobbysystem.manager.SettingManager;
import de.sinqular.lobbysystem.mysql.entity.User;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;


public class LotterieClickListener implements Listener {


    public int ei = 0;
    public int z = 0;
    public int d = 0;
    public int v = 0;


    @EventHandler
    public void LotterieClickListener(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        ItemStack clicked = p.getItemInHand();
        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getClickedBlock().getType() == Material.ENDER_CHEST) {

                Inventory inv = Bukkit.createInventory(null, 9*3, "§8» §eLotterie");

                inv.setItem(0, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(1, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(2, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(3, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(4, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(5, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(6, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(7, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(8, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

                inv.setItem(9, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

                inv.setItem(11, ItemAPI.createLore(Material.CHEST, " ","§eGewinne tolle Sachen!",0,"§7Chest-Opening"));

                inv.setItem(13, ItemAPI.createLore(Material.PAPER, "§7Ticket Kaufen für §e1000 Coins", "§7Du hast §e"+ user.getTickets() +  " Tickets", 0, "§7Tickets"));

                inv.setItem(15, ItemAPI.createLore(Material.ENDER_CHEST, " ","§7Du brauchst 1 Ticket.",0,"§eLotterie"));

                inv.setItem(17, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

                inv.setItem(18, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(19, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(20, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(21, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(22, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(23, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(24, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(25, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

                inv.setItem(26, ItemAPI.createItem(Material.BARRIER, 0, "§cSchließen"));

                p.openInventory(inv);
            }
        }




    }

    @EventHandler
    public void onInventoryClick(org.bukkit.event.inventory.InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
        if (e.getInventory().getName().equals("§8» §eLotterie")) {
            e.setCancelled(true);
            if (!ItemAPI.onCheck(e)) {
                return;
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cSchließen")) {
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Chest-Opening")) {
                Inventory inv = Bukkit.createInventory(null, 9*3, "§7Suche dir eine Kiste aus");

                inv.setItem(0, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(1, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(2, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(3, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(4, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(5, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(6, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(7, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(8, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

                inv.setItem(9, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

                inv.setItem(11, ItemAPI.createLore(Material.CHEST, " ","§7Du hast §e"+ user.getChests() +" Kisten",0,"§7Normale Kiste"));

                inv.setItem(13, ItemAPI.createLore(Material.EYE_OF_ENDER, "", "§7Hier ist der Kisten Shop", 0, "§aKiste kaufen"));

                inv.setItem(15, ItemAPI.createLore(Material.ENDER_CHEST, " ","§7Du hast §e"+ user.getPremiumChest() +" Kisten",0,"§6Premium Kiste"));

                inv.setItem(17, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

                inv.setItem(18, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(19, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(20, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(21, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(22, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(23, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(24, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(25, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

                inv.setItem(26, ItemAPI.createItem(Material.BARRIER, 0, "§cSchließen"));

                p.openInventory(inv);
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Tickets")) {
                if(user.getCoins() <= 999) {
                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cDu hast zu wenig Coins");
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
                } else if(user.getCoins() >= 1000) {
                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast dir §eein Ticket §agekauft§7.");
                    p.playSound(p.getLocation(), Sound.BURP, 1, 1);
                    int amountickets = user.getTickets() + 1;
                    int amountcoins = user.getCoins() - 1000;
                    user.setTickets(amountickets);
                    user.setCoins(amountcoins);
                    if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {

                    } else if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                        DisplayScoreBoard.setScoreboard(p);
                    }
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                    p.closeInventory();

                }
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eLotterie")) {
                if(user.getTickets() <= 0) {
                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cDu hast zu wenig Tickets!");
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1, 1);

                } else if(user.getTickets() >= 1) {
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1,1);
                    p.closeInventory();
                    p.openInventory(LotterieInventory.LotterieInventory());
                    int amountickets = user.getTickets() - 1;
                    user.setTickets(amountickets);
                }

            }
        } else if (e.getInventory().getName().equals("§7Suche dir eine Kiste aus")) {
            e.setCancelled(true);
            if (!ItemAPI.onCheck(e)) {
                return;
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cSchließen")) {
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Normale Kiste")) {
                if(user.getChests() <= 0) {
                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cDu hast keine Kiste");
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1, 1);
                } else if(user.getChests() >= 1) {
                    int amchest = user.getChests() - 1;
                    user.setChests(amchest);
                    p.playSound(p.getLocation(), Sound.CLICK, 1 ,3);
                    LobbySystem.animator = new Animator(p, new IAnimator() {

                        @Override
                        public Inventory setupInventory(Player p) {
                            Inventory inv = Bukkit.createInventory(null, 27, "§cÖffnet...");
                            for(int i = 0; i < inv.getSize(); i++) {
                                inv.setItem(i, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                            }

                            return inv;
                        }

                        @Override
                        public void onTick(Player p, Inventory inv, List<ItemStack> items, boolean lastTick) {
                            p.playSound(p.getLocation(), Sound.CLICK, 1 ,3);
                            if(lastTick) {
                                if(p != null) {
                                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                                    if(inv.getItem(13).getItemMeta().getDisplayName().equalsIgnoreCase("§e1.000 Coins")) {
                                      p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast §e1.000 Coins §agewonnen§7!");
                                      int amountcoins = user.getCoins() + 1000;
                                      user.setCoins(amountcoins);
                                        if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {

                                        } else if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                                            DisplayScoreBoard.setScoreboard(p);
                                        }

                                    } else if(inv.getItem(13).getItemMeta().getDisplayName().equalsIgnoreCase("§e10.000 Coins")) {
                                        p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast §e10.000 Coins §agewonnen§7!");
                                        int amountcoins = user.getCoins() + 10000;
                                        user.setCoins(amountcoins);
                                        if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {

                                        } else if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                                            DisplayScoreBoard.setScoreboard(p);
                                        }
                                    } else if(inv.getItem(13).getItemMeta().getDisplayName().equalsIgnoreCase("§e100.000 Coins")) {
                                        p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast §e100.000 Coins §agewonnen§7!");
                                        int amountcoins = user.getCoins() + 100000;
                                        user.setCoins(amountcoins);
                                        if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {

                                        } else if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                                            DisplayScoreBoard.setScoreboard(p);
                                        }
                                    }

                                        Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), new Runnable() {
                                        @Override
                                        public void run() {
                                            FireworkAPI.spawnFirework(p);
                                        }
                                    }, 10);
                                    return;
                                }
                            }

                            inv.setItem(4, ItemAPI.createItem(Material.HOPPER, 0, "§cDein Gewinn!"));
                            inv.setItem(10, items.get(0));
                            inv.setItem(11, items.get(1));
                            inv.setItem(12, items.get(2));
                            inv.setItem(13, items.get(3));
                            inv.setItem(14, items.get(4));
                            inv.setItem(15, items.get(5));
                            inv.setItem(16, items.get(6));
                            items.add(items.get(0).clone());
                            items.remove(items.get(0));
                        }
                    });

                    new ChestLoot().getNormalLoot();
                }

            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Premium Kiste")) {
                if(user.getPremiumChest() <= 0) {
                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cDu hast keine Kiste");
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1, 1);
                } else if(user.getPremiumChest() >= 1) {
                    int ampchest = user.getPremiumChest() - 1;
                    user.setPremiumChest(ampchest);
                    p.playSound(p.getLocation(), Sound.CLICK, 1 ,3);
                    LobbySystem.animator = new Animator(p, new IAnimator() {

                        @Override
                        public Inventory setupInventory(Player p) {
                            Inventory inv = Bukkit.createInventory(null, 27, "§cÖffnet...");
                            for(int i = 0; i < inv.getSize(); i++) {
                                inv.setItem(i, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                            }

                            return inv;
                        }

                        @Override
                        public void onTick(Player p, Inventory inv, List<ItemStack> items, boolean lastTick) {
                            p.playSound(p.getLocation(), Sound.CLICK, 1 ,3);
                            if(lastTick) {
                                if(p != null) {
                                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                                    if(inv.getItem(13).getItemMeta().getDisplayName().equalsIgnoreCase("§e1.000 Coins")) {
                                        p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast §e1.000 Coins §agewonnen§7!");
                                        int amountcoins = user.getCoins() + 1000;
                                        user.setCoins(amountcoins);
                                        if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {

                                        } else if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                                            DisplayScoreBoard.setScoreboard(p);
                                        }

                                    } else if(inv.getItem(13).getItemMeta().getDisplayName().equalsIgnoreCase("§e10.000 Coins")) {
                                        p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast §e10.000 Coins §agewonnen§7!");
                                        int amountcoins = user.getCoins() + 10000;
                                        user.setCoins(amountcoins);
                                        if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {

                                        } else if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                                            DisplayScoreBoard.setScoreboard(p);
                                        }
                                    } else if(inv.getItem(13).getItemMeta().getDisplayName().equalsIgnoreCase("§e100.000 Coins")) {
                                        p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast §e100.000 Coins §agewonnen§7!");
                                        int amountcoins = user.getCoins() + 100000;
                                        user.setCoins(amountcoins);
                                        if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {

                                        } else if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                                            DisplayScoreBoard.setScoreboard(p);
                                        }

                                    }

                                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), new Runnable() {
                                        @Override
                                        public void run() {
                                            FireworkAPI.spawnFirework(p);
                                        }
                                    }, 10);
                                    return;
                                }
                            }

                            inv.setItem(4, ItemAPI.createItem(Material.HOPPER, 0, "§cDein Gewinn!"));
                            inv.setItem(10, items.get(0));
                            inv.setItem(11, items.get(1));
                            inv.setItem(12, items.get(2));
                            inv.setItem(13, items.get(3));
                            inv.setItem(14, items.get(4));
                            inv.setItem(15, items.get(5));
                            inv.setItem(16, items.get(6));
                            items.add(items.get(0).clone());
                            items.remove(items.get(0));
                        }
                    });

                    new ChestLoot().getPremiumLoot();
                }

            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aKiste kaufen")) {
                p.closeInventory();
                Inventory inv = Bukkit.createInventory(null, 9*3, "§eKisten Shop");
                p.playSound(p.getLocation(), Sound.CLICK, 1 ,3);

                inv.setItem(0, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(1, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(2, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(3, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(4, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(5, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(6, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(7, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(8, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

                inv.setItem(9, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

                inv.setItem(11, ItemAPI.createLore(Material.CHEST, " ","§7Kosten : §e10.000 Coins" ,0,"§7Normale Kiste"));

                inv.setItem(13, ItemAPI.createLore(Material.GOLD_INGOT, "", "§eCoins " + user.getCoins(), 0, "§eKisten Shop"));

                inv.setItem(15, ItemAPI.createLore(Material.ENDER_CHEST, " ","§7Kosten : §e50.000 Coins",0,"§6Premium Kiste"));

                inv.setItem(17, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

                inv.setItem(18, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(19, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(20, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(21, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(22, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(23, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(24, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(25, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

                inv.setItem(26, ItemAPI.createItem(Material.BARRIER, 0, "§cSchließen"));

                p.openInventory(inv);
            }
        } else if (e.getInventory().getName().equals("§eKisten Shop")) {
            e.setCancelled(true);
            if (!ItemAPI.onCheck(e)) {
                return;
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cSchließen")) {
                p.closeInventory();
                p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Normale Kiste")) {
                if(user.getCoins() <= 9999) {
                    p.closeInventory();
                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cDu hast zu wenig Coins!");
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
                } else if(user.getCoins() >= 10000) {

                    int amountchest = user.getChests() + 1;
                    int amountcoins = user.getCoins() - 10000;
                    user.setChests(amountchest);
                    user.setCoins(amountcoins);

                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast dir §eEine Kiste §agekauft!");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                    if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {

                    } else if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                        DisplayScoreBoard.setScoreboard(p);
                    }
                    p.closeInventory();
                }
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Premium Kiste")) {
                if(user.getCoins() <= 49999) {
                    p.closeInventory();
                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§cDu hast zu wenig Coins!");
                    p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
                } else if(user.getCoins() >= 50000) {
                    int amountchest = user.getPremiumChest() + 1;
                    int amountcoins = user.getCoins() - 50000;
                    user.setPremiumChest(amountchest);
                    user.setCoins(amountcoins);
                    p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast dir §6Eine Premium Kiste §agekauft!");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                    if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {

                    } else if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                        DisplayScoreBoard.setScoreboard(p);
                    }
                    p.closeInventory();





                }
            }
        } else if (e.getInventory().getName().equals("§8» §eDrücke auf die Kisten!")) {
            e.setCancelled(true);
            if (!ItemAPI.onCheck(e)) {
                return;
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§eKlicke hier!")) {
                int count = LobbySystem.LOTTERIE_PLAYER_INTERACT_COUNTER.containsKey(p) ? LobbySystem.LOTTERIE_PLAYER_INTERACT_COUNTER.get(p) : 0;
                LobbySystem.LOTTERIE_PLAYER_INTERACT_COUNTER.put(p, count + 1);

                if (LobbySystem.LOTTERIE_PLAYER_INTERACT_COUNTER.get(p).intValue() <= 4) {

                    List<Integer> coins = LobbySystem.getLobbySystem().getRandomCoins().getList();
                    e.getClickedInventory().setItem(e.getSlot(), ItemAPI.createItem(Material.GOLD_INGOT, 0, "§7" + coins.get(e.getSlot()) + " §eCoins"));
                    int amountcoins = user.getCoins() + coins.get(e.getSlot());
                    user.setCoins(amountcoins);





                    if (LobbySystem.LOTTERIE_PLAYER_INTERACT_COUNTER.get(p).intValue() == 1) {
                        ei = coins.get(e.getSlot()).intValue();
                    } else if (LobbySystem.LOTTERIE_PLAYER_INTERACT_COUNTER.get(p).intValue() == 2) {
                        z = coins.get(e.getSlot()).intValue();
                    } else if (LobbySystem.LOTTERIE_PLAYER_INTERACT_COUNTER.get(p).intValue() == 3) {
                        d = coins.get(e.getSlot()).intValue();
                    } else if (LobbySystem.LOTTERIE_PLAYER_INTERACT_COUNTER.get(p).intValue() == 4) {
                        v = coins.get(e.getSlot()).intValue();
                    }


                } else if (LobbySystem.LOTTERIE_PLAYER_INTERACT_COUNTER.get(p).intValue() == 5) {

                    LobbySystem.LOTTERIE_PLAYER_INTERACT_COUNTER.remove(p);
                    if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {

                    } else if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                        DisplayScoreBoard.setScoreboard(p);
                    }
                    p.closeInventory();
                    int completeAmount = ei + z + d + v;
                    //p.sendMessage(LobbySystem.LOTTERIE_PREIFX + "§7Du hast §e" + Integer.valueOf(completeAmount) +" §eCoins §agewonnen");


                    Inventory inv = LotterieInventory.LotterieInventory();

                    inv.clear();
                    for(int i = 0; i < inv.getSize(); i++) {
                        inv.setItem(i, ItemAPI.createItem(Material.ENDER_CHEST , 0, "§cBitte Warte!"));
                    }
                    p.openInventory(inv);


                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), new Runnable() {
                        @Override
                        public void run() {
                            inv.setItem(0, null);
                            inv.setItem(1, null);
                            inv.setItem(2, null);
                            inv.setItem(3, null);
                            inv.setItem(4, null);
                            inv.setItem(5, null);
                            inv.setItem(6, null);
                            inv.setItem(7, null);
                            inv.setItem(8, null);
                        }
                    }, 4L);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), new Runnable() {
                        @Override
                        public void run() {
                            inv.setItem(9, null);
                            inv.setItem(10, null);
                            inv.setItem(11, null);
                            inv.setItem(12, null);
                            inv.setItem(13, null);
                            inv.setItem(14, null);
                            inv.setItem(15, null);
                            inv.setItem(16, null);
                            inv.setItem(17, null);
                        }
                    }, 8L);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), new Runnable() {
                        @Override
                        public void run() {
                            inv.setItem(18, null);
                            inv.setItem(19, null);
                            inv.setItem(20, null);
                            inv.setItem(21, null);
                            inv.setItem(22, null);
                            inv.setItem(23, null);
                            inv.setItem(24, null);
                            inv.setItem(25, null);
                            inv.setItem(26, null);
                        }
                    },12L);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), new Runnable() {
                        @Override
                        public void run() {
                            inv.setItem(27, null);
                            inv.setItem(28, null);
                            inv.setItem(29, null);
                            inv.setItem(30, null);
                            inv.setItem(31, null);
                            inv.setItem(32, null);
                            inv.setItem(33, null);
                            inv.setItem(34, null);
                            inv.setItem(35, null);
                        }
                    }, 16L);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), new Runnable() {
                        @Override
                        public void run() {
                            inv.setItem(36, null);
                            inv.setItem(37, null);
                            inv.setItem(38, null);
                            inv.setItem(39, null);
                            inv.setItem(40, null);
                            inv.setItem(41, null);
                            inv.setItem(42, null);
                            inv.setItem(43, null);
                            inv.setItem(44, null);
                        }
                    }, 20L);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), new Runnable() {
                        @Override
                        public void run() {
                            inv.setItem(45, null);
                            inv.setItem(46, null);
                            inv.setItem(47, null);
                            inv.setItem(48, null);
                            inv.setItem(49, null);
                            inv.setItem(50, null);
                            inv.setItem(51, null);
                            inv.setItem(52, null);
                            inv.setItem(53, null);
                        }
                    }, 24L);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), new Runnable() {
                        @Override
                        public void run() {
                            FireworkAPI.spawnFirework(p);
                            p.closeInventory();
                            p.openInventory(LotterieData.EndingInventar(ei, z, d, v, completeAmount));
                            p.sendMessage(LobbySystem.LOTTERIE_PREFIX + "§7Du hast §e " + completeAmount + " Coins §agewonnen");

                        }
                    }, 30L);


                }

            }
        } else if (e.getInventory().getName().equals("§8» §cDein Gewinn!")) {
            e.setCancelled(true);
            if (!ItemAPI.onCheck(e)) {
                return;
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§cSchließen")) {
                p.closeInventory();

            }
        }

    }

}
