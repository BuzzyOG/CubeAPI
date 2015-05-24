package com.cubemc.api.game.kits.perks;

import com.cubemc.api.game.kits.GameKit;
import org.bukkit.entity.Player;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 06/04/2015
 */
public abstract class Perk {

    private GameKit host;
    private String name;
    private String desc;

    protected Perk(GameKit host, String name, String desc) {
        this.host = host;
        this.name = name;
        this.desc = desc;
    }

    public GameKit getHost() {
        return host;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public abstract void backgroundStart();

    public abstract void activate(Player p);

}
