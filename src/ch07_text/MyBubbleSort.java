package ch07_text;

import java.util.Comparator;

/**
 * 冒泡排序
 * @param <AnyType>
 */
public class MyBubbleSort<AnyType> {

    private AnyType[] input;
    private Comparator<? super AnyType> comparator;

    public MyBubbleSort(AnyType[] input, Comparator<? super AnyType> comparator) {
        this.input = input;
        this.comparator = comparator;
    }

    public AnyType[] getInput() {
        return input;
    }

    public void bubbleSort(){
        boolean flag; // 标记是否发生置换
        AnyType temp;
        for(int i = input.length - 1; i >= 0; i--){
            flag = false; // 默认情况下，认为不会发生置换
            // 从序列[0,i]中选择一个最大值，将其放置在序列i的位置上
            for(int j = 0; j < i; j++){
                // 比较两个相邻的元素，使得较大值在右边，较小值在左边
                if(comparator.compare(input[j], input[j+1]) > 0){
                    temp = input[j];
                    input[j] = input[j+1];
                    input[j+1] = temp;
                    flag = true;
                }
            }
            // 如果一趟下来，都不发生置换，则说明该序列已经排好序了
            if(flag == false){
                break;
            }
        }
    }
}
