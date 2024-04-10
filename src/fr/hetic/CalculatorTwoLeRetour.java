package fr.hetic;
import fr.hetic.fileHandler.FileHandler;
import fr.hetic.operations.OperationFactory;
import fr.hetic.operations.OperationStrategy;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static fr.hetic.PrintUtil.printAny;

public class CalculatorTwoLeRetour {
    public static void main(String[] args) {
            File dir = new File("/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/fr/hetic/inputs");
                List<String> filePath = FileHandler.getFiles("/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/fr/hetic/inputs");
                if (!filePath.isEmpty()) {
                    FileHandler.computeFiles(filePath);
                } else {
                    printAny("Erreur", "il n'y a pas de fichiers d'opérations");
                }

    }

    public static Boolean verifiyOpArgs(String[] args) {
        List<String> correctOperators = Arrays.asList(
                "+", "-", "x", "*"
        );
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
