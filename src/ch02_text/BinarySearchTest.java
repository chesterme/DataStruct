package ch02_text;

import java.util.ArrayList;
import java.util.Random;

/**
 * 折半查找
 */
public class BinarySearchTest {

    private static int NOT_FOUND = -1;

    public static <AnyType extends Comparable<? super AnyType>>
    int binarySearch(AnyType[] input, AnyType target){
        int low = 0;
        int height = input.length - 1;

        while(low <= height){
            int mid = (low + height) / 2;
            if(input[mid].compareTo(target) == 0){
                return mid;
            }else if(input[mid].compareTo(target) < 0){
                low = mid + 1;
            }else{
                height = mid - 1;
            }
        }

        return NOT_FOUND;
    }

    public static void main(String[] args){
        int size = 50;
        int[] input = new int[size];
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < size; i++){
            input[i] = random.nextInt(100);
        }
        // 插入排序
        int temp = 0;
        for(int i = 0; i < size; i++){
            for(int j = i + 1; j < size; j++){
                if(input[i] > input[j]){
                    temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }
            }
        }

        Integer[] arr = new Integer[size];
        for(int i = 0; i < size; i++){
            arr[i] = input[i];
            System.out.print("(index: " + i + ", value: " + input[i] + "), ");
        }
        System.out.println("\n-----------");


        int key = random.nextInt(100);
        System.out.println(key + "是否在输入列表中：" + binarySearch(arr, key));
    }

}
