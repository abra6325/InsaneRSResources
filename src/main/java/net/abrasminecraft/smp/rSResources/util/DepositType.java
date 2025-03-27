package net.abrasminecraft.smp.rSResources.util;

public enum DepositType {;
    public static int OIL = 1;
    public static int MINERAL = 2;
    public static int FERTILITY = 3;
    public static int getFromString(String s){
        switch(s){
            case "OIL": return 1;
            case "FERTILITY": return 2;
            case "MINERALS" : return 3;
            default: return 3;
        }
    }
}
