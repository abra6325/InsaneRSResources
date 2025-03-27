package net.abrasminecraft.smp.rSResources.items;


import net.abrasminecraft.smp.rSResources.util.Config;
import net.abrasminecraft.smp.rSResources.util.Key;
import net.itsrelizc.nbt.NBT;
import net.minecraft.nbt.CompoundTag;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_20_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.units.qual.C;

public class DepositDetectorItem {
    public static ItemStack get(){
        ItemStack stack = new ItemStack(Material.IRON_HOE);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("Deposit Detector");
        stack.setItemMeta(meta);

        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(stack);
        CompoundTag tag = nmsStack.hasTag() ? nmsStack.getTag() : new CompoundTag();
        tag.putBoolean(Key.getKey("identifier").toString(),true);
        tag.putInt(Key.getKey("id").toString(), Config.ItemID.DETECTOR);
        nmsStack.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsStack);
    }
}
