package ch03_text;

import java.util.Iterator;

/**
 * 链式栈
 */
public class MyLinkedStack<AnyType> implements Iterable<AnyType>{

    // 使用内部类来表示栈中的节点元素
    private class Node<AnyType>{
        private AnyType data;
        private Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<AnyType> top; // 栈顶元素
    private Node<AnyType> bottom; // 栈底元素
    private int size; // 栈的大小

    public MyLinkedStack(){
        create();
    }

    // 建立一个空栈
    public void create(){
        top = bottom = new Node<>(null, null);
        size = 0;
    }

    // 判断栈是否为空
    public boolean isEmpty(){
        return top == bottom;
    }

    // 返回栈大小
    public int size(){
        return size;
    }

    // 入栈
    public void push(AnyType data){
        // 建立一个新节点
        Node<AnyType> newNode = new Node<>(data, null);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // 出栈
    public AnyType pop(){
        if(isEmpty()){
            return null;
        }
        AnyType result = top.data;
        top = top.next;
        size--;
        return result;
    }

    // 获取栈顶元素
    public AnyType getTop(){
        if(isEmpty()){
            return null;
        }
        return top.data;
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
        return new MyLinkedStackIterator<>();
    }

    private class MyLinkedStackIterator<AnyType> implements Iterator<AnyType>{

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
