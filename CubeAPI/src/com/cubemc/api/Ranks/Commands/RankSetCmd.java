package com.cubemc.api.Ranks.Commands;

import com.cubemc.api.Commands.CommandBase;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.Ranks.Rank;
import com.cubemc.api.Ranks.RankManager;
import com.cubemc.api.Utils.M;
import com.cubemc.api.Utils.MySQL;
import com.cubemc.api.Utils.UUIDh;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class RankSetCmd extends CommandBase<RankManager> {

    public RankSetCmd(RankManager plugin) {
        super(plugin, Rank.ADMINISTRATOR, Arrays.asList("set", "s"));
    }

    @Override
    public void execute(Player p, String[] args) {
        if (args==null || args.length!=2){
            p.sendMessage(M.usage("rank set", "<player> <rank>"));
            return;
        }

        String tname = args[0];
        if (Bukkit.getPlayer(tname)==null){
            p.sendMessage(M.error("That player isn't online."));
            return;
        }
        Player t = Bukkit.getPlayer(tname);

        String rs = args[1].toUpperCase();
        if (isRankValid(rs)==false){
            p.sendMessage(M.error("That rank type couldn't be found. Possibilities:"));
            p.sendMessage(M.error("§7MEMBER, YOUTUBER, BUILDER, MODERATOR, SR_MODERATOR, ADMINISTRATOR, DEVELOPER, OWNER"));
            return;
        }
        Rank rank = Rank.valueOf(rs);

        try {
            MySQL.connectToDB("UPDATE PlayerData SET rankType='" + rs + "' WHERE UUID='" + UUIDh.get(t) + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RankManager.setupRank(t);
        t.sendMessage(CubeAPI.PREFIX + "Your rank was set to " + rank.getPrefix() + "§7.");
        p.sendMessage(CubeAPI.PREFIX + "Set player rank to " + rank.getPrefix() + "§7.");
        return;
    }

    public boolean isRankValid(String s){
        for (Rank rank : Rank.values()){
            if (rank.toString().equals(s)){
                return true;
            }
        }
        return false;
    }
}
