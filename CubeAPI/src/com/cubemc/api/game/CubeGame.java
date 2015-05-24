package com.cubemc.api.game;

import com.cubemc.api.game.executors.EndGameExecutor;
import com.cubemc.api.game.executors.StartGameExecutor;
import com.cubemc.api.game.kits.GameKit;
import com.cubemc.api.game.lobby.JoinAction;
import com.cubemc.api.game.maps.GameMap;
import com.cubemc.api.game.prevention.PreventionSet;
import com.cubemc.api.game.teams.GameTeam;

import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 06/04/2015
 */
public class CubeGame {

    private String name;
    private String shortName;
    private String color;
    private PreventionSet set;
    private List<GameTeam> teams;
    private StartGameExecutor startGameExecutor;
    private EndGameExecutor endGameExecutor;

    private GameMap currentMap;
    private GameState state;
    private int ticks;

    private boolean enforcePlayableArena = false;
    private JoinAction joinAction = JoinAction.SEND_TO_LOBBY;
    private boolean kitsEnabled = false;
    private List<GameKit> gameKits;

    private String subtitles;
    private List<String> description;

    private int maxPlayers = 16;
    private int minPlayers = 2;

    public CubeGame(String name, String shortName, String color, PreventionSet set) {
        this.name = name;
        this.shortName = shortName;
        this.color = color;
        this.set = set;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getColor() {
        return color;
    }

    public PreventionSet getSet() {
        return set;
    }

    public List<GameTeam> getTeams() {
        return teams;
    }

    public StartGameExecutor getStartGameExecutor() {
        return startGameExecutor;
    }

    public EndGameExecutor getEndGameExecutor() {
        return endGameExecutor;
    }

    public GameMap getCurrentMap() {
        return currentMap;
    }

    public GameState getState() {
        return state;
    }

    public int getTicks() {
        return ticks;
    }

    public boolean isEnforcePlayableArena() {
        return enforcePlayableArena;
    }

    public JoinAction getJoinAction() {
        return joinAction;
    }

    public boolean isKitsEnabled() {
        return kitsEnabled;
    }

    public List<GameKit> getGameKits() {
        return gameKits;
    }

    public String getSubtitles() {
        return subtitles;
    }

    public List<String> getDescription() {
        return description;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setSet(PreventionSet set) {
        this.set = set;
    }

    public void setTeams(List<GameTeam> teams) {
        this.teams = teams;
    }

    public void setStartGameExecutor(StartGameExecutor startGameExecutor) {
        this.startGameExecutor = startGameExecutor;
    }

    public void setEndGameExecutor(EndGameExecutor endGameExecutor) {
        this.endGameExecutor = endGameExecutor;
    }

    public void setCurrentMap(GameMap currentMap) {
        this.currentMap = currentMap;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    public void setEnforcePlayableArena(boolean enforcePlayableArena) {
        this.enforcePlayableArena = enforcePlayableArena;
    }

    public void setJoinAction(JoinAction joinAction) {
        this.joinAction = joinAction;
    }

    public void setKitsEnabled(boolean kitsEnabled) {
        this.kitsEnabled = kitsEnabled;
    }

    public void setGameKits(List<GameKit> gameKits) {
        this.gameKits = gameKits;
    }

    public void setSubtitles(String subtitles) {
        this.subtitles = subtitles;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }
}
