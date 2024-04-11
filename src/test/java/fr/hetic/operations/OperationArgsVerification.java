package fr.hetic.operations;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import fr.hetic.Calculators.CalculatorTwoLeRetour;
import org.junit.jupiter.api.Test;

class OperationArgsVerification {

    @Test
    void correctData() {
        String[] testData = new String[]{"1", "2", "+"};
        Boolean result = CalculatorTwoLeRetour.verifyOpArgs(testData);
        assertTrue(result);
    }

    @Test
    void falseData() {
        String[] testData = new String[]{"Z", "B", "p"};
        Boolean result = CalculatorTwoLeRetour.verifyOpArgs(testData);
        assertFalse(result);
    }

}