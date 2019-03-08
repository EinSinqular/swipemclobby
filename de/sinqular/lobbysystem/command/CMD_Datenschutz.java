package de.sinqular.lobbysystem.command;

import de.sinqular.lobbysystem.LobbySystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Datenschutz implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("datenschutz")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if(strings.length == 0) {
                    p.sendMessage(LobbySystem.PREFIX + "§7Die §eDatenschutzerklärung §7findest du unter §bhttps://swipemc.net/datenschutz");

                } else {
                    p.sendMessage(LobbySystem.PREFIX + "§cNutze nur /Datenschutz");
                }
            }
        }
        return false;
    }
}
