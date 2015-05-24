package com.cubemc.api.Ranks;

import com.cubemc.api.Core.Module;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.Ranks.Commands.RankBaseCmd;
import com.cubemc.api.Utils.MySQL;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 23/05/2015
 */
public class RankManager extends Module {

    public RankManager(JavaPlugin plugin) {
        super("Rank Manager", plugin);
    }

    private static HashMap<String, Rank> ranks = new HashMap<String, Rank>();

    @Override
    public void AddCommands() {
        CubeAPI.getCommandCenter().addComand(new RankBaseCmd(this));
    }

    public static void setupRank(Player p){
        String ladderName = "troll";
        try {
            String uuid = p.getUniqueId().toString();
            uuid = uuid.replaceAll("-", "");
            MySQL.connectToDB("INSERT INTO PlayerData (UUID, PlayerName, rankType, Gems, Credits) VALUES ('" + uuid + "', '" + p.getName() + "', 'MEMBER', 0, 0) ON DUPLICATE KEY UPDATE PlayerName='" + p.getName() + "';");
            ladderName = MySQL.getResultsString("SELECT * FROM PlayerData WHERE UUID='" + uuid + "';", "rankType");
        }catch (SQLException ex){
            ex.printStackTrace();
            p.sendMessage("§4§lSEVERE: §cUnable to connect to MySQL and retrieve rank!");
            p.sendMessage("§4§lSEVERE: §cSetting rank to §lMEMBER §cautomatically.");
            ladderName = "MEMBER";
        }
        Rank rank = null;
        for (Rank r : Rank.values()){
            if (r.toString().equals(ladderName)){
                rank = r;
            }
        }
        ranks.put(p.getName(), rank);
        p.setDisplayName(getRank(p).getColor() + p.getName());
        return;
    }

    public static Rank getRank(Player p){
        if (ranks.containsKey(p.getName())){
            return ranks.get(p.getName());
        }else{
            return Rank.MEMBER;
        }
    }

    public static boolean isRankHighEnough(Rank pr, Rank needed){
        if (pr.getLadder() <= needed.getLadder()){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isRankHighEnough(Player p, Rank needed){
        Rank pr = RankManager.getRank(p);
        if (pr.getLadder() <= needed.getLadder()){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isPlayerMember(Player p){
        if (getRank(p).equals(Rank.MEMBER)) return true;
        return false;
    }

    @EventHandler
    public void onJoin(PlayerLoginEvent e){
        Player p = e.getPlayer();
        setupRank(p);
    }

}
