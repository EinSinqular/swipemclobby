package de.sinqular.lobbysystem.utils;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.api.CloudLobbyAPI;
import de.sinqular.lobbysystem.api.ItemAPI;
import de.sinqular.lobbysystem.manager.LocationManager;
import de.sinqular.lobbysystem.manager.SettingManager;
import de.sinqular.lobbysystem.mysql.entity.User;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Navigator implements Listener {

    @EventHandler
    public void onNavigatorInteract(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        User user = (User) LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
        ItemStack clicked = p.getItemInHand();
        if((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            if(clicked == null || !clicked.hasItemMeta()) {
                if(LobbySystem.getLobbySystem().getEditPlayer().contains(p)) {
                } else {
                    e.setCancelled(true);
                    return;
                }
            } else if(clicked.getItemMeta().getDisplayName().equals("§7• §8┃ §3Navigator §8┃ §7•")) {
                e.setCancelled(true);
                final Inventory inv = Bukkit.createInventory(null, 9*5, "§8» §bSpielmodis");

                inv.setItem(0, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(1, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(2, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(6, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(7, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(8, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));


                inv.setItem(9, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(17, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));

                inv.setItem(18, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(26, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));

                inv.setItem(27, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(35, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));

                inv.setItem(36, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(37, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(38, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(42, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(43, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));
                inv.setItem(44, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 15, " "));

                if(user.getUserSettings().contains(SettingManager.showAnimation)) {
                    inv.setItem(3, ItemAPI.createLore(Material.STICK, "", "§8» §7Teleportiere dich zu §3Aura§7.", 0, "§3§lAura"));
                    inv.setItem(5, ItemAPI.createLore(Material.IRON_SWORD, "", "§8» §7Teleportiere dich zu §6FFA§7.", 0, "§6§lFFA"));
                    inv.setItem(10, ItemAPI.createLore(Material.DIAMOND_PICKAXE, "", "§8» §7Teleportiere dich zu §bCityBuild§7.", 0, "§b§lCityBuild"));
                    inv.setItem(16, ItemAPI.createLore(Material.BED, "", "§8» §7Teleportiere dich zu §cBedWars§7.", 0, "§c§lBedWars"));
                    inv.setItem(21, ItemAPI.createLore(Material.FIREWORK, "", "§8» §7Trete dem nächst bestem Server bei.", 0, "§9§lQuickJoin"));
                    inv.setItem(22, ItemAPI.createLore(Material.SLIME_BALL, "", "§8» §7Teleportiere dich zum §fSpawn§7.", 0, "§f§lSpawn"));
                    inv.setItem(23, ItemAPI.createSkullLore(p.getName(), "", "§d§lCommunity", "§8» §7Teleportiere dich zur §dCommunity§7."));
                    inv.setItem(28, ItemAPI.createLore(Material.GRASS, "", "§8» §7Teleportiere dich zu §aSkyWars§7.", 0, "§a§lSkyWars"));
                    inv.setItem(34, ItemAPI.createLore(Material.REDSTONE_TORCH_ON, "", "§8» §7Teleportiere dich zu §4TTT§7.", 0, "§4§lTTT"));
                    inv.setItem(39, ItemAPI.createLore(Material.ENDER_CHEST, "", "§8» §7Teleportiere dich zur §eLotterie§7.", 0, "§e§lLotterie"));
                    inv.setItem(41, ItemAPI.createLore(Material.GOLD_NUGGET, "", "§8» §7Teleportiere dich zum §6Daily-Reward§7.", 0, "§6§lDaily-Reward"));
                    p.openInventory(inv);

                } else if(!user.getUserSettings().contains(SettingManager.showAnimation)) {
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), () ->{
                        inv.setItem(23, ItemAPI.createSkullLore(p.getName(), "", "§d§lCommunity", "§8» §7Teleportiere dich zur §dCommunity§7."));
                        p.playSound(p.getLocation(), Sound.CLICK, 1, 3);
                    }, 2);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), ()->{
                        inv.setItem(22, ItemAPI.createLore(Material.SLIME_BALL, "", "§8» §7Teleportiere dich zum §fSpawn§7.", 0, "§f§lSpawn"));
                        p.playSound(p.getLocation(), Sound.CLICK, 1, 3);
                    }, 3);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), ()->{
                        inv.setItem(21, ItemAPI.createLore(Material.FIREWORK, "", "§8» §7Trete dem nächst bestem Server bei.", 0, "§9§lQuickJoin"));
                        p.playSound(p.getLocation(), Sound.CLICK, 1, 3);
                    },4);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), ()->{
                        inv.setItem(10, ItemAPI.createLore(Material.DIAMOND_PICKAXE, "", "§8» §7Teleportiere dich zu §bCityBuild§7.", 0, "§b§lCityBuild"));
                        p.playSound(p.getLocation(), Sound.CLICK, 1, 3);
                    },6);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), ()->{
                        inv.setItem(3, ItemAPI.createLore(Material.STICK, "", "§8» §7Teleportiere dich zu §3Aura§7.", 0, "§3§lAura"));
                        p.playSound(p.getLocation(), Sound.CLICK, 1, 3);
                    },8);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), ()->{
                        inv.setItem(5, ItemAPI.createLore(Material.IRON_SWORD, "", "§8» §7Teleportiere dich zu §6FFA§7.", 0, "§6§lFFA"));
                        p.playSound(p.getLocation(), Sound.CLICK, 1, 3);
                    },10);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(),()->{
                        inv.setItem(16, ItemAPI.createLore(Material.BED, "", "§8» §7Teleportiere dich zu §cBedWars§7.", 0, "§c§lBedWars"));
                        p.playSound(p.getLocation(), Sound.CLICK, 1, 3);
                    }, 12);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), ()->{
                        inv.setItem(34, ItemAPI.createLore(Material.REDSTONE_TORCH_ON, "", "§8» §7Teleportiere dich zu §4TTT§7.", 0, "§4§lTTT"));
                        p.playSound(p.getLocation(), Sound.CLICK, 1, 3);
                    }, 14);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), ()->{
                        inv.setItem(41, ItemAPI.createLore(Material.GOLD_NUGGET, "", "§8» §7Teleportiere dich zum §6Daily-Reward§7.", 0, "§6§lDaily-Reward"));
                        p.playSound(p.getLocation(), Sound.CLICK, 1, 3);
                    }, 16);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), ()->{
                        inv.setItem(39, ItemAPI.createLore(Material.ENDER_CHEST, "", "§8» §7Teleportiere dich zur §eLotterie§7.", 0, "§e§lLotterie"));
                        p.playSound(p.getLocation(), Sound.CLICK, 1, 3);
                    }, 18);
                    Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), ()->{
                        inv.setItem(28, ItemAPI.createLore(Material.GRASS, "", "§8» §7Teleportiere dich zu §aSkyWars§7.", 0, "§a§lSkyWars"));
                        p.playSound(p.getLocation(), Sound.CLICK, 1, 3);
                    }, 20);
                    p.openInventory(inv);
                }


            }
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
        if (e.getInventory().getName().equals("§8» §bSpielmodis")) {
            e.setCancelled(true);
           try {
               if (!ItemAPI.onCheck(e)) {
                   return;
               } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3§lAura")) {
                   p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                   p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
                   p.teleport(LocationManager.getLocation(p, "aura"));
               } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lFFA")) {
                   p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                   p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
                   p.teleport(LocationManager.getLocation(p, "ffa"));
               } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lCityBuild")) {
                   p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                   p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
                   p.teleport(LocationManager.getLocation(p, "citybuild"));
               } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lBedWars")) {
                   p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                   p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
                   p.teleport(LocationManager.getLocation(p, "bedwars"));
               } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§f§lSpawn")) {
                   p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                   p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
                   p.teleport(LocationManager.getLocation(p, "spawn"));
               } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§d§lCommunity")) {
                   p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                   p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
                   p.teleport(LocationManager.getLocation(p, "community"));
               } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3§lSkyWars")) {
                   p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                   p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
                   p.teleport(LocationManager.getLocation(p, "skywars"));
               } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§4§lTTT")) {
                   p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                   p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
                   p.teleport(LocationManager.getLocation(p, "ttt"));
               } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§e§lLotterie")) {
                   p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                   p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
                   p.teleport(LocationManager.getLocation(p, "lotterie"));
               } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lDaily-Reward")) {
                   p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                   p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20, 0));
                   p.teleport(LocationManager.getLocation(p, "dailyreward"));
               } else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§9§lQuickJoin")) {
                   Inventory inv = Bukkit.createInventory(null, 9*5, "§cQuickJoin");
                   inv.setItem(3, ItemAPI.createLore(Material.STICK, "", "§8» §7Teleportiere dich zu §3Aura§7.", 0, "§3§lAura"));
                   inv.setItem(5, ItemAPI.createLore(Material.IRON_SWORD, "", "§8» §7Teleportiere dich zu §6FFA§7.", 0, "§6§lFFA"));
                   inv.setItem(10, ItemAPI.createLore(Material.DIAMOND_PICKAXE, "", "§8» §7Teleportiere dich zu §bCityBuild§7.", 0, "§b§lCityBuild"));
                   inv.setItem(16, ItemAPI.createLore(Material.BED, "", "§8» §7Teleportiere dich zu §cBedWars§7.", 0, "§c§lBedWars"));
                   inv.setItem(21, ItemAPI.createLore(Material.FIREWORK, "", "§8» §7Trete dem nächst bestem Server bei.", 0, "§9§lQuickJoin"));
                   inv.setItem(23, ItemAPI.createSkullLore(p.getName(), "", "§d§lCommunity", "§8» §7Teleportiere dich zur §dCommunity§7."));
                   inv.setItem(28, ItemAPI.createLore(Material.GRASS, "", "§8» §7Teleportiere dich zu §aSkyWars§7.", 0, "§a§lSkyWars"));
                   inv.setItem(34, ItemAPI.createLore(Material.REDSTONE_TORCH_ON, "", "§8» §7Teleportiere dich zu §4TTT§7.", 0, "§4§lTTT"));
                   p.openInventory(inv);
               }
           } catch (Exception ex) {
               p.sendMessage("§cDer Spielmodi ist nicht gesetzt!");
               p.closeInventory();
           }
        }
    }
    @EventHandler
    public void onQuickJoinClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
        if (e.getInventory().getName().equals("§cQuickJoin")) {
            e.setCancelled(true);
            if (!ItemAPI.onCheck(e)) {
                return;
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3§lAura")) {
                CloudLobbyAPI.quickJoin(p, "Aura");
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lFFA")) {
                CloudLobbyAPI.quickJoin(p, "FFA");
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lCityBuild")) {
                CloudLobbyAPI.quickJoin(p, "CityBuild");
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lBedWars")) {
                CloudLobbyAPI.quickJoin(p, "BedWars");
            }  else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§d§lCommunity")) {
                CloudLobbyAPI.quickJoin(p, "Community");
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§3§lSkyWars")) {
                CloudLobbyAPI.quickJoin(p, "SkyWars");
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§4§lTTT")) {
                CloudLobbyAPI.quickJoin(p, "TTT");

            }
        }
    }

}
