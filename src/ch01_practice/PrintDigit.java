package ch01_practice;

/**
 * 打印数字
 */
public class PrintDigit {


    public static void printDouble(double input){
        if(input < 0){
            System.out.print("-");
        }
        double decimalPart = input % 10;
        int integerPart = (int)(input - decimalPart);
        printDigit(integerPart);
        System.out.print(decimalPart);
    }

    public static void printDigit(int input){
        if(input >= 10){
            printDigit(input / 10);
        }
        System.out.print(input % 10);
    }

    public static void main(String[] args){
        System.out.println(12.3888 % 10);
//        printDigit(100000);
        printDouble(100001.23456);
    }

}
