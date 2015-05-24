package com.cubemc.api.Punish;

import com.cubemc.api.Core.Module;
import com.cubemc.api.CubeAPI;
import com.cubemc.api.Punish.Commands.BanCmd;
import com.cubemc.api.Punish.Commands.KickCmd;
import com.cubemc.api.Utils.MySQL;
import com.cubemc.api.Utils.UUIDh;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 24/05/2015
 */
public class PunishManager extends Module {

    public PunishManager(JavaPlugin plugin){
        super("Punishment Manager", plugin);
    }

    @Override
    public void AddCommands() {
        CubeAPI.getCommandCenter().addComand(new KickCmd(this));
        CubeAPI.getCommandCenter().addComand(new BanCmd(this));
    }

    @EventHandler
    public void banned(PlayerLoginEvent e){
        Player p = e.getPlayer();

        try {
            if (MySQL.doesTableContain("SELECT * FROM PunishmentData WHERE UUID='" + UUIDh.get(p) + "' AND Type='Ban';")){
                String staff = MySQL.getResultsString("SELECT * FROM PunishmentData WHERE UUID='" + UUIDh.get(p) + "' AND Type='Ban';", "Punisher");
                String reason = MySQL.getResultsString("SELECT * FROM PunishmentData WHERE UUID='" + UUIDh.get(p) + "' AND Type='Ban';", "Reason");
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§cYou have been banned from CubeMC.\n\n" +
                        "§cReason - §7" + reason + "\n" +
                        "§cStaff Member - §7" + staff);
                return;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
