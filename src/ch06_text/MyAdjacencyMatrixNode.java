package ch06_text;

import ch03_text.MyLinkedQueue;

/**
 * 使用邻接矩阵表示图中各顶点之间的关系
 */
public class MyAdjacencyMatrixNode<AnyType> {

    private static final int  MAX_VERTEX_NUM = 100; // 最大顶点数
    private int vertexNum; // 顶点数
    private int edgeNum; // 边数
    private int[][] weights; // 临界矩阵
    private AnyType[] data; // 顶点数据
    private boolean[] visits; // 标志节点是否已经被访问了
    private static final int INFINITY = Integer.MAX_VALUE; // 表示两个顶点之间不相连

    public MyAdjacencyMatrixNode(int vertexNum, int edgeNum){
        this.vertexNum = vertexNum;
        this.edgeNum = edgeNum;
        weights = new int[vertexNum][vertexNum];
        visits = new boolean[vertexNum];
        // 初始化，各个顶点不相连
        for(int i = 0; i < vertexNum; i++){
            visits[i] = false; // 默认情况下没有被访问
            for(int j = 0; j < vertexNum; j++){
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
                if(weights[i][j] == INFINITY){
                    System.out.printf("%10s\t", "null");
                }else{
                    System.out.printf("%10d\t", weights[i][j]);
                }
            }
            System.out.println("\n==================");
        }
    }

    // 设置顶点x与顶点y相连
    public void setAdjacency(int x, int y, int weight){
        weights[x][y] = weight;
    }


    /**
     * 判断顶点x和顶点y之间是否存在一条边
     * @param x，顶点x
     * @param y，顶点y
     * @return
     */
    public boolean isEdge(int x, int y){
        return weights[x][y] < INFINITY ? true : false;
    }

    public void visit(int index){
        System.out.println("正在访问顶点：" + index);
    }

    /**
     * 广度优先搜索
     * @param startIndex，开始搜索的顶点下标
     */
    public void bfs(int startIndex){
        MyLinkedQueue<Integer> queue = new MyLinkedQueue<>();
        visit(startIndex);
        visits[startIndex] = true;
        queue.enqueue(startIndex);
        while(!queue.isEmpty()){
            int deleteIndex = queue.dequeue();
            // 遍历图中所有顶点
            for(int i = 0; i < vertexNum; i++){
                if(!visits[i] && isEdge(deleteIndex, i)){
                    // 访问顶点i
                    visit(i);
                    visits[i] = true;
                    queue.enqueue(i); // 将顶点i入队
                }
            }
        }
    }

    /**
     * 深度优先搜索
     * @param startIndex，开始搜索的顶点下标
     */
    public void dfs(int startIndex){
        // 访问顶点startIndex
        visit(startIndex);
        visits[startIndex] = true;
        // 遍历顶点startIndex的每一个邻接顶点
        for(int i = 0; i < vertexNum; i++){
            if(isEdge(startIndex, i)){
                if(!visits[i]){
                    // 对顶点startIndex的每一个邻接顶点递归调用DFS
                    dfs(i);
                }
            }
        }
    }
}
