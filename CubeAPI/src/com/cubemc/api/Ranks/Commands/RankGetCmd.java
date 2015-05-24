package com.cubemc.api.Ranks.Commands;

import com.cubemc.api.Commands.CommandBase;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.Ranks.Rank;
import com.cubemc.api.Ranks.RankManager;
import com.cubemc.api.Utils.M;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class RankGetCmd extends CommandBase<RankManager> {

    public RankGetCmd(RankManager plugin) {
        super(plugin, Rank.ADMINISTRATOR, Arrays.asList("get", "g"));
    }

    @Override
    public void execute(Player p, String[] args) {
        if (args==null || args.length != 1){
            p.sendMessage(M.usage("rank get", "<player>"));
            return;
        }

        if (Bukkit.getPlayer(args[0])==null){
            p.sendMessage(M.error("That player isn't online."));
            return;
        }

        p.sendMessage(CubeAPI.PREFIX + "The rank of §e" + Bukkit.getPlayer(args[0]).getName() + " §7is " + RankManager.getRank(Bukkit.getPlayer(args[0])).getPrefix() + "§7.");
        return;
    }
}
