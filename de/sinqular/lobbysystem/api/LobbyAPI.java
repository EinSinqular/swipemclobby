package de.sinqular.lobbysystem.api;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.display.DisplayScoreBoard;
import de.sinqular.lobbysystem.manager.SettingManager;
import de.sinqular.lobbysystem.mysql.PreparedStatementBuilder;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.util.Calendar;


public class LobbyAPI {

    private static float step = 0.0F;
    private static boolean reversed = false;

    public long getDayEnd() {

        Calendar c = Calendar.getInstance();

        c.setTimeInMillis(System.currentTimeMillis());

        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.DAY_OF_MONTH, 1);

        return c.getTimeInMillis();
    }


    public static void runHelix(Location loc, Player p, Effect effect) {

        double radius = 5;

        for (double y = 5; y >= 0; y -= 0.007) {
            radius = y / 3;
            double x = radius * Math.cos(3 * y);
            double z = radius * Math.sin(3 * y);

            double y2 = 5 - y;

            Location loc2 = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y2, loc.getZ() + z);
            p.getWorld().playEffect(loc2.add(0, 0, 0), effect, 1);
        }

        for (double y = 5; y >= 0; y -= 0.007) {
            radius = y / 3;
            double x = -(radius * Math.cos(3 * y));
            double z = -(radius * Math.sin(3 * y));

            double y2 = 5 - y;

            Location loc2 = new Location(loc.getWorld(), loc.getX() + x, loc.getY() + y2, loc.getZ() + z);

            p.getWorld().playEffect(loc2.add(0, 0, 0), effect, 1);

        }

    }


    public static void insertRank(Player p) {
        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
        if (CloudLobbyAPI.getPlayerPermissionGroup(p.getUniqueId()).equals("Serverinhaber")) {
            user.setPlayerRank("Serverinhaber");
        } else if (CloudLobbyAPI.getPlayerPermissionGroup(p.getUniqueId()).equals("Admin")) {
            user.setPlayerRank("Administrator");
        } else if (CloudLobbyAPI.getPlayerPermissionGroup(p.getUniqueId()).equals("SrModerator")) {
            user.setPlayerRank("SrModerator");
        } else if (CloudLobbyAPI.getPlayerPermissionGroup(p.getUniqueId()).equals("Moderator")) {
            user.setPlayerRank("SrModerator");
        } else if (CloudLobbyAPI.getPlayerPermissionGroup(p.getUniqueId()).equals("SrSupporter")) {
            user.setPlayerRank("SrSupporter");
        } else if (CloudLobbyAPI.getPlayerPermissionGroup(p.getUniqueId()).equals("Supporter")) {
            user.setPlayerRank("Supporter");
        } else if (CloudLobbyAPI.getPlayerPermissionGroup(p.getUniqueId()).equals("Bauleitung")) {
            user.setPlayerRank("BauLeitung");
        } else if (CloudLobbyAPI.getPlayerPermissionGroup(p.getUniqueId()).equals("Builder")) {
            user.setPlayerRank("Builder");
        } else if (CloudLobbyAPI.getPlayerPermissionGroup(p.getUniqueId()).equals("YouTuber")) {
            user.setPlayerRank("YouTuber");
        } else if (CloudLobbyAPI.getPlayerPermissionGroup(p.getUniqueId()).equals("GoldPlus")) {
            user.setPlayerRank("Gold+");
        } else if (CloudLobbyAPI.getPlayerPermissionGroup(p.getUniqueId()).equals("Diamond")) {
            user.setPlayerRank("Diamond");
        } else if (CloudLobbyAPI.getPlayerPermissionGroup(p.getUniqueId()).equals("Emerald")) {
            user.setPlayerRank("Emerald");
        } else if (CloudLobbyAPI.getPlayerPermissionGroup(p.getUniqueId()).equals("Gold")) {
            user.setPlayerRank("Gold");
        } else {
            user.setPlayerRank("Spieler");
        }
    }

    public static void spawnHologram(Player p, Location loc, String[] text) {
        HoloAPI holo = new HoloAPI(text, loc);
        holo.showPlayer(p);
    }

    public static void resetRewards() {
        LobbySystem.getLobbySystem().getMySQLManager().update(new PreparedStatementBuilder("SELECT rewardTime FROM user WHERE UUID=?", LobbySystem.getLobbySystem().getMySQLManager()).build());
        LobbySystem.getLobbySystem().getMySQLManager().update(new PreparedStatementBuilder("SELECT premiumRewardTime FROM user WHERE UUID=?", LobbySystem.getLobbySystem().getMySQLManager()).build());
        for (OfflinePlayer allOfflinePlayers : Bukkit.getOfflinePlayers()) {
            User ops = LobbySystem.getLobbySystem().getBackendManager().getUser(allOfflinePlayers.getUniqueId().toString());
            ops.setPremiumRewardTime(false);
            ops.setRewardTime(false);
        }
    }

    public static void buyItem(int preis, Player player, String headname, String gadget) {
        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(player.getUniqueId().toString());
        if(user.getCoins() <= preis - 1) {
            player.sendMessage(LobbySystem.PREFIX + "§cDu hast zu wenig Coins!");
            player.closeInventory();
            player.playSound(player.getLocation(), Sound.CHEST_CLOSE, 1,1);
        } else if(user.getCoins() >= preis) {
            player.sendMessage(LobbySystem.PREFIX + "§7Du hast den Kopf von §e" + headname + " §agekauft§7.");
            user.getGadgets().add(gadget);
            player.closeInventory();
            int c = user.getCoins() - preis;
            user.setCoins(c);
            if(!user.getUserSettings().contains(SettingManager.displayScoreboard)) {
            } else {
                DisplayScoreBoard.setScoreboard(player);
            }
        }
    }
}

