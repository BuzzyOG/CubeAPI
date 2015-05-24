package com.cubemc.api.Shop;

import com.cubemc.api.Ranks.Rank;
import com.cubemc.api.Ranks.RankManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 11/11/2014
 */
public class ShopItem implements Serializable {

    public static List<ShopItem> allItems = new ArrayList<ShopItem>();

    private String name;
    private List<String> lore;
    private String category;
    private Material icon;
    private int price;
    private CurrencyType type;
    private Rank rank;
    private ActionHandler actionIfAlreadyHad = null;
    private boolean canBeMadeActive;
    private boolean canBePurchasedAgain = false;

    public ShopItem(String name, List<String> lore, String category, int price, CurrencyType type, boolean canBeMadeActive, Rank rank, Material icon) {
        this.name = name;
        this.lore = lore;
        this.category = category;
        this.price = price;
        this.type = type;
        this.canBeMadeActive = canBeMadeActive;
        this.rank = rank;
        this.icon = icon;

        allItems.add(this);
    }

    public ShopItem(String name, List<String> lore, String category, int price, CurrencyType type, boolean canBeMadeActive, boolean canBePurchasedAgain, Rank rank, Material icon) {
        this.name = name;
        this.lore = lore;
        this.category = category;
        this.price = price;
        this.type = type;
        this.canBeMadeActive = canBeMadeActive;
        this.rank = rank;
        this.icon = icon;
        this.canBePurchasedAgain = canBePurchasedAgain;

        allItems.add(this);
    }



    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public CurrencyType getType() {
        return type;
    }

    public boolean isCanBeMadeActive() {
        return canBeMadeActive;
    }

    public Rank getRank() {
        return rank;
    }

    public Material getIcon() {
        return icon;
    }

    public void setActionIfAlreadyHad(ActionHandler actionIfAlreadyHad) {
        this.actionIfAlreadyHad = actionIfAlreadyHad;
    }

    public ItemStack getItemStack(Player p){
        ItemStack i = new ItemStack(icon, 1);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName("§d§lBUY §9§l" + name);
        List<String> l = new ArrayList<String>();
        if (RankManager.isRankHighEnough(RankManager.getRank(p), rank)){
            if (Currency.hasEnough(p, type, price)) {
                for (String s : lore) {
                    l.add("§7" + s);
                }
                l.add("");
                l.add("§7Category: §6" + category);
                l.add("§7Price: " + type.getColour() + price + " " + type.getName());
                if (!(rank.equals(Rank.MEMBER))) {
                    l.add("§7Rank: " + rank.getPrefix());
                }
                l.add("");
                if (ShopUtil.hasBoughtItem(this, p)) {
                    l.add("§7§lALREADY PURCHASED");
                    if (canBeMadeActive == true){
                        if (actionIfAlreadyHad.checkIfActive(p, this) == true){
                            l.add("§3[Activated]");
                        }else{
                            l.add("§3[Click To Activate]");
                        }
                    }
                } else l.add("§a§lCLICK §7to purchase.");
            }else{
                for (String s : lore){
                    l.add("§7" + s);
                }
                l.add("");
                l.add("§7Category: §6" + category);
                l.add("§7Price: " + type.getColour() + price + " " + type.getName());
                if (!(rank.equals(Rank.MEMBER))){
                    l.add("§7Rank: " + rank.getPrefix());
                }
                l.add("");
                if (ShopUtil.hasBoughtItem(this, p)) {
                    l.add("§7§lALREADY PURCHASED");
                    if (canBeMadeActive == true){
                        if (actionIfAlreadyHad.checkIfActive(p, this) == true){
                            l.add("§3[Activated]");
                        }else{
                            l.add("§3[Click To Activate]");
                        }
                    }
                } else l.add("§c§lNOT ENOUGH " + type.getName().toUpperCase());
            }
        }else{
            for (String s : lore){
                l.add("§7" + s);
            }
            l.add("");
            l.add("§7Category: §6" + category);
            l.add("§7Price: " + type.getColour() + price + " " + type.getName());
            if (!(rank.equals(Rank.MEMBER))){
                l.add("§7Rank: " + rank.getPrefix());
            }
            l.add("");
            l.add("§c§lHIGHER RANK NEEDED");
        }
        im.setLore(l);
        i.setItemMeta(im);
        return i;
    }

    public boolean isCanBePurchasedAgain() {
        return canBePurchasedAgain;
    }

    public ActionHandler getActionIfAlreadyHad() {
        return actionIfAlreadyHad;
    }
}
