package com.cubemc.api.game.teams;

import com.cubemc.api.Core.Module;
import com.cubemc.api.Utils.M;
import com.cubemc.api.game.events.UpdateEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 06/04/2015
 */
public class TeamManager extends Module {

    private List<GameTeam> allTeams = new ArrayList<GameTeam>();

    public TeamManager(JavaPlugin plugin) {
        super("Team Manager", plugin);
    }

    public void registerTeam(GameTeam team){
        allTeams.add(team);
    }

    public GameTeam getTeam(String name){
        for (GameTeam team : allTeams){
            if (team.getName().equalsIgnoreCase(name)){
                return team;
            }
        }
        return null;
    }

    public GameTeam getTeam(Player p){
        for (GameTeam team : allTeams){
            if (team.getMembers().contains(p.getName())){
                return team;
            }
        }
        return null;
    }

    public void joinTeam(Player p, GameTeam team){
        if (team == null){
            return;
        }

        if (team.getMaxSize() == -1){
            leaveTeam(p);
            p.sendMessage(M.reg("You joined team " + team.getColor() + "§l" + team.getName() + "§7."));
            team.getMembers().add(p.getName());
            return;
        }
        if (team.getMaxSize() > team.getMembers().size()){
            leaveTeam(p);
            p.sendMessage(M.reg("You joined team " + team.getColor() + "§l" + team.getName() + "§7."));
            team.getMembers().add(p.getName());
            return;
        }

        p.sendMessage(M.error("That team is already full."));
        return;
    }

    public boolean hasTeam(Player p){
        if (getTeam(p) != null) return true;
        return false;
    }

    public void leaveTeam(Player p){
        if (hasTeam(p)){
            GameTeam team = getTeam(p);
            team.getMembers().remove(p.getName());
            p.sendMessage(M.reg("You left team " + team.getColor() + "§l" + team.getName() + "§7."));
            return;
        }
    }

    @EventHandler
    public void HidePlayers(UpdateEvent e){
        for (GameTeam team : allTeams){
            if (team.isVisible() == false){
                for (String s : team.getMembers()){
                    Player p = Bukkit.getPlayer(s);
                    for (Player all : Bukkit.getOnlinePlayers()){
                        all.hidePlayer(p);
                    }
                }
            }
        }
    }

}
