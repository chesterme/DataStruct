package ch07_text;

/**
 * 整数节点
 */
public class IntegerNode {

    private int key;
    private IntegerNode next;

    public IntegerNode(int key, IntegerNode next) {
        this.key = key;
        this.next = next;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public IntegerNode getNext() {
        return next;
    }

    public void setNext(IntegerNode next) {
        this.next = next;
    }
}
