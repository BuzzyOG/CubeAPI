package com.cubemc.api.Ranks.Commands;

import com.cubemc.api.Commands.MultiCommandBase;
import com.cubemc.api.Commands.Random.CMDh;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.Ranks.Rank;
import com.cubemc.api.Ranks.RankManager;
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
public class RankBaseCmd extends MultiCommandBase<RankManager> {

    public RankBaseCmd(RankManager plugin) {
        super(plugin, Rank.ADMINISTRATOR, Arrays.asList("rank"));
        CMDh.register("rank", "", Rank.ADMINISTRATOR, "Shows the rank help menu");

        addCommand(new RankGetCmd(plugin));
        addCommand(new RankSetCmd(plugin));
        addCommand(new RankResetCmd(plugin));
    }

    @Override
    public void help(Player p, String[] args) {
        p.sendMessage("§8»§m-------------------------");
        p.sendMessage(CubeAPI.PREFIX + "§aCommands for §2Rank Manager");
        p.sendMessage(CubeAPI.PREFIX + "§7<> = Required   [] = Optional");
        p.sendMessage("§8»§m-------------------------");
        p.sendMessage("§8» §6/rank get §e<player>");
        p.sendMessage("§8» §6/rank set §e<player> <rank>");
        p.sendMessage("§8» §6/rank reset §e<player>");
        p.sendMessage("§8»§m-------------------------");
    }
}
