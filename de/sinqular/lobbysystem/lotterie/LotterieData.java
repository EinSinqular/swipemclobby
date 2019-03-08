package de.sinqular.lobbysystem.lotterie;

import de.sinqular.lobbysystem.api.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;

public class LotterieData {

    public static Inventory EndingInventar(int eins, int zwei, int drei, int vier, int all) {
        Inventory inv = Bukkit.createInventory(null, 9 * 3, "§8» §cDein Gewinn!");
        inv.setItem(0, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(1, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

        inv.setItem(4, ItemAPI.createItemEnch(Material.HOPPER, 1, 0, "§e§lGesammt Coins", null, Enchantment.KNOCKBACK, 1));

        inv.setItem(7, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(8, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(9, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

        inv.setItem(10, ItemAPI.createItem(Material.GOLD_INGOT, 0, "§e" + eins));
        inv.setItem(11, ItemAPI.createItem(Material.GOLD_INGOT, 0, "§e" + zwei));

        // inv.setItem(13, ItemAPI.createItem(Material.GOLD_BLOCK, 0, "§e" + all));
        inv.setItem(13, ItemAPI.createItemEnch(Material.GOLD_BLOCK, 1, 0, "§e" + all, null, Enchantment.KNOCKBACK, 1));

        inv.setItem(15, ItemAPI.createItem(Material.GOLD_INGOT, 0, "§e" + drei));
        inv.setItem(16, ItemAPI.createItem(Material.GOLD_INGOT, 0, "§e" + vier));

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


        return inv;
    }

}
