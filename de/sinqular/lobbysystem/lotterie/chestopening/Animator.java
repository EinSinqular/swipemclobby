package de.sinqular.lobbysystem.lotterie.chestopening;

import de.sinqular.lobbysystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class Animator implements Listener {

    private Player player;
    private IAnimator iAnimator;
    private Animator animator;

    private Inventory inventory;
    private boolean running;
    private BukkitTask task;

    private int rolls;
    private int timeout;

    public Animator(Player p, IAnimator iAnimator) {
        this.player = p;
        this.iAnimator = iAnimator;
        this.animator = this;
    }

    public void startAnimation(int rolls, List<ItemStack> items, long waitAEnd) {
        this.inventory = this.iAnimator.setupInventory(this.player);
        this.rolls = rolls;
        this.player.openInventory(this.inventory);

        this.running = true;


        this.task = Bukkit.getScheduler().runTaskTimer(LobbySystem.getLobbySystem(), new Runnable() {
            @Override
            public void run() {
                if(Animator.this.timeout != 0) {
                    Animator.this.timeout--;
                } else {
                    Animator.this.iAnimator.onTick(Animator.this.player, Animator.this.inventory, items, false);

                    Animator.this.rolls--;
                    if(Animator.this.rolls >= 20) {
                        Animator.this.timeout = 0;
                    }else if(Animator.this.rolls <= 19 && Animator.this.rolls >= 10) {
                        Animator.this.timeout = 1;
                    }else if(Animator.this.rolls <= 9 && Animator.this.rolls >= 5) {
                        Animator.this.timeout = 2;
                    }else if(Animator.this.rolls <= 4 && Animator.this.rolls >= 0) {
                        Animator.this.timeout = 3;
                    }
                    if(Animator.this.rolls == 0) {
                        Animator.this.task.cancel();
                        Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), new Runnable() {

                            @Override
                            public void run() {
                                Animator.this.iAnimator.onTick(Animator.this.player, Animator.this.inventory, items, true);

                                Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), new Runnable() {

                                    @Override
                                    public void run() {
                                        HandlerList.unregisterAll(Animator.this.animator);

                                        Animator.this.running = false;
                                        Animator.this.player.closeInventory();
                                    }
                                }, waitAEnd);

                            }
                        }, 20);
                    }
                }
            }
        }, 0L, 2L);
    }
    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getInventory().equals(this.inventory) && this.running) {
            e.setCancelled(true);
            e.setResult(Event.Result.DENY);
        }
    }
    @EventHandler
    public void onClose(InventoryCloseEvent e){
        Player p = (Player) e.getPlayer();
        if(e.getInventory().equals(this.inventory) && this.running) {
            Bukkit.getScheduler().runTaskLater(LobbySystem.getLobbySystem(), new Runnable() {

                @Override
                public void run() {
                    p.openInventory(Animator.this.inventory);
                }
            }, 3);
        }
    }

}
