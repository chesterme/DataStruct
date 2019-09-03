package ch01_text;

public class RecursiveSimple {

    public static int f(int x){
        if(x == 0){
            return 0;
        }
        else{
            return f(x - 1) * 2 + x * x;
        }
    }

    public static int bad(int n){
        if(n == 0){
            return 0;
        }
        else{
            return bad(n / 3 + 1) + n - 1;
        }
    }


    public static void main(String[] args){
        System.out.println(f(10));
        System.out.println(bad(4));
    }
}
