package de.sinqular.lobbysystem.fakeplayer.packet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.fakeplayer.dailyreward.DailyInventory;
import de.sinqular.lobbysystem.fakeplayer.listener.FakeNPCCreateListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_8_R3.Packet;

public class PacketReader {


    Player player;
    Channel channel;

    public PacketReader(Player player) {
        this.player = player;
    }

    public void inject(){
        CraftPlayer cPlayer = (CraftPlayer)this.player;
        channel = cPlayer.getHandle().playerConnection.networkManager.channel;
        channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<Packet<?>>() {@Override protected void decode(ChannelHandlerContext arg0, Packet<?> packet,List<Object> arg2) throws Exception {arg2.add(packet);readPacket(packet);}});
    }

    public void uninject(){
        if(channel.pipeline().get("PacketInjector") != null){
            channel.pipeline().remove("PacketInjector");
        }
    }

    private ArrayList<Player> cooldown = new ArrayList<>();
    @SuppressWarnings("deprecation")
    public void readPacket(Packet<?> packet){
        if(packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity")) {
            if (FakeNPCCreateListener.ids.get(player) == null) {
                return;
            }
            int id = (Integer)getValue(packet, "a");
            if (!cooldown.contains(player)) {
                if (FakeNPCCreateListener.ids.get(player) == id)  {
                    if(getValue(packet, "action").toString().equalsIgnoreCase("INTERACT") | getValue(packet, "action").toString().equalsIgnoreCase("ATTACK")){
                        player.sendMessage(LobbySystem.REWARD_PREFIX + "§cDas Daily-Reward ist in Wartungen");
                        DailyInventory.DailyRewardInventory(player);

                        cooldown.add(player);

                        Bukkit.getScheduler().scheduleAsyncDelayedTask(LobbySystem.getLobbySystem(), () -> {
                            cooldown.remove(player);
                        }, 20*5);
                    }
                }
            }
        }
    }

    private ItemStack createItem(Material material, int amount, int subid, String name) {
        ItemStack item = new ItemStack(material, amount, (short) subid);
        ItemMeta iMeta = item.getItemMeta();
        iMeta.setDisplayName(name);
        iMeta.spigot().setUnbreakable(true);
        iMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        item.setItemMeta(iMeta);
        return item;
    }

    public static ItemStack item(Material material, Integer amount, byte subid, String name) {
        ItemStack item = new ItemStack(material, amount, (byte) subid);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName(name);
        item.setItemMeta(m);
        return item;
    }

    public void setValue(Object obj,String name,Object value){
        try{
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(obj, value);
        }catch(Exception e){}
    }

    public Object getValue(Object obj,String name){
        try{
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(obj);
        }catch(Exception e){}
        return null;
    }
}

