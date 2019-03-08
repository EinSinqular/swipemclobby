package de.sinqular.lobbysystem.manager;

import de.sinqular.lobbysystem.api.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InventoryManager {

    public static Inventory particleInventory(Player p, String name) {
        Inventory inv = Bukkit.createInventory(null, 9*3, name);

        inv.setItem(0, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(1, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(2, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(3, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(4, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(5, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(6, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(7, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(8, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

        inv.setItem(11, ItemAPI.createLore(Material.FIREWORK, "", "§8» §7Wähle die §9Helix Partikel§7.", 0, "§9§lHelix"));
        inv.setItem(13, ItemAPI.createLore(Material.LEASH, "", "§8» §7Wähle die §eHalo Partikel§7.", 0, "§e§lHalo"));
        inv.setItem(15, ItemAPI.createLore(Material.FEATHER, "", "§8» §7Wähle die §5§lFlügel Partikel", 0, "§5§lFlügel"));

        inv.setItem(18, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(19, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(20, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(21, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(22, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(23, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(24, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(25, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(26, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

        return inv;
    }

}
