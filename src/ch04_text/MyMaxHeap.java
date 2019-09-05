package ch04_text;

import java.util.Comparator;

/**
 * 最大堆
 */
public class MyMaxHeap<AnyType> {

    private AnyType[] data;
    private static final int DEFAULT_SIZE = 11;
    private int size; // 堆中元素的个数
    private Comparator<? super AnyType> comparator;

    public MyMaxHeap(Comparator<? super AnyType> comparator){
        create(comparator);
    }

    // 建立一个空白的堆
    private void create(Comparator<? super AnyType> comparator){
        data = (AnyType[]) new Object[DEFAULT_SIZE];
        size = 0;
        this.comparator = comparator;
    }

    public AnyType[] getData(){
        return data;
    }

    // 是否为空堆
    public boolean isEmpty(){
        return size == 0;
    }

    // 堆是否已经满
    public boolean isFull(){
        // data[0]不存储数据
        return size == data.length - 1;
    }

    // 修改堆的容量
    private void ensureCapacity(int newCapacity){
        if(newCapacity < size){
            return;
        }
        AnyType[] temp = (AnyType[]) new Object[newCapacity];
        for(int i = 0; i <= size; i++){
            temp[i] = data[i];
        }
        data = temp;
        temp = null;
    }

    // 往最大堆中插入元素
    public boolean insert(AnyType x){
        if(isFull()){
            ensureCapacity(data.length * 2);
        }
        // 获取插入元素的位置
        int insertIndex = size + 1;
        // 调整堆序性
        for(int parent = insertIndex / 2; parent >= 1 && comparator.compare(data[parent], x) < 0; ){
            data[insertIndex] = data[parent];
            insertIndex = parent;
            parent = parent / 2;
        }
        data[insertIndex] = x;
        size++;
        return true;
    }

    // 从最大堆中删除最大元素
    public AnyType deleteMax(){
        if(isEmpty()){
            return null;
        }
        int deleteIndex = 1;
        AnyType maxElement = data[deleteIndex];
        // 将堆中最后一个元素当作是堆的根节点，然后调整其堆序性
        AnyType insertElement = data[size];
        // 从左右子树中找出合适的元素插入到根元素中，然后删除该元素
        for(int child = deleteIndex * 2; child <= size;){
            // 选中左右子树中最大的元素
            if(child < size && comparator.compare(data[child], data[child + 1]) < 0){
                child++;
            }
            // 如果找到合适的插入位置，则退出
            if(comparator.compare(data[child], insertElement) <= 0){
                break;
            }
            // 将该元素往上移动
            data[deleteIndex] = data[child];
            deleteIndex = child;
            child = child * 2;
        }
        data[deleteIndex] = insertElement;
        size--;
        return maxElement;
    }

    // 输出data数组中的内容
    public void printAll(){
        for(int i = 1; i <= size; i++){
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    // 建立一个最大堆
    public void buildHeap(AnyType[] input){
        data = input;
        size = input.length-1;
        for(int i = size / 2; i > 0; i--){
            percDown(i);
        }
    }

    // 下滤，以data[i]为根节点，调整其堆序性
    private void percDown(int i){
        // 将data[i]插入到子树的合适位置
        int insertIndex = i;
        AnyType insertElement = data[insertIndex];
        for(int child = insertIndex * 2; child <= size; ){
            // 选择左右子树中最大者
            if(child < size && comparator.compare(data[child], data[child + 1]) < 0){
                child++;
            }
            // 如果该节点的后代都比插入元素小，则该节点就是插入的位置
            if(comparator.compare(data[child], insertElement) < 0){
                break;
            }
            // 将子节点上移
            data[insertIndex] = data[child];
            insertIndex = child;
            child = child * 2;
        }
        data[insertIndex] = insertElement;
    }

}
