package fr.hetic;
import fr.hetic.Calculators.CalculatorTwoLeRetour;

import java.io.FileNotFoundException;

public class Main {
    private static final String resultPath = "/Users/lounisord/Desktop/Cours/MT4/tp-java-siber/src/main/java/fr/hetic/results/test.txt";


    public static void main(String[] args) throws FileNotFoundException {
        CalculatorTwoLeRetour.startProcessing(args[0]);
    }
}
