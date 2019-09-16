package ch06_text;

import ch03_text.MyLinkedQueue;

/**
 * 使用邻接矩阵表示图中各顶点之间的关系
 */
public class MyAdjacencyMatrixNode<AnyType> {

    private static final int ERROR = -1; // 错误标记，表示生成树不存在
    private static final int  MAX_VERTEX_NUM = 100; // 最大顶点数
    private static final int INFINITY = Integer.MAX_VALUE; // 表示两个顶点之间不相连
    private int vertexNum; // 顶点数
    private int edgeNum; // 边数
    private int[][] weights; // 临界矩阵
    private AnyType[] data; // 顶点数据
    private boolean[] visits; // 标志节点是否已经被访问了
    private MyAdjacencyListGraph<AnyType> mst; // 使用邻接表表示最小生成树
    private int[] dist; // 保存顶点Vj到顶点集Vt的边的最小权值
    private int[] parent; // 保存当前树的顶点生长过程

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

    /**
     * 从当前顶点开始，找出一条与其邻接的权值最小的另一个顶点
     * @param dist，记录与当前顶点邻接的所有顶点的权值
     * @return
     */
    private int findMinDist(int[] dist){
        int minIndex = -1; // 权值最小的邻接顶点
        int minWeight = INFINITY; // 设置权值初始值为INFINITY

        // 遍历所有的邻接顶点
        for(int i = 0; i < vertexNum; i++){
            // 若dist[i]值为0，表示该顶点已经被纳入顶点集Vt中
            // 若dist[i]值为INFINITY，表示顶点i与当前选择的顶点并不邻接
            if(dist[i] != 0 && dist[i] < minWeight){
                minWeight = dist[i]; // 更新最小权值
                minIndex = i; // 更新最小权值对应的顶点
            }
        }

        if(minWeight == INFINITY){
            // 若没有顶点与当前选择顶点邻接，则返回-1
            return ERROR;
        }
        else{
            return minIndex;
        }
    }

    public int prim(){
        dist = new int[vertexNum];
        parent = new int[vertexNum];
        MyEdgeNode edgeNode = new MyEdgeNode();

        // 默认情况下，当前选择的顶点的下标为0
        for(int i = 0; i < vertexNum; i++){
            dist[i] = weights[0][i]; // 若顶点i与顶点0不邻接，那么它们的权重是INFINITY
            parent[i] = 0; // 这里假设所有顶点的父节点为顶点0
        }

        int totalWeight = 0; // 权重和
        int vertexCount = 0; // 统计纳入顶点集Vt的顶点数

        // 构建一个包含所有顶点，但没有边的图
        // 一颗最小生成树一共有n个顶点和n-1条边
        mst = new MyAdjacencyListGraph<>(vertexNum, vertexNum - 1);

        // 选择当前顶点为顶点0，并将顶点0加入当前树中
        dist[0] = 0;
        vertexCount++;
        parent[0] = -1; // 设置顶点0为当前树的根节点

        while(true){
            // 从当前选择的顶点出发，寻找权值最小的邻接点
            int minIndex = findMinDist(dist);
            if(minIndex == ERROR){
                break;
            }

            // 将顶点minIndex, 和<parent[minIndex], minIndex>插入最小生成树中
            edgeNode.setVertex1(parent[minIndex]);
            edgeNode.setVertex2(minIndex);
            edgeNode.setWeight(dist[minIndex]);
            mst.insertEdge(edgeNode);

            // 更新最小权重和
            totalWeight += dist[minIndex];
            // 设置顶点minIndex为当前选择的顶点，并把顶点minIndex加入当前树中
            dist[minIndex] = 0;
            vertexCount++;

            // 更新与当前选择顶点的邻接权值
            for(int i = 0; i < vertexNum; i++){
                if(dist[i] != 0 && weights[minIndex][i] != INFINITY){
                    if(weights[minIndex][i] < dist[i]){
                        dist[i] = weights[minIndex][i];
                        parent[i] = minIndex;
                    }
                }
            }
        }
        if(vertexCount < vertexCount){
            totalWeight = ERROR;
        }
        return totalWeight;
    }

    public MyAdjacencyListGraph<AnyType> getMst() {
        return mst;
    }

    public int[] getDist() {
        return dist;
    }

    public int[] getParent() {
        return parent;
    }
}
