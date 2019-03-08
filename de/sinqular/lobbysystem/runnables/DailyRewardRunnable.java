package de.sinqular.lobbysystem.runnables;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.api.LobbyAPI;
import de.sinqular.lobbysystem.mysql.PreparedStatementBuilder;
import org.bukkit.Bukkit;

import java.util.Calendar;

public class DailyRewardRunnable {

    public static void startTimer() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(LobbySystem.getLobbySystem(), ()->{
            Calendar calendar = Calendar.getInstance();
            if(calendar.getTime().getHours() == 0 && calendar.getTime().getMinutes() == 0) {
                LobbySystem.getLobbySystem().getMySQLManager().update(new PreparedStatementBuilder("SELECT * FROM user WHERE uuid=?", LobbySystem.getLobbySystem().getMySQLManager()).build());
                LobbyAPI.resetRewards();
            }
        }, 1200, 1200);
    }

}
