package de.sinqular.lobbysystem.command;

import de.sinqular.lobbysystem.LobbySystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Nick implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("nick")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if(p.hasPermission("nick.nick")) {
                    if(strings.length == 0) {
                        p.sendMessage(LobbySystem.NICK_PREFIX + "Du musst dich in einer Runde befinden!");
                    } else {
                        p.sendMessage("§cNutze nur Nick");
                    }
                } else {
                    p.sendMessage(LobbySystem.NICK_NOPERM);
                }
            }
        }
        return false;
    }
}
