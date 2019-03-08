package de.sinqular.lobbysystem.fakeplayer;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.DataWatcher;
import net.minecraft.server.v1_8_R3.MathHelper;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntity.PacketPlayOutEntityLook;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityHeadRotation;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityStatus;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;
import net.minecraft.server.v1_8_R3.WorldSettings.EnumGamemode;

public class PlayerManager {
    static int entityId;
    private GameProfile gameProfile;
    private DataWatcher dataWatcher;
    public Location location;


    public PlayerManager(GameProfile gameProfile) {
        entityId = (int)Math.ceil(Math.random() * 1000) + 2000;
        this.gameProfile = gameProfile;
        this.dataWatcher = new DataWatcher(null);
        byte status = 0;
        status = changeMask(status, 0, false);
        status = changeMask(status, 1, false);
        status = changeMask(status, 3, false);
        status = changeMask(status, 4, false);
        status = changeMask(status, 5, false);
        this.dataWatcher.a(0, status);
        this.dataWatcher.a(6, 20F);
        this.dataWatcher.a(10, (byte) 127);
    }

    private byte changeMask(byte bitMask, int bit, boolean state) {
        if (state)
            return bitMask |= 1 << bit;
        else
            return bitMask &= ~(1<< bit);
    }

    public void status(int status, Player p) {
        PacketPlayOutEntityStatus packet = new PacketPlayOutEntityStatus();
        set(packet, "a", entityId);
        set(packet, "b", (byte) status);
        sendPacket(packet, p);
    }

    public void spawn(Location location, Player p) {
        this.location = location;
        addtoTablist(p);
        PacketPlayOutNamedEntitySpawn playerSpawn = new PacketPlayOutNamedEntitySpawn();
        set(playerSpawn, "a", entityId);
        set(playerSpawn, "b", this.gameProfile.getId());
        set(playerSpawn, "c", toFixedPointNumber(location.getX()));
        set(playerSpawn, "d", toFixedPointNumber(location.getY()));
        set(playerSpawn, "e", toFixedPointNumber(location.getZ()));
        set(playerSpawn, "f", toAngle(location.getYaw()));
        set(playerSpawn, "g", toAngle(location.getPitch()));
        set(playerSpawn, "h", 0);
        set(playerSpawn, "i", this.dataWatcher);
        sendPacket(playerSpawn, p);
    }

    public void headRotation(float yaw,float pitch, Player p) {
        PacketPlayOutEntityLook packet = new PacketPlayOutEntityLook(getEntityId(), toAngle(yaw), toAngle(pitch) , true);
        PacketPlayOutEntityHeadRotation packetHead = new PacketPlayOutEntityHeadRotation();
        set(packetHead, "a", entityId);
        set(packetHead, "b", toAngle(yaw));


        sendPacket(packet, p);
        sendPacket(packetHead, p);
    }



    public void removeTabList(Player p) {
        PacketPlayOutPlayerInfo playerInfo = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER);
        set(playerInfo, "b", Arrays.asList(playerInfo.new PlayerInfoData(this.gameProfile, 0, null, null)));
        sendPacket(playerInfo, p);
    }

    public static int getEntityId() {
        return entityId;
    }
    public void lookAtPlayer(Player p, int id) {
        final double diffX = p.getLocation().getX() - this.location.getX();
        final double diffY = (p.getLocation().getY() + p.getEyeHeight() * 0.9D) - (this.location.getY() + 1.6F);
        final double diffZ = p.getLocation().getZ() -  this.location.getZ();
        double hypoXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
        final float f = (float)(MathHelper.b(diffZ, diffX) * 180.0 / 3.141592741012573) - 90.0f;
        final float f2 = (float)(-(MathHelper.b(diffY, hypoXZ) * 180.0 / 3.141592741012573));
        look(f, f2, p, id);
    }

    private void look(float yaw, float pitch, Player p, int id) {
        PacketPlayOutEntityHeadRotation headRotation = new PacketPlayOutEntityHeadRotation();
        set(headRotation, "a", id);
        set(headRotation, "b", toAngle(yaw));
        set(headRotation, "c", toAngle(pitch));
        sendPacket(headRotation, p);
        sendPacket(new PacketPlayOutEntityLook(id, toAngle(yaw), toAngle(pitch), true), p);
    }

    private byte toAngle(float value) {
        return (byte) ((int) (value * 256.0F / 360.0F));
    }

    private int toFixedPointNumber(double value) {
        return (int) Math.floor(value * 32D);
    }

    private void addtoTablist(Player p) {
        PacketPlayOutPlayerInfo playerInfo = new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER);
        set(playerInfo, "b", Arrays.asList(playerInfo.new PlayerInfoData(this.gameProfile, 0, EnumGamemode.NOT_SET, new ChatComponentText(this.gameProfile.getName()))));
        sendPacket(playerInfo, p);
    }

    private void sendPacket(Packet<?> packet, Player p) {
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(packet);
    }

    @SuppressWarnings("unused")
    private Object get(Object instance, String name) {
        return get(instance.getClass(), instance, name);
    }

    private Object get(Class<?> clazz, Object instance, String name) {
        try {
            java.lang.reflect.Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return field.get(instance);
        } catch (Exception e) {
        }
        return null;
    }

    public void set(Object instance, String name, Object value) {
        set(instance.getClass(), instance, name, value);
    }

    private void set(Class<?> clazz, Object instance, String name, Object value) {
        try {
            java.lang.reflect.Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (Exception e) {
        }
    }
}