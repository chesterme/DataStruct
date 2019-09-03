package ch03_text;

import java.util.Iterator;

/**
 * 顺序栈
 */
public class MyArrayStack<AnyType> implements Iterable<AnyType> {

    private AnyType[] list; // 栈容器
    private int top; // 栈顶索引
    private int bottom; // 栈底索引
    private int DEFAULT_SIZE = 10;
    private int size; // 栈的逻辑大小

    public MyArrayStack(){
        create();
    }

    // 建立一个空栈
    public void create(){
        list = (AnyType[]) new Object[DEFAULT_SIZE];
        top = bottom = 0;
        size = 0;
    }

    // 判断栈是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 返回栈的大小
    public int size(){
        return size;
    }

    // 修改栈的容量
    public void ensureCapacity(int newSize){
        if(newSize < size){
            return;
        }
        // 建立一个临时的栈
        AnyType[] tempStack = (AnyType[]) new Object[newSize];
        for(int i = 0; i < size; i++){
            tempStack[i] = list[i];
        }
        list = tempStack;
        tempStack = null;
    }

    // 往栈中添加一个元素
    public void push(AnyType data){
        // 如果超出范围，则扩容
        if(isFull()){
            ensureCapacity(list.length * 2);
        }
        // 从栈顶中添加元素
        list[top++] = data;
        size++;
    }

    // 获取栈顶元素
    public AnyType getTop(){
        return list[top - 1];
    }

    // 从栈中删除一个元素
    public AnyType pop(){
        // 如果实际大小小于容器大小的一半，则缩小容量
        if(size < list.length / 2){
            ensureCapacity(list.length / 2);
        }
        // 从栈顶中删除一个元素
        AnyType data = list[--top];
        list[top] = null;
        size--;
        return data;
    }

    // 判断栈是否满
    public boolean isFull(){
        return size == list.length;
    }

    // 栈中的元素全部出栈
    public void printAll(){
        Iterator<AnyType> iterator = iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new MyArrayStackIterator<>();
    }

    private class MyArrayStackIterator<AnyType> implements Iterator<AnyType>{

        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        @Override
        public AnyType next() {
            return (AnyType) pop();
        }
    }
}
