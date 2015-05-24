package com.cubemc.api.Punish.Commands;

import com.cubemc.api.Commands.CommandBase;
import com.cubemc.api.Commands.Random.CMDh;
import com.cubemc.api.Punish.PunishManager;
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
public class BanCmd extends CommandBase<PunishManager> {

    public BanCmd(PunishManager plugin) {
        super(plugin, Rank.SR_MODERATOR, Arrays.asList("ban"));
        CMDh.register("ban", "<player> <reason>", Rank.SR_MODERATOR, "Bans a player");
    }

    @Override
    public void execute(Player p, String[] args) {
        if (args==null || args.length<2){
            p.sendMessage(M.usage("ban", "<player> <reason>"));
            return;
        }

        String ts = args[0];
        if (Bukkit.getPlayer(ts)==null){
            p.sendMessage(M.error("That player isn't online."));
            return;
        }
        Player target = Bukkit.getPlayer(ts);
        String tdn = target.getDisplayName();

        if (RankManager.isRankHighEnough(target, RankManager.getRank(p))){
            p.sendMessage(M.error("You can't punish someone with the same or a higher rank than you."));
            return;
        }

        String reason = "";
        for (int i=1; i<args.length; i++){
            reason += args[i] + " ";
        }
        reason = reason.trim();

        target.kickPlayer("§cYou have been banned from CubeMC.\n\n" +
                "§cReason - §7" + reason + "\n" +
                "§cStaff Member - §7" + p.getName());

        M.staff(p.getDisplayName() + " §7banned " + tdn + " §7with reason: §c" + reason + "§7.");

        reason = reason.replaceAll("'", "\'");
        try {
            MySQL.connectToDB("INSERT INTO PunishmentData (UUID, PlayerName, Type, Time, Punisher, Reason) VALUES (" +
                    "'" + UUIDh.get(target) + "'," +
                    "'" + target.getName() + "'," +
                    "'Ban'," +
                    "" + System.currentTimeMillis() + "," +
                    "'" + p.getName() + "'," +
                    "'" + reason + "'" +
                    ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
}
