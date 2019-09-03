package ch03_text;

import java.util.Iterator;

/**
 * 队列
 */
public class MyArrayQueue<AnyType> implements Iterable<AnyType>{

    private AnyType[] data; // 使用数组表示队列的容器
    private int front; // 表示队头
    private int rear; // 表示队尾
    private static int DEFAULT_SIZE = 4;
    private int size; // 队列中元素的个数

    public MyArrayQueue(){
        create();
    }

    // 创建一个空白的队列
    public void create(){
        data = (AnyType[]) new Object[DEFAULT_SIZE];
        front = rear = 0; // 当队列为空时，队头和队尾指向数组的第一个元素
        size = 0;
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }

    // 判断队列是否已经满
    public boolean isFull(){
        return (rear + 1) % data.length == front;
    }

    // 重新分配大小
    public void ensureCapacity(int newCapacity){
        if(size > newCapacity){
            return;
        }
        AnyType[] temp = (AnyType[]) new Object[newCapacity];
        int i = 0;
        int tempSize = size;
        while(!isEmpty()){
            temp[++i] = dequeue();
        }
        size = tempSize;
        front = 0;
        rear = i;
        data = temp;
        temp = null;
    }

    // 队列中元素个数
    public int size(){
        return size;
    }

    // 入队
    public void enqueue(AnyType x){
        // 如果队列已满，则扩容
        if(isFull()){
            ensureCapacity(data.length * 2);
        }
        rear = (rear + 1) % data.length;
        data[rear] = x;
        size++;
    }

    // 出队
    public AnyType dequeue(){
        if(isEmpty()){
            return null;
        }
        front = (front + 1) % data.length;
        AnyType result = data[front];
        data[front] = null;
        size--;
        return result;
    }

    // 获取队头元素
    public AnyType getFront(){
        if(!isEmpty()){
            return data[front + 1];
        }
        return null;
    }

    // 将队列中的所有内容出队并打印
    public void printAll(){
        Iterator<AnyType> iterator = new MyArrayQueueIterator<>();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new MyArrayQueueIterator();
    }

    private class MyArrayQueueIterator<AnyType> implements Iterator<AnyType>{

        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        @Override
        public AnyType next() {
            return (AnyType) dequeue();
        }
    }
}
