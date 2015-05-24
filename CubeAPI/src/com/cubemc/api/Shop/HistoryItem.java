package com.cubemc.api.Shop;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 05/03/2015
 */
public class HistoryItem {

    private String UUID;
    private String PlayerNameUsed;
    private String ItemName;
    private String Category;
    private int Price;
    private CurrencyType CurrencyType;

    public HistoryItem(String UUID, String playerNameUsed, String itemName, String category, int price, String currencyType) {
        this.UUID = UUID;
        PlayerNameUsed = playerNameUsed;
        ItemName = itemName;
        Category = category;
        Price = price;
        CurrencyType = CurrencyType.valueOf(currencyType);
    }

    public String getUUID() {
        return UUID;
    }

    public String getPlayerNameUsed() {
        return PlayerNameUsed;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getCategory() {
        return Category;
    }

    public int getPrice() {
        return Price;
    }

    public CurrencyType getCurrencyType() {
        return CurrencyType;
    }
}
