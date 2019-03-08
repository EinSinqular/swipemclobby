package de.sinqular.lobbysystem.command;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.display.DisplayScoreBoard;
import de.sinqular.lobbysystem.manager.SettingManager;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_REMOVE_COINS implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("removecoins")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if(p.hasPermission("lobby.removecoins")) {
                    if(strings.length == 0) {
                        p.sendMessage(LobbySystem.COINS_PREFIX + "§7Benutze §e/removecoins <Spieler> <Anzahl>");
                    } else if(strings.length == 2) {
                        Player taPlayer = Bukkit.getPlayer(strings[0]);
                        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(taPlayer.getUniqueId().toString());
                        int amountcoins = user.getCoins() - Integer.parseInt(strings[1]);
                        user.setCoins(amountcoins);
                        int msgcoins = Integer.parseInt(strings[1]);
                        p.sendMessage(LobbySystem.COINS_PREFIX + "§7Du hast §a" + taPlayer.getDisplayName() + " §e" + strings[1] + " Coins §centfernt§8.");
                        if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {

                        } else if(user.getUserSettings().contains(SettingManager.displayScoreboard)) {
                            DisplayScoreBoard.setScoreboard(taPlayer);
                        }
                    } else {
                        p.sendMessage(LobbySystem.COINS_PREFIX + "§7Benutze §e/removecoins <Spieler> <Anzahl>");
                    }
                }
            }
        }
        return false;
    }
}
