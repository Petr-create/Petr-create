package com.company;

import java.util.HashSet;
import java.util.Set;

public class DataEntryValidation {
    public boolean numberAndOddness(String[] args){
        if(args.length < 3 || args.length % 2 == 0){
            System.out.println("You must enter three or more values. The total must be odd.\n" +
                    "For example: 1 2 3 4 5\n");
            return false;
        }
        return true;
    }

    public boolean definitionOfRepetitions(String[] args){
        Set<String> repetition = new HashSet<>();

        for (int i = 0; i < args.length; i++) {
            for (int j = i + 1; j < args.length; j++) {
                if(args[i].equals(args[j])){
                    repetition.add(args[i]);
                }
            }
        }

        if (repetition.size() != 0) {
            System.out.print("These values are repeated: ");
            for (String s : repetition) {
                System.out.print(s + " ");
            }
            System.out.println("\nYou must enter unique values! For example: 1 2 3 4 5");
            return false;
        }
        return true;
    }

}
