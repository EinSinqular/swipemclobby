package de.sinqular.lobbysystem.api;

import java.util.HashMap;

import de.sinqular.lobbysystem.LobbySystem;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionbarAPI {

    final static HashMap<String, Integer> Count = new HashMap<String,Integer>();

    public static void sendActionBar(Player player, String Nachricht) {

        final String NachrichtNeu = Nachricht.replace("_", " ");
        String s = ChatColor.translateAlternateColorCodes('&', NachrichtNeu);
        IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + s +
                "\"}");
        PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(bar);
    }

    public static void sendActionBarTime(final Player player, final String Nachricht,final Integer Zeit) {


        final String NachrichtNeu = Nachricht.replace("_", " ");

        if(!Count.containsKey(player.getName())){
            String s = ChatColor.translateAlternateColorCodes('&', NachrichtNeu);
            IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + s +
                    "\"}");
            PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
            ((CraftPlayer)player).getHandle().playerConnection.sendPacket(bar);
        }

        Bukkit.getScheduler().runTaskLater(LobbySystem
                .getLobbySystem(), new Runnable() {
            @Override
            public void run() {
                String s = ChatColor.translateAlternateColorCodes('&', NachrichtNeu);
                IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + s +
                        "\"}");
                PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
                ((CraftPlayer)player).getHandle().playerConnection.sendPacket(bar);

                if(!Count.containsKey(player.getName())){
                    Count.put(player.getName(),0);
                }
                int count = Count.get(player.getName());
                int newCount = count+20;
                Count.put(player.getName(), newCount);

                if(newCount < Zeit-20){
                    ActionbarAPI.wait(player,Nachricht,Zeit);
                }else{
                    Count.remove(player.getName());
                }
            }
        }, 10);
    }

    private static void wait(final Player player, final String Nachricht,final Integer Zeit){
        Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), new Runnable() {
            @Override
            public void run() {
                sendActionBarTime(player,Nachricht,Zeit);
            }
        }, 10);

    }

}