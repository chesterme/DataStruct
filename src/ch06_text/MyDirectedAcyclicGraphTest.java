package ch06_text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MyDirectedAcyclicGraphTest {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("resources/dag.txt");
        FileReader reader = new FileReader(file);
        Scanner scanner = new Scanner(reader);

        System.out.println("读入顶点数：");
        int vertexNumber = scanner.nextInt();
        System.out.println("读入边数：");
        int edgeNumber = scanner.nextInt();
        MyDirectedAcyclicGraph<String> graph = new MyDirectedAcyclicGraph<>(vertexNumber, edgeNumber);
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

        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("邻接表内容：");
        graph.printAll();

        int startIndex = 0;
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("深度优先搜索：");
        graph.dfs(startIndex);

        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("广度优先搜索：");
        graph.bfs(startIndex);

        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println("拓扑排序内容为：");
        int[] topOrder = graph.getTopOrder();
        for(int i = 0; i < topOrder.length; i++){
            System.out.printf("顶点的下标为：%d, 顶点的内容为：%s\n", i, graph.getGraph()[i].getData());
        }
    }

}
