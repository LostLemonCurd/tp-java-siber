package fr.hetic;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CalculatorTwoLeRetour {
    public static final int ERROR = Integer.MIN_VALUE;

    public static void main(String[] args) {
            File dir = new File("/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/fr/hetic/inputs");
                List<String> filePath = findCorrectFiles(dir.listFiles());
                if (!filePath.isEmpty()) {
                    for (String s : filePath) {
                        try {
                            File myObj = new File(s);
                            Scanner myReader = new Scanner(myObj);
                            String newFileName = myObj.getName().split("\\.")[0] + ".res";
                            File newFile = new File("/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/fr/hetic/results/" + newFileName);
                            if (newFile.createNewFile()) {
                                FileWriter myWriter = new FileWriter(newFile);
                                System.out.println("File created: " + newFileName);
                                while (myReader.hasNextLine()) {
                                    String data = myReader.nextLine();
                                    String[] splitData = data.split(" ");
                                    if (verifiyOpArgs(splitData)) {
                                        int num1 = Integer.parseInt(splitData[0]);
                                        int num2 = Integer.parseInt(splitData[1]);
                                        String operation = splitData[2].toLowerCase();
                                        Integer operationResult = Calculator.findCorrectOperation(num1, num2, operation);
                                        myWriter.write("L'opération " + num1 + " " + operation + " " + num2 + " est égale à: " + operationResult + "\n");
                                    } else {
                                        myWriter.write("L'opération " + splitData[0] + " " + splitData[2] + " " + splitData[1] + " est égale à : " + "ERROR" + "\n");
                                    }
                                }
                                myWriter.close();
                            } else {
                                printAny("Error", "File already exists.");
                            }
                        } catch (FileNotFoundException e) {
                            printAny("Error","An error occurred.");
                             printAny("Error",e.getMessage());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
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

    public static List<String> findCorrectFiles(File[] files){
        List<String> filesPath= new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Directory: " + file.getAbsolutePath());
                /*findCorrectFiles(file.listFiles()); // Calls same method again.*/
                filesPath.addAll(findCorrectFiles(file.listFiles()));
            } else {
                System.out.println("File: " + file.getAbsolutePath());
                if (file.getName().toLowerCase().endsWith(".op")) {
                    printAny("URL NAME",file.getName());
                    filesPath.add(file.getAbsolutePath());
                }
            }
        }
        return filesPath;
    }

    public static void printAny(String description, Object obj ) {
        System.out.println(description + " " + obj.toString());
    }


}
