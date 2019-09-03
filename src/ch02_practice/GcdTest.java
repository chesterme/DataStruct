package ch02_practice;

import java.util.Random;

/**
 * 求最大公约数
 */
public class GcdTest {

    // 连续求余数
    public static int gcd1(int a, int b){
        if(b > a){
            gcd1(b, a);
        }
        int remainder = 0;
        while(b != 0){
            remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    public static int gcd2(int a, int b){
        if(a < b){
            gcd2(b, a);
        }
        int aFlag = a % 2 == 0 ? 0 : 1;
        int bFlag = b % 2 == 0 ? 0 : 2;

        int flag = aFlag + bFlag;
        switch(flag){
            // a偶b偶
            case 0:
                return 2 * gcd2(a / 2, b / 2);
            // a奇b偶
            case 1:
                return gcd2(a, b / 2);
            // a偶b奇
            case 2:
                return gcd2(a / 2, b);
            // a奇b奇
            case 3:
            default:
                return gcd2((a + b) / 2, (a - b) / 2);
        }
    }

    public static void main(String[] args){
        Random random = new Random(System.currentTimeMillis());
//        int a = random.nextInt(100);
//        int b = random.nextInt(100);
        int a = 3;
        int b = 9;
        System.out.println("a : " + a + ", b: " + b + ", 它们的最大公约数为：" + gcd1(a, b));
        System.out.println("a : " + a + ", b: " + b + ", 它们的最大公约数为：" + gcd2(a, b));
    }

}
