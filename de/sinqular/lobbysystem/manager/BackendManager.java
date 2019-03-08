package de.sinqular.lobbysystem.manager;

import de.sinqular.lobbysystem.LobbySystem;
import de.sinqular.lobbysystem.mysql.Callback;
import de.sinqular.lobbysystem.mysql.PreparedStatementBuilder;
import de.sinqular.lobbysystem.mysql.entity.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BackendManager {

    private final LobbySystem lobbySystem;

    public BackendManager(LobbySystem lobbySystem) {
        this.lobbySystem = lobbySystem;

    }

    public boolean isUserExist(final String uuid) {
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        lock.lock();
        final boolean[] exist = new boolean[1];
        lobbySystem.getMySQLManager().query(new PreparedStatementBuilder("SELECT * FROM user WHERE uuid=?", lobbySystem.getMySQLManager()).bindString(uuid).build(), new Callback<ResultSet>() {
            @Override
            public void onSuccess(ResultSet result) {
                try {
                    if(result.next()) {
                        exist[0] = result.getString("uuid") != null;

                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable cause) {
                cause.printStackTrace();
            }
        }, lock, condition);
        try {
            condition.await();
        } catch (InterruptedException x) {
            x.printStackTrace();
        } finally {
            lock.unlock();
        }
        return exist[0];

    }
    public User getUser(final String uuid) {
        Player p = Bukkit.getPlayer(UUID.fromString(uuid));
        if(p.hasMetadata("userData")) {
            return (User) p.getMetadata("userData").get(0).value();

        }
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        lock.lock();
        final User user = new User();
        if(!isUserExist(uuid)) {
            lobbySystem.getMySQLManager().update(new PreparedStatementBuilder("INSERT INTO user(uuid, name, coins, gadgets, isNicked, isPlayerHiding, termsAccepted, isInSilentHub, rewardTime, premiumRewardTime, chests, premiumChests, tickets, lastIP, lastSeen, playerRank, getActiveGadGet, userSettings, cookies, userSecrets) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", lobbySystem.getMySQLManager()).bindString(uuid).bindString(p.getName()).bindInt(0).bindString("").bindBoolean(false).bindBoolean(false).bindBoolean(false).bindBoolean(false).bindBoolean(false).bindBoolean(false).bindInt(0).bindInt(0).bindInt(0).bindString(p.getAddress().toString().split(":")[0]).bindInt((int)System.currentTimeMillis()).bindString("Spieler").bindString("").bindString("").bindInt(0).bindString("").build());
        }
        lobbySystem.getMySQLManager().query(new PreparedStatementBuilder("SELECT * FROM user WHERE uuid=?", lobbySystem.getMySQLManager()).bindString(uuid).build(), new Callback<ResultSet>() {
            @Override
            public void onSuccess(ResultSet result) {
                try {
                    if(result.next()) {
                        user.setUuid(uuid);
                        user.setName(result.getString("name"));
                        user.setCoins(result.getInt("coins"));
                        ArrayList<String> gadgets = new ArrayList<>();
                        String[] gadgetString = result.getString("gadgets").split(",");
                        for(String gadget : gadgetString) {
                            gadgets.add(gadget);

                        }
                        user.setGadgets(gadgets);
                        user.setNicked(result.getBoolean("isNicked"));
                        user.setPlayerHiding(result.getBoolean("isPlayerHiding"));
                        user.setTermsAccepted(result.getBoolean("termsAccepted"));
                        user.setInSilentHub(result.getBoolean("isInSilentHub"));
                        user.setRewardTime(result.getBoolean("rewardTime"));
                        user.setPremiumRewardTime(result.getBoolean("premiumRewardTime"));
                        user.setChests(result.getInt("chests"));
                        user.setPremiumChest(result.getInt("premiumChests"));
                        user.setTickets(result.getInt("tickets"));
                        user.setLastIP(result.getString("lastIP"));
                        user.setLastSeen(result.getInt("lastSeen"));
                        user.setPlayerRank(result.getString("playerRank"));
                        ArrayList<String> activeGadgets = new ArrayList<>();
                        String[] activeGadgetString = result.getString("getActiveGadGet").split(",");
                        for(String gadget : activeGadgetString) {
                            gadgets.add(gadget);

                        }
                        user.setGetActiveGadGet(activeGadgets);
                       ArrayList<String> activeUserSettings = new ArrayList<>();
                       String[] activeUserSettingString = result.getString("userSettings").split(",");
                       for(String setting : activeUserSettingString) {
                           activeUserSettings.add(setting);
                       }
                       user.setUserSettings(activeUserSettings);
                       user.setCookies(result.getInt("cookies"));
                        ArrayList<String> activeUserSecrets = new ArrayList<>();
                        String[] activeUSerSecretString = result.getString("userSecrets").split(",");
                        for(String secret : activeUSerSecretString) {
                            activeUserSecrets.add(secret);
                        }
                        user.setUserSecrets(activeUserSecrets);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void onFailure(Throwable cause) {
                cause.printStackTrace();
            }
        }, lock, condition);
        try {
            condition.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
        lobbySystem.setMetadata(p, "userData", user);
        return user;
    }
    public void updateUser(final String uuid) {
        Player p = Bukkit.getPlayer(UUID.fromString(uuid));
        User user = (User) p.getMetadata("userData").get(0).value();
        String gadgets;
        String getActiveGadtets;
        String getActiveUserSetting;
        String getActiveUserSecret;
        {
            StringBuilder builder = new StringBuilder();
            user.getGadgets().forEach(gadget -> {
                builder.append(gadget).append(",");
            });
            gadgets = builder.toString();
        }
        {
            StringBuilder builder = new StringBuilder();
            user.getGetActiveGadGet().forEach(activegadget -> {
                builder.append(activegadget).append(",");
            });
            getActiveGadtets = builder.toString();
        }
        {
            StringBuilder builder = new StringBuilder();
            user.getUserSettings().forEach(activesetting -> {
                builder.append(activesetting).append(",");
            });
            getActiveUserSetting = builder.toString();
        }
        {
            StringBuilder builder = new StringBuilder();
            user.getUserSecrets().forEach(activesecret ->{
                builder.append(activesecret).append(",");
            });
            getActiveUserSecret = builder.toString();
        }


        lobbySystem.getMySQLManager().update(new PreparedStatementBuilder("UPDATE user SET name=?, coins=?, gadgets=?, isNicked=?, isPlayerHiding=?, termsAccepted=?, isInSilentHub=?, rewardTime=?, premiumRewardTime=?, chests=?, premiumChests=?, tickets=?, lastIP=?, lastSeen=?, playerRank=?, getActiveGadGet=?, userSettings=?, cookies=?, userSecrets=? WHERE uuid=?", lobbySystem.getMySQLManager()).bindString(user.getName()).bindInt(user.getCoins()).bindString(gadgets).bindBoolean(user.isNicked()).bindBoolean(user.isPlayerHiding()).bindBoolean(user.isTermsAccepted()).bindBoolean(user.isInSilentHub()).bindBoolean(user.getRewardTime()).bindBoolean(user.getPremiumRewardTime()).bindInt(user.getChests()).bindInt(user.getPremiumChest()).bindInt(user.getTickets()).bindString(user.getLastIP()).bindInt((int) user.getLastSeen()).bindString(user.getPlayerRank()).bindString(getActiveGadtets).bindString(getActiveUserSetting).bindInt(user.getCookies()).bindString(getActiveUserSecret).bindString(uuid).build());


    }

}


