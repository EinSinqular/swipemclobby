package de.sinqular.lobbysystem.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;;

public class CMD_Test implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("test")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if(strings.length == 0) {
                }
            }
        }
        return false;
    }
}
