package ch07_text;

import ch03_text.MyLinkedList;

import java.util.Comparator;

/**
 * 希尔排序
 * @param <AnyType>
 */
public class MyShellSort<AnyType> {

    private AnyType[] input;
    private Comparator<AnyType> comparator;

    public MyShellSort(AnyType[] input, Comparator<AnyType> comparator) {
        this.input = input;
        this.comparator = comparator;
    }

    public void shellSort(){
        // 生成一个增量序列
        MyLinkedList<Integer> list = new MyLinkedList<>();
        int length = input.length;
        while(length != 0){
            list.add(length / 2);
            length = length / 2;
        }
        Object[] objects = list.toArray();
        int[] increments = new int[objects.length];
        for(int i = 0; i < increments.length; i++){
            increments[i] = (Integer) objects[i];
        }

        int index = increments.length - 1;
        AnyType temp;
        // 遍历增量序列
        for(int i = increments[index]; i > 0; i = increments[--index]){
            // 假定输入序列存在i个已经排好序的子序列，每个子序列的位置增量为i
            // 即[0, i-1]是i个子序列的各自开始元素
            for(int j = i, k; j < input.length; j++){
                // 逐个取出未排序的元素
                temp = input[j];
                // 将未排序的元素input[k]与{input[k-i], input[k-2i], ..}进行比较
                // 如果小于已经排好的序列，则将input[k-i]后移一位
                for(k = j; k >= i && comparator.compare(input[k - i], temp) > 0; k -= i){
                    input[k] = input[k - i];
                }
                input[k] = temp;
            }
        }
    }

    public AnyType[] getInput() {
        return input;
    }
}
