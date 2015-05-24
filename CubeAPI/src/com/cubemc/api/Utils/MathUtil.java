package com.cubemc.api.Utils;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by henry on 4/2/15.
 */
public class MathUtil {

    public MathUtil(){}

    public static Random random = new Random();

    public static double trim(int degree, double d) {
        String format = "#.#";

        for(int twoDForm = 1; twoDForm < degree; ++twoDForm) {
            format = format + "#";
        }

        DecimalFormat var5 = new DecimalFormat(format);
        return Double.valueOf(var5.format(d)).doubleValue();
    }

    public static int r(int i) {
        return random.nextInt(i);
    }

    public static double offset2d(Entity a, Entity b) {
        return offset2d(a.getLocation().toVector(), b.getLocation().toVector());
    }

    public static double offset2d(Location a, Location b) {
        return offset2d(a.toVector(), b.toVector());
    }

    public static double offset2d(Vector a, Vector b) {
        a.setY(0);
        b.setY(0);
        return a.subtract(b).length();
    }

    public static double offset(Entity a, Entity b) {
        return offset(a.getLocation().toVector(), b.getLocation().toVector());
    }

    public static double offset(Location a, Location b) {
        return offset(a.toVector(), b.toVector());
    }

    public static double offset(Vector a, Vector b) {
        return a.subtract(b).length();
    }

}
