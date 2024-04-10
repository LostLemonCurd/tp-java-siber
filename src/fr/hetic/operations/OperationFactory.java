package fr.hetic.operations;

public class OperationFactory {
    public static OperationStrategy createOperation(String operator) {
        return switch (operator) {
            case "+" -> new AdditionOperation();
            case "-" -> new SubtractionOperation();
            case "x", "*" -> new MultiplicationOperation();
            default -> throw new IllegalArgumentException("Invalid operator: " + operator);
        };
    }
}
