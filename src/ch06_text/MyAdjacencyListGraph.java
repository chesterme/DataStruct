package ch06_text;


import ch03_text.MyLinkedQueue;

/**
 * 邻接表表示的图
 */
public class MyAdjacencyListGraph<AnyType> {

    private int vertexNumber; // 顶点数
    private int edgeNumber; // 边数
    private MyVNode<AnyType>[] graph; // 邻接表
    private boolean[] visits; // 标志邻接表上的顶点是否已经被访问


    public MyAdjacencyListGraph(int vertexNumber, int edgeNumber) {
        this.vertexNumber = vertexNumber;
        this.edgeNumber = edgeNumber;
        createGraph();
    }

    /**
     * 初始化一个有vertexNumber个顶点但没有边的图
     */
    private void createGraph(){
        graph = new MyVNode[vertexNumber];
        visits = new boolean[vertexNumber];
        for(int i = 0; i < vertexNumber; i++){
            graph[i] = new MyVNode<>();
            visits[i] = false;
        }
    }

    /**
     * 在图中建立一条边
     * @param edge，一条边，由两个顶点和边上的权值组成
     */
    public void insertEdge(MyEdgeNode edge){
        // 插入边<v1, v2>
        // 新建一个邻接点，并插入到对应链表的头节点后
        MyAdjacencyListNode node = new MyAdjacencyListNode(edge.getVertex2(), edge.getWeight());
        node.setNext(graph[edge.getVertex1()].getFirstEdge());
        graph[edge.getVertex1()].setFirstEdge(node);

        // 若是无向图，同时还需要插入边<v2, v1>
        node = new MyAdjacencyListNode(edge.getVertex1(), edge.getWeight());
        node.setNext(graph[edge.getVertex2()].getFirstEdge());
        graph[edge.getVertex2()].setFirstEdge(node);
    }

    public MyVNode<AnyType>[] getGraph() {
        return graph;
    }

    public void setGraph(int i, AnyType data) {
        this.graph[i].setData(data);
    }


    public void printAll(){
        MyAdjacencyListNode tempNode;
        for(int i = 0; i < vertexNumber; i++){
            if(graph[i] != null && graph[i].getData() != null){
                System.out.println("顶点的数据为：" + graph[i].getData());
            }
            tempNode = graph[i].getFirstEdge();
            if(tempNode != null){
                System.out.println("顶点的邻接点为：");
            }
            while(tempNode != null){
                System.out.println(tempNode);
                tempNode = tempNode.getNext();
            }
            System.out.println("++++++++++++++++++++++++++++");
        }
    }

    private void visit(int index){
        System.out.println("正在访问顶点: " + index);
    }

    /**
     * 深度优先搜索
     * @param index，开始搜索的起始位置
     */
    public void dfs(int index){
        visit(index);
        visits[index] = true;
        for(MyAdjacencyListNode node = graph[index].getFirstEdge(); node != null; node = node.getNext()){
            // 如果该顶点未被访问，则递归访问它
            if(!visits[node.getIndex()]){
                dfs(node.getIndex());
            }
        }
    }

    /**
     * 广度优先搜索
     * @param index，开始搜索的起始位置
     */
    public void bfs(int index){
        MyLinkedQueue<Integer> queue = new MyLinkedQueue<>();
        visit(index);
        visits[index] = true; // 标志该顶点已经被访问了
        queue.enqueue(index); // 顶点入队

        while(!queue.isEmpty()){
            int deleteIndex = queue.dequeue();
            // 将被删除顶点的所有未被访问的邻接顶点入队
            MyAdjacencyListNode next = graph[deleteIndex].getFirstEdge();
            while(next != null){
                while(next != null && visits[next.getIndex()]){
                    next = next.getNext();
                }
                if(next != null){
                    visit(next.getIndex());
                    visits[next.getIndex()] = true;
                    queue.enqueue(next.getIndex());
                    next = next.getNext();
                }
            }
        }
    }
}
