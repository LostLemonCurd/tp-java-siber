package fr.hetic.FileHandler;

import fr.hetic.Arguments.Arguments;
import fr.hetic.operations.OperationFactory;
import fr.hetic.operations.OperationStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import static fr.hetic.PrintUtil.PrintUtil.log;

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
        for (String s : filePath) {
            File myFile = new File(s);
            List<Arguments> dataFile = extractDataFromFile(myFile);
            // String fileName = getCorrectDestination(myFile.getAbsolutePath());
            String fileName = "/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/main/java/fr/hetic/results/" + myFile.getName().replace(".op", ".res");
            fileCreation(dataFile);
        }
    }

    public static String getCorrectDestination(String absolutePath) {
        return absolutePath.replace(".op", ".res");
    }

    public static void fileCreation(List<Arguments> dataFile) {
        try {
            List<String> fileNames = new ArrayList<>();
            for (Arguments args : dataFile) {
                if (!fileNames.contains(args.fileName)) {
                    fileNames.add(args.fileName);
                }
            }
            for (String fileName : fileNames) {
                File newFile = new File("/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/main/java/fr/hetic/results/" + fileName + ".res");
                deleteIfExists(newFile);
                if (newFile.createNewFile()) {
                    FileWriter myWriter = new FileWriter(newFile);
                    dataFile.forEach(s -> {
                        if (Objects.equals(s.fileName, fileName)) {
                            String content = computeFileContent(s);
                            try {
                                myWriter.write(content);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    myWriter.close();
                } else {
                    log("Error", "File already exists.");
                }
            }
        } catch (FileNotFoundException e) {
            log("Error", "An error occurred.");
            log("Error", e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteIfExists(File newFile) throws IOException {
        if (newFile.exists()) {
            log("File already exists", "deleting it");
            newFile.delete();
        }
    }

    public static List<Arguments> extractDataFromFile(File myFile) throws FileNotFoundException {
        Scanner myReader = new Scanner(myFile);
        List<Arguments> data = new ArrayList<>();
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String tmpLine = line + " " + myFile.getName().replace(".op", "1");
            data.add(new Arguments(tmpLine));
        }
        return data;
    }

    public static List<Arguments> extractDataFromSQL(List<String[]> dbData) {
        List<Arguments> data = new ArrayList<>();
        for (String[] rowData : dbData) {
            String arg0 = rowData[0]; // Extracting the first string
            String arg1 = rowData[1]; // Extracting the second string
            String arg2 = rowData[2]; // Extracting the third string
            String fileName = rowData[3]; // Extracting the fourth string
            String tmpData = arg0 + " " + arg1 + " " + arg2 + " " + fileName;
            log("arg0", arg0);
            log("arg1", arg1);
            log("arg2", arg2);
            log("FILENAMe", fileName);
            log("tmpData", tmpData);
            Arguments argument = new Arguments(tmpData);
            data.add(argument);
        }
        return data;
    }

    public static String computeFileContent(Arguments argument) {
        if (argument.isValid) {
            OperationStrategy operation = OperationFactory.createOperation(argument.operator);
            int result = operation.calculate(argument.number_1, argument.number_2);
            return "FROM FILE (" + argument.fileName + ") : " + argument.number_1 + " " + argument.operator + " " + argument.number_2
                    + " = " + result + "\n";
        } else {
            return "FROM FILE (" + argument.fileName + ") : " + argument.args_0 + " " + argument.args_2 + " " + argument.args_1
                    + " = ERROR" + "\n";
        }
    }
}
