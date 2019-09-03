package ch03_text;

import java.util.Comparator;

/**
 * 双向链表
 */
public class MyDoubleLinkedList<AnyType> {

    // 使用内部类表示节点
    private class Node<AnyType>{
        private AnyType data;
        private Node<AnyType> prefix;
        private Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> prefix, Node<AnyType> next){
            this.data = data;
            this.prefix = prefix;
            this.next = next;
        }
    }

    private Node<AnyType> header; // 头节点
    private Node<AnyType> tail; // 尾节点
    private int size; //链表大小

    public MyDoubleLinkedList(){
        create();
    }

    // 创建一个空白的链表
    public void create(){
        header = new Node<>(null, null, null);
        tail = new Node<>(null, null, null);
        header.next = tail;
        header.prefix = tail;
        tail.prefix = header;
        tail.next = header;
        size = 0;
    }

    // 判断链表是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 获取链表的长度
    public int size(){
        return size;
    }

    // 在链表的第i位置上插入一个元素
    public void insert(int i, AnyType data){
        // 判断插入的位置是否合法
        if(i < 1 || i > size + 1){
            System.out.println("插入的位置i(" + i + ")不合法");
            return;
        }

        // 获取第i - 1位置上的元素
        Node<AnyType> temp = header;
        for(int j = 1; j < i; j++){
            temp = temp.next;
        }
        // 建立一个新的节点
        Node<AnyType> newNode = new Node<>(data, null, null);
        newNode.next = temp.next;
        newNode.prefix = temp;
        temp.next.prefix = newNode;
        temp.next = newNode;
        temp = null;
        newNode = null;
        size++;
    }

    // 在链表中获取第k位置上的元素
    public AnyType findKth(int k){
        if(k < 1 || k > size){
            System.out.println("查找的位置k(" + k + ")不合法");
            return null;
        }
        Node<AnyType> temp = header.next;
        for(int i = 1; i < k; i++){
            temp = temp.next;
        }
        return temp.data;
    }

    // 打印链表所有内容
    public void printAll(){
        Node<AnyType> start = header.next;
        while(start != tail){
            System.out.println(start.data);
            start = start.next;
        }
    }

    // 删除链表中第i位置上的节点
    public void delete(int i){
        if(i < 1 || i > size){
            System.out.println("删除的位置i(" + i + ")不合法");
            return;
        }
        // 找到链表第i-1位置上的节点
        Node<AnyType> start = header;
        for(int j = 1; j < i; j++){
            start = start.next;
        }
        // 删除节点
        Node<AnyType> deletedNode = start.next;
        start.next = deletedNode.next;
        deletedNode.prefix = start;
        deletedNode = null;
        start = null;
        size--;
    }

    // 在链表中存在一个元素，返回该元素在链表中的位置
    public int find(AnyType data){
        Node<AnyType> start = header.next;
        int counter = 1;
        while(start != tail){
            if(start.data.equals(data)){
                return counter;
            }
            counter++;
            start = start.next;
        }
        return -1;
    }

    // 简单排序
    public void sort(Comparator<? super AnyType> comparator){
        AnyType temp = null;
        for(Node<AnyType> start = header.next; start != tail; start = start.next){
            for(Node<AnyType> second = start.next; second != tail; second = second.next){
                if(comparator.compare(start.data, second.data) > 0){
                    temp = start.data;
                    start.data = second.data;
                    second.data = temp;
                }
            }
        }
        temp = null;
    }

}
