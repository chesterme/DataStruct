package ch02_text;

import java.util.Random;

/**
 * 欧几里得算法，求最大公约数
 */
public class GcdTest {

    public static long gcb(long m, long n){
        System.out.println("m: " + m + ", n: " + n);
        if(m < n){
            return gcb(n, m);
        }
        // 连续计算余数，直到余数为零
        while(n != 0){
            long rem = m % n;
            m = n;
            n = rem;
        }
        return m;
    }

    public static void main(String[] args){
        Random random = new Random(System.currentTimeMillis());
        System.out.println(gcb(random.nextInt(100), random.nextInt(100)));
    }

}
