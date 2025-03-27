package net.abrasminecraft.smp.rSResources.util;

import net.abrasminecraft.smp.rSResources.items.*;
import org.bukkit.inventory.ItemStack;

public class Config {
//    public static int minOilAmount;
//    public static int maxOilAmount;
//
//    public static int minFertilityAmount;
//    public static int maxFertilityAmount;
//    public static int
    public static enum ItemID {HI;
            public static final int DETECTOR=1;
            public static final int OIL_RIG = 2;
            public static final int DIESEL_GENERATOR = 3;
            public static final int CREATIVE_SOURCE = 4;
            public static final int ENERGY_RELAY = 5;
            public static final int RELAY_LINKER = 6;
            public static ItemStack getFromId(int id){
                switch (id){
                    case DETECTOR:
                        return DepositDetectorItem.get();
                    case OIL_RIG:
                        return OilExtractorItem.get();
                    case DIESEL_GENERATOR:
                        return DieselGeneratorItem.get();
                    case CREATIVE_SOURCE:
                        return CreativeEnergySourceItem.get();
                    case ENERGY_RELAY:
                        return EnergyRelayItem.get();
                    case RELAY_LINKER:
                        return RelayLinkerItem.get();
                    default:
                        return null;
                }
            }


};
    public static enum Direction {HI;
        public static final int SUPPLY = 1;
        public static final int EXTRACT = 2;
        public static final int IDLE = 3;
    };
    public static int OIL_DRAIN_RATE = 10;
    public static int OIL_RIG_ELECTRICITY_CONSUMPTION = 80;
    public static int GENERATOR_OIL_CONSUMPTION =20;
    public static int GENERATOR_ELECTRICITY_PRODUCTION = 170;
    public static int REDSTONE_ENERGY_CONSUMPTION = 5;
    public static int RELAY_TRANSFER_RATE = 30;

}
