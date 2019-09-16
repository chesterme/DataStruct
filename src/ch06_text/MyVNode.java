package ch06_text;

/**
 * 邻接表表头节点
 */
public class MyVNode<AnyType> {

    private MyAdjacencyListNode firstEdge; // 由邻接表的一个顶点vi的所有邻接点组成的链表，其中表头节点是vi
    private AnyType data; // 表头节点的数据

    public MyVNode(){
        this(null, null);
    }

    public MyVNode(MyAdjacencyListNode firstEdge, AnyType data) {
        this.firstEdge = firstEdge;
        this.data = data;
    }

    public MyAdjacencyListNode getFirstEdge() {
        return firstEdge;
    }

    public void setFirstEdge(MyAdjacencyListNode firstEdge) {
        this.firstEdge = firstEdge;
    }

    public AnyType getData() {
        return data;
    }

    public void setData(AnyType data) {
        this.data = data;
    }
}
