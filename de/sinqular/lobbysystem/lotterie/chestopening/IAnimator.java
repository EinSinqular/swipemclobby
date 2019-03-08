package de.sinqular.lobbysystem.lotterie.chestopening;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class IAnimator {

    public abstract Inventory setupInventory(Player p);

    public abstract void onTick(Player p, Inventory inv, List<ItemStack> items, boolean lastTick);

}
