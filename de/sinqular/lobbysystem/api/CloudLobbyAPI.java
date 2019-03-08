package de.sinqular.lobbysystem.api;

import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.api.player.PlayerExecutorBridge;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.server.info.ServerInfo;
import de.sinqular.lobbysystem.LobbySystem;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

public class CloudLobbyAPI {

    public static void quickJoin(final Player p, final String serverGroup) {
        Collection<ServerInfo> servers = CloudAPI.getInstance().getServers(serverGroup);
        ServerInfo serverInfo = null;
        for(ServerInfo gameServer : servers) {
            if(!gameServer.isIngame() || gameServer.getOnlineCount() < gameServer.getMaxPlayers()) {
                if(serverInfo != null) {
                    if(gameServer.getOnlineCount() > serverInfo.getOnlineCount()) {
                        serverInfo = gameServer;
                    }
                } else {
                    serverInfo = gameServer;
                }
            }
        }
        if(serverInfo != null) {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);

            try {
                out.writeUTF("Connect");
                out.writeUTF(serverInfo.getServiceId().toString().split("#")[0]);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            p.sendPluginMessage(LobbySystem.getLobbySystem(), "BungeeCord", b.toByteArray());
        } else {
            p.sendMessage(LobbySystem.PREFIX + "§cEs konnte kein freier Server gefunden werden");
        }
    }
    public static String getPlayerPermissionGroup(UUID uuid) {
        String groupname = CloudAPI.getInstance().getOfflinePlayer(uuid).getPermissionEntity().getHighestPermissionGroup(CloudAPI.getInstance().getPermissionPool()).getName();

        return groupname;
    }

    public static void sendPlayerToServer(Player player, String Server) {
        CloudPlayer cloudPlayer = CloudAPI.getInstance().getOnlinePlayer(player.getUniqueId());
        PlayerExecutorBridge playerExecutorBridge = new PlayerExecutorBridge();
        playerExecutorBridge.sendPlayer(cloudPlayer, Server);
    }

    public static void getServerFromPlayer(String server) {
        CloudAPI.getInstance().getServerId().equalsIgnoreCase(server);

    }

}