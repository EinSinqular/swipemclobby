package de.sinqular.lobbysystem.utils;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.api.ItemAPI;
import de.sinqular.lobbysystem.manager.GadGetsManager;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Bukkit;
import org.bukkit.Color;
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

public class Extras implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        ItemStack clicked = p.getItemInHand();
        if((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)) {
            if(clicked == null || !clicked.hasItemMeta()) {
                if(LobbySystem.getLobbySystem().getEditPlayer().contains(p)) {

                } else {
                    e.setCancelled(true);
                    return;
                }
            } else if(clicked.getItemMeta().getDisplayName().equals("§7• §8┃ §eExtras §8┃ §7•")) {
                Inventory inv = Bukkit.createInventory(null, 9*6, "§8» §eExtras");
                User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());

                inv.setItem(0, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(1, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(7, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(8, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(9, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(10, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));


                inv.setItem(11, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                inv.setItem(12, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                inv.setItem(13, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                inv.setItem(14, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                inv.setItem(15, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));



                inv.setItem(16, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(17, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));


                inv.setItem(18, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(26, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(27, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(35, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(36, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(44, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));


                inv.setItem(45, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(46, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(47, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(48, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(49, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(50, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(51, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(52, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
                inv.setItem(53, ItemAPI.createItem(Material.BARRIER, 7, "§cSchließen"));

                inv.setItem(2, ItemAPI.createLore(Material.DIAMOND_BOOTS, "", "§8» §7Suche dir ein paar Schuhe aus!", 0, "§b§lBoots"));
                inv.setItem(3, ItemAPI.createLore(Material.BLAZE_POWDER, "", "§8» §7Suche dir ein paar Partikel Effecte aus!", 0,"§e§lPartikel"));
                inv.setItem(4, ItemAPI.createSkullLore("xShirro", "", "§6§lKöpfe", "§8» §7Suche dir ein paar Köpfe aus!"));
                inv.setItem(5, ItemAPI.createLetherLore(Material.LEATHER_CHESTPLATE, "", "§8» §7Zieh dir doch was schickes an!", 0, Color.MAROON,"§c§lUmkleide"));
                inv.setItem(6, ItemAPI.createLore(Material.FISHING_ROD, "", "§8» §7Nutze coole GadGets!", 0, "§9§lGadgets"));







                //NETHER IRON EMERALD

                //19 - 25
                //28 - 34
                //37 - 43



                p.playSound(p.getLocation(), Sound.CHEST_OPEN, 1 ,1);
                p.openInventory(inv);


            }
        }
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
        if (e.getInventory().getName().equals("§8» §eExtras")) {
            e.setCancelled(true);
            if (!ItemAPI.onCheck(e)) {
                return;
            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§b§lBoots")) {
                p.getOpenInventory().setItem(11, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 5, "§aDu bist hier"));
                p.getOpenInventory().setItem(12, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(13, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(14, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(15, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.playSound(p.getLocation(), Sound.CLICK, 1, 3);

                if(user.getGadgets().contains(GadGetsManager.beefboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(19, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.MAROON, "§cBeef-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.beefboots) ) {
                    p.getOpenInventory().setItem(19, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.MAROON, "§cBeef-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.redstoneboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(20, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.RED, "§4Redstone-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.redstoneboots) ) {
                    p.getOpenInventory().setItem(20, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.RED, "§4Redstone-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.waldboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(21, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.GREEN, "§2Wald-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.waldboots) ) {
                    p.getOpenInventory().setItem(21, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e10.000 Coins", 0, Color.GREEN, "§2Wald-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.goldboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(22, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.YELLOW, "§eGold-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.goldboots) ) {
                    p.getOpenInventory().setItem(22, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.YELLOW, "§eGold-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.enderboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(23, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.PURPLE, "§5Ender-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.enderboots )) {
                    p.getOpenInventory().setItem(23, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.PURPLE, "§5Ender-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.musikboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(24, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.YELLOW, "§eMusik-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.musikboots) ) {
                    p.getOpenInventory().setItem(24, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.YELLOW, "§eMusik-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.snowboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(25, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.WHITE, "§fSnow-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.snowboots) ) {
                    p.getOpenInventory().setItem(25, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.WHITE, "§fSnow-Boots"));
                }

                if(user.getGadgets().contains(GadGetsManager.flowerboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(28, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.LIME, "§2Flower-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.flowerboots) ) {
                    p.getOpenInventory().setItem(28, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.LIME, "§2Flow-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.poisonboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(29, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.PURPLE, "§5Poison-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.poisonboots) ) {
                    p.getOpenInventory().setItem(29, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.PURPLE, "§5Poison-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.buchboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(30, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.GRAY, "§7Buch-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.buchboots) ) {
                    p.getOpenInventory().setItem(30, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.GRAY, "§7Buch-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.sugarboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(31, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.WHITE, "§fSugar-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.sugarboots) ) {
                    p.getOpenInventory().setItem(31, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.WHITE, "§fSugar-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.kohleboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(32, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.BLACK, "§0Kohle-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.kohleboots) ) {
                    p.getOpenInventory().setItem(32, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.BLACK, "§0Kohle-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.buchboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(33, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.GRAY, "§7Bucket-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.buchboots) ) {
                    p.getOpenInventory().setItem(33, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.GRAY, "§7Bucket-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.seaboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(34, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.TEAL, "§3See-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.seaboots) ) {
                    p.getOpenInventory().setItem(34, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.TEAL, "§3See-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.farmboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(37, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.LIME, "§aFarm-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.farmboots) ) {
                    p.getOpenInventory().setItem(37, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.LIME, "§aFarm-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.potionboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(38, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.FUCHSIA, "§dPotion-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.potionboots) ) {
                    p.getOpenInventory().setItem(38, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.FUCHSIA, "§dPotion-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.soupboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(39, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.MAROON, "§cSoup-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.soupboots) ) {
                    p.getOpenInventory().setItem(39, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.MAROON, "§cSoup-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.cookieboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(40, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.MAROON, "§6Cookie-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.cookieboots) ) {
                    p.getOpenInventory().setItem(40, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.MAROON, "§6Cookie-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.netherboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(41, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.RED, "§cNether-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.netherboots) ) {
                    p.getOpenInventory().setItem(41, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.RED, "§cNether-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.ironboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(42, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.GRAY, "§7Iron-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.ironboots) ) {
                    p.getOpenInventory().setItem(42, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.GRAY, "§7Iron-Boots"));
                }
                if(user.getGadgets().contains(GadGetsManager.emeraldboots) || p.hasPermission("lobby.gadgets")) {
                    p.getOpenInventory().setItem(43, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §aIm besitz", 0, Color.LIME, "§aEmerald-Boots"));
                } else if(!user.getGadgets().contains(GadGetsManager.emeraldboots) ) {
                    p.getOpenInventory().setItem(43, ItemAPI.createLetherLore(Material.LEATHER_BOOTS, "", "§8» §7Kosten §8: §e7.500 Coins", 0, Color.LIME, "§aEmerald-Boots"));
                }




            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§e§lPartikel")) {
                p.getOpenInventory().setItem(11, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(12, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 5, "§aDu bist hier"));
                p.getOpenInventory().setItem(13, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(14, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(15, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.playSound(p.getLocation(), Sound.CLICK, 1, 3);

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lKöpfe")) {
                p.getOpenInventory().setItem(11, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(12, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(13, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 5, "§aDu bist hier"));
                p.getOpenInventory().setItem(14, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(15, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.playSound(p.getLocation(), Sound.CLICK, 1, 3);

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lUmkleide")) {
                p.getOpenInventory().setItem(11, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(12, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(13, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(14, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 5, "§aDu bist hier"));
                p.getOpenInventory().setItem(15, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.playSound(p.getLocation(), Sound.CLICK, 1, 3);

            } else if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§9§lGadgets")) {
                p.getOpenInventory().setItem(11, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(12, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(13, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(14, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 0, " "));
                p.getOpenInventory().setItem(15, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 5, "§aDu bist hier"));
                p.playSound(p.getLocation(), Sound.CLICK, 1, 3);

            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cSchließen")) {
                p.getOpenInventory().close();
                p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1 ,1);
            }
        }
    }

}
