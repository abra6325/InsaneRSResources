package net.abrasminecraft.smp.rSResources.util;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DataManager {
    public static File getFile(String fname){
        return new File(new File(System.getProperty("user.dir")).toString() + "\\database\\"+fname);


    }
    public static String getStringFromFile(String fname) {
        File f = getFile(fname);


        if(f.exists()){
            Scanner scan = null;
            try {
                scan = new Scanner(f);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            StringBuilder ret = new StringBuilder();
            while(scan.hasNextLine()){

                ret.append(scan.nextLine());
                ret.append("\n");
            }
            return ret.toString();



        }else{
            return "";
        }
    }
    public static JSONObject readJSONFromFile(String fname) throws ParseException {
        File f = getFile(fname);


        if(f.exists()){
            Scanner scan = null;
            try {
                scan = new Scanner(f);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            StringBuilder ret = new StringBuilder();
            while(scan.hasNextLine()){

                ret.append(scan.nextLine());
                ret.append("\n");
            }
            String r= ret.toString();
            JSONParser parser = new JSONParser();
            JSONObject rr = (JSONObject) parser.parse(r);

            return rr;



        }else{
            return new JSONObject();
        }
    }
    public static String getStringFromFile(File f) throws FileNotFoundException {

        if(f.exists()){
            Scanner scan = new Scanner(f);
            StringBuilder ret = new StringBuilder();
            while(scan.hasNextLine()){
                ret.append(scan.nextLine());
                ret.append("\n");
            }
            return ret.toString();

        }else{
            return "";
        }
    }
    public static void saveStringToFile(String fname,String sav) {
        File f = getFile(fname);
        System.out.println(f.getAbsolutePath());
        if(f.exists()){
            System.out.println("EXISTS");
            try {
                try (PrintWriter out = new PrintWriter(f)) {
                    out.println(sav);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else{
            try {
                System.out.println(f.createNewFile());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try (PrintWriter out = new PrintWriter(f)) {
                out.println(sav);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
