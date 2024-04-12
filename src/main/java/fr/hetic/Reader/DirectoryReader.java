package fr.hetic.Reader;

import fr.hetic.Calculators.CalculatorTwoLeRetour;

public class DirectoryReader implements ReaderStrategy{
    private final String filePath;

    public DirectoryReader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void read() {
        CalculatorTwoLeRetour.startProcessing(filePath);
    }
}
