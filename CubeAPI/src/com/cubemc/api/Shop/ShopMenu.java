package com.cubemc.api.Shop;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 12/11/2014
 */
public class ShopMenu implements Serializable {

    private String name;
    private Material icon;
    private List<ShopItem> items = new ArrayList<ShopItem>();
    private List<ShopPage> pages = new ArrayList<ShopPage>();
    private ActionHandler goBackAction;

    public ShopMenu(String name, Material icon, ActionHandler goBackAction) {
        this.name = name;
        this.icon = icon;
        this.goBackAction = goBackAction;

        pages.add(new ShopPage(this, 1));
    }

    public void addItem(ShopItem i){
        int lastPage = pages.size();
        if (pages.get((lastPage - 1)).getPageItems().size() < 14){
            pages.get((lastPage - 1)).addItem(i);
            return;
        }else if (pages.get((lastPage - 1)).getPageItems().size() == 14){
            int oldSize = pages.size();
            pages.add(new ShopPage(this, (oldSize + 1)));
            pages.get(lastPage).addItem(i);
            return;
        }
    }

    public void setGoBackAction(ActionHandler goBackAction) {
        this.goBackAction = goBackAction;
    }

    public String getName() {
        return name;
    }

    public Material getIcon() {
        return icon;
    }

    public List<ShopItem> getItems() {
        return items;
    }

    public List<ShopPage> getPages() {
        return pages;
    }

    public ActionHandler getGoBackAction() {
        return goBackAction;
    }

    public void openShop(Player p){
        pages.get(0).openForPlayer(p);
        return;
    }
}
