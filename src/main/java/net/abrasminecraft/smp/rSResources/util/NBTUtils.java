package net.abrasminecraft.smp.rSResources.util;

import jline.internal.Nullable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.TileState;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R1.block.CraftBlock;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.Objects;

public class NBTUtils {
    public static boolean verifyItem(ItemStack itemStack){
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        CompoundTag tag = nmsStack.getOrCreateTag();
        return tag.getBoolean(Key.getKey("identifier").toString());
    }
    public static boolean verifyItem(net.minecraft.world.item.ItemStack nmsStack){
        CompoundTag tag = nmsStack.getOrCreateTag();
        return tag.getBoolean(Key.getKey("identifier").toString());
    }
    public static int getID(ItemStack itemStack){
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        CompoundTag tag = nmsStack.getOrCreateTag();
        return tag.getInt(Key.getKey("id").toString());
    }
    public static Integer getNBTInt(ItemStack itemStack,String key){
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        CompoundTag tag = nmsStack.getOrCreateTag();
        if(tag.contains(Key.getKey(key).toString())){
        return tag.getInt(Key.getKey(key).toString());}
        else return null;
    }
    public static ItemStack setNBTInt(ItemStack itemStack,String key,int value){
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        CompoundTag tag = nmsStack.getOrCreateTag();
        tag.putInt(Key.getKey(key).toString(),value);
        nmsStack.setTag(tag);

        return CraftItemStack.asBukkitCopy(nmsStack);
    }
    public static boolean verifyBlock(org.bukkit.block.Block itemStack){
        return Boolean.TRUE.equals(getCustomNBTBool(itemStack.getLocation(),"value"));
    }
    public static int getID(Block itemStack){
        Integer ret = getCustomNBTInt(itemStack.getLocation(),"id");
        if(ret == null) return 0;
        else return ret;
    }
    public static String getNBTForm(Location loc, World world){
        return "blockNBTs/"+loc.getBlockX()+"-"+loc.getBlockY()+"-"+loc.getBlockZ()+"-"+world.getName()+".json";
    }
    public static Integer getCustomNBTInt(Location loc,String key){
        try {

            JSONObject obj = DataManager.readJSONFromFile(getNBTForm(loc, loc.getWorld()));
            if(!obj.isEmpty()){
                if(obj.get(key) instanceof Long l){
                    return Math.toIntExact(l);
                }else {
                    System.out.println(obj.get(key).getClass().toString());
                    return null;
                }
            }
        } catch (ParseException e) {
            return null;
        }
        return null;

    }
    public static Boolean getCustomNBTBool(Location loc,String key){
        try {
            JSONObject obj = DataManager.readJSONFromFile(getNBTForm(loc,loc.getWorld()));
            if(!obj.isEmpty()){
                if(obj.get(key) instanceof Boolean){
                    return (Boolean) obj.get(key);
                }else return null;
            }
        } catch (ParseException e) {
            return null;
        }
        return null;

    }
    public static void saveCustomNBT(Location loc,JSONObject obj){

        DataManager.saveStringToFile(getNBTForm(loc,loc.getWorld()),obj.toJSONString());

    }
    public static JSONObject getCustomNBT(Location loc){
        try {
            return DataManager.readJSONFromFile(getNBTForm(loc,loc.getWorld()));
        } catch (ParseException e) {
            return null;
        }
    }
}
