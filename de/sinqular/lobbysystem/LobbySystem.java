package de.sinqular.lobbysystem;

import de.sinqular.lobbysystem.fakeplayer.dailyreward.DailyClickEvent;
import de.sinqular.lobbysystem.fakeplayer.listener.FakeNPCCreateListener;
import de.sinqular.lobbysystem.games.commands.CMD_Cookie;
import de.sinqular.lobbysystem.games.listener.CookieClicker;
import de.sinqular.lobbysystem.listener.PlayerConnectionListener;
import de.sinqular.lobbysystem.listener.other.DatenschutzListener;
import de.sinqular.lobbysystem.listener.world.WorldJumpPlateListener;
import de.sinqular.lobbysystem.listener.world.WorldMobListener;
import de.sinqular.lobbysystem.listener.world.WorldWeatherChangeListener;
import de.sinqular.lobbysystem.lotterie.chestopening.Animator;
import de.sinqular.lobbysystem.lotterie.coins.RandomCoins;
import de.sinqular.lobbysystem.lotterie.command.LotterieCommand;
import de.sinqular.lobbysystem.lotterie.listener.LotterieClickListener;
import de.sinqular.lobbysystem.manager.BackendManager;
import de.sinqular.lobbysystem.mysql.MySQLManager;
import de.sinqular.lobbysystem.mysql.PreparedStatementBuilder;
import de.sinqular.lobbysystem.runnables.BackendRunnable;
import de.sinqular.lobbysystem.runnables.DailyRewardRunnable;
import de.sinqular.lobbysystem.runnables.ParticlesRunnable;
import de.sinqular.lobbysystem.secrets.SignClickListener;
import de.sinqular.lobbysystem.command.*;
import de.sinqular.lobbysystem.listener.playerevents.*;
import de.sinqular.lobbysystem.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LobbySystem extends JavaPlugin {

    private static LobbySystem lobbySystem;

    private MySQLManager mySQLManager;
    private BackendManager backendManager;
    private RandomCoins randomCoins = new RandomCoins();


    public static Animator animator;


    private ArrayList<String> playerHider = new ArrayList<>();
    private ArrayList<Player> editPlayer = new ArrayList<>();
    private ArrayList<String> fireworkdRiders = new ArrayList<>();

    private HashMap<ItemStack, String> headCache = new HashMap<>();

    public static HashMap<Player, Integer> LOTTERIE_PLAYER_INTERACT_COUNTER = new HashMap<>();
    public static HashMap<Player, Integer> LOTTERIE_PLAYER_COIN_SAVE = new HashMap<>();

    private boolean isBackendUpdating = false;

    final public static String SYSTEM_IN_CODE = "§8» §bSystem §8• §cDas System ist in Wartungen!";

    final public static String PREFIX = "§8» §bLobbySystem §8• §7";
    final public static String NO_PERMISSION = PREFIX + "§7Dazu hast du keine §cRechte§7.";

    final public static String NICK_PREFIX = "§8» §5Nick §8• §7";
    final public static String NICK_NOPERM = NICK_PREFIX + "§7Dazu hast du keine §cRechte§7.";

    final public static String COINS_PREFIX = "§8» §3CoinSystem §8• §7";
    final public static String COINS_NOPERM = COINS_PREFIX + "§7Dazu hast du keine §cRechte§7.";

    final public static String LOTTERIE_PREFIX = "§8» §eLotterieSystem §8• §7";
    final public static String LOTTERIE_NOPERM = LOTTERIE_PREFIX + "§7Dazu hast du keine §cRechte§7.";

    final public static String REWARD_PREFIX = "§8» §6Daily-Reward §8• §7";
    final public static String REWARD_NOPERM = REWARD_PREFIX + "§7Dazu hast du keine §cRechte§7.";

    @Override
    public void onEnable() {
        lobbySystem = this;
        this.mySQLManager = new MySQLManager("localhost", 3306, "Lobby", "admin", "ficken!!11");
        this.backendManager = new BackendManager(this);
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        buildTable();
        register();
        BackendRunnable.startBackendTimer();
        ParticlesRunnable.startSpawnScheduler();
        DailyRewardRunnable.startTimer();

        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
    void register() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerConnectionListener(this), this);
        pluginManager.registerEvents(new PlayerDamageListener(), this);
        pluginManager.registerEvents(new PlayerDropItemListener(), this);
        pluginManager.registerEvents(new PlayerEditMapListener(), this);
        pluginManager.registerEvents(new PlayerFoodLevelChangeListener(), this);
        pluginManager.registerEvents(new PlayerHeldItemListener(), this);
        pluginManager.registerEvents(new PlayerPickupItemListener(), this);
        pluginManager.registerEvents(new PlayerInventoryClickListener(), this);
        pluginManager.registerEvents(new PlayerDoubleJumpListener(), this);
        pluginManager.registerEvents(new PlayerAsyncChatListener(), this);
        pluginManager.registerEvents(new DatenschutzListener(), this);
        pluginManager.registerEvents(new WorldMobListener(), this);
        pluginManager.registerEvents(new WorldWeatherChangeListener(), this);
        pluginManager.registerEvents(new WorldJumpPlateListener(), this);
        pluginManager.registerEvents(new FakeNPCCreateListener(), this);
        pluginManager.registerEvents(new LotterieClickListener(), this);
        pluginManager.registerEvents(new NickTool(), this);
        pluginManager.registerEvents(new PlayerHider(), this);
        pluginManager.registerEvents(new SilentHub(), this);
        pluginManager.registerEvents(new Profil(), this);
        pluginManager.registerEvents(new Navigator(), this);
        pluginManager.registerEvents(new Extras(), this);
        pluginManager.registerEvents(new CookieClicker(), this);
        pluginManager.registerEvents(new SignClickListener(), this);
        pluginManager.registerEvents(new DailyClickEvent(), this);

        getCommand("addcoins").setExecutor(new CMD_ADD_COINS());
        getCommand("edit").setExecutor(new CMD_Edit());
        getCommand("set").setExecutor(new CMD_Location());
        getCommand("nick").setExecutor(new CMD_Nick());
        getCommand("removecoins").setExecutor(new CMD_REMOVE_COINS());
        getCommand("spawn").setExecutor(new CMD_Spawn());
        getCommand("datenschutz").setExecutor(new CMD_Datenschutz());
        getCommand("lotterie").setExecutor(new LotterieCommand());
        getCommand("cookies").setExecutor(new CMD_Cookie());
        getCommand("test").setExecutor(new CMD_Test());
        getCommand("bauserver").setExecutor(new CMD_BauServer());

    }
    void buildTable() {
        try {
            mySQLManager.openConnection(() ->{
                mySQLManager.update(new PreparedStatementBuilder("SET GLOBAL connect_timeout=28800", mySQLManager).build());
                mySQLManager.update(new PreparedStatementBuilder("SET GLOBAL wait_timeout=28800", mySQLManager).build());
                mySQLManager.update(new PreparedStatementBuilder("SET GLOBAL interactive_timeout=28800", mySQLManager).build());
                mySQLManager.update(new PreparedStatementBuilder("CREATE TABLE IF NOT EXISTS user(uuid VARCHAR(36), name VARCHAR(64), coins INT(32), gadgets TEXT, isNicked BOOLEAN, isPlayerHiding BOOLEAN ,termsAccepted BOOLEAN, isInSilentHub BOOLEAN, rewardTime BOOLEAN, premiumRewardTime BOOLEAN, chests INT(32), premiumChests INT(32), tickets INT(32), lastIP VARCHAR(32), lastSeen INT(32), playerRank VARCHAR(32), getActiveGadget TEXT, userSettings TEXT, cookies INT(32), userSecrets TEXT)", mySQLManager).build());
            });
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void setMetadata(Player p, String key, Object value) {
        removeMetadata(p, key);
        p.setMetadata(key, new FixedMetadataValue(this, value));
    }
    public void removeMetadata(Player p, String key) {
        if(p.hasMetadata(key)) {
            p.removeMetadata(key, this);
        }

    }
    public static LobbySystem getLobbySystem() {
        return lobbySystem;
    }

    public BackendManager getBackendManager() {
        return backendManager;
    }

    public MySQLManager getMySQLManager() {
        return mySQLManager;
    }

    public boolean isBackendUpdating() {
        return isBackendUpdating;
    }

    public void setBackendUpdating(boolean backendUpdating) {
        isBackendUpdating = backendUpdating;
    }

    public ArrayList<String> getPlayerHider() {
        return playerHider;
    }

    public ArrayList<Player> getEditPlayer() {
        return editPlayer;
    }

    public HashMap<ItemStack, String> getHeadCache() {
        return headCache;
    }

    public RandomCoins getRandomCoins() {
        return randomCoins;
    }

    public ArrayList<String> getFireworkdRiders() {
        return fireworkdRiders;
    }
}
