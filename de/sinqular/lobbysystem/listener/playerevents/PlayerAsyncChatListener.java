package de.sinqular.lobbysystem.listener.playerevents;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.manager.SettingManager;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerAsyncChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        User user = (User) LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());

       if(!user.getUserSettings().contains(SettingManager.chatSounds)) {
           p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1,2);
       } else if(user.getUserSettings().contains(SettingManager.chatSounds)) {
           return;
       }



    }

}
