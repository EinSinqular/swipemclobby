package de.sinqular.lobbysystem.games.commands;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Cookie implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("cookies")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                User user = (User) LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
                if(strings.length == 0) {
                    p.sendMessage(LobbySystem.PREFIX + "Du hast §e" + user.getCookies() + " Cookies§7.");
                }
            }
        }
        return false;
    }
}
