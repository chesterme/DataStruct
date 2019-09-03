package ch03_text;

/**
 * 广义表
 * 广义表中的节点有两种情况：
 * 1）单元素
 * 2）广义表
 */
public class MyGeneralizedList<AnyType> {

    // 使用内部类表示广义表中的节点
    private class Node<AnyType>{
        private AnyType data; // 节点中的数据元素
        private int flag; // 标志域，0表示该节点是单元素节点；1表示该节点是广义表
        private Node<AnyType> next; // 该节点的后继节点，指向单元素节点
        private MyGeneralizedList sublist; // 指向另一个广义表

        public Node(AnyType data, int flag, Node<AnyType> next, MyGeneralizedList<AnyType> sublist) {
            this.data = data;
            this.flag = flag;
            this.next = next;
            this.sublist = sublist;
        }
    }

    private Node<AnyType> header;
    private Node<AnyType> tail;
    private int size;

    public MyGeneralizedList(){
        create();
    }

    public void create(){
        header = new Node<>(null, 0, null, null);
        tail = new Node<>(null, 0, null, null);
        header.next = tail;
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    // 在广义表中的第i位置上插入一个单元素
    public void insert(int i, AnyType data){
        if(i < 1 || i > size + 1){
            System.out.println("插入的位置i(" + i + ")不合法");
            return;
        }
        // 找到第i-1位置上的元素
        Node<AnyType> temp = header;
        for(int j = 1; j < i; j++){
            temp = temp.next;
        }
        // 创建一个空白的节点
        Node<AnyType> newNode = new Node<>(data, 0, null, null);
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    // 在广义表的第i位置上插入一个广义表
    public void insert(int i, MyGeneralizedList<AnyType> list){
        if(i < 1 || i > size + 1){
            System.out.println("插入的位置i(" + i + ")不合法");
            return;
        }
        // 找到第i-1位置上的元素
        Node<AnyType> temp = header;
        for(int j = 1; j < i; j++){
            temp = temp.next;
        }
        // 创建一个空白的节点
        Node<AnyType> newNode = new Node<>(null, 1, null, list);
        newNode.next = temp.next;
        temp.next = newNode;
        size++;
    }

    // 打印广义表
    public void printAll(){
        if(isEmpty()){
//            System.out.println("广义表为空");
            return;
        }else{
            printAll(header);
        }
    }

    private void printAll(Node<AnyType> node){
        if(node != null){
            if(node.sublist != null){
                printAll(node.sublist);
                // 递归打印子表的出口
                if(node.next != null && (node.next.data != null || node.next.flag == 1)){
                    System.out.print(",");
                }
            }
            // 对于在广义表中的开头元素打印(字符
            if(node.data == null && node.next != null && node.flag == 0){
                System.out.print("(");
            }
            // 对于在广义表中的结尾元素打印)字符
            if(node.data == null && node.next == null && node.flag == 0){
                System.out.print(")");
            }
            if(node.data != null){
                System.out.print(node.data + " ");
                if(node.next != null && (node.next.data != null || node.next.flag == 1)){
                    System.out.print(",");
                }
            }
            printAll(node.next);
        }
    }

    private void printAll(MyGeneralizedList<AnyType> list){
        if(list == null){
//            System.out.println("广义表为空");
            return;
        }else{
            printAll(list.header);
        }
    }



}
