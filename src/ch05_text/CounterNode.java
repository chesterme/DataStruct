package ch05_text;

/**
 * 单链表的节点
 * @param <AnyType>
 */
public class CounterNode<AnyType>{

    private int counter;
    private AnyType data;
    private CounterNode<AnyType> next;

    public CounterNode(){
        this(null, 0, null);
    }

    public CounterNode(AnyType data, int counter, CounterNode<AnyType> next){
        this.data = data;
        this.counter = counter;
        this.next = next;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public AnyType getData() {
        return data;
    }

    public void setData(AnyType data) {
        this.data = data;
    }

    public CounterNode<AnyType> getNext() {
        return next;
    }

    public void setNext(CounterNode<AnyType> next) {
        this.next = next;
    }
}
