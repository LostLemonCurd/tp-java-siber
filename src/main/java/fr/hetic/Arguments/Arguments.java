package fr.hetic.Arguments;

import fr.hetic.Calculators.CalculatorTwoLeRetour;

import java.util.Arrays;

import static fr.hetic.PrintUtil.PrintUtil.log;


public class Arguments {
    public Integer number_1 = null;
    public Integer number_2 = null;
    public String operator = null;
    public Boolean isValid = false;
    public String fileName = null;
    public String args_0;
    public String args_1;
    public String args_2;

    public Arguments(String argument_line) {
        String[] arguments = argument_line.split(" ");
        this.args_0 = arguments[0];
        this.args_1 = arguments[1];
        this.args_2 = arguments[2];
        this.fileName = arguments[3];
        if (CalculatorTwoLeRetour.verifyOpArgs(new String[]{arguments[0], arguments[1], arguments[2]})) {
            this.number_1 = Integer.parseInt(arguments[0]);
            this.number_2 = Integer.parseInt(arguments[1]);
            this.operator = arguments[2];
            this.fileName = arguments[3];
            this.isValid = true;
        }

    }
}
