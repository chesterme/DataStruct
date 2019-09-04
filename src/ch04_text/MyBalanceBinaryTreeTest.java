package ch04_text;

public class MyBalanceBinaryTreeTest {

    public static void main(String[] args){

        MyBalancedBinaryTree<Integer> tree = new MyBalancedBinaryTree<>(new MyIntegerComparator());
        for(int i = 1; i <= 7; i += 1){
            tree.insert(i);
        }
        tree.preOrderTraversal();
        System.out.println("++++++++++++++");
        for(int i = 16; i >= 8; i--){
            tree.insert(i);
        }
        tree.preOrderTraversal();

        System.out.println("max: " + tree.findMax().getData());
        System.out.println("min: " + tree.findMin().getData());
        int key = 6;
        System.out.println("是否存在元素 " +  key + ": " + tree.isContain(key));

        key = 4;
        tree.delete(key);
        System.out.println("删除元素 " + key + "之后，先序遍历的结果为：");
        tree.delete(key);
        tree.preOrderTraversal();

    }

}
