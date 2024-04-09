package fr.hetic;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CalculatorTwoLeRetour {
    public static void main(String[] args) {
        try {
            File myObj = new File("/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/fr/hetic/additions.op");
            Scanner myReader = new Scanner(myObj);
            String newFileName = "additions" ;
            File newFile = new File(newFileName);
            if (newFile.createNewFile()) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println("File created: " + newFileName);
                    String[] splitData = data.split(" ");
                    int num1 = Integer.parseInt(splitData[0]);
                    int num2 = Integer.parseInt(splitData[1]);
                    String operation = splitData[2];
                    int operationResult = Calculator.findCorrectOperation(num1, num2, operation);
                    FileWriter myWriter = new FileWriter(newFile);
                    myWriter.write("The result is: " + operationResult + "\n");
                    myWriter.close();
                }
            } else {
                System.out.println("File already exists.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
