package net.abrasminecraft.smp.rSResources.items;

import net.abrasminecraft.smp.rSResources.util.Config;
import net.abrasminecraft.smp.rSResources.util.Key;
import net.minecraft.nbt.CompoundTag;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EnergyRelayItem {
    public static ItemStack get(){
        org.bukkit.inventory.ItemStack stack = new org.bukkit.inventory.ItemStack(Material.SCULK_SENSOR);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("Energy Relay");
        stack.setItemMeta(meta);

        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(stack);
        CompoundTag tag = nmsStack.hasTag() ? nmsStack.getTag() : new CompoundTag();
        tag.putBoolean(Key.getKey("identifier").toString(),true);
        tag.putInt(Key.getKey("id").toString(), Config.ItemID.ENERGY_RELAY);
        nmsStack.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }
}
