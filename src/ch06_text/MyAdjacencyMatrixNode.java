package ch06_text;

import ch03_text.MyLinkedQueue;
import ch04_text.MyMinHeap;

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
    private int[] dist;
    private int[] parent;
    private boolean[] collected; // 表示图中各顶点是否已经纳入最短路径的顶点集中
    private int[] minimumDist; // 图中各顶点到源顶点的最短路径
    private int[] path; // 表示图中各顶点到源顶点的最短路径

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

    /**
     * prim算法实现的最小生成树
     * @return 返回该最小生成树的权值总和
     */
    public int prim(){
        dist = new int[vertexNum]; // 保存顶点Vj到顶点集Vt的边的最小权值
        parent = new int[vertexNum]; // 保存当前树的顶点生长过程
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

    /**
     * 使用kruskal算法实现的最小生成树
     * @return 最小生成树的权值总和
     */
    public int kruskal(){
        parent = new int[vertexNum]; // 使用一个数组来表示一个集合，用来判断两个顶点是否属于同一颗树
        // 图中的每个顶点默认情况下，是不属于同一个集合的
        for(int i = 0; i < vertexNum; i++){
            parent[i] = -1;
        }

        // 生成一个只包含顶点集，不包含边集的连接表图
        mst = new MyAdjacencyListGraph<>(vertexNum, edgeNum -1);

        // 使用最小堆保存图中所有的边集
        MyMinHeap<MyEdgeNode> minHeap = new MyMinHeap<>(new MyEdgeNodeComparator());
        for(int i = 0; i < vertexNum; i++){
            for(int j = i + 1; j < vertexNum; j++){
                if(isEdge(i, j)){
                    minHeap.insert(new MyEdgeNode(i, j, weights[i][j]));
                }
            }
        }

        int vertexCount = 1; // 统计最小生成树的顶点数
        int minWeight = 0;
        while(vertexCount < vertexNum && !minHeap.isEmpty()){
            // 选择一条权值最小的边
            MyEdgeNode tempNode = minHeap.deleteMin();
            int vertex1 = tempNode.getVertex1();
            int vertex2 = tempNode.getVertex2();
            // 查找这两个顶点是否在同一颗树下
            int root1 = findRoot(vertex1);
            int root2 = findRoot(vertex2);
            // 如果这两个顶点不构成回路，则将它们合并到同一颗树中
            if(root1 != root2){
                union(root1, root2);
                mst.insertEdge(tempNode);
                vertexCount++;
                minWeight += tempNode.getWeight();
            }
        }
        return minWeight;
    }

    /**
     * 初始化顶点访问标志
     */
    private void initVisits(){
        for(int i = 0; i < vertexNum; i++){
            visits[i] = false;
        }
    }

    /**
     * 合并两个顶点到树中
     * @param index1，顶点1
     * @param index2，顶点2
     */
    private void union(int index1, int index2){
        parent[index2] = index1;
    }

    /**
     * 返回该元素所在集合的根元素
     * @param currentIndex
     * @return
     */
    private int findRoot(int currentIndex){
        for(; parent[currentIndex] != -1; currentIndex = parent[currentIndex]);
        return currentIndex;
    }


    /**
     * 在Vx集合中查找一个顶点，它到源顶点V0的路径最短
     * 其中Vx集合是图顶点集V除去已经纳入最短路径的顶点集后剩下的顶点集
     * @return 图中一个顶点，它到源顶点V0的路径最短
     */
    private int findMinDistInVx() {
        int minDist = INFINITY;
        int minIndex = ERROR;
        // 遍历整个图
        for (int i = 0; i < vertexNum; i++) {
            if (collected[i] != true && minimumDist[i] < minDist) {
                minDist = minimumDist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    /**
     * 使用dijkstra算法构建图中各顶点到源顶点的最短路径
     * @param startIndex，源顶点
     * @return 算法是否成功
     */
    public boolean dijkstra(int startIndex){
        collected = new boolean[vertexNum];
        minimumDist = new int[vertexNum];
        path = new int[vertexNum];

        for(int i = 0; i < vertexNum; i++){
            minimumDist[i] = weights[startIndex][i];
            // 如果顶点i与顶点startIndex相邻接
            if(minimumDist[i] < INFINITY){
                path[i] = startIndex;
            }
            else{
                path[i] = -1;
            }
            // 默认情况下，所有顶点都没有纳入最短路径的顶点集中
            collected[i] = false;
        }

        // 设置源顶点
        collected[startIndex] = true;
        minimumDist[startIndex] = 0;

        // 将图的各个顶点纳入最短路径的顶点集Vt
        while(true){
            // 从顶点集Vx中选择一个顶点，它到源顶点的距离最短
            int minIndex = findMinDistInVx();
            if(minIndex == ERROR){
                break;
            }
            // 将顶点minIndex纳入最短路径集
            collected[minIndex] = true;
            // 更新剩余顶点到源顶点的距离
            for(int i = 0; i < vertexNum; i++){
                // 如果顶点i与顶点minIndex邻接，并且顶点i还没有被纳入最短路径集
                if(weights[minIndex][i] < INFINITY && collected[i] != true){
                    if(weights[minIndex][i] < 0){
                        // 如果存在负权值，则不适合使用该算法
                        return false;
                    }
                    // 如果纳入顶点i使得它到源顶点的路径变短，则需要更新minimumDist和path
                    if(minimumDist[minIndex] + weights[minIndex][i] < minimumDist[i]){
                        minimumDist[i] = minimumDist[minIndex] + weights[minIndex][i]; // 更新顶点i到源顶点的最短路径
                        path[i] = minIndex; // 使得顶点i的的父顶点是顶点minIndex
                    }
                }
            }
        }
        return true;
    }

    public boolean[] getCollected() {
        return collected;
    }

    public int[] getMinimumDist() {
        return minimumDist;
    }

    public int[] getPath() {
        return path;
    }
}
