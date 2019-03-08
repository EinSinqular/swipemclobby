package de.sinqular.lobbysystem.listener.playerevents;

import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class PlayerDoubleJumpListener implements Listener {

    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode() == GameMode.ADVENTURE)
            if(p.hasPermission("lobby.doublejump")) {
                e.setCancelled(true);
                p.setAllowFlight(false);
                p.setFlying(false);
                p.setVelocity(p.getLocation().getDirection().multiply(1.5D).add(new Vector(0.0D, 0.7D, 0.0D)));
                p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
                p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 1, 1);
            }

    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if(p.getGameMode().equals(GameMode.ADVENTURE)) {
            if(p.hasPermission("lobby.doublejump")) {
                if(p.getLocation().add(0.0D, -1.0D, 0.0D).getBlock().getType() != Material.AIR) {
                    p.setAllowFlight(true);
                }
            }
        }
    }

}
