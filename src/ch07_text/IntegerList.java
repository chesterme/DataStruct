package ch07_text;

/**
 * 整数链表
 */
public class IntegerList {

    private IntegerNode header;
    private IntegerNode tailor;
    private int size;

    public IntegerList(){
        header = new IntegerNode(0, null);
        tailor = new IntegerNode(0, null);
        header.setNext(tailor);
        size = 0;
    }

    public boolean isEmpty(){
        return header.getNext() == tailor;
    }

    /**
     * 从表头开始插入
     * @param key ，插入元素
     */
    public void insertFromHead(int key){
        IntegerNode tempNode = new IntegerNode(key, null);
        tempNode.setNext(header.getNext());
        header.setNext(tempNode);
        size++;
    }

    /**
     * 从表尾开始插入
     * @param key，插入元素
     */
    public void insertFromTailor(int key){
        insert(size + 1, key);
    }

    /**
     * 从表中某个位置插入
     * @param index，插入点
     * @param key，插入元素
     */
    public void insert(int index, int key){
        if(index < 1 || index > size + 1){
            return;
        }
        int insertIndex = 0;
        IntegerNode tempNode = header;
        while(tempNode.getNext() != tailor && insertIndex < index - 1){
            tempNode = tempNode.getNext();
            insertIndex++;
        }
        IntegerNode node = new IntegerNode(key, null);
        node.setNext(tempNode.getNext());
        tempNode.setNext(node);
        size++;
    }

    /**
     * 表头插入元素
     * @param key，需要插入的元素
     */
    public void add(int key){
        insertFromHead(key);
    }

    /**
     * 从表头删除元素
     * @return
     */
    public IntegerNode deleteFromHead(){
        if(!isEmpty()){
            IntegerNode deleteNode = header.getNext();
            header.setNext(deleteNode.getNext());
            size--;
            return deleteNode;
        }
        return null;
    }

    /**
     * 从表尾删除元素
     * @return
     */
    public IntegerNode deleteFromTail(){
        if(!isEmpty()){
            IntegerNode preNode = header.getNext();
            IntegerNode currentNode = preNode.getNext();
            while(currentNode.getNext() != tailor){
                preNode = currentNode;
                currentNode = currentNode.getNext();
            }
            preNode.setNext(tailor);
            currentNode.setNext(null);
            size--;
            return currentNode;
        }
        return null;
    }

    /**
     * 删除表中第index个元素
     * @param index
     * @return
     */
    public IntegerNode delete(int index){
        if(index < 1 || index > size){
            return null;
        }
        IntegerNode preNode = header;
        IntegerNode deleteNode = header.getNext();
        int count = 1;
        while(count < index){
            preNode = deleteNode;
            deleteNode = deleteNode.getNext();
            count++;
        }
        preNode.setNext(deleteNode.getNext());
        deleteNode.setNext(null);
        size--;
        return deleteNode;
    }

    public IntegerNode getHeader() {
        return header;
    }

    public void setHeader(IntegerNode header) {
        this.header = header;
    }

    public IntegerNode getTailor() {
        return tailor;
    }

    public void setTailor(IntegerNode tailor) {
        this.tailor = tailor;
    }

    public int getSize() {
        return size;
    }

    public void printAll(){
        IntegerNode temp = header.getNext();
        while(temp.getNext() != tailor){
            System.out.println("data: " + temp.getKey());
            temp = temp.getNext();
        }
    }
}
