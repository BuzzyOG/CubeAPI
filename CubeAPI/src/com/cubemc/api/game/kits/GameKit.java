package com.cubemc.api.game.kits;

import com.cubemc.api.Shop.ShopItem;
import com.cubemc.api.game.kits.perks.Perk;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 06/04/2015
 */
public abstract class GameKit {

    private String name;
    private List<String> desc;
    private Material icon;
    private ShopItem item;
    private Perk[] perks;

    public GameKit(String name, List<String> desc, Material icon, ShopItem item, Perk[] perks) {
        this.name = name;
        this.desc = desc;
        this.icon = icon;
        this.item = item;
        this.perks = perks;
    }

    public String getName() {
        return name;
    }

    public List<String> getDesc() {
        return desc;
    }

    public Material getIcon() {
        return icon;
    }

    public ShopItem getItem() {
        return item;
    }

    public Perk[] getPerks() {
        return perks;
    }

    public abstract void applyKit(Player p);
}
