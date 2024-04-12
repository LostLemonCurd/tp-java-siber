package fr.hetic.Arguments;

import fr.hetic.Calculators.CalculatorTwoLeRetour;

import java.util.Arrays;


public class Arguments {
    public Integer number_1 = null;
    public Integer number_2 = null;
    public String operator = null;
    public Boolean isValid = false;
    public String fileName = null;
    public String args_0;
    public String args_1;
    public String args_2;

    public Arguments(String[] argument_line) {
        String[] arguments = {argument_line[0], argument_line[1], argument_line[2]};
        this.args_0 = argument_line[0];
        this.args_1 = argument_line[1];
        this.args_2 = argument_line[2];
        this.fileName = argument_line[3];
        if (CalculatorTwoLeRetour.verifyOpArgs(arguments)) {
            this.number_1 = Integer.parseInt(argument_line[0]);
            this.number_2 = Integer.parseInt(argument_line[1]);
            this.operator = argument_line[2];
            this.fileName = argument_line[3];
            this.isValid = true;
        }

    }
}
