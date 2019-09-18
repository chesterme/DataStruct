package ch07_text;

import java.util.Comparator;

/**
 * 插入排序
 * @param <AnyType>
 */
public class MyInsertionSort<AnyType> {

    private AnyType[] input;
    private Comparator<? super AnyType> comparator;

    public MyInsertionSort(AnyType[] input, Comparator<? super AnyType> comparator){
        this.input = input;
        this.comparator = comparator;
    }

    public void insertSort(){
        AnyType temp;
        int insertIndex = 0;
        for(int i = 1; i < input.length; i++){
            temp = input[i];
            // 将input[i]元素插入到已经排好序的序列[0,i-1]中
            for(int j = i - 1; j >= 0; j--){
                // 如果input[j] < 插入的元素，那么将input[j]元素向后移动一位
                if(comparator.compare(temp, input[j]) < 0){
                    input[j + 1] = input[j];
                    insertIndex = j;
                }
                // 如果input[j] >= 插入的元素，那么插入点就是j+1
                else{
                    insertIndex = j + 1;
                    break;
                }
            }
            input[insertIndex] = temp;
        }
    }

    public AnyType[] getInput(){
        return input;
    }

}
