package ch06_text;

/**
 * 邻接点的定义
 * 使用邻接表表示图时，使用一个链表来表示图中每个顶点vi的所有邻接点vj
 */
public class MyAdjacencyListNode {

    private int index; // 邻接点的下标
    private int weight; // 边上的权重
    private MyAdjacencyListNode next; // 下一个邻接点

    public MyAdjacencyListNode(){
        this(-1, 0, null);
    }

    public MyAdjacencyListNode(int index, int weight, MyAdjacencyListNode next){
        this.index = index;
        this.weight = weight;
        this.next = next;
    }

    public MyAdjacencyListNode(int index, int weight) {
        this.index = index;
        this.weight = weight;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public MyAdjacencyListNode getNext() {
        return next;
    }

    public void setNext(MyAdjacencyListNode next) {
        this.next = next;
    }

    @Override
    public String toString(){
        return "顶点为：" + index + ", 权值为：" + weight;
    }
}
