package net.abrasminecraft.smp.rSResources.events.depletionEvents;

import net.abrasminecraft.smp.rSResources.util.ChunkSpawningUtil;
import net.abrasminecraft.smp.rSResources.util.DepletionManager;
import net.abrasminecraft.smp.rSResources.util.DepositType;
import net.abrasminecraft.smp.rSResources.util.SeedRandomizer;
import net.abrasminecraft.smp.rSResources.util.depletion.ChunkResourcesManager;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ChunkLoadEvent implements Listener {
    @EventHandler
    public static void onNewChunkLoaded(PlayerMoveEvent ev){
        Player player = ev.getPlayer();
        Chunk thisChunk = player.getWorld().getChunkAt(player.getLocation());
        if(DepletionManager.loadFromChunk(thisChunk).isModified == 0){
            int spawnDeposit = SeedRandomizer.randomizer.nextInt(0,101);
            if(spawnDeposit<=6){
                  int whichDeposit = SeedRandomizer.randomizer.nextInt(1,101);
                  if(whichDeposit <=60){
                      ChunkSpawningUtil.spawnDeposit(thisChunk,player, DepositType.MINERAL);
                  }
                  if(whichDeposit >=60){
                      ChunkSpawningUtil.spawnDeposit(thisChunk,player,DepositType.OIL);
                  }
                  if(whichDeposit<=10){
                      ChunkSpawningUtil.spawnDeposit(thisChunk,player,DepositType.OIL);
                  }
//                int thisX = thisChunk.getX();
//                int thisZ = thisChunk.getZ();
//                int radius = SeedRandomizer.randomizer.nextInt(1,6);
//                int baseMinerals = SeedRandomizer.randomizer.nextInt(5000,35000);
//                int baseOils = SeedRandomizer.randomizer.nextInt(5000,35000);

//                for(int i=thisX-radius;i<=thisX+radius;i++){
//                    for(int j=thisZ-radius;j<=thisZ+radius;j++){
//                        int dx = i-thisX;
//                        int dy = j-thisZ;
//                        int rad = (int) Math.sqrt(dx*dx + dy*dy);
//                        if(dx*dx+dy*dy <= radius*radius){
//
//                            ChunkResourcesManager manager = DepletionManager.loadFromChunk(player.getWorld().getChunkAt(i,j));
//                            manager.isModified = 1;
//
//                            if(whichDeposit <=50){
//                                manager.minerals += baseMinerals * (radius - rad)/radius;
//                            }
//                            if(whichDeposit > 30 && whichDeposit <= 80){
//                                manager.oil += baseOils * (radius - rad)/radius;
//                            }
//                            if(whichDeposit > 80){
//                                int choose = SeedRandomizer.randomizer.nextInt(1,3);
//                                if(choose == 1){
//                                    manager.minerals += baseMinerals * (radius - rad)/radius;
//                                }else if(choose == 2){
//                                    manager.oil += baseOils * (radius - rad)/radius;
//                                }
//                            }
//                            DepletionManager.saveToChunk(player.getWorld().getChunkAt(i,j),manager);
//                        }
//                    }
//                }
            }else{
                ChunkResourcesManager manager = DepletionManager.loadFromChunk(thisChunk);
                manager.isModified = 1;
                DepletionManager.saveToChunk(thisChunk,manager);
            }
        }
    }
}
