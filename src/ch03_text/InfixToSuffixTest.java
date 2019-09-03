package ch03_text;

import java.util.Scanner;

public class InfixToSuffixTest {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        InfixToSuffix solution = new InfixToSuffix(input);
        System.out.println(solution.handleExpress());

    }

}
