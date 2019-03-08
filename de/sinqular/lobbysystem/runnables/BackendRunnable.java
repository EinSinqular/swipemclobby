package de.sinqular.lobbysystem.runnables;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Bukkit;

public class BackendRunnable {



    public static void startBackendTimer() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(LobbySystem.getLobbySystem(), new Runnable() {
            @Override
            public void run() {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    LobbySystem.getLobbySystem().setBackendUpdating(true);
                    LobbySystem.getLobbySystem().getBackendManager().updateUser(player.getUniqueId().toString());
                    LobbySystem.getLobbySystem().setBackendUpdating(false);
                    User user = LobbySystem.getLobbySystem().getBackendManager().getUser(player.getUniqueId().toString());
                    user.setLastSeen(System.currentTimeMillis());
                    System.out.println("MySQL Upload erfolgreich");
                });
            }
        },100, 100);
    }

}

//        },18000, 18000);
