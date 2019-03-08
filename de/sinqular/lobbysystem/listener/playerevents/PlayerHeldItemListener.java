package de.sinqular.lobbysystem.listener.playerevents;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.manager.SettingManager;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class PlayerHeldItemListener implements Listener {

    @EventHandler
    public void onHeld(PlayerItemHeldEvent e) {
        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(e.getPlayer().getUniqueId().toString());

        if(user.getUserSettings().contains(SettingManager.chatSounds)) {

        } else {
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.NOTE_STICKS, 1, 2);
        }

    }
}
