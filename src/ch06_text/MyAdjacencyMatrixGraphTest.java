package ch06_text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MyAdjacencyMatrixGraphTest {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("resources/graphTest.txt");
        FileReader reader = new FileReader(file);
        Scanner scanner = new Scanner(reader);

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
            System.out.println("++++++++++++++++++++++++++");
            System.out.println("图的邻接矩阵为：");
            myGraph.getMGraph().printAll();
        }

        int startIndex = 0;
//        myGraph.getMGraph().bfs(startIndex);
        System.out.println("++++++++++++++++++++++++++");
        System.out.println("图的深度优先搜索为：");
        myGraph.getMGraph().dfs(startIndex);

        System.out.println("++++++++++++++++++++++++++");
//        int totalWeight = myGraph.getMGraph().prim();
        int totalWeight = myGraph.getMGraph().kruskal();
        System.out.println("最小权值和为：" + totalWeight);

        System.out.println("++++++++++++++++++++++++++");
        System.out.println("最小生成树为：");
        int[] parent = myGraph.getMGraph().getParent();
        for(int i = 0; i < parent.length; i++){
            System.out.printf("下标为：%10d\t，其值为：%10d\n", i, parent[i]);
        }

        System.out.println("++++++++++++++++++++++++++");
        System.out.printf("图中各个顶点到源顶点%d, 的最短路径为：\n", startIndex);
        myGraph.getMGraph().dijkstra(startIndex);
        int[] path = myGraph.getMGraph().getPath();
        for(int i = 0; i < path.length; i++){
            System.out.printf("下标为：%10d\t，其值为：%10d\n", i, path[i]);
        }

        System.out.println("++++++++++++++++++++++++++");
        System.out.printf("图中各个顶点到源顶点%d, 的最短路径大小为：\n", startIndex);
        myGraph.getMGraph().dijkstra(startIndex);
        int[] minimumDist = myGraph.getMGraph().getMinimumDist();
        for(int i = 0; i < path.length; i++){
            System.out.printf("下标为：%10d\t，其值为：%10d\n", i, minimumDist[i]);
        }

        System.out.println("++++++++++++++++++++++++++");
        System.out.println("图的广度优先搜索为：");
        MyAdjacencyListGraph<String> mst = myGraph.getMGraph().getMst();
        mst.bfs(startIndex);
    }

}
