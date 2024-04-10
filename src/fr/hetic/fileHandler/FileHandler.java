package fr.hetic.fileHandler;

import fr.hetic.Arguments;
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
import static fr.hetic.PrintUtil.log;

public class FileHandler {
    public static List<String> getFiles(String path) {
        File dir = new File(path);
        return findCorrectFiles(dir.listFiles());
    }

    public static List<String> findCorrectFiles(File[] files) {
        List<String> filesPath = new ArrayList<>();
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

    public static void computeFiles(List<String> filePath) throws FileNotFoundException {
        // switch to for each loop
        for (String s : filePath) {
            File myFile = new File(s);
            List<Arguments> dataFile = extractDataFromFile(myFile);
            String fileName = getCorrectDestination(myFile.getAbsolutePath());
            fileCreation(dataFile, fileName);
        }
    }

    public static String getCorrectDestination(String absolutePath) {
        return absolutePath.replace(".op", ".res");
    }

    public static void fileCreation(List<Arguments> dataFile, String fileName) {
        try {
            File newFile = new File(fileName);
            if (newFile.createNewFile()) {
                FileWriter myWriter = new FileWriter(newFile);
                dataFile.forEach(s -> {
                    String content = computeFileContent(s);
                    try {
                        myWriter.write(content);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                myWriter.close();
            } else {
                log("Error", "File already exists.");
            }
        } catch (FileNotFoundException e) {
            log("Error", "An error occurred.");
            log("Error", e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Arguments> extractDataFromFile(File myFile) throws FileNotFoundException {
        Scanner myReader = new Scanner(myFile);
        List<Arguments> data = new ArrayList<>();
        while (myReader.hasNextLine()) {
            data.add(new Arguments(myReader.nextLine()));
        }
        return data;
    }

    public static String computeFileContent(Arguments argument) {
        if (argument.isValid) {
            OperationStrategy operation = OperationFactory.createOperation(argument.operator);
            int result = operation.calculate(argument.number_1, argument.number_2);
            return "L'opération " + argument.number_1 + " " + argument.operator + " " + argument.number_2
                    + " est égale à: " + result + "\n";
        } else {
            return "L'opération " + argument.args_0 + " " + argument.args_2 + " " + argument.args_1
                    + " est égale à: ERROR" + "\n";
        }
    }
}
