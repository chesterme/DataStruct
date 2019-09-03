package ch03_text;

import java.util.Scanner;

public class PolynomialTest {

    public static void main(String[] arg){

        Scanner scanner = new Scanner(System.in);
        String input1 = scanner.nextLine();
        String input2 = scanner.nextLine();

        Polynomial target1 = new Polynomial(input1);
        Polynomial target2 = new Polynomial(input2);
        MyLinkedList<Polynomial.Struct> result =  target1.add(target2);

        for(int i = 1; i <= result.size(); i++){
            System.out.print(result.findKth(i));
            if(i != result.size()){
                System.out.print(" + ");
            }
        }

    }

}
