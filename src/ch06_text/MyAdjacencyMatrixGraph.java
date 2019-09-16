package ch06_text;

import java.util.Scanner;

public class MyAdjacencyMatrixGraph<AnyType> {

    MyAdjacencyMatrixNode<AnyType> MGraph; // 以邻接矩阵表示图

    public MyAdjacencyMatrixGraph(int vertexNum){
        createGraph(vertexNum);
    }

    // 创建一个有顶点但没有边的矩阵
    private void createGraph(int vertexNum){
        MGraph = new MyAdjacencyMatrixNode<>(vertexNum, 0);
    }

    // 在邻接矩阵中插入边
    public void insertEdge(MyEdgeNode edgeNode){
        int vertex1 = edgeNode.getVertex1();
        int vertex2 = edgeNode.getVertex2();
        int weight = edgeNode.getWeight();
        // 插入边<vertex1, vertex2>
        MGraph.getWeights()[vertex1][vertex2] = weight;
        // 若是无向图
        MGraph.getWeights()[vertex2][vertex1] = weight;
    }


    public MyAdjacencyMatrixNode<AnyType> getMGraph() {
        return MGraph;
    }

    public void printAll(){
        MGraph.printAll();
    }
}
