package ch07_text;

import java.util.Comparator;

public class MyHeapSort<AnyType> {

    private AnyType[] input;
    private Comparator<AnyType> comparator;

    public MyHeapSort(AnyType[] input, Comparator<AnyType> comparator){
        this.input = input;
        this.comparator = comparator;
    }

    /**
     * 将数组元素input[p]下滤到它的左子树或者右子树的合适位置
     * @param p, 下滤的目标元素的下标
     * @param length, 堆的大小
     * @return 下滤操作是否成功
     */
    private boolean percDown(int p, int length){
        if(p < 0 || p >= input.length){
            return false;
        }
        AnyType target = input[p];
        int parent = p;
        int child = parent * 2 + 1; // p元素的左节点
        while(child < length){
            // 选择左右节点中值最大的一个
            if(child < length - 1 && comparator.compare(input[child], input[child + 1]) < 0){
                child++;
            }
            // 如果target的值比p元素的左右节点的值都大，那么就找到合适的插入点
            if(comparator.compare(target, input[child]) > 0){
                break;
            }
            // 将子树中较大者向上移动
            else{
                input[parent] = input[child];
                parent = child;
                child = parent * 2 + 1;
            }
        }
        input[parent] = target;
        return true;
    }

    /**
     * 堆排序
     */
    public void heapSort(){
        // 构建一个最大堆
        for(int i = input.length / 2 - 1; i >= 0; i--){
            percDown(i, input.length);
        }

        AnyType temp;
        for(int i = input.length - 1; i >= 0; i--){
            temp = input[0]; // 堆中最大者
            input[0] = input[i];
            input[i] = temp;
            percDown(0, i);
        }
    }

    public AnyType[] getInput() {
        return input;
    }
}
