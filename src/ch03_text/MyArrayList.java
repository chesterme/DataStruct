package ch03_text;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 线性表的顺序存储结构
 */
// 注意：这里没有办法利用泛型使用存储对象中的比较方法
// 比如：<Anytype extends Comparable<? super AnyType>>，这里想使用实际类型中的compareTo方法
// 如果这样子做，将在AnyType[] theItems = (AnyType[]) new Object[DEFAULT_SIZE]中出错，表示
// 不能把Object[]类型转换成Comparable[]类型，因为Object类型并没有实现Comparable接口，因此，这里
// 需要使用另一种途径，可以自定义一个比较器（实现Comparator接口），它可以返回这两个对象的比较结果
// 同时与线性表中数据项的泛型表示不冲突
public class MyArrayList<AnyType> implements Iterable<AnyType> {

    private AnyType[] theItems; // 数据项
    private int thisSize; // 线性表的逻辑大小
    private static final int DEFAULT_SIZE = 3; // 默认的初始容量

    public MyArrayList(){
        create();
    }

    // 创建一个空白的线性表
    public void create(){
        theItems = (AnyType[]) new Object[DEFAULT_SIZE];
        thisSize = 0;
    }

    // 返回线性表的长度
    public int size(){
        return thisSize;
    }

    // 判断线性表是否为空
    public boolean isEmpty(){
        return size() == 0;
    }

    // 改变线性表的容量
    public void ensureCapacity(int newCapacity){
        // 如果新的容量比当前容量小，则不需要改变
        if(thisSize > newCapacity){
            return;
        }

        // 构建一个新数组，把旧数组中的内容复制到新数组中
        AnyType[] newArray = (AnyType[]) new Object[newCapacity];
        for(int i = 0; i < size(); i++){
            newArray[i] = theItems[i];
        }
        AnyType[] temp = newArray;
        theItems = newArray;
        temp = null;
    }

    // 根据指定的位序i，返回线性表中对应的元素ai
    public AnyType findKth(int i){
        // 判断位序i是否合法
        if(i < 1 || i > size()){
            System.out.println("错误：输入的位序i(" + i + ")不合法");
            return null;
        }

        return theItems[i - 1];
    }

    // 已知一个元素x，在线性表中查找与元素x相同的第一个元素的位置
    public int find(AnyType x){
        // 遍历整个线性表
        for(int i = 0; i < size(); i++){
            if(theItems[i].equals(x)){
                return i + 1;
            }
        }
        return -1; // 在线性表中不存在与x相同的元素
    }

    // 在线性表中的指定位序i前插入一个新元素x，若成功则返回true，否则返回false
    public boolean insert(int i, AnyType x){
        // 判断i是否合法
        if(i - 1 < 0 || i - 1 > size()){
            System.out.println("错误：输入的位序i(" + i + ")不合法");
            return false;
        }

        // 判断是否需要扩容
        if(size() + 1 > DEFAULT_SIZE){
            ensureCapacity(theItems.length * 2);
        }

        // 找到位序为i-1的元素，将其后的元素向后移动一位
        // size()表示当前数组中最后一个元素的后一位
        for(int j = size(); j >= i; j--){
            theItems[j] = theItems[j - 1];
        }
        theItems[i - 1] = x;
        thisSize++;
        return true;
    }


    // 从线性表中删除指定位序i上的元素，若成功删除则返回true，否则返回false
    public boolean delete(int i){
        // 判断i是否合法
        if(i - 1 < 1 || i - 1 > size()){
            System.out.println("错误：输入的位序i(" + i + ")不合法");
            return false;
        }

        // 是否需要缩小线性表的容量
        if(size() < theItems.length / 2){
            ensureCapacity(theItems.length / 2);
        }

        // 找到位序为i-1的元素，将其后的元素向前移动一位
        for(int j = i - 1; j < size(); j++){
            theItems[j] = theItems[j + 1];
        }
        theItems[size() - 1] = null;
        thisSize--;
        return true;
    }

    // 打印线性表中的内容
    public void printAll(){
        // 写法1：
//        for(int i = 0; i < thisSize; i++){
//            System.out.println(theItems[i]);
//        }
        // 写法2：
        Iterator iterator = iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        // 写法3：error：并没有调用迭代器中的方法
//        for(AnyType item : theItems){
//            System.out.println(item);
//        }
    }

    // 简单实现排序
    public void sort(Comparator<? super AnyType> comparator){
        if(isEmpty()){
            System.out.println("错误：线性表大小为0");
            return;
        }
        MyArrays.sort(theItems, comparator, size());
    }

    // 获取数据项
    public AnyType[] getTheItems() {
        return theItems;
    }

    // 返回一个迭代器，方便使用for-item形式遍历线性表
    @Override
    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    // 使用一个私有类实现迭代器接口
    private class ArrayListIterator implements Iterator<AnyType>{

        int current = 0; // 表示当前遍历的位置，初始值为0

        @Override
        public boolean hasNext() {
            return current < size();  // 当前遍历是否已经达到线性表的末端
        }

        @Override
        public AnyType next() {
            // 如果到达线性表的末端，则抛出异常
            if(!hasNext()){
                throw new NoSuchElementException("已经到达线性表的末端，没有元素了");
            }
            return theItems[current++];
        }

        @Override
        public void remove() {
            delete(--current);
        }
    }
}
