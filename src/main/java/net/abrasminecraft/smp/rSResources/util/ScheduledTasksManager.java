package net.abrasminecraft.smp.rSResources.util;

import org.bukkit.Location;

import java.util.Map;

public class ScheduledTasksManager {
    public static Map<Location, Integer> allTasks = new java.util.HashMap<>();
    public static void addTask(Location location, int taskID){
        allTasks.put(location, taskID);
    }
    public static void removeTask(Location location){
        allTasks.remove(location);
    }
    public static int getTask(Location location){
        return allTasks.get(location);
    }
}
