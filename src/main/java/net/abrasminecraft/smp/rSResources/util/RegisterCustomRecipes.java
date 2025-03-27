package net.abrasminecraft.smp.rSResources.util;

import net.abrasminecraft.smp.rSResources.RSResources;
import net.abrasminecraft.smp.rSResources.items.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;


public class RegisterCustomRecipes {
    public static void register(){
        ItemStack hoe = DepositDetectorItem.get();
        ShapelessRecipe recipe = new ShapelessRecipe(new NamespacedKey(RSResources.getMain(),"hoerecipe"),hoe);
        recipe.addIngredient(Material.IRON_HOE);
        recipe.addIngredient(Material.REDSTONE);
        Bukkit.addRecipe(recipe);
        ItemStack rig = OilExtractorItem.get();
        ShapedRecipe rigRecipe = new ShapedRecipe(new NamespacedKey(RSResources.getMain(),"rigrecipe"),rig);
        rigRecipe.shape("XXX","XAX","XAX");
        rigRecipe.setIngredient('X',Material.IRON_BLOCK);
        rigRecipe.setIngredient('A',Material.POINTED_DRIPSTONE);
        Bukkit.addRecipe(rigRecipe);

        ShapelessRecipe creativeRecipe = new ShapelessRecipe(new NamespacedKey(RSResources.getMain(),"creative_recipe"), CreativeEnergySourceItem.get());
        creativeRecipe.addIngredient(Material.COMMAND_BLOCK);
        Bukkit.addRecipe(creativeRecipe);

        ShapedRecipe generatorRecipe = new ShapedRecipe(new NamespacedKey(RSResources.getMain(),"generator_recipe"), DieselGeneratorItem.get());
        generatorRecipe.shape("XBX","XAX","XXX");
        generatorRecipe.setIngredient('X',Material.IRON_BLOCK);
        generatorRecipe.setIngredient('A',Material.FURNACE);
        generatorRecipe.setIngredient('B',Material.WATER_BUCKET);
        Bukkit.addRecipe(generatorRecipe);

        ShapedRecipe relayRecipe = new ShapedRecipe(new NamespacedKey(RSResources.getMain(),"relay_recipe"), EnergyRelayItem.get());
        relayRecipe.shape("XAX","XBX","XAX");
        relayRecipe.setIngredient('X',Material.IRON_BLOCK);
        relayRecipe.setIngredient('A',Material.REDSTONE);
        relayRecipe.setIngredient('B',Material.SCULK_SENSOR);
        Bukkit.addRecipe(relayRecipe);

        ShapelessRecipe linkerRecipe = new ShapelessRecipe(new NamespacedKey(RSResources.getMain(),"linker_recipe"), RelayLinkerItem.get());
        linkerRecipe.addIngredient(Material.SHEARS);
        linkerRecipe.addIngredient(Material.REDSTONE);
        Bukkit.addRecipe(linkerRecipe);
//        recipe = new ShapedRecipe(Key.getKey("metaldetector_recipe"),hoe);
//        recipe.shape("xxo");
//        recipe.shape("yzo");
//        recipe.shape("yoo");
//        recipe.setIngredient('x', Material.IRON_INGOT);
//        recipe.setIngredient('y',Material.STICK);
//        recipe.setIngredient('z',Material.COMPASS);
//        Bukkit.addRecipe(recipe);

    }
}
