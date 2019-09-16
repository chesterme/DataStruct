package ch06_text;

import java.util.Scanner;

public class MyAdjacencyMatrixGraphTest {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.printf("输入顶点个数：\n");
        int vertexNum = scanner.nextInt();

        // 初始化一个有vertex个顶点但没有边的图
        MyAdjacencyMatrixGraph<String> myGraph = new MyAdjacencyMatrixGraph<>(vertexNum);

        System.out.printf("输入边的个数：\n");
        int edgeNum = scanner.nextInt();

        // 如果图有边
        MyEdgeNode edgeNode = new MyEdgeNode();
        if(edgeNum > 0){
            myGraph.getMGraph().setEdgeNum(edgeNum);
            // 读入边，格式为：起点 终点 权重，插入矩阵中
            for(int i = 0; i < edgeNum; i++){
                System.out.printf("输入边，格式为\"起点 终点 权重\": \n");
                edgeNode.setVertex1(scanner.nextInt());
                edgeNode.setVertex2(scanner.nextInt());
                edgeNode.setWeight(scanner.nextInt());
                myGraph.insertEdge(edgeNode);
            }
            myGraph.getMGraph().printAll();
        }
    }

}
