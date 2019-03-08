package de.sinqular.lobbysystem.lotterie.inv;

import de.sinqular.lobbysystem.api.ItemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

public class LotterieInventory {

    public static Inventory LotterieInventory() {

        Inventory inv = Bukkit.createInventory(null, 54, "§8» §eDrücke auf die Kisten!");

        for(int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, ItemAPI.createItem(Material.ENDER_CHEST , 0, "§eKlicke hier!"));
        }
        return inv;

    }

}
