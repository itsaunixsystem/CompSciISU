package com.github.itsaunixsystem.chunks.item;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by alex on 4/30/2014.
 */
public class LootTable {
    public static Random rand = new Random();
    private static final int ITEM_BASE = 3, COMMON = 0, BLUE = 2, RARE = 4;
    private static final int SWORD = 1, BOW = 2;
    public static int MOD_PDMG = 1, MOD_FDMG = 2, MOD_WDMG = 3, MOD_EDMG = 4, MOD_ADMG = 5;

    public static void getItem(int implicitLevel, int mobMod) {
        int type, rarity;
        WepMod[] mods;
        type = rand.nextInt(ITEM_BASE);

        if (type == 0) {
            //GIVE HP POT
        } else {
            rarity = getRarity(rand.nextInt(100), mobMod);
            mods = generateMods(rarity, filterMods(implicitLevel, type));
            //PRODUCE WEAPON WITH MODS / RARITY ETC
        }
    }

    private static int getRarity(int i, int mod) {
        if (i <= 60 - mod * 0.75) {
            return COMMON;
        } else if (i > 60 * 0.75 && i <= 95 - mod * 0.5) {
            return BLUE;
        } else {
            return RARE;
        }
    }

    private static WepMod[] generateMods(int rarity, ArrayList<WepMod> modArray) {
        WepMod[] mods = new WepMod[rarity];
        for(int i = 0; i < rarity; i++){
            mods[i] = modArray.get(rand.nextInt(modArray.size()));
        }
        return mods;
    }

    private static ArrayList<WepMod> filterMods(int implicitLevel, int type){
        ArrayList<WepMod> mods = null;
        for(WepMod w : WepMod.values()){
            if(w.impLevel == implicitLevel && w.itemType == type){
                mods.add(w);
            }
        }
        return mods;
    }

    private enum WepMod {
        PDMG_1(SWORD, MOD_PDMG, 1, 3, 1),
        ADMG_1(SWORD, MOD_ADMG, 1, 3, 1),
        EDMG_1(SWORD, MOD_EDMG, 1, 3, 1),
        FDMG_1(SWORD, MOD_FDMG, 1, 3, 1),
        WDMG_1(SWORD, MOD_WDMG, 1, 3, 1),;

        protected final int itemType, dmgType, dmgBot, dmgTop, impLevel;

        private WepMod(int type, int dtype, int dB, int dT, int ilvl) {
            this.itemType = type;
            this.dmgType = dtype;
            this.dmgBot = dB;
            this.dmgTop = dT;
            this.impLevel = ilvl;
        }
    }

}
