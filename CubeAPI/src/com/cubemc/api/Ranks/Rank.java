package com.cubemc.api.Ranks;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 23/05/2015
 */
public enum Rank {

    MEMBER("§8[§eMember§8]", "§e", 10),
    VIP("§8[§aVIP§8]", "§a", 9),
    MVP("§8[§6MVP§8]", "§6", 8),
    GOD("§8[§bGOD§8]", "§b", 7),
    YOUTUBER("§8[§6Youtuber§8]", "§6", 6),
    BUILDER("§8[§9Builder§8]", "§9", 5),
    MODERATOR("§8[§dMod§8]", "§d", 4),
    SR_MODERATOR("§8[§5Sr. Mod§8]", "§5", 3),
    ADMINISTRATOR("§8[§cAdmin§8]", "§c", 2),
    DEVELOPER("§8[§4Dev§8]", "§4", 1),
    OWNER("§8[§4Owner§8]", "§4", 0);

    private String prefix;
    private String color;
    private int ladder;

    Rank(String prefix, String color, int ladder) {
        this.prefix = prefix;
        this.color = color;
        this.ladder = ladder;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getColor() {
        return color;
    }

    public int getLadder() {
        return ladder;
    }

}
