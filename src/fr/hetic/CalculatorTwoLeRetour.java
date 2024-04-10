package fr.hetic;
import fr.hetic.fileHandler.FileHandler;

import java.io.FileNotFoundException;
import java.util.*;

import static fr.hetic.PrintUtil.printAny;

public class CalculatorTwoLeRetour {
    public static void startProcessing(String args) throws FileNotFoundException {
                List<String> filePath = FileHandler.getFiles(args);
                if (!filePath.isEmpty()) {
                    FileHandler.computeFiles(filePath);
                } else {
                    printAny("Erreur", "il n'y a pas de fichiers d'opérations");
                }

    }

    public static Boolean verifyOpArgs(String[] args) {
        List<String> correctOperators = Arrays.asList("+", "-", "x", "*");
     try {
         if (args.length != 3) {
             throw new Exception("Number of arguments is not equal to three");
         }
         if (!correctOperators.contains(args[2])) {
             throw new Exception("Wrong operator");
         }
        Integer.parseInt(args[0]);
         Integer.parseInt(args[1]);
         return true;
     } catch (Exception e) {
         printAny("Error: ",e.getMessage());
         return false;
     }
    }


}
