package ch01_practice;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 选择问题
 */
public class SelectionProblem {

    /**
     * 对该collection进行排序，然后取出第key位置上的元素
     */
    public static<AnyType extends Comparable<? super AnyType>> AnyType solution1(AnyType[] arr, int key)
            throws Exception {
        if(key > arr.length){
            throw new Exception("error, key不能比arr的长度大");
        }
        AnyType temp = null;
        for(int i = 0; i < arr.length; i++){
            for(int j = i + 1; j < arr.length; j++){
                if(arr[i].compareTo(arr[j]) > 0){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr[key-1];
    }

    /**
     * 先对数组中[0,...,key)中的元素进行排序，然后将[key,...,length)中的元素插入到已排好序的数组中合适的位置
     * @throws Exception
     */
    public static<AnyType extends Comparable<? super AnyType>> AnyType solution2(AnyType[] arr, int key) throws Exception {
        if(key > arr.length){
            throw new Exception("error, key不能比arr的长度大");
        }

        // 先对[0,...,key)元素进行排序
        AnyType temp = null;
        for(int i = 0; i < key; i++){
            for(int j = i + 1; j < key; j++){
                if(arr[i].compareTo(arr[j]) > 0){
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        // 处理剩下的元素
        for(int i = key; i < arr.length; i++){
            AnyType insert = arr[i];
            for(int j = key - 1; j >= 0;){
                if(insert.compareTo(arr[j]) < 0) {
                    arr[j] = arr[j-1];
                    j--;
                }else{
                    arr[j+1] = insert;
                    break;
                }
            }
        }

        return arr[key - 1];
    }

    public static void main(String[] args) throws Exception {
        Collection<Integer> input = new ArrayList<>();

        Integer[] intArr = {1, 2, 3, 4, 5, 1, 2, 3, 4,5, 6, 7, 8, 9};
        System.out.println(solution1(intArr, 3));
        System.out.println(solution2(intArr, 3));
    }
}
