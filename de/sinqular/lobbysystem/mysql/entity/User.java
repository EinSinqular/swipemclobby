package de.sinqular.lobbysystem.mysql.entity;

import java.util.ArrayList;

public class User {

    private String uuid;
    private String name;
    private int coins;
    private ArrayList<String> gadgets;
    private boolean isNicked;
    private boolean isPlayerHiding;
    private boolean termsAccepted;
    private boolean isInSilentHub;
    private boolean rewardTime;
    private boolean premiumRewardTime;
    private int chests;
    private int premiumChest;
    private int tickets;
    private String lastIP;
    private long lastSeen;
    private String playerRank;
    private ArrayList<String> getActiveGadGet;
    private ArrayList<String> userSettings;
    private int cookies;
    private ArrayList<String> userSecrets;

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getCoins() {
        return coins;
    }

    public ArrayList<String> getGadgets() {
        return gadgets;
    }

    public boolean isNicked() {
        return isNicked;
    }

    public boolean isPlayerHiding() {
        return isPlayerHiding;
    }

    public boolean isTermsAccepted() {
        return termsAccepted;
    }

    public boolean isInSilentHub() {
        return isInSilentHub;
    }

    public boolean getRewardTime() {
        return rewardTime;
    }

    public boolean getPremiumRewardTime() {
        return premiumRewardTime;
    }

    public int getChests() {
        return chests;
    }

    public int getPremiumChest() {
        return premiumChest;
    }

    public int getTickets() {
        return tickets;
    }

    public String getLastIP() {
        return lastIP;
    }

    public long getLastSeen() {
        return lastSeen;
    }

    public String getPlayerRank() {
        return playerRank;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void setGadgets(ArrayList<String> gadgets) {
        this.gadgets = gadgets;
    }

    public void setNicked(boolean nicked) {
        isNicked = nicked;
    }

    public void setPlayerHiding(boolean playerHiding) {
        isPlayerHiding = playerHiding;
    }

    public void setTermsAccepted(boolean termsAccepted) {
        this.termsAccepted = termsAccepted;
    }

    public void setInSilentHub(boolean inSilentHub) {
        isInSilentHub = inSilentHub;
    }

    public void setRewardTime(boolean rewardTime) {
        this.rewardTime = rewardTime;
    }

    public void setPremiumRewardTime(boolean premiumRewardTime) {
        this.premiumRewardTime = premiumRewardTime;
    }

    public void setChests(int chests) {
        this.chests = chests;
    }

    public void setPremiumChest(int premiumChest) {
        this.premiumChest = premiumChest;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public void setLastIP(String lastIP) {
        this.lastIP = lastIP;
    }

    public void setLastSeen(long lastSeen) {
        this.lastSeen = lastSeen;
    }

    public void setPlayerRank(String playerRank) {
        this.playerRank = playerRank;
    }

    public void setGetActiveGadGet(ArrayList<String> getActiveGadGet) {
        this.getActiveGadGet = getActiveGadGet;
    }

    public ArrayList<String> getGetActiveGadGet() {
        return getActiveGadGet;


    }

    public ArrayList<String> getUserSettings() {
        return userSettings;
    }

    public void setUserSettings(ArrayList<String> userSettings) {
        this.userSettings = userSettings;
    }

    public int getCookies() {
        return cookies;
    }

    public void setCookies(int cookies) {
        this.cookies = cookies;
    }

    public ArrayList<String> getUserSecrets() {
        return userSecrets;
    }

    public void setUserSecrets(ArrayList<String> userSecrets) {
        this.userSecrets = userSecrets;
    }
}

