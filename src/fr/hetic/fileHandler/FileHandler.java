package fr.hetic.fileHandler;

import fr.hetic.CalculatorTwoLeRetour;
import fr.hetic.operations.OperationFactory;
import fr.hetic.operations.OperationStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static fr.hetic.PrintUtil.printAny;


public class FileHandler {
    public static List<String> getFiles(String path) {
        File dir = new File(path);
        return findCorrectFiles(dir.listFiles());

    }

    public static List<String> findCorrectFiles(File[] files){
        List<String> filesPath= new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                filesPath.addAll(findCorrectFiles(file.listFiles()));
            } else {
                if (file.getName().toLowerCase().endsWith(".op")) {
                    filesPath.add(file.getAbsolutePath());
                }
            }
        }
        return filesPath;
    }

    public static void computeFiles(List<String> filePath) {
        for (String s : filePath) {
            try {
                File myObj = new File(s);
                Scanner myReader = new Scanner(myObj);
                File newFile = new File(getCorrectDestination(myObj.getAbsolutePath()));
                if (newFile.createNewFile()) {
                    FileWriter myWriter = new FileWriter(newFile);
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        String[] splitData = data.split(" ");
                        if (CalculatorTwoLeRetour.verifyOpArgs(splitData)) {
                            int num1 = Integer.parseInt(splitData[0]);
                            int num2 = Integer.parseInt(splitData[1]);
                            String operator = splitData[2].toLowerCase();
                            OperationStrategy operation = OperationFactory.createOperation(operator);
                            int result = operation.calculate(num1, num2);
                            myWriter.write("L'opération " + num1 + " " + operator + " " + num2 + " est égale à: " + result + "\n");
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
    }

    public static String getCorrectDestination(String absolutePath) {
        return absolutePath.replace(".op", ".res");
    }
}
