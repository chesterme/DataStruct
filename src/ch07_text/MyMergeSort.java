package ch07_text;

import java.util.Comparator;

/**
 * 归并排序
 * @param <AnyType>
 */
public class MyMergeSort<AnyType> {

    private AnyType[] input;
    private AnyType[] temp; // 辅助数组
    private Comparator<AnyType> comparator;

    public MyMergeSort(AnyType[] input, Comparator<AnyType> comparator) {
        this.input = input;
        this.comparator = comparator;
        temp = (AnyType[]) new Object[input.length];
    }

    public AnyType[] getInput() {
        return input;
    }

    /**
     * 合并两个有序序列
     * @param leftStart，操作序列的左端起始位置
     * @param rightStart，操作序列的右端起始位置
     * @param rightEnd，操作序列的右端结束位置
     */
    private void merge(int leftStart, int rightStart, int rightEnd){
        int leftEnd = rightStart - 1; // 操作序列的左端结束位置
        // 合并两个有序列表：input[leftStart]~input[leftEnd]和input[rightStart]~input[rightEnd]
        int tempIndex = leftStart; // 辅助数组的起始位置
        int elementCount = rightEnd - leftStart + 1; // 统计参与合并的元素个数

        // 遍历这两个有序列表
        while(leftStart <= leftEnd && rightStart <= rightEnd){
            // 选择两者中的较小者，将其放置在辅助数组中
            if(comparator.compare(input[leftStart], input[rightStart]) < 0){
                temp[tempIndex++] = input[leftStart++];
            }
            else{
                temp[tempIndex++] = input[rightStart++];
            }
        }

        while(leftStart <= leftEnd){
            temp[tempIndex++] = input[leftStart++];
        }

        while(rightStart <= rightEnd){
            temp[tempIndex++] = input[rightStart++];
        }

        // 将辅助数组中的元素复制到操作数组的对应位置
        for(int i = 0; i < elementCount; i++, rightEnd--){
            input[rightEnd] = temp[rightEnd];
        }
    }

    /**
     * 快速排序
     * @param left，左边界
     * @param right，右边界
     */
    public void mergeSort(int left, int right){
        int center;
        if(left < right){
            center = (left + right) / 2;
            // 递归解决左边
            mergeSort(left, center);
            // 递归解决右边
            mergeSort(center + 1, right);
            // 合并两个有序的序列
            merge(left, center + 1, right);
        }
    }

    /**
     * 归并排序
     */
    public void mergeSort(){
        mergeSort(0, input.length - 1);
    }
}
