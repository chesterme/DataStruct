package ch04_text;



public class MyLinkedBinaryTreeTest {

    public static void main(String[] args){

        MyLinkedBinaryTree<String> tree = new MyLinkedBinaryTree<>();
//        Scanner scanner = new Scanner(System.in);
//        String[] input = scanner.nextLine().split("(\\s)+");
        String[] input = "a b c d e f g h 0 0 i 0 j 0".split("(\\s)+");
        tree.createInLevelOrder(input);
        System.out.println("前序遍历：");
        tree.preOrderTraversal();
        System.out.println("中序遍历：");
        tree.inOrderTraversal();
        System.out.println("后序遍历: ");
        tree.postOrderTraversal();

    }

}
