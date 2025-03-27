package net.abrasminecraft.smp.rSResources.util.depletion;

public class ChunkResourcesManager {
    public int oil = 0;
    public int fertility = 0;
    public int minerals = 0;
    public int isModified = 0;
    public int electricitySupply;
    public int oilSupply;

    public ChunkResourcesManager(int oil, int fertility,int minerals,int electricitySupply, int oilSupply){
        this.oil = oil;
        this.fertility = fertility;
        this.minerals = minerals;
        this.isModified = 0;
        this.electricitySupply = electricitySupply;
        this.oilSupply = oilSupply;
    }


}
