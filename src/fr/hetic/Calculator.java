package fr.hetic;

public class Calculator {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Calculator num1 num2");
        }
        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);
        String operator = args[2];

        findCorrectOperation(num1, num2, operator);



    }

    public static void findCorrectOperation(int num1, int num2, String operator) {
        switch (operator) {
            case "+":
                System.out.println(num1 + num2);
                break;
            case "-":
                System.out.println(num1 - num2);
                break;
            case "x":
                System.out.println(num1 * num2);
                break;
            default:
                System.out.println("Invalid operator");
        }
    }




}
