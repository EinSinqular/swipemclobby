package de.sinqular.lobbysystem.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

public class ItemAPI extends JavaPlugin {
    private static ItemAPI ItemAPI;

    public static ItemStack createItem(Material material, int subid, String displayname) {
        ItemStack item = new ItemStack(material, 1, (short)subid);
        ItemMeta mitem = item.getItemMeta();
        mitem.setDisplayName(displayname);
        item.setItemMeta(mitem);

        return item;
    }

    public static ItemStack createLether(Material material, int subid, Color color, String displayname) {
        ItemStack item = new ItemStack(material, 1, (short)subid);
        LeatherArmorMeta mitem = (LeatherArmorMeta)item.getItemMeta();
        mitem.setDisplayName(displayname);
        mitem.setColor(color);
        item.setItemMeta(mitem);

        return item;
    }

    public static boolean onCheck(InventoryClickEvent e) {
        ItemStack clicked = e.getCurrentItem();
        if ((clicked == null) || (!clicked.hasItemMeta())) {
            return false;
        }
        return true;
    }

    public static ItemStack createSkull(String playername, String displayname) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta m = (SkullMeta)skull.getItemMeta();
        m.setOwner(playername);
        m.setDisplayName(displayname);
        skull.setItemMeta(m);
        return skull;
    }

    public static ItemStack createLore(Material material, String lore, String lore2, int subid, String displayname) {
        List<String> list = new ArrayList();
        list.add(lore);
        list.add(lore2);

        ItemStack item = new ItemStack(material, 1, (short)subid);
        ItemMeta mitem = item.getItemMeta();
        mitem.setLore(list);
        mitem.setDisplayName(displayname);
        item.setItemMeta(mitem);
        list.remove(lore);
        list.remove(lore2);
        return item;
    }

    public static ItemStack createSkullLore(String playername, String lore, String displayname, String lore2) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta m = (SkullMeta)skull.getItemMeta();
        m.setOwner(playername);

        List<String> list = new ArrayList();
        list.add(lore);
        list.add(lore2);
        m.setLore(list);

        m.setDisplayName(displayname);
        skull.setItemMeta(m);
        list.remove(lore);
        list.remove(lore2);
        return skull;
    }

    public static void GetInfo(String host) {}

    public static ItemStack createLetherLore(Material material, String lore, String lore2, int subid, Color color, String displayname) {
        ItemStack item = new ItemStack(material, 1, (short)subid);
        LeatherArmorMeta mitem = (LeatherArmorMeta)item.getItemMeta();

        List<String> list = new ArrayList();
        list.add(lore);
        list.add(lore2);

        mitem.setLore(list);
        mitem.setDisplayName(displayname);
        mitem.setColor(color);
        item.setItemMeta(mitem);
        list.remove(lore);
        list.remove(lore2);
        return item;
    }

    public static void updateBoard(Scoreboard board, Player p) {
        if (p.isOnline()) {
            try {
                p.setScoreboard(board);
            }
            catch (IllegalStateException localIllegalStateException) {}
        }
    }
    public static ItemStack createItemEnch(Material material, int anzahl, int subid, String displayname, String lore, Enchantment enc, int level)
    {
        short neuesubid = (short)subid;
        ItemStack is = new ItemStack(material, anzahl, neuesubid);
        ItemMeta sm = is.getItemMeta();
        sm.spigot().setUnbreakable(true);
        sm.setLore(Arrays.asList(new String[] { lore }));
        sm.setDisplayName(displayname);
        sm.addEnchant(enc, level, false);
        is.setItemMeta(sm);

        return is;
    }

    public static void getPlayer(Player p) {}
}
