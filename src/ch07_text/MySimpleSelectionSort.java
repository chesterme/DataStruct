package ch07_text;

import java.util.Comparator;

/**
 * 选择排序
 * @param <AnyType>
 */
public class MySimpleSelectionSort<AnyType> {

    private AnyType[] input;
    private Comparator<AnyType> comparator;

    public MySimpleSelectionSort(AnyType[] input, Comparator<AnyType> comparator){
        this.input = input;
        this.comparator = comparator;
    }

    public void sort(){
        int min = 0;
        AnyType temp;
        for(int i = 0; i < input.length - 1; i++){
            min = i;
            // 每次在数组input[j, length)中寻找最小元素，然后替换input[i]
            for(int j = i + 1; j < input.length; j++){
                if(comparator.compare(input[j], input[min]) < 0){
                    min = j;
                }
            }
            temp = input[i];
            input[i] = input[min];
            input[min] = temp;
        }
    }

    public AnyType[] getInput() {
        return input;
    }
}
