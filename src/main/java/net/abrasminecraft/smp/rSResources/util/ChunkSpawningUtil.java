package net.abrasminecraft.smp.rSResources.util;

import net.abrasminecraft.smp.rSResources.util.depletion.ChunkResourcesManager;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

public class ChunkSpawningUtil {
    public static void spawnDeposit(Chunk thisChunk, Player player,int type){
        int thisX = thisChunk.getX();
        int thisZ = thisChunk.getZ();
        int radius = SeedRandomizer.randomizer.nextInt(1,6);
        int baseMinerals = SeedRandomizer.randomizer.nextInt(5000,35000);
        int baseOils = SeedRandomizer.randomizer.nextInt(5000,35000);
        int baseFertilities = SeedRandomizer.randomizer.nextInt(9000,100000);
        for(int i=thisX-radius;i<=thisX+radius;i++){
            for(int j=thisZ-radius;j<=thisZ+radius;j++){
                int dx = i-thisX;
                int dy = j-thisZ;
                int rad = (int) Math.sqrt(dx*dx + dy*dy);
                if(dx*dx+dy*dy <= radius*radius){

                    ChunkResourcesManager manager = DepletionManager.loadFromChunk(player.getWorld().getChunkAt(i,j));
                    manager.isModified = 1;

                    if(type == DepositType.MINERAL){
                        manager.minerals += baseMinerals * (radius - rad)/radius;
                    }
                    else if(type == DepositType.OIL ){
                        manager.oil += baseOils * (radius - rad)/radius;
                    }else if(type == DepositType.FERTILITY){
                        manager.fertility += baseFertilities * (radius - rad)/radius;
                    }
                    DepletionManager.saveToChunk(player.getWorld().getChunkAt(i,j),manager);
                }
            }
        }
    }
}
