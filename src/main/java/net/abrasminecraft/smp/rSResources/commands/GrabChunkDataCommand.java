package net.abrasminecraft.smp.rSResources.commands;

import net.abrasminecraft.smp.rSResources.util.DepletionManager;
import net.abrasminecraft.smp.rSResources.util.depletion.ChunkResourcesManager;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GrabChunkDataCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {


//        Random randomizer = new Random(RSResources.SEED);
//        int surex = 0;
//        int surey = 0;
//        for(int i=0;i< DepletionManager.chunkDatas.length;i++){
//            for(int j=0;j<DepletionManager.chunkDatas.length;j++){
//                int gen = randomizer.nextInt(100);
//                if(gen<=8){
//
//                    //Spawn resource deposit around this chunk
//                    int depositRadius = randomizer.nextInt(1,6);
//                    int depositAmountMinerals = randomizer.nextInt(5000,200000);
//                    int depositAmountOils = randomizer.nextInt(5000,200000);
//                    for(int x=Math.max(0,i-depositRadius);x<=Math.min(DepletionManager.chunkDatas.length-1,i+depositRadius);x++){
//                        for(int y=Math.max(0,j-depositRadius);y<=Math.min(DepletionManager.chunkDatas.length-1,j+depositRadius);y++){
////                            System.out.println("adding stuff for chunk"+x+" "+y);
//                            int dx = x-depositRadius;
//                            int dy = y-depositRadius;
//                            int dist = dx*dx + dy*dy;
//                            int rad = (int) Math.sqrt(dist);
//                            if(dist <= depositRadius*depositRadius){
//
//                                int mineralDeposit = randomizer.nextInt(1,101);
//                                int oilDeposit = randomizer.nextInt(1,101);
//                                if(mineralDeposit <=60){
//                                    DepletionManager.chunkDatas[x][y].minerals += depositAmountMinerals * (depositRadius-rad)/depositRadius;
//                                }
//                                if(oilDeposit <= 60){
//                                    DepletionManager.chunkDatas[x][y].oil += depositAmountOils * (depositRadius-rad)/depositRadius;
//                                }
//                                if(mineralDeposit >60 && oilDeposit >60){
//                                    int choose = randomizer.nextInt(1,3);
//                                    if(choose == 1){
//                                        DepletionManager.chunkDatas[x][y].minerals += depositAmountMinerals * (depositRadius-rad)/depositRadius;
//                                    }else if(choose == 2){
//                                        DepletionManager.chunkDatas[x][y].oil += depositAmountOils * (depositRadius-rad)/depositRadius;
//                                    }
//
//                                }
//                                surex = x;
//                                surey = y;
//
//                            }
//
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println(DepletionManager.chunkDatas[surex][surey].minerals+" "+DepletionManager.chunkDatas[surex][surey].oil+" "+DepletionManager.chunkDatas[surex][surey].fertility);
//        DepletionManager.saveToJSON();

        String name = commandSender.getName();
        if(Bukkit.getPlayer(name) == null){
            commandSender.sendMessage("failed");
            return false;
        }
        Player p = Bukkit.getPlayer(name);
        Chunk chunk = p.getWorld().getChunkAt(p.getLocation());
        ChunkResourcesManager manager = DepletionManager.loadFromChunk(chunk);
        commandSender.sendMessage(manager.oil + " " + manager.fertility + " " + manager.minerals + " " +manager.isModified);
        return true;
    }

}
