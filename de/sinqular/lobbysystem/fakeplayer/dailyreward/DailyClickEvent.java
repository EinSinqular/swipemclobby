package de.sinqular.lobbysystem.fakeplayer.dailyreward;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.api.ItemAPI;
import de.sinqular.lobbysystem.display.DisplayScoreBoard;
import de.sinqular.lobbysystem.manager.SettingManager;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class DailyClickEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getInventory().getName().equals("§cRewards")) {
            e.setCancelled(true);
            if(!ItemAPI.onCheck(e)) {
                return;
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Normales Reward")) {
                User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                if(!user.getRewardTime()) {
                    int endingCoins = user.getCoins() + 1000;
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1, 1);
                    user.setRewardTime(true);
                    user.setCoins(endingCoins);
                    p.sendMessage(LobbySystem.REWARD_PREFIX + "§7Du hast deine §eTägliche Belohnung §aerhalten!");
                    if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                    } else {
                        DisplayScoreBoard.setScoreboard(p);
                    }
                } else if(user.getRewardTime()) {
                    p.closeInventory();
                    p.sendMessage(LobbySystem.REWARD_PREFIX + "§cDu hast dein Reward schon abgeholt");
                }
            } else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§6Premium Reward")) {
                if(p.hasPermission("lobby.premiumreward")) {
                    User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                    if(!user.getPremiumRewardTime()) {
                        int endingCoins = user.getCoins() + 2500;
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1 ,1);
                        user.setPremiumRewardTime(true);
                        p.sendMessage(LobbySystem.REWARD_PREFIX + "§7Du hast deinen §6Täglichen Premium Bonus §aerhalten!");
                        user.setCoins(endingCoins);
                        if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                        } else {
                            DisplayScoreBoard.setScoreboard(p);
                        }
                    } else {
                        p.closeInventory();
                        p.sendMessage(LobbySystem.REWARD_PREFIX + "§cDu benötigst Premium um diese Belohnung zu bekommen!");
                    }
                }
            }
        }
    }

}
