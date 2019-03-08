package de.sinqular.lobbysystem.command;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.manager.LocationManager;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Spawn implements CommandExecutor {

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("spawn")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if(strings.length == 0) {
                    p.sendMessage(LobbySystem.PREFIX + "§7Du wurdest zum §eSpawn §ateleportiert§8.");
                    p.teleport(LocationManager.getLocation(p.getPlayer(), "spawn"));
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1 ,1);
                    p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1 );
                } else {
                    p.sendMessage("§cNutze /Spawn");
                }
            }
        }
        return false;
    }
}
