package fr.hetic.Calculators;

public class Calculator {
    public static final int ERROR = Integer.MIN_VALUE; // Assuming ERROR code is different from valid results

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Calculator num1 num2");
        }
        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);
        String operator = args[2];

        System.out.print(findCorrectOperation(num1, num2, operator));



    }

    public static Integer findCorrectOperation(int num1, int num2, String operator) {
        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "x", "*" -> num1 * num2;
            default -> null;
        };
    }




}
