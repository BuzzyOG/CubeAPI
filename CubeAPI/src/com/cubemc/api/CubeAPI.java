package com.cubemc.api;

import com.cubemc.api.Chat.ChatListener;
import com.cubemc.api.Commands.CommandCenter;
import com.cubemc.api.Commands.PluginsOverwrite;
import com.cubemc.api.Commands.Random.RandomCommandManager;
import com.cubemc.api.Punish.PunishManager;
import com.cubemc.api.Ranks.RankManager;
import com.cubemc.api.Shop.PurchaseHistory;
import com.cubemc.api.Game.GameManager;
import com.cubemc.api.Game.events.PlayerJoin;
import com.cubemc.api.Game.events.PlayerLeave;
import com.cubemc.api.Game.kits.KitSelectorListener;
import com.cubemc.api.Game.maps.MapSelectorListener;
import com.cubemc.api.Game.prevention.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 23/05/2015
 */
public class CubeAPI extends JavaPlugin {

    public static String PREFIX = "§3§lCubeMC §8» §7";

    private static RankManager rankManager;
    private static CommandCenter commandCenter;
    private static PunishManager punishManager;
    private static GameManager gameManager;

    @Override
    public void onEnable() {
        commandCenter = new CommandCenter(this);
        rankManager = new RankManager(this);
        reloadRanks();
        punishManager = new PunishManager(this);
        gameManager = new GameManager(this);
        new PurchaseHistory(this);
        new RandomCommandManager(this);

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new PluginsOverwrite(), this);

        registerGameListeners();

        removeMobs();

        if (new File(Bukkit.getWorldContainer() + File.separator + "lobby").exists()){
            Bukkit.broadcastMessage("Loading Lobby...");
            Bukkit.createWorld(new WorldCreator("lobby"));
            Bukkit.broadcastMessage("Loaded.");
        }else{
            Bukkit.broadcastMessage("No Lobby Exists. None created.");
        }
    }

    public static Plugin getPlugin(){
        return Bukkit.getPluginManager().getPlugin("CubeAPI");
    }

    public static RankManager getRankManager(){
        return rankManager;
    }

    public static CommandCenter getCommandCenter() {
        return commandCenter;
    }

    public static PunishManager getPunishManager() {
        return punishManager;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static void reloadRanks(){
        for (Player p : Bukkit.getOnlinePlayers()){
            getRankManager().setupRank(p);
        }
    }

    public void removeMobs(){
        for (World w : Bukkit.getWorlds()){
            for (LivingEntity e : w.getLivingEntities()){
                e.remove();
            }
        }
        return;
    }

    public void registerGameListeners(){
        Bukkit.getPluginManager().registerEvents(new KitSelectorListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerLeave(), this);
        Bukkit.getPluginManager().registerEvents(new MapSelectorListener(), this);

        //Prevention
        Bukkit.getPluginManager().registerEvents(new PVP(), this);
        Bukkit.getPluginManager().registerEvents(new PVE(), this);
        Bukkit.getPluginManager().registerEvents(new PlayersTakeDamage(), this);
        Bukkit.getPluginManager().registerEvents(new MobsSpawn(), this);
        Bukkit.getPluginManager().registerEvents(new PlayersDropAndPickup(), this);
        Bukkit.getPluginManager().registerEvents(new PlayersBreakAndPlace(), this);
        //Bukkit.getPluginManager().registerEvents(new UseBuckets(), this); //TODO
    }
}
