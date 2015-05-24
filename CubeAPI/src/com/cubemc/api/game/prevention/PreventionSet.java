package com.cubemc.api.game.prevention;

/**
 * Created by william_burns.
 * All the code and any API's associated with it
 * are not to be used anywhere else without written
 * consent of William Burns. 2014.
 * 06/04/2015
 */
public class PreventionSet {

    private boolean isCanPVP = false;
    private boolean isCanPVE = false;
    private boolean isCanPlayersTakeDamage = false;
    private boolean isCanMobsSpawn = false;
    private boolean isCanPlayersDropItems = false;
    private boolean isCanPlayersPickupItems = false;
    private boolean isCanPlayersBreakBlocks = false;
    private boolean isCanPlayersPlaceBlocks = false;
    private boolean isCanPlayersUseBuckets = false;

    public PreventionSet(){}

    public PreventionSet(boolean isCanPVP, boolean isCanPVE, boolean isCanPlayersTakeDamage, boolean isCanMobsSpawn, boolean isCanPlayersDropItems, boolean isCanPlayersPickupItems, boolean isCanPlayersBreakBlocks, boolean isCanPlayersPlaceBlocks, boolean isCanPlayersUseBuckets) {
        this.isCanPVP = isCanPVP;
        this.isCanPVE = isCanPVE;
        this.isCanPlayersTakeDamage = isCanPlayersTakeDamage;
        this.isCanMobsSpawn = isCanMobsSpawn;
        this.isCanPlayersDropItems = isCanPlayersDropItems;
        this.isCanPlayersPickupItems = isCanPlayersPickupItems;
        this.isCanPlayersBreakBlocks = isCanPlayersBreakBlocks;
        this.isCanPlayersPlaceBlocks = isCanPlayersPlaceBlocks;
        this.isCanPlayersUseBuckets = isCanPlayersUseBuckets;
    }

    public boolean isCanPVP() {
        return isCanPVP;
    }

    public void setCanPVP(boolean isCanPVP) {
        this.isCanPVP = isCanPVP;
    }

    public boolean isCanPVE() {
        return isCanPVE;
    }

    public void setCanPVE(boolean isCanPVE) {
        this.isCanPVE = isCanPVE;
    }

    public boolean isCanPlayersTakeDamage() {
        return isCanPlayersTakeDamage;
    }

    public void setCanPlayersTakeDamage(boolean isCanPlayersTakeDamage) {
        this.isCanPlayersTakeDamage = isCanPlayersTakeDamage;
    }

    public boolean isCanMobsSpawn() {
        return isCanMobsSpawn;
    }

    public void setCanMobsSpawn(boolean isCanMobsSpawn) {
        this.isCanMobsSpawn = isCanMobsSpawn;
    }

    public boolean isCanPlayersDropItems() {
        return isCanPlayersDropItems;
    }

    public void setCanPlayersDropItems(boolean isCanPlayersDropItems) {
        this.isCanPlayersDropItems = isCanPlayersDropItems;
    }

    public boolean isCanPlayersPickupItems() {
        return isCanPlayersPickupItems;
    }

    public void setCanPlayersPickupItems(boolean isCanPlayersPickupItems) {
        this.isCanPlayersPickupItems = isCanPlayersPickupItems;
    }

    public boolean isCanPlayersBreakBlocks() {
        return isCanPlayersBreakBlocks;
    }

    public void setCanPlayersBreakBlocks(boolean isCanPlayersBreakBlocks) {
        this.isCanPlayersBreakBlocks = isCanPlayersBreakBlocks;
    }

    public boolean isCanPlayersPlaceBlocks() {
        return isCanPlayersPlaceBlocks;
    }

    public void setCanPlayersPlaceBlocks(boolean isCanPlayersPlaceBlocks) {
        this.isCanPlayersPlaceBlocks = isCanPlayersPlaceBlocks;
    }

    public boolean isCanPlayersUseBuckets() {
        return isCanPlayersUseBuckets;
    }

    public void setCanPlayersUseBuckets(boolean isCanPlayersUseBuckets) {
        this.isCanPlayersUseBuckets = isCanPlayersUseBuckets;
    }
}
