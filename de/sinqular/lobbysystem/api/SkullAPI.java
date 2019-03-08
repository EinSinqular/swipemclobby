package de.sinqular.lobbysystem.api;

import java.util.Arrays;
import java.util.UUID;

import de.sinqular.lobbysystem.profile.GameProfilBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullAPI {

    static String nullString = " ";


    private static Class<?> skullMetaClass;
    public static ItemStack getFriendHead(String uuidtostring, String name, String ... lore){
        String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        Integer.parseInt(version.replaceAll("[^0-9]", ""));
        try {
            skullMetaClass = Class.forName("org.bukkit.craftbukkit." + version + ".inventory.CraftMetaSkull");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        final ItemStack head = new ItemStack(Material.SKULL_ITEM,1,(short) 3);
        final SkullMeta headm = (SkullMeta) head.getItemMeta();
        //headm.setOwner(SQL_Friend.getName(s));
        headm.setDisplayName(name);
        headm.setLore(Arrays.asList(nullString));
        headm.setLore(Arrays.asList(lore));
        try {
            java.lang.reflect.Field profileField = skullMetaClass.getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headm, GameProfilBuilder.fetch(UUID.fromString(uuidtostring)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        head.setItemMeta(headm);
        return head;
    }

}