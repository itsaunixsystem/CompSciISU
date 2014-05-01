package com.github.itsaunixsystem.chunks;

import java.util.Random;

/**
 * Created by alex on 4/30/2014.
 */
public class LootTable
{
    public static Random rand = new Random();

    private static int type, rarity;
    //private static Mod[] mods;

    private static final int ITEM_BASE = 3, RARITY = 3;

    public static Item getItem(int implicitLevel, int mobMod)
    {
        type = rand.nextInt(ITEM_BASE);
        getRarity(rand.nextInt(100), mobMod);
        return null;

    }

    private static int getRarity(int i, int mod)
    {
        if(i <= 60 - mod * 0.75)
        {
                return 0;
        }
        else if(i > 60 * 0.75 && i <= 95 - mod * 0.5)
        {
            return 1;
        }
        else
        {
            return 2;
        }
    }
}
