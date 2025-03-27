package net.abrasminecraft.smp.rSResources.util;


import com.google.gson.Gson;
import net.abrasminecraft.smp.rSResources.RSResources;
import net.abrasminecraft.smp.rSResources.util.depletion.ChunkResourcesManager;
import net.itsrelizc.nbt.ChunkMetadata;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DepletionManager {
    public static ChunkResourcesManager loadFromChunk(Chunk chunk){
        int[] data = chunk.getPersistentDataContainer().get(new NamespacedKey(RSResources.getMain(),"resources"), PersistentDataType.INTEGER_ARRAY);
        if (data != null) {
            ChunkResourcesManager manager = new ChunkResourcesManager(data[0],data[1],data[2],data[4], data[5]);
            manager.isModified = data[3];
            return manager;
        }else{
            return new ChunkResourcesManager(SeedRandomizer.randomizer.nextInt(50,199),SeedRandomizer.randomizer.nextInt(9000,12000),SeedRandomizer.randomizer.nextInt(50,199),0,0);
        }

    }
    public static void saveToChunk(Chunk chunk,ChunkResourcesManager manager){
        int[] data = {manager.oil,manager.fertility,manager.minerals,manager.isModified,manager.electricitySupply,manager.oilSupply};
        chunk.getPersistentDataContainer().set(new NamespacedKey(RSResources.getMain(),"resources"),PersistentDataType.INTEGER_ARRAY,data);
    }



//    public static ChunkResourcesManager[][] chunkDatas = new ChunkResourcesManager[2048][2048];
//    public static void init(){
//        for(int i=0;i<chunkDatas.length;i++){
//            for(int j=0;j<chunkDatas.length;j++){
//                chunkDatas[i][j] = new ChunkResourcesManager(0,9000000,0);
//            }
//        }
//        System.out.println("hello");
//    }
//    public static ChunkResourcesManager getManager(int x, int y){
//        return chunkDatas[x+1024][1024-y];
//    }
////    public static ChunkResourcesManager getManagerNBT()
//    public static void loadFromJson()  {
//        JSONObject read1 = null;
//        try {
//            read1 = DataManager.readJSONFromFile("chunkdata.json");
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        if (Objects.equals(read1, new JSONObject())){
//            System.out.println("saving initial save");
//            saveToJSON();
//            return;
//        }
//        chunkDatas = new ChunkResourcesManager[2048][2048];
//        Gson gson = new Gson();
//
//        Map read = gson.fromJson(read1.toJSONString(),Map.class);
//        ArrayList<ArrayList<ArrayList<Double>>> readData = (ArrayList<ArrayList<ArrayList<Double>>>) read.get("chunkDatas");
//
//        for(int i=0;i<readData.size();i++){
//            for(int j=0;j<readData.get(0).size();j++){
//                ArrayList<Double> tmp = readData.get(i).get(j);
//
//                ChunkResourcesManager tmpManager = new ChunkResourcesManager(tmp.get(0), tmp.get(1), tmp.get(2));
//                chunkDatas[i][j] = tmpManager;
//
//
//            }
//        }
//
//
//    }
//    public static void saveToJSON(){
//        Map<String, Integer[][][]> tmpMap1 = new HashMap<>();
//        Integer[][][] tmp1 = new Integer[2048][2048][3];
//        for(int i=0;i<chunkDatas.length;i++){
//            for(int j=0;j<chunkDatas.length;j++){
//                tmp1[i][j] = new Integer[]{chunkDatas[i][j].oil, chunkDatas[i][j].fertility, chunkDatas[i][j].minerals};
//            }
//        }
////        ArrayList<ArrayList<ArrayList<Integer>>> readData = new ArrayList<>();
////        for(int i=0;i<chunkDatas.length;i++){
////            for(int j=0;j<chunkDatas.length;j++){
////                read
////            }
////        }
//
//        tmpMap1.put("chunkDatas",tmp1);
//        Gson gson = new Gson();
//        DataManager.saveStringToFile("chunkdata.json",gson.toJson(tmpMap1));
//
//    }
}
