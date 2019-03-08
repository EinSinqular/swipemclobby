package de.sinqular.lobbysystem.secrets;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Sound;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class SignClickListener implements Listener {

    @EventHandler
    public void onSignClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        User user = (User) LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
        String secretline = "[Secret]";
        if((e.getAction() == Action.RIGHT_CLICK_BLOCK && ((e.getClickedBlock().getState() instanceof Sign)))) {
            Sign sign = (Sign)e.getClickedBlock().getState();
            if(sign.getLine(0).equals(secretline)) {
                if(sign.getLine(2).equals("Test")) {
                    if(!user.getUserSecrets().contains(SecretManager.testSecret)) {
                        user.getUserSecrets().add(SecretManager.testSecret);
                        p.sendMessage(LobbySystem.PREFIX + "§7Du hast das §aTest Secret gefunden§7.");
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                    } else if(user.getUserSecrets().contains(SecretManager.testSecret)) {
                        p.sendMessage(LobbySystem.PREFIX + "§cDu hast dieses Secret bereits gefunden§7.");
                    }
                }
            }
        } else {
            e.setCancelled(true);
        }
    }
}
