package de.sinqular.lobbysystem.games;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.api.ActionbarAPI;
import de.sinqular.lobbysystem.api.ItemAPI;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class GameManager {

    public static void cookieClickerFunction(Location blockLocation, Player p, int addingCoinsAtClick) {
        User user = LobbySystem.getLobbySystem().getBackendManager().getUser(p.getUniqueId().toString());
        int cookieAmunt = user.getCookies() + addingCoinsAtClick;
        user.setCookies(cookieAmunt);
        ActionbarAPI.sendActionBarTime(p.getPlayer(), "§7Du hast §e1 Cookie §abekommen§7.", 20);
        blockLocation.getWorld().dropItem(blockLocation, ItemAPI.createItem(Material.COOKIE, 0, null));

    }


}
