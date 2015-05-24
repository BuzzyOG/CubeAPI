package com.cubemc.api.Shop;

import org.bukkit.entity.Player;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 10/12/2014
 */
public interface ActionHandler {

    public void onGoBackAction(Player p, ShopMenu menu);

    public void onActivate(Player p, ShopItem item);

    public boolean checkIfActive(Player p, ShopItem item);

}
