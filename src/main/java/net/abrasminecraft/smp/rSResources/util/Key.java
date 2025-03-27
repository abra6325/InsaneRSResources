package net.abrasminecraft.smp.rSResources.util;

import net.abrasminecraft.smp.rSResources.RSResources;
import org.bukkit.NamespacedKey;

public class Key {
    public static NamespacedKey getKey(String s){ return new NamespacedKey(RSResources.getMain(),s);}
}
