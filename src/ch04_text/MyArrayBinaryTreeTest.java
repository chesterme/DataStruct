package ch04_text;

public class MyArrayBinaryTreeTest {

    public static void main(String[] args){

        MyArrayBinaryTree<String> tree = new MyArrayBinaryTree<>("虚节点");
        String[] input = {"a", "b", "o", "c", "s", "m", "q", "w", "k"};
        for(int i = 0; i < input.length; i++){
            tree.insert(input[i]);
        }
        System.out.println("a 的父节点为：" + tree.getParent("a"));
        System.out.println("s 的左子节点为：" + tree.getLeftChild("s"));
        System.out.println("s 的右子节点为：" + tree.getRightChild("s"));

        tree.insertLeftChild("m", "d");
        tree.insertRightChild("q", "e");
        System.out.println("d 的父节点为：" + tree.getParent("d"));
        System.out.println("m 的左子节点为：" + tree.getLeftChild("m"));
        System.out.println("q 的右子节点为：" + tree.getRightChild("q"));

    }

}
