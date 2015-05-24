package com.cubemc.api.Shop;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 11/11/2014
 */
public enum CurrencyType {

    GEMS("Gems", "§a"),CREDITS("Credits", "§b");

    private String name;
    private String colour;

    CurrencyType(String name, String colour) {
        this.name = name;
        this.colour = colour;
    }

    public String getName() {
        return name;
    }

    public String getColour() {
        return colour;
    }
}
