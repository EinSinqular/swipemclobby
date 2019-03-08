package de.sinqular.lobbysystem.games.listener;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.games.GameManager;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class CookieClicker implements Listener {

    @EventHandler
    public void CookieClickListener(PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        ItemStack clicked = p.getItemInHand();
        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if(e.getClickedBlock().getType() == Material.GOLD_BLOCK) {
                GameManager.cookieClickerFunction(e.getClickedBlock().getLocation(), p, 1);
                p.playEffect(e.getClickedBlock().getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
            }
        }
    }
}
