package ch03_text;

import java.util.Iterator;

/**
 * 链式队列
 */
public class MyLinkedQueue<AnyType> implements Iterable<AnyType>{

    // 使用内部类表示链式队列中的节点
    private class Node<AnyType>{
        private AnyType data;
        private Node<AnyType> next;
        private Node<AnyType> prefix;

        public Node(AnyType data, Node<AnyType> next, Node<AnyType> prefix) {
            this.data = data;
            this.next = next;
            this.prefix = prefix;
        }
    }

    private Node<AnyType> front; // 队头
    private Node<AnyType> rear; // 队尾
    private int size; // 队列大小

    public MyLinkedQueue(){
        create();
    }

    // 创建一个空白的队列
    public void create(){
        front = new Node<>(null, null, null);
        rear = new Node<>(null, null, null);
        front.next = rear;
        rear.prefix = front;
        size = 0;
    }

    // 返回队列的大小
    public int size(){
        return size;
    }

    // 队列是否为空
    public boolean isEmpty(){
        return front.next == rear;
    }

    // 入队，从队尾中插入元素
    public void enqueue(AnyType x){
        // 建立一个新的节点
        Node<AnyType> newNode = new Node<>(x, null, null);
        Node<AnyType> prefixNode = rear.prefix;
        prefixNode.next = newNode;
        newNode.prefix = prefixNode;
        newNode.next = rear;
        rear.prefix = newNode;
        size++;
    }

    // 出队，从队头中删除一个元素
    public AnyType dequeue(){
        if(isEmpty()){
            System.out.println("Empty Queue");
            return null;
        }
        Node<AnyType> deletedNode = front.next;
        front.next = deletedNode.next;
        deletedNode.next.prefix = front;
        size--;
        return deletedNode.data;
    }

    // 打印队列中所有内容
    public void printAll(){
        Iterator<AnyType> iterator = iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new MyLinkedQueueIterator<>();
    }

    private class MyLinkedQueueIterator<AnyType> implements Iterator<AnyType>{

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
