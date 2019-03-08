package de.sinqular.lobbysystem.listener.world;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class WorldJumpPlateListener implements Listener {

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent e) {
        Player p = e.getPlayer();

        if(p.getLocation().getBlock().getType() == Material.GOLD_PLATE) {
            if(p.getLocation().subtract(0D, 1D, 0D).getBlock().getType() == Material.COAL_BLOCK) {


                Vector v = p.getLocation().getDirection().multiply(1.5D).setY(1D);
                p.setVelocity(v);

                p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 2);
                p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 1F, 1F);

                p.setFallDistance(-999F);


            }
        }
    }

}
