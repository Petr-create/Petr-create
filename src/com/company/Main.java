package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static Map<String, String> mapValues = new HashMap<>();
    static Map <Integer, String> detrmWinner = new HashMap<>();

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {

        DataEntryValidation dev = new DataEntryValidation();
        if(!dev.numberAndOddness(args) | !dev.definitionOfRepetitions(args)){
            System.exit(0);
        }

        fillingMaps(args);
        play();
    }

    public static void fillingMaps(String[] args){
        for (int i = 0; i < args.length; i++) {
            mapValues.put(Integer.toString(i + 1), args[i]);
        }
        mapValues.put("0", "exit");
        mapValues.put("?", "help");

        for(Map.Entry<String, String> pair : mapValues.entrySet()){
            if(pair.getKey().equals("0") || pair.getKey().equals("?")){
                continue;
            }
            detrmWinner.put(Integer.parseInt(pair.getKey()), pair.getValue());
        }
    }

    public static void play() throws IOException, InvalidKeyException, NoSuchAlgorithmException {
        HmacAndKey hmacAndKey = new HmacAndKey();
        Whowinbin whowinbin = new Whowinbin();
        TableHelp tableHelp = new TableHelp();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SecureRandom random = new SecureRandom();
        int count = 0;
        String user;
        do{
            String key = hmacAndKey.key();
            int pc = random.nextInt(mapValues.size() - 2) + 1;
            System.out.println("\n\nHMAC: " + hmacAndKey.hmac(pc));
            menu();
            count++;
            do{
                System.out.println("Enter your move: ");

                user = reader.readLine();

                if(user.equals("?")){
                    tableHelp.help(detrmWinner);
                }

                if(user.equals("0") && count % 2 != 0 && count != 1){
                    break;
                }
            } while (user.equals("?") || user.equals("0") || user.equals("") || Integer.parseInt(user) < 0 || Integer.parseInt(user) > detrmWinner.size());
            if(user.equals("0")){
                return;
            }
            System.out.println("Your move: " + mapValues.get(user));
            System.out.println("Computer move: " + mapValues.get(Integer.toString(pc)));
            System.out.println("YOU: " + whowinbin.winner(detrmWinner.size(), pc, Integer.parseInt(user)));
            System.out.println("HMAC key: " + key);
        }while (true);
    }

    public static void menu(){
        System.out.println("Available moves:");
        for(Map.Entry<String, String> pair : mapValues.entrySet()){
            System.out.println(pair.getKey() + " - " + pair.getValue());
        }
    }
}
