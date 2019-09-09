package ch04_text;

/**
 * 简单的集合
 */
public class MySimpleSet{

    private static final int MAX_SIZE = 100;
    private int[] set;

    public MySimpleSet(){
        // 默认情况下集合元素全部初始化为-1
        set = new int[MAX_SIZE];
        for(int i = 0; i < MAX_SIZE; i++){
            set[i] = -1;
        }
    }

    // 查找某个元素所在的集合
    public int find(int x){
        // 数组中第i项对应集合元素i，保存的是其父元素的下标
        // 例如：元素x对应数组中的第x项，其父元素的下标为set[x]，其中根节点默认情况下保存的值为-1
        for(; set[x] > -1; x = set[x]);
        return x;
    }

    // 是否属于同一个集合
    public boolean isInSameSet(int x, int y){
        int xRoot = find2(x);
        int yRoot = find2(y);
        if(xRoot == yRoot){
            return true;
        }
        return false;
    }

    // 集合的并运算，合并元素x，y所在的两个集合
    public void union(int x, int y){
        int xRoot = find(x);
        int yRoot = find(y);
        // 如果x和y元素的根元素相同，即它们属于同一个集合，则不需要合并
        if(xRoot == yRoot){
            return;
        }
        set[yRoot] = xRoot;
    }

    // 按秩合并
    public void union2(int x, int y){
        int xRoot = find2(x);
        int yRoot = find2(y);
        // 如果x和y元素的根元素相同，即它们属于同一个集合，则不需要合并
        if(xRoot == yRoot){
            return;
        }

        // 保证小集合并入大集合
        if(set[yRoot] < set[xRoot]){
            set[yRoot] += set[xRoot]; // 集合x并入集合y
            set[xRoot] = yRoot;
        }
        else{
            set[xRoot] += set[yRoot];  // 集合y并入集合x
            set[yRoot] = xRoot;
        }
    }

    // find的路径压缩算法
    public int find2(int x){
        // 如果查找的根元素，则直接返回该集合的根节点下标
        if(set[x] < 0){
            return x;
        }
        else{
            // 递归将x元素的父节点设置为其父节点的父节点，直到根元素为止
            // 效果是：在元素x与其根元素之间的路径上的每一个元素，
            // 将它们的父节点修改为根节点，这样子就降低了树的高度
            return set[x] = find2(set[x]);
        }
    }

}
