package ch04_text;

public class MyHuffmanTreeTest {

    public static void main(String[] args){

        DataAndWeight<String>[] input = new DataAndWeight[7];
        String[] names = {"张三", "李四", "王五", "赵六", "展昭", "张龙", "赵虎"};
        for(int i = 0; i < names.length; i++){
            input[i] = new DataAndWeight<>(names[i], i);
        }
        MyHuffmanTree<String> tree = new MyHuffmanTree<>(input);
        tree.preOrderTraversal();

    }

}
