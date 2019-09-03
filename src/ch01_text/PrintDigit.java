package ch01_text;

public class PrintDigit {

    public static void printOut(int n){
        // 如果n比10大，即n至少是两位数，那么就可以把n分成两部分，其一是个位数，其二是剩下的数
        // 十位数以上的可以递归打印
        if( n >= 10) {
            printOut(n / 10);
        }
        // 个位数可以直接打印
        System.out.print(n % 10);
    }

    public static void main(String[] args){
        printOut(10000);
    }

}
