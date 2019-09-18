package ch07_text;

import java.util.Comparator;

/**
 * 快速排序
 * @param <AnyType>
 */
public class MyQuickSort<AnyType> {

    private AnyType[] input;
    private Comparator<? super AnyType> comparator;
    private static int SCALE = 1000; // 输入规模

    public MyQuickSort(AnyType[] input, Comparator<? super AnyType> comparator) {
        this.input = input;
        this.comparator = comparator;
    }

    public AnyType[] getInput() {
        return input;
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

    /**
     * 交换数组中两个元素的数据
     * @param x，数组下标x
     * @param y， 数组下标y
     */
    private void swap(int x, int y){
        AnyType temp = input[x];
        input[x] = input[y];
        input[y] = temp;
    }

    /**
     * 选择主元
     * @param left， 左边界
     * @param right， 右边界
     * @return 主元
     */
    private AnyType selectMedia(int left, int right){
        int center = (left + right) / 2;
        if(comparator.compare(input[left], input[right]) > 0){
            swap(left, right);
        }
        if(comparator.compare(input[left], input[center]) > 0){
            swap(left, center);
        }
        if(comparator.compare(input[center], input[right]) > 0){
            swap(center, right);
        }

        // 此时，该三个元素满足：input[left] <= input[center] <= input[right]
        // 将主元input[center]移动到右端
        swap(center, right - 1);
        // 返回主元
        return input[right - 1];
    }

    /**
     * 快速排序
     * @param left，左边界
     * @param right，右边界
     */
    public void quickSort(int left, int right){
        AnyType media; // 主元，用来划分序列
        int low, height;
        // 如果输入规模超过设定的阈值，使用快速排序
        if(right-left > SCALE){
            media = selectMedia(left, right);
            low = left;
            height = right - 1;
            // 将序列中比主元小的元素移动到左边，比主元大的元素移动到右边
            while(true){
                while(comparator.compare(input[++low], media) < 0);
                while(comparator.compare(input[--height], media) > 0);
                // 如果在左边遇到比主元大的元素，同时在右边遇到比主元小的元素，则交换它们
                if(low < height){
                    swap(left, height);
                }
                // 如果发生指针错位，则说明已经遍历完整个序列，完成了将较小的移动到左边，较大者移动到右边
                else{
                    break;
                }
            }
            // 将主元放置在序列的合适位置
            swap(low, right - 1);
            // 递归解决左边序列
            quickSort(left, low - 1);
            // 递归解决右边序列
            quickSort(low + 1, right);
        }
        // 如果输入规模没有达到预定的阈值，使用插入排序
        else{
            insertSort();
        }
    }

    /**
     * 快速排序，默认选择的边界是[0, length-1]
     */
    public void quickSort(){
        quickSort(0, input.length - 1);
    }
}
