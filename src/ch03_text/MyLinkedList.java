package ch03_text;

import java.util.Comparator;
import java.util.Iterator;

/**
 * 单向线性表的链式存储结构
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {

    private int size; // 表示当前链表的大小
    private Node<AnyType> list; // 表示链表
    private Node<AnyType> header; // 头节点
    private Node<AnyType> tail; // 尾节点

    // 使用私有内部类来表示链表中的节点
    static class Node<AnyType>{
        private AnyType data; // 数据域
        private Node<AnyType> next; // 指针域

        public Node(AnyType data, Node<AnyType> next) {
            this.data = data;
            this.next = next;
        }
    }

    public MyLinkedList(){
        create();
    }

    public Node<AnyType> getHeader() {
        return header;
    }

    public void setHeader(Node<AnyType> header) {
        this.header = header;
    }

    public Node<AnyType> getTail() {
        return tail;
    }

    public void setTail(Node<AnyType> tail) {
        this.tail = tail;
    }

    // 创建一个空白的链表
    public void create(){
        header = new Node<>(null, null); // 表示链表的头节点
        tail = new Node<>(null, null); // 表示链表的尾节点
        header.next = tail; // 当链表为空时，头节点指向尾节点
        list = header; // 链表指向头节点
        size = 0;
    }

    // 判断链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 从线性表的头部添加信息
    public void add(AnyType data){
        insert(1, data);
    }

    // 在链表的第i位置上插入一个节点newNode
    public void insert(int i, AnyType data){
        // 判断i是否合适
        if(i < 1 || i > size + 1){
            System.out.println("错误：插入点i（" + i + "）不合适");
            return;
        }

        Node<AnyType> newNode = new Node<>(data, null);
        Node<AnyType> temp = list;
        // 找到第i位置的前一项
        for(int index = 1; index < size && index < i; index++){
            temp = temp.next;
        }
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    // 删除链表中第i位置上的元素
    public AnyType delete(int i){
        // 判断位置i是否合理
        if(i < 1 || i > size){
            System.out.println("错误：删除的位置i（" + i +  "）不合适");
            return null;
        }
        // 找打链表的第i-1位置上的元素
        Node<AnyType> preNode = list;
        for(int index = 1; index < size && index < i; index++){
            preNode = preNode.next;
        }
        Node<AnyType> result = preNode.next;
        preNode.next = result.next;
        result.next= null;
        return result.data;
    }

    // 返回链表的大小
    public int size(){
        return size;
    }

    // 打印链表
    public void printAll(){
        // 指向第一个元素
        Node<AnyType> temp = list.next;
        while(temp != tail){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    // 找出链表中第k位置上的元素
    public AnyType findKth(int k){
        // 判断位置i是否合理
        if(k < 1 || k > size){
            System.out.println("错误：删除的位置k（" + k +  "）不合适");
            return null;
        }
        Node<AnyType> temp = list;
        for(int i = 1; i <= k; i++){
            temp = temp.next;
        }
        return temp.data;
    }

    // 找出链表中是否存在元素data
    public int find(AnyType data){
        Node<AnyType> temp = list.next; // 指向第一个节点元素
        int index = 1;
        while(temp != tail && index <= size){
            if(temp.data.equals(data)){
                return index;
            }
            index++;
            temp = temp.next;
        }
        return -1;
    }

    // 排序
    public void sort(Comparator<? super AnyType> comparator){
        if(isEmpty()){
            System.out.println("链表大小为空");
            return;
        }

        AnyType temp = null;
        for(Node<AnyType> start = header.next; start != tail; start = start.next){
            for(Node<AnyType> second = start.next; second != tail; second = second.next){
                if(comparator.compare(start.data, second.data) > 0){
                    // 交换这两个节点
                    temp = start.data;
                    start.data = second.data;
                    second.data = temp;
                }
            }

        }
    }

    public AnyType[] toArray(){
        AnyType[] result = (AnyType[]) new Object[size];
        int index = 0;
        Node<AnyType> tempNode = header.next;
        while(tempNode != null){
            result[index++] = tempNode.data;
            if(tempNode.next == tail){
                break;
            }
            tempNode = tempNode.next;
        }
        return result;
    }

    public int getSize(){
        return size;
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new MyLinkedListIterator<>();
    }


    class MyLinkedListIterator<AnyType> implements Iterator<AnyType>{

        int index = 1;

        @Override
        public boolean hasNext() {
            return !isEmpty();
        }

        @Override
        public AnyType next() {
            return (AnyType) findKth(index++);
        }
    }
}
