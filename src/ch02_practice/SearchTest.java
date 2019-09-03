package ch02_practice;

import java.util.Random;

/**
 * 给出一个已经排好序的数组a，其中a1 < a2 < a3 < ... < an
 * 是否存在一个整数i是的ai = i
 */
public class SearchTest {

    public static int[] generateArray(int size){
        int[] result = new int[size];
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < result.length; i++){
            result[i] = random.nextInt(100);
        }
        insertSort(result);
        return result;
    }

    public static void insertSort(int[] input){
        int temp = 0;
        for(int i = 0; i < input.length; i++){
            for(int j = i + 1; j < input.length; j++){
                if(input[i] > input[j]){
                    temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }
            }
        }
    }

    // 枚举
    public static boolean isPresence1(int[] input){
        for(int i = 0; i < input.length; i++){
            if(input[i] == i){
                return true;
            }
        }
        return false;
    }

    //

}
