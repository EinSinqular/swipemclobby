package de.sinqular.lobbysystem.fakeplayer.listener;

import java.util.HashMap;
import java.util.UUID;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.fakeplayer.PlayerManager;
import de.sinqular.lobbysystem.fakeplayer.packet.PacketReader;
import de.sinqular.lobbysystem.fakeplayer.players.PlayerSkin;
import de.sinqular.lobbysystem.manager.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;



public class FakeNPCCreateListener implements Listener {



    public static PlayerManager playerManager;
    public static HashMap<Player, Integer> ids = new HashMap<>();

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        PacketReader reader = new PacketReader(p);
        reader.inject();

        PlayerSkin skin = new PlayerSkin();


        String args[] = skin.getFromPlayer(p);
        String texture = args[0];
        String signature = args[1];

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "§6Daily-Reward");
        gameProfile.getProperties().put("textures", new Property("textures", texture, signature));
        playerManager = new PlayerManager(gameProfile);

        Location loc = LocationManager.getLocation(p, "npc");
        playerManager.spawn(loc, p);


        ids.put(p, PlayerManager.getEntityId());

        Bukkit.getScheduler().scheduleAsyncDelayedTask(LobbySystem.getLobbySystem(), () -> {

            playerManager.removeTabList(p);

        }, 20);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location loc = LocationManager.getLocation(p, "npc");
        if (p.getLocation().distance(loc) < 25) {
            if (ids.containsKey(p)) {
                playerManager.lookAtPlayer(p, ids.get(p));
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        ids.remove(e.getPlayer());
    }
}