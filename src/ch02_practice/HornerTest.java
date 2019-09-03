package ch02_practice;

import java.util.Random;

/**
 * 使用horner法则进行求和
 */
public class HornerTest {

    // 计算 a0x^1 + a1x^1 + a2x^2 + a3x^3 + ... + anx^n
    // ==> a0 + x(a1 + x(a2 + x(a3 + ... + an-2 + x(an-1 + xan))...)
    // xan = an + x * 0
    public static void hornerSum(int[] a, int x){
        int sum = 0;
        for(int i = a.length - 1; i >= 0; i--){
            sum = sum * x + a[i];
        }
        System.out.println(sum);
    }

    public static void main(String[] args){
        int DEFAULT_SIZE = 10;
        int[] a = new int[DEFAULT_SIZE];
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < a.length; i++){
            a[i] = random.nextInt(10);
            System.out.print("a[" + i + "] = " + a[i] + ", ");
        }
        System.out.println("\n---------------------");
        int x = 2;
        System.out.println("x: " + x);
        System.out.println("---------------------");
        hornerSum(a, x);
    }

}
