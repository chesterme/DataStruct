package ch03_text;

import java.util.Scanner;

public class CalculateExpressionTest {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        CalculateExpression calculator = new CalculateExpression(input);
        System.out.println(calculator.calculateExpress());

    }

}
