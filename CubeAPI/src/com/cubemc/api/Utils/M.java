package com.cubemc.api.Utils;

import com.cubemc.api.CubeAPI;
import com.cubemc.api.Ranks.Rank;
import com.cubemc.api.Ranks.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class M {

    public static String noRank(Rank r){
        return CubeAPI.PREFIX + "§cYou need " + r.getPrefix() + " §cto do this.";
    }

    public static String usage(String cmd, String args){
        return CubeAPI.PREFIX + "§cUsage: §7/" + cmd + " §d" + args;
    }

    public static String error(String msg){
        return CubeAPI.PREFIX + "§c" + msg;
    }

    public static String err(String nothing, String msg){
        return CubeAPI.PREFIX + "§c" + msg;
    }

    public static String fatal(String msg){
        return CubeAPI.PREFIX + "§c" + msg;
    }

    public static String reg(String msg){
        return CubeAPI.PREFIX + msg;
    }

    public static String game(String msg){
        return CubeAPI.PREFIX + msg;
    }

    public static void staff(String msg){
        for (Player p : Bukkit.getOnlinePlayers()){
            if (RankManager.isRankHighEnough(p, Rank.BUILDER)){
                p.sendMessage(CubeAPI.PREFIX + msg);
            }
        }
    }

}
