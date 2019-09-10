package ch05_text;

/**
 * 链式存储结构的散列表中节点结构
 * @param <AnyType>
 */
public class MyLinkedTableNode<AnyType> {

    private AnyType data; // 节点存储的数据
    private MyLinkedTableNode<AnyType> next; // 指向下一个节点

    public MyLinkedTableNode(){
        this(null, null);
    }

    public MyLinkedTableNode(AnyType data, MyLinkedTableNode<AnyType> next) {
        this.data = data;
        this.next = next;
    }

    public AnyType getData() {
        return data;
    }

    public void setData(AnyType data) {
        this.data = data;
    }

    public MyLinkedTableNode<AnyType> getNext() {
        return next;
    }

    public void setNext(MyLinkedTableNode<AnyType> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "data: " + data.toString();
    }
}
