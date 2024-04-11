package fr.hetic.operations;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.hetic.Calculators.CalculatorTwoLeRetour;
import org.junit.jupiter.api.Test;

class CalculatorTwoLeRetourTest {

    @Test
    public void correctData() {
        // Given
        String[] testData = new String[]{"1", "2", "+"};

        // When
        Boolean result = CalculatorTwoLeRetour.verifyOpArgs(testData);

        // Then
        assertTrue(result);
    }

    @Test
    public void falseData() {
        // Given
        String[] testData = new String[]{"Z", "B", "p"};

        // When
        Boolean result = CalculatorTwoLeRetour.verifyOpArgs(testData);

        // Then
        assertFalse(result);
    }

}