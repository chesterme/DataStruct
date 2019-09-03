package ch03_text;

import java.util.Comparator;

public class MyArrays {

    // 简单排序
    public static <AnyType> void sort(AnyType[] arr, Comparator<? super AnyType> comparator, int size){
        AnyType temp = null;
        for(int i = 0; i < size; i++){
            // 每次遍历，确保第i项是当前序列[i,size)中最小的
            for(int j = i + 1; j < size; j++){
                if(comparator.compare(arr[j], arr[i]) < 0){
                    //交换
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


}
