package com.cubemc.api.Commands.Random;

import com.cubemc.api.Commands.CommandBase;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.Ranks.Rank;
import com.cubemc.api.Utils.M;
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
public class HelpCmd extends CommandBase<RandomCommandManager> {

    public HelpCmd(RandomCommandManager plugin) {
        super(plugin, Rank.MEMBER, Arrays.asList("help"));
        CMDh.register("help", "[page]", Rank.MEMBER, "Shows the help menu");
    }

    @Override
    public void execute(Player p, String[] args) {
        if (args==null || args.length!=1){
            HelpPage page = CMDh.pages.get(0);

            p.sendMessage("§8»§m-------------------------");
            p.sendMessage(CubeAPI.PREFIX + "§aCommand Help §2(Page 1)");
            p.sendMessage(CubeAPI.PREFIX + "§7<> = Required   [] = Optional");
            p.sendMessage("§8»§m-------------------------");
            for (HelpData data : page.getCommands()){
                p.sendMessage("§8» §6/" + data.getCmd() + " §e" + data.getArgs());
            }
            p.sendMessage("§8»§m-------------------------");
        }else{
            int page = 1;
            try {
                page = Integer.parseInt(args[0]);
            }catch (Exception e){
                p.sendMessage(M.error("That is an invalid page number."));
                return;
            }
            HelpPage helpPage = null;
            if (!(CMDh.pages.size() >= page)){
                p.sendMessage(M.error("There are not enough commands for " + page + " pages."));
                return;
            }
            helpPage = CMDh.pages.get(page - 1);

            p.sendMessage("§8»§m-------------------------");
            p.sendMessage(CubeAPI.PREFIX + "§aCommand Help §2(Page " + page + ")");
            p.sendMessage(CubeAPI.PREFIX + "§7<> = Required   [] = Optional");
            p.sendMessage("§8»§m-------------------------");
            for (HelpData data : helpPage.getCommands()){
                p.sendMessage("§8» §6/" + data.getCmd() + " §e" + data.getArgs());
            }
            p.sendMessage("§8»§m-------------------------");
        }
    }
}
