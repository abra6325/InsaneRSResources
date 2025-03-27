package net.abrasminecraft.smp.rSResources.commands;

import net.abrasminecraft.smp.rSResources.util.ChunkSpawningUtil;
import net.abrasminecraft.smp.rSResources.util.DepositType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpawnNewDepositCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player player){
            ChunkSpawningUtil.spawnDeposit(player.getWorld().getChunkAt(player.getLocation()),player, DepositType.getFromString(strings[0]));
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        List<String> lString = Arrays.asList("OIL","FERTILITY","MINERALS");
        return lString;
    }
}
