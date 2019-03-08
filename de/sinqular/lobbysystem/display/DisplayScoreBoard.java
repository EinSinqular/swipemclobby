package de.sinqular.lobbysystem.display;

import de.dytanic.cloudnet.api.CloudAPI;
import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class DisplayScoreBoard {

    public static void setScoreboard(Player p) {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        Scoreboard board = sm.getNewScoreboard();
        Objective o = board.registerNewObjective("board", "lobbyscoreboard");

        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());

        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        o.setDisplayName("§8• §7● §bSwipeMC.net §7● §8•");
        o.getScore("§a").setScore(14);
        o.getScore("§8•§7● §7Profil:").setScore(13);
        o.getScore("§7» §e" + p.getDisplayName()).setScore(12);
        o.getScore("§1").setScore(11);
        o.getScore("§8•§7● §7Rang:").setScore(10);
        if(p.hasPermission("display.serverinhaber")) {
            o.getScore("§7» §4Serverinhaber").setScore(9);
        } else if(p.hasPermission("display.admin")) {
            o.getScore("§7» §cAdmin").setScore(9);
        } else if(p.hasPermission("display.dev")) {
            o.getScore("§7» §bDeveloper").setScore(9);
        } else if(p.hasPermission("display.srmod")) {
            o.getScore("§7» §9SrModerator").setScore(9);
        } else if(p.hasPermission("display.mod")) {
            o.getScore("§7» §9Moderator").setScore(9);
        } else if(p.hasPermission("display.srsupporter")) {
            o.getScore("§7» §eSrSupporter").setScore(9);
        } else if(p.hasPermission("display.supporter")) {
            o.getScore("§7» §eSupporter").setScore(9);
        } else if(p.hasPermission("display.bauleitung")) {
            o.getScore("§7» §2Bau-Leitung").setScore(9);
        } else if(p.hasPermission("display.builder")) {
            o.getScore("§7» §2Builder").setScore(9);
        } else if(p.hasPermission("display.youtuber")) {
            o.getScore("§7» §5YouTuber").setScore(9);
        } else if(p.hasPermission("display.goldplus")) {
            o.getScore("§7» §6Gold §e+").setScore(9);
        } else if(p.hasPermission("display.diamond")) {
            o.getScore("§7» §3Diamond").setScore(9);
        } else if(p.hasPermission("display.emerald")) {
            o.getScore("§7» §aEmerald").setScore(9);
        } else if(p.hasPermission("display.gold")) {
            o.getScore("§7» §6Gold").setScore(9);
        } else {
            o.getScore("§7» Spieler").setScore(9);
        }
        o.getScore("§5").setScore(8);
        o.getScore("§8•§7● §7Coins:").setScore(7);
        o.getScore("§7» §e" + user.getCoins()).setScore(6);
        o.getScore("§9").setScore(5);
        o.getScore("§8•§7● §7Lobby:").setScore(4);
        o.getScore("§7» §b" + CloudAPI.getInstance().getServerId()).setScore(3);
        o.getScore("§8").setScore(2);
        o.getScore("§8•§7● §7TeamSpeak:").setScore(1);
        o.getScore("§7» §cSwipeMC.net").setScore(0);

        p.setScoreboard(board);
    }


}
