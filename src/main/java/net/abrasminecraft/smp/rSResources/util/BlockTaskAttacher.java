package net.abrasminecraft.smp.rSResources.util;

import net.abrasminecraft.smp.rSResources.RSResources;
import net.abrasminecraft.smp.rSResources.util.depletion.ChunkResourcesManager;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.json.simple.JSONObject;

public class BlockTaskAttacher {
    public static void attach(Location bLoc,String wName,int ID){
        int generatedTask = 0;
        if(ID == Config.ItemID.OIL_RIG){
            JSONObject obj = new JSONObject();
            obj.put("value",true);
            obj.put("id",Config.ItemID.OIL_RIG);
            NBTUtils.saveCustomNBT(bLoc,obj);

            generatedTask = Bukkit.getScheduler().scheduleAsyncRepeatingTask(RSResources.getMain(), () -> {
                World world = Bukkit.getWorld(wName);
                if(world != null && world.isChunkLoaded(bLoc.getBlockX() >> 4, bLoc.getBlockZ() >> 4)) {
                    Block block = world.getBlockAt(bLoc);
                    if(block != null && NBTUtils.getID(block) == Config.ItemID.OIL_RIG){
                        Chunk c = block.getChunk();
                        ChunkResourcesManager manager = DepletionManager.loadFromChunk(c);
                        if(manager.oil > 0 && manager.electricitySupply > 0){
                            manager.oil -= Config.OIL_DRAIN_RATE;
                            manager.oilSupply += Config.OIL_DRAIN_RATE;
                            manager.electricitySupply -= Config.OIL_RIG_ELECTRICITY_CONSUMPTION;
                            if(manager.oil < 0) manager.oil = 0;
                            if(manager.electricitySupply < 0) manager.electricitySupply = 0;
                        }
                        DepletionManager.saveToChunk(c,manager);
                    }
                }
            },20L,20L);


        }else if(ID == Config.ItemID.CREATIVE_SOURCE){
            JSONObject obj = new JSONObject();
            obj.put("value",true);
            obj.put("id",Config.ItemID.CREATIVE_SOURCE);
            NBTUtils.saveCustomNBT(bLoc,obj);

            generatedTask = Bukkit.getScheduler().scheduleAsyncRepeatingTask(RSResources.getMain(), () -> {
                World world = Bukkit.getWorld(wName);

                Block block = world.getBlockAt(bLoc);
                if(block != null && NBTUtils.getID(block) == Config.ItemID.CREATIVE_SOURCE){
                    Chunk c = block.getChunk();
                    ChunkResourcesManager manager = DepletionManager.loadFromChunk(c);
                    manager.electricitySupply += 4000;
                    DepletionManager.saveToChunk(c,manager);
                }

            },20L,20L);
        }else if(ID == Config.ItemID.DIESEL_GENERATOR){
            JSONObject obj = new JSONObject();
            obj.put("value",true);
            obj.put("id",Config.ItemID.DIESEL_GENERATOR);
            NBTUtils.saveCustomNBT(bLoc,obj);
            generatedTask = Bukkit.getScheduler().scheduleAsyncRepeatingTask(RSResources.getMain(), () -> {
                World world = Bukkit.getWorld(wName);
                Block block = world.getBlockAt(bLoc);
                if(block != null && NBTUtils.getID(block) == Config.ItemID.DIESEL_GENERATOR){
                    Chunk c = block.getChunk();
                    ChunkResourcesManager manager = DepletionManager.loadFromChunk(c);
                    if(manager.oilSupply > 0){
                        manager.oilSupply -= Config.GENERATOR_OIL_CONSUMPTION;
                        manager.electricitySupply += Config.GENERATOR_ELECTRICITY_PRODUCTION;
                        if(manager.oilSupply < 0) manager.oilSupply = 0;
                    }
                    DepletionManager.saveToChunk(c,manager);
                }

            },20L,20L);

        }else if(ID == Config.ItemID.ENERGY_RELAY){

            generatedTask = Bukkit.getScheduler().scheduleAsyncRepeatingTask(RSResources.getMain(), () -> {
                World world = Bukkit.getWorld(wName);
                Block block = world.getBlockAt(bLoc);
                if(block != null && NBTUtils.getID(block) == Config.ItemID.ENERGY_RELAY){
                    Chunk c = block.getChunk();
                    ChunkResourcesManager manager = DepletionManager.loadFromChunk(c);
                    JSONObject loading = NBTUtils.getCustomNBT(bLoc);
                    Long x = (Long) loading.get("linkx");
                    Long y = (Long) loading.get("linky");
                    Long z = (Long) loading.get("linkz");
                    Chunk c2 = world.getBlockAt(Math.toIntExact(x), Math.toIntExact(y), Math.toIntExact(z)).getChunk();
                    if(!(c.getX() == c2.getX() && c.getZ() == c2.getZ())) {
                        ChunkResourcesManager manager2 = DepletionManager.loadFromChunk(c2);
                        if (manager2.electricitySupply > Config.RELAY_TRANSFER_RATE) {
                            manager2.electricitySupply -= Config.RELAY_TRANSFER_RATE;
                            manager.electricitySupply += Config.RELAY_TRANSFER_RATE;
                            if (manager2.electricitySupply < 0) manager2.electricitySupply = 0;
                        }
                        DepletionManager.saveToChunk(c2, manager2);
                        DepletionManager.saveToChunk(c, manager);
                    }

                }

            },20L,20L);


        }
        ScheduledTasksManager.addTask(bLoc,generatedTask);
    }
}
