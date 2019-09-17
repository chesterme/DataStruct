package ch06_text;

import ch03_text.MyLinkedQueue;

/**
 * 使用邻接表表示有向无环图
 */
public class MyDirectedAcyclicGraph<AnyType> extends MyAdjacencyListGraph<AnyType> {

    private int[] inDegree; // 统计图中每个顶点对应的入度
    private int[] inDegreeBackup; // inDegree数组的备份
    private int[] topOrder; // 保存拓扑排序的顺序
    private boolean[] visited; // 标志已经输出的顶点
    private MyLinkedQueue<Integer> queue; // 保存入度为0的顶点

    /**
     * 构造函数
     * @param vertexNumber， 顶点数
     * @param edgeNumber，边数
     */
    public MyDirectedAcyclicGraph(int vertexNumber, int edgeNumber){
        super(vertexNumber, edgeNumber);
        topOrder = new int[vertexNumber];
        inDegree = new int[vertexNumber];
        visited = new boolean[vertexNumber];
        inDegreeBackup = new int[vertexNumber];
        initInDegreeBackup();
        initTopOrder();
        initVisited();
    }

    private void initInDegreeBackup(){
        for(int i = 0; i < inDegreeBackup.length; i++){
            inDegreeBackup[i] = 0;
        }
    }

    private void initQueue(){
        queue = new MyLinkedQueue<>();
    }

    private void initInDegree(){
        for(int i = 0; i < inDegree.length; i++){
            inDegree[i] = inDegreeBackup[i];
        }
    }
    private void initVisited(){
        for(int i = 0; i < visited.length; i++){
            visited[i] = false;
        }
    }
    private void initTopOrder(){
        for(int i = 0; i < topOrder.length; i++){
            topOrder[i] = 0;
        }
    }

    /**
     * 往图中插入一条边
     * @param edge，一条边，由两个顶点和边上的权值组成
     */
    @Override
    public void insertEdge(MyEdgeNode edge){
        // 插入边<v1, v2>
        // 新建一个邻接点，并插入到对应链表的头节点后
        MyAdjacencyListNode node = new MyAdjacencyListNode(edge.getVertex2(), edge.getWeight());
        node.setNext(getGraph()[edge.getVertex1()].getFirstEdge());
        getGraph()[edge.getVertex1()].setFirstEdge(node);

        // 更新顶点v2的入度
        inDegreeBackup[edge.getVertex2()]++;
    }

    /**
     * 返回inDegree数组中下标为k的元素
     * @param k，数组的下标
     * @return inDegree[k]
     */
    public int getKthInDegree(int k){
        return inDegree[k];
    }

    /**
     * 设置inDegree数组中下标为k的元素的值为x
     * @param x，重置的值
     * @param k，数组的下标
     */
    public void setKthInDegree(int k, int x){
        inDegree[k] = x;
    }

    /**
     * 获取入度为0并且未输出的顶点下标
     * @return 入度为0并且为输出的顶点下标，若返回-1则表示这样的顶点不存在
     */
    public int getDegreeIndex(){
        for(int i = 0; i < inDegree.length; i++){
            if(inDegree[i] == 0 && visited[i] != true){
                return i;
            }
        }
        return -1;
    }

    /**
     * 拓扑排序
     * @return 算法是否成功
     */
    public boolean topSort(){
        initInDegree();
        int topIndex = 0; // 拓扑排序结果数组的当前下标

        while(topIndex < topOrder.length){
            // 找到入度为0的顶点
            int v = getDegreeIndex();
            if(v == -1){
                System.out.println("图中有回路");
                break;
            }
            // 将顶点v设置为拓扑排序结果列表下一个元素
            topOrder[topIndex++] = v;
            // 将顶点v从图中移除，对入度为顶点v的所有邻接顶点，它们的入度数-1
            visited[v] = true;
            MyAdjacencyListNode tempNode = getGraph()[v].getFirstEdge();
            while(tempNode != null){
                inDegree[tempNode.getIndex()]--;
                tempNode = tempNode.getNext();
            }
        }
        // 如果不是所有顶点都被访问，则表示图中存在回路
        if(topIndex != topOrder.length){
            return false;
        }
        else{
            return true;
        }
    }

    public int[] getTopOrder() {
        return topOrder;
    }

    /**
     * 拓扑排序改进版
     * @return
     */
    public boolean topSort2(){
        initInDegree();
        initQueue();
        initTopOrder();
        int topIndex = 0; // 拓扑排序结果列表的当前下标
        // 遍历整个inDegree数组，将所有入度为0的顶点入队
        for(int i = 0; i < inDegree.length; i++){
            if (inDegree[i] == 0){
                queue.enqueue(i);
            }
        }

        while(!queue.isEmpty()){
            int deleteIndex = queue.dequeue();
            topOrder[topIndex++] = deleteIndex;
            // 修改顶点deleteIndex的邻接顶点的入度
            MyAdjacencyListNode tempNode = getGraph()[deleteIndex].getFirstEdge();
            while(tempNode != null){
                inDegree[tempNode.getIndex()]--;
                // 如果邻接顶点的入度等于0，则将该邻接顶点的下标入队
                if(inDegree[tempNode.getIndex()] == 0){
                    queue.enqueue(tempNode.getIndex());
                }
                tempNode = tempNode.getNext();
            }
        }
        if(topIndex < topOrder.length){
            return false;
        }
        return true;
    }
}
