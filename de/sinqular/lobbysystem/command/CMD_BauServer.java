package de.sinqular.lobbysystem.command;

import de.sinqular.lobbysystem.api.CloudLobbyAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_BauServer implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("bauserver")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if(strings.length == 0) {
                    if(p.hasPermission("display.builder") || p.hasPermission("display.bauleitung")) {
                        CloudLobbyAPI.sendPlayerToServer(p, "BauServer-1");
                    }
                }
            }
        }
        return false;
    }
}
