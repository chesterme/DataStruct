package ch06_text;

/**
 * 使用邻接矩阵表示图中各顶点之间的关系
 */
public class MyAdjacencyMatrixNode<AnyType> {

    private static final int  MAX_VERTEX_NUM = 100; // 最大顶点数
    private int vertexNum; // 顶点数
    private int edgeNum; // 边数
    private int[][] weights; // 临界矩阵
    private AnyType[] data; // 顶点数据
    private static final int INFINITY = Integer.MAX_VALUE; // 表示两个顶点之间不相连

    public MyAdjacencyMatrixNode(int vertexNum, int edgeNum){
        this.vertexNum = vertexNum;
        this.edgeNum = edgeNum;
        weights = new int[MAX_VERTEX_NUM][MAX_VERTEX_NUM];
        // 初始化，各个顶点不相连
        for(int i = 0; i < MAX_VERTEX_NUM; i++){
            for(int j = 0; j < MAX_VERTEX_NUM; j++){
                weights[i][j] = INFINITY;
            }
        }
    }

    public int getVertexNum() {
        return vertexNum;
    }

    public void setVertexNum(int vertexNum) {
        this.vertexNum = vertexNum;
    }

    public int getEdgeNum() {
        return edgeNum;
    }

    public void setEdgeNum(int edgeNum) {
        this.edgeNum = edgeNum;
    }

    public int[][] getWeights() {
        return weights;
    }

    public void setWeights(int[][] weights) {
        this.weights = weights;
    }

    public AnyType[] getData() {
        return data;
    }

    public void setData(AnyType[] data) {
        this.data = data;
    }

    // 打印邻接矩阵的内容
    public void printAll(){
        for(int i = 0; i < vertexNum; i++){
            for(int j = 0; j < vertexNum; j++){
                System.out.printf("%10d\t", weights[i][j]);
            }
            System.out.println("\n==================");
        }
    }

    // 设置顶点x与顶点y相连
    public void setAdjacency(int x, int y, int weight){
        weights[x][y] = weight;
    }

}
