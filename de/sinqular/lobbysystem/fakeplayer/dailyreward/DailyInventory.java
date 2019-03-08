package de.sinqular.lobbysystem.fakeplayer.dailyreward;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.api.ItemAPI;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class DailyInventory {

    public static Inventory DailyRewardInventory(Player p) {
        Inventory inv = Bukkit.createInventory(null, 9*3, "§cReward");

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
        inv.setItem(10, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());


        if(!user.getRewardTime()) {
            inv.setItem(11, ItemAPI.createLore(Material.STORAGE_MINECART, "§e1.000 Coins" , "§aDaily Reward verügbar", 0, "§7Normales Reward"));
        } else if(user.getRewardTime()) {
            inv.setItem(11, ItemAPI.createLore(Material.MINECART, " ", "§cDaily Reward abgeholt!", 0, "§7Normales Reward"));
        }
        if(!user.getPremiumRewardTime()) {
            //14
            inv.setItem(15, ItemAPI.createLore(Material.STORAGE_MINECART, "§e2.500 Coins", "§6Nur für Premium", 0, "§6Premium Reward"));
        } else if(user.getPremiumRewardTime()) {
            inv.setItem(15, ItemAPI.createLore(Material.MINECART, "§cDaily Reward abgeholt!", "§6Nur für Premium", 0, "§6Premium Reward"));
        }

        // 16 17

        inv.setItem(16, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(17, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

        inv.setItem(18, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(19, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(20, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(21, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(22, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(23, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(24, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(25, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));
        inv.setItem(26, ItemAPI.createItem(Material.STAINED_GLASS_PANE, 7, " "));

        p.openInventory(inv);
        return inv;
    }

}
