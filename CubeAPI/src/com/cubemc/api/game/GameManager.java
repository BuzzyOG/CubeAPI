package com.cubemc.api.game;

import com.cubemc.api.Core.Module;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.Utils.BungeeUtil;
import com.cubemc.api.Utils.M;
import com.cubemc.api.game.events.UpdateEvent;
import com.cubemc.api.game.kits.KitManager;
import com.cubemc.api.game.kits.perks.PerkManager;
import com.cubemc.api.game.lobby.LobbyManager;
import com.cubemc.api.game.maps.GameMap;
import com.cubemc.api.game.maps.MapManager;
import com.cubemc.api.game.spectators.SpectateManager;
import com.cubemc.api.game.teams.GameTeam;
import com.cubemc.api.game.teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 06/04/2015
 */
public class GameManager extends Module {

    private TeamManager teamManager;
    private MapManager mapManager;
    private KitManager kitManager;
    private PerkManager perkManager;
    private LobbyManager lobbyManager;
    private SpectateManager spectateManager;

    public GameManager(JavaPlugin plugin) {
        super("Game Manager", plugin);
    }

    public SpectateManager getSpectateManager() {
        return spectateManager;
    }

    public LobbyManager getLobbyManager() {
        return lobbyManager;
    }

    public PerkManager getPerkManager() {
        return perkManager;
    }

    public KitManager getKitManager() {
        return kitManager;
    }

    public TeamManager getTeamManager() {
        return teamManager;
    }

    public MapManager getMapManager() {
        return mapManager;
    }

    private CubeGame game;

    public boolean forceStarted = false;

    private int gameTimeSeconds = 0; //TOTAL GAME RUNNING TIME

    private static void startUpdateEvents(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(CubeAPI.getPlugin(), new Runnable() {
            @Override
            public void run() {
                Bukkit.getPluginManager().callEvent(new UpdateEvent());
            }
        }, 0L, 20L);
    }

    public void initializeGame(CubeGame game, List<GameMap> maps){
        teamManager = new TeamManager((JavaPlugin)CubeAPI.getPlugin());
        mapManager = new MapManager((JavaPlugin)CubeAPI.getPlugin());
        kitManager = new KitManager((JavaPlugin)CubeAPI.getPlugin());
        perkManager = new PerkManager((JavaPlugin)CubeAPI.getPlugin());
        lobbyManager = new LobbyManager((JavaPlugin)CubeAPI.getPlugin());
        spectateManager = new SpectateManager((JavaPlugin)CubeAPI.getPlugin());

        this.game = game;
        getMapManager().giveSuppliedMaps(maps);
        startUpdateEvents();
        getMapManager().checkForVoting();
    }

    public CubeGame getGame() {
        return game;
    }

    public void displayStartMessage(int time){
        Bukkit.broadcastMessage("§3§m-----------------------------------------------------");
        Bukkit.broadcastMessage(" §b§l" + game.getName() + " §bis starting in " + time + " seconds!");
        Bukkit.broadcastMessage(" ");
        for (String line : game.getDescription()){
            Bukkit.broadcastMessage(" §d» " + line);
        }
        Bukkit.broadcastMessage(" ");
        Bukkit.broadcastMessage(" §aYou are about to play the map §l" + getGame().getCurrentMap().getName() + " §aby " + getGame().getCurrentMap().getAuthor() + ".");
        Bukkit.broadcastMessage("§3§m-----------------------------------------------------");
        return;
    }

    public void displayWinner(String[] displayNamePositions){
        Bukkit.broadcastMessage("§3§m-----------------------------------------------------");
        Bukkit.broadcastMessage(" §b§l" + game.getName() + " §bhas ended!");
        Bukkit.broadcastMessage(" ");
        if (displayNamePositions.length >= 1){
            Bukkit.broadcastMessage(" §c§l1st Place - " + displayNamePositions[0]);
        }
        if (displayNamePositions.length >= 2){
            Bukkit.broadcastMessage(" §6§l2nd Place - " + displayNamePositions[1]);
        }
        if (displayNamePositions.length >= 3){
            Bukkit.broadcastMessage(" §e§l3rd Place - " + displayNamePositions[2]);
        }
        Bukkit.broadcastMessage(" ");
        Bukkit.broadcastMessage(" §aYou have just played the map §l" + getGame().getCurrentMap().getName() + " §aby " + getGame().getCurrentMap().getAuthor() + ".");
        Bukkit.broadcastMessage("§3§m-----------------------------------------------------");
        return;
    }

    public void displayWinner(GameTeam team, String customLine){
        Bukkit.broadcastMessage("§3§m-----------------------------------------------------");
        Bukkit.broadcastMessage(" §b§l" + game.getName() + " §bhas Ended!");
        Bukkit.broadcastMessage(" ");
        if (team != null) {
            Bukkit.broadcastMessage(" " + team.getColor() + "§l" + team.getName() + " §bhas won the game.");
        }else{
            Bukkit.broadcastMessage(" §5§lNOBODY WON! §D§LIT'S A DRAW!");
        }
        if (customLine != null){
            Bukkit.broadcastMessage(" §e" + customLine);
        }
        Bukkit.broadcastMessage(" ");
        Bukkit.broadcastMessage(" §aYou have just played the map §l" + getGame().getCurrentMap().getName() + " §aby " + getGame().getCurrentMap().getAuthor() + ".");
        Bukkit.broadcastMessage("§3§m-----------------------------------------------------");
        return;
    }

    public void beginCountdownSequence(){
        if (game == null) return;
        if (game.getState().equals(GameState.WAITING)){
            game.setState(GameState.STARTING);
        }
    }

    public void startWarmup(){
        game.setState(GameState.WARMUP);
        game.getStartGameExecutor().startWarmup();
        displayStartMessage(5);
    }

    public void startGame(){
        game.setState(GameState.INGAME);
        game.getStartGameExecutor().startGame();
    }

    public void endGame(){
        game.setState(GameState.ENDED);
        game.getEndGameExecutor().endGame();
        Bukkit.broadcastMessage(M.reg("Server restarting in 10 seconds."));
        Bukkit.getScheduler().scheduleSyncDelayedTask(CubeAPI.getPlugin(), new Runnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()){
                    BungeeUtil.send(p, "hub");
                }

                getMapManager().deleteOldMap(getGame().getCurrentMap()); // Delete the old map.

                Bukkit.getScheduler().scheduleSyncDelayedTask(CubeAPI.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        //Wait 30 seconds, and then reload the server... hopefully the map is deleted by now.
                        Bukkit.reload();
                    }
                }, 600L);
            }
        }, 200L);
    }

    @EventHandler
    public void Countdown(UpdateEvent e){
        if (game == null) return;
        if (game.getState().equals(GameState.STARTING) || game.getState().equals(GameState.WARMUP)){
            if (forceStarted == false){
                if (Bukkit.getOnlinePlayers().size() < game.getMinPlayers()){
                    int needed = (game.getMinPlayers() - Bukkit.getOnlinePlayers().size());
                    Bukkit.broadcastMessage(M.error("Countdown aborted: " + needed + " player(s) needed."));
                    game.setState(GameState.WAITING);
                    return;
                }
            }

            if (game.getTicks() == 5){
                startWarmup();
            }

            if (game.getTicks() == 0){
                Bukkit.broadcastMessage(M.reg("§aThe game has begun!"));
                startGame();
                return;
            }

            if (game.getTicks() == 15){
                getMapManager().setupVotedMap();
            }

            if (game.getTicks() % 15 == 0){
                Bukkit.broadcastMessage(M.reg("Game starts in §e" + game.getTicks() + "s§7."));
            }

            game.setTicks(game.getTicks() - 1);
            return;
        }
    }

    @EventHandler
    public void GameTime(UpdateEvent e){
        if (game == null) return;
        if (game.getState().equals(GameState.INGAME)){
            gameTimeSeconds++;
            return;
        }
    }

}
