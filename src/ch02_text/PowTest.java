package ch02_text;

/**
 * 幂运算
 */
public class PowTest {

    public static long pow(long x, int n){
        if(n == 0){
            return 1;
        }else if( n == 1){
            return x;
        }else if(n % 2 == 0){
            return pow(x * x, n / 2);
        }else{
            return pow(x * x, n / 2) * x;
        }
    }

    public static void main(String[] args){
        System.out.println(pow(2, 10));
    }

}
