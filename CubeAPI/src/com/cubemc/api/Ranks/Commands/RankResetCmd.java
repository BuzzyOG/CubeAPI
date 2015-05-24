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
public class RankResetCmd extends CommandBase<RankManager> {

    public RankResetCmd(RankManager plugin) {
        super(plugin, Rank.ADMINISTRATOR, Arrays.asList("reset", "r"));
    }

    @Override
    public void execute(Player p, String[] args) {
        if (args==null || args.length!=1){
            p.sendMessage(M.usage("rank reset", "<player>"));
            return;
        }

        String ts = args[0];
        if (Bukkit.getPlayer(ts)==null){
            p.sendMessage(M.error("That player isn't online."));
            return;
        }
        Player t = Bukkit.getPlayer(ts);

        try {
            MySQL.connectToDB("UPDATE PlayerData SET rankType='MEMBER' WHERE UUID='" + UUIDh.get(t) + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RankManager.setupRank(t);

        t.sendMessage(M.reg("Your rank was reset to " + Rank.MEMBER.getPrefix() + "§7."));
        p.sendMessage(M.reg("Reset player's rank to " + Rank.MEMBER.getPrefix() + "§7."));
        return;
    }
}
