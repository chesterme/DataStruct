package ch04_text;


public class MyBinarySearchTest {

    public static void main(String[] args){

        MyBinarySearchTree<String> tree = new MyBinarySearchTree<>(new MyStringComparator());
        String[] input = "Jan,Feb,Mar,Apr,May,Jun,July,Aug,Sep,Oct,Nov,Dec".split(",");
        for(int i = 0; i < input.length; i++){
            tree.insert(input[i]);
        }
        System.out.println("中序遍历的结果为：");
        tree.inOrderTraversal();
        String target = "Jan";
        tree.delete(target);
        System.out.println("删除元素：" + target + ", 后：");
        System.out.println("中序遍历的结果为：");
        tree.inOrderTraversal();
    }

}
