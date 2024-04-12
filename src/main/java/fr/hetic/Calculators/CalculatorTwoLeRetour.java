package fr.hetic.Calculators;
import fr.hetic.FileHandler.FileHandler;

import java.io.FileNotFoundException;
import java.util.*;

import static fr.hetic.PrintUtil.PrintUtil.log;

public class CalculatorTwoLeRetour {
    public static void startProcessing(String args) {
        try {
            List<String> filePath = FileHandler.getFiles(args);
            if (!filePath.isEmpty()) {
                FileHandler.computeFiles(filePath);
            } else {
                log("Erreur", "il n'y a pas de fichiers d'opérations");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erreur: " + e.getMessage());

        }
    }

    public static Boolean verifyOpArgs(String[] args) {
        List<String> correctOperators = Arrays.asList("+", "-", "x", "*");
     try {
         if (args.length != 3) {
             throw new Exception("Number of arguments is not equal to three arguments");
         }
         if (!correctOperators.contains(args[2])) {
             throw new Exception("Wrong operator");
         }
        Integer.parseInt(args[0]);
         Integer.parseInt(args[1]);
         return true;
     } catch (Exception e) {
         log("Error: ",e.getMessage());
         return false;
     }
    }


}
