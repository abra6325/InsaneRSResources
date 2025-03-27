package net.abrasminecraft.smp.rSResources;

import net.abrasminecraft.smp.rSResources.commands.SpawnNewDepositCommand;
import net.abrasminecraft.smp.rSResources.events.RightClickItemEvents;
import net.abrasminecraft.smp.rSResources.events.depletionEvents.ChunkLoadEvent;
import net.abrasminecraft.smp.rSResources.util.BlockTaskAttacher;
import net.abrasminecraft.smp.rSResources.util.DataManager;
import net.abrasminecraft.smp.rSResources.util.RegisterCustomRecipes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;

public final class RSResources extends JavaPlugin {
    private static RSResources main;
    public static RSResources getMain(){
        return main;
    }
    public static final Long  SEED =  Long.parseLong("259259259114514");

    @Override
    public void onEnable() {
        main = this;
        RegisterCustomRecipes.register();
        System.out.println("ENABLEFD");
        Bukkit.getPluginCommand("spawnnewdeposit").setExecutor(new SpawnNewDepositCommand());
        getServer().getPluginManager().registerEvents(new ChunkLoadEvent(),this);
        getServer().getPluginManager().registerEvents(new RightClickItemEvents(),this);
        File f = DataManager.getFile("blockNBTs/");
        if(f.isDirectory()){
            for(File file : f.listFiles()){
                try {

                    JSONObject obj = DataManager.readJSONFromFile(f.getName() + "/" +file.getName());
                    System.out.println(obj);
                    if(!obj.isEmpty()){
                        World w = Bukkit.getWorld(file.getName().split("-")[3].replace(".json",""));
                        String wName = w.getName();
                        int ID = Math.toIntExact((Long) obj.get("id"));
                        Location bLoc = new Location(w,Double.parseDouble(file.getName().split("-")[0]),Double.parseDouble(file.getName().split("-")[1]),Double.parseDouble(file.getName().split("-")[2]));
                        System.out.println(bLoc);
                        BlockTaskAttacher.attach(bLoc,wName,ID);
                    }else{
                        file.delete();
                    }
                } catch (ParseException e) {
                    System.out.println("error reading file");
                }

            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("disabling");
        File f = DataManager.getFile("blockNBTs/");
        if(f.isDirectory()){
            for(File file : f.listFiles()){
                try {

                    JSONObject obj = DataManager.readJSONFromFile(f.getName() + "/" +file.getName());
                    System.out.println(obj);
                    if(obj.isEmpty()){
                        file.deleteOnExit();
                    }
                } catch (ParseException e) {
                    System.out.println("error reading file");
                }

            }
        }
    }
}
