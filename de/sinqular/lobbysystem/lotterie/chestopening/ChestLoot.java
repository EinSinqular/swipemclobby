package de.sinqular.lobbysystem.lotterie.chestopening;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.api.ItemAPI;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChestLoot {

    public static List<ItemStack> normalLoot = new ArrayList<>();
    public static List<ItemStack> premiumLoot = new ArrayList<>();

    public void getNormalLoot() {
        normalLoot.add(ItemAPI.createItem(Material.GOLD_NUGGET, 0, "§e1.000 Coins"));
        normalLoot.add(ItemAPI.createItem(Material.GOLD_INGOT, 0 , "§e10.000 Coins"));





        Collections.shuffle(normalLoot);
        LobbySystem.animator.startAnimation(60, ChestLoot.normalLoot, 20);


    }
    public void getPremiumLoot() {
        normalLoot.add(ItemAPI.createItem(Material.GOLD_NUGGET, 0, "§e1.000 Coins"));
        normalLoot.add(ItemAPI.createItem(Material.GOLD_INGOT, 0 , "§e10.000 Coins"));
        normalLoot.add(ItemAPI.createItem(Material.GOLD_BLOCK, 0, "§e100.000 Coins"));
        Collections.shuffle(premiumLoot);
        LobbySystem.animator.startAnimation(60, ChestLoot.premiumLoot, 20);




    }


}
