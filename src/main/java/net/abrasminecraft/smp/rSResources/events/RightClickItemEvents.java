package net.abrasminecraft.smp.rSResources.events;

import net.abrasminecraft.smp.rSResources.RSResources;
import net.abrasminecraft.smp.rSResources.util.*;
import net.abrasminecraft.smp.rSResources.util.depletion.ChunkResourcesManager;
import net.itsrelizc.nbt.BlockMetadata;
import net.itsrelizc.nbt.NBT;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.commons.io.FileDeleteStrategy;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.inventory.HopperInventorySearchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;

public class RightClickItemEvents implements Listener {
    @EventHandler
    public static void onRightClick(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            //LODESTONE_PREVENTION
            if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
                if(e.getClickedBlock().getType() == Material.DROPPER){
                    Block b = e.getClickedBlock();
                    if(NBTUtils.verifyBlock(b) && NBTUtils.getID(b) == Config.ItemID.OIL_RIG){

                        e.setCancelled(true);
                    }

                }
                if(e.getClickedBlock().getType() == Material.ENCHANTING_TABLE){
                    Block b = e.getClickedBlock();
                    if(NBTUtils.verifyBlock(b) && NBTUtils.getID(b) == Config.ItemID.CREATIVE_SOURCE){
                        e.setCancelled(true);
                    }
                }
                if(e.getClickedBlock().getType() == Material.FURNACE){
                    Block b = e.getClickedBlock();
                    if(NBTUtils.verifyBlock(b) && NBTUtils.getID(b) == Config.ItemID.DIESEL_GENERATOR){
                        e.setCancelled(true);
                    }
                }
            }
            if(NBTUtils.verifyItem(e.getItem())){

                Player p = e.getPlayer();
                int id = NBTUtils.getID(e.getItem());
                if(id == Config.ItemID.DETECTOR){
                    //METAL DETECTOR
                    ChunkResourcesManager m = DepletionManager.loadFromChunk(p.getWorld().getChunkAt(p.getLocation()));
                    p.sendMessage("OILS:"+m.oil + " FERTILITY:" + m.fertility + " MINERALS:"+m.minerals +" "+ m.oilSupply +" "+m.electricitySupply);
                }else if(id == Config.ItemID.RELAY_LINKER && e.getHand() == EquipmentSlot.HAND){
                    if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
                    //RELAY LINKER
                        Block b = e.getClickedBlock();
                        ItemStack copyStack = e.getItem();
                        if(NBTUtils.verifyBlock(b)){
                            if(NBTUtils.getID(b) == Config.ItemID.ENERGY_RELAY){
                                Integer relayedBlockX = NBTUtils.getNBTInt(e.getItem(),"relayedBlockX");
                                Integer relayedBlockY = NBTUtils.getNBTInt(e.getItem(),"relayedBlockY");
                                Integer relayedBlockZ = NBTUtils.getNBTInt(e.getItem(),"relayedBlockZ");
                                Integer isLinking = NBTUtils.getNBTInt(e.getItem(),"linking");

                                if(isLinking==null || isLinking == 0){
                                    copyStack = NBTUtils.setNBTInt(e.getItem(),"relayedBlockX",b.getX());
                                    copyStack = NBTUtils.setNBTInt(copyStack,"relayedBlockY",b.getY());
                                    copyStack = NBTUtils.setNBTInt(copyStack,"relayedBlockZ",b.getZ());
                                    copyStack = NBTUtils.setNBTInt(copyStack,"linking",1);
                                    p.sendMessage("Relay Linker set to "+b.getX()+" "+b.getY()+" "+b.getZ());
                                    ItemStack finalCopyStack = copyStack;
                                    Bukkit.getScheduler().scheduleSyncDelayedTask(RSResources.getMain(),() -> {e.getPlayer().getInventory().setItemInMainHand(finalCopyStack);},1);
                                    JSONObject x = NBTUtils.getCustomNBT(b.getLocation());
                                    x.put("linkx",b.getX());
                                    x.put("linky",b.getY());
                                    x.put("linkz",b.getZ());
                                    x.put("direction",Config.Direction.IDLE);
                                    NBTUtils.saveCustomNBT(b.getLocation(),x);
                                }else{
                                    JSONObject x = NBTUtils.getCustomNBT(b.getLocation());
                                    x.put("linkx",relayedBlockX);
                                    x.put("linky",relayedBlockY);
                                    x.put("linkz",relayedBlockZ);
                                    x.put("direction",Config.Direction.EXTRACT);
                                    NBTUtils.saveCustomNBT(b.getLocation(),x);
                                    p.sendMessage("Relay Linker set to "+b.getX()+" "+b.getY()+" "+b.getZ() + " direction: EXTRACT" + " from "+relayedBlockX+" "+relayedBlockY+" "+relayedBlockZ);
                                }

                            }
                        }
                    }else{
                        e.getPlayer().getInventory().setItemInMainHand(NBTUtils.setNBTInt(e.getItem(),"linking",0));

                    }
                }
            }

        }
    }

    @EventHandler
    public static void onDestroyBlock(BlockBreakEvent e) throws IOException {
        Block b = e.getBlock();
        if(NBTUtils.verifyBlock(b)){


            ItemStack drop = Config.ItemID.getFromId(NBTUtils.getID(b));
            int taskId = ScheduledTasksManager.getTask(b.getLocation());
            Bukkit.getScheduler().cancelTask(taskId);
            ScheduledTasksManager.removeTask(b.getLocation());
            DataManager.saveStringToFile(NBTUtils.getNBTForm(b.getLocation(),b.getWorld()),new JSONObject().toJSONString());
            File f = DataManager.getFile(NBTUtils.getNBTForm(b.getLocation(),b.getWorld()));
            f.delete();
            if(drop!=null){
                e.setDropItems(false);
                e.setExpToDrop(0);
                e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(),drop);
                System.out.println("hell");
            }
        }
    }
    @EventHandler
    public static void onPlaceBlock(BlockPlaceEvent e){

        ItemStack blockItem = e.getItemInHand();
        if(NBTUtils.verifyItem(blockItem)){
            int generatedTask = 0;
            Location bLoc = e.getBlockPlaced().getLocation();
            String wName = e.getBlockPlaced().getWorld().getName();
            int ID = NBTUtils.getID(blockItem);
            BlockTaskAttacher.attach(bLoc,wName,ID);
            if(ID == Config.ItemID.ENERGY_RELAY){
                JSONObject obj = new JSONObject();
                obj.put("value",true);
                obj.put("id",Config.ItemID.ENERGY_RELAY);
                obj.put("linkx",bLoc.getBlockX());
                obj.put("linky",bLoc.getBlockY());
                obj.put("linkz",bLoc.getBlockZ());
                obj.put("direction",Config.Direction.IDLE);
                NBTUtils.saveCustomNBT(bLoc,obj);
            }
        }

    }
    @EventHandler
    public static void onRedstoneActivation(HopperInventorySearchEvent e){
        System.out.printf("redstone event");
        if(NBTUtils.verifyBlock(e.getSearchBlock())){
            if(NBTUtils.getID(e.getSearchBlock()) == Config.ItemID.OIL_RIG){

                e.setInventory(e.getInventory());
            }
        }
    }
    @EventHandler
    public static void blockRedstoneEvent(BlockRedstoneEvent e){
        ChunkResourcesManager m = DepletionManager.loadFromChunk(e.getBlock().getChunk());
        if(NBTUtils.verifyBlock(e.getBlock())){
            e.setNewCurrent(e.getOldCurrent());
            return;
        }
        if(m.electricitySupply >= Config.REDSTONE_ENERGY_CONSUMPTION){

            m.electricitySupply -= Config.REDSTONE_ENERGY_CONSUMPTION;
            DepletionManager.saveToChunk(e.getBlock().getChunk(),m);
        }else{
            e.setNewCurrent(e.getOldCurrent());
        }
    }


}
