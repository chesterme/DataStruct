package ch06_text;

import java.util.Scanner;

public class MyAdjacencyListGraphTest {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        System.out.println("读入顶点数：");
        int vertexNumber = scanner.nextInt();
        System.out.println("读入边数：");
        int edgeNumber = scanner.nextInt();
        MyAdjacencyListGraph<String> graph = new MyAdjacencyListGraph<>(vertexNumber, edgeNumber);
        if(edgeNumber > 0){
            // 构建边
            for(int i = 0; i < edgeNumber; i++){
                System.out.println("读入边，输入格式如下：起点 终点 权重");
                int vertex1 = scanner.nextInt();
                int vertex2 = scanner.nextInt();
                int weight = scanner.nextInt();
                graph.insertEdge(new MyEdgeNode(vertex1, vertex2, weight));
            }
        }

        scanner.nextLine();
        System.out.println("按顺序输入顶点数据：");
        for(int i = 0; i < vertexNumber; i++){
            String data = scanner.nextLine();
            graph.setGraph(i, data);
        }

        graph.printAll();

        int startIndex = 0;
//        graph.dfs(startIndex);
        graph.bfs(startIndex);
    }

}
