package de.sinqular.lobbysystem.command;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.manager.HoloLocationManager;
import de.sinqular.lobbysystem.manager.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_Location implements CommandExecutor {



    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("set")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if(p.hasPermission("lobby.setloc")) {
                    if(strings.length == 0) {
                        p.sendMessage(LobbySystem.PREFIX + "§7Derzeit Funktioniert nur /set <Aura | FFA | BedWars | TTT | Lotterie | DailyReward | SkyWars | CityBuild | Spawn | Community>!");
                    } else if(strings[0].equalsIgnoreCase("Aura")) {
                        LocationManager.setLocation(p, "aura");
                        p.sendMessage("§7Du hast §eAura §ageetzt§7.");
                    } else if(strings[0].equalsIgnoreCase("FFA")) {
                        LocationManager.setLocation(p, "ffa");
                        p.sendMessage("§7Du hast §eFFA §ageetzt§7.");
                    } else if(strings[0].equalsIgnoreCase("BedWars")) {
                        LocationManager.setLocation(p, "bedwars");
                        p.sendMessage("§7Du hast §eBedWars §ageetzt§7.");
                    } else if(strings[0].equalsIgnoreCase("TTT")) {
                        LocationManager.setLocation(p, "ttt");
                        p.sendMessage("§7Du hast §eTTT §ageetzt§7.");
                    } else if(strings[0].equalsIgnoreCase("Lotterie")) {
                        LocationManager.setLocation(p, "lotterie");
                        p.sendMessage("§7Du hast §eLotterie §ageetzt§7.");
                    } else if(strings[0].equalsIgnoreCase("DailyReward")) {
                        LocationManager.setLocation(p, "dailyreward");
                        p.sendMessage("§7Du hast §eDailyReward §ageetzt§7.");
                    } else if(strings[0].equalsIgnoreCase("SkyWars")) {
                        LocationManager.setLocation(p, "skywars");
                        p.sendMessage("§7Du hast §eSkyWars §ageetzt§7.");
                    } else if(strings[0].equalsIgnoreCase("CityBuild")) {
                        LocationManager.setLocation(p, "citybuild");
                        p.sendMessage("§7Du hast §eCityBuild §ageetzt§7.");
                    } else if(strings[0].equalsIgnoreCase("Spawn")) {
                        LocationManager.setLocation(p, "spawn");
                        p.sendMessage("§7Du hast §eSpawn §ageetzt§7.");
                    } else if(strings[0].equalsIgnoreCase("Community")) {
                        LocationManager.setLocation(p, "community");
                        p.sendMessage("§7Du hast §eCommunity §ageetzt§7.");
                    } else if(strings[0].equalsIgnoreCase("npc")) {
                        LocationManager.setLocation(p, "npc");
                        p.sendMessage("§7Du hast §eFakePlayer §ageetzt§7.");
                    } else if(strings[0].equalsIgnoreCase("lotterieholo")) {
                        HoloLocationManager.setLocation(p, "lotterieholo");
                        p.sendMessage("§7Du hast §eLotterie Holo §ageetzt§7.");
                    } else if(strings[0].equalsIgnoreCase("lotterieholo2")) {
                        HoloLocationManager.setLocation(p, "lotterieholo2");
                        p.sendMessage("§7Du hast §eLotterie Holo §ageetzt§7.");
                    }


                } else {
                    p.sendMessage(LobbySystem.NO_PERMISSION);
                }
            }
        }
        return false;
    }
}
