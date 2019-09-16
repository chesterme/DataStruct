package ch06_text;

/**
 * 表示图中两个顶点的边
 */
public class MyEdgeNode{

    private int vertex1; // 使用下标表示顶点1
    private int vertex2; // 使用下标表示顶点2
    private int weight; // 边上的权值
    private static final int INFINITY = Integer.MAX_VALUE; // 表示两个顶点之间不相连

    public MyEdgeNode(){
        this(0, 0, INFINITY);
    }

    public MyEdgeNode(int vertex1, int vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public int getVertex1() {
        return vertex1;
    }

    public void setVertex1(int vertex1) {
        this.vertex1 = vertex1;
    }

    public int getVertex2() {
        return vertex2;
    }

    public void setVertex2(int vertex2) {
        this.vertex2 = vertex2;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
