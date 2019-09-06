package ch04_text;

import java.util.Comparator;

/**
 * 哈夫曼树
 */
public class MyHuffmanTree<AnyType> extends MyLinkedBinaryTree<AnyType> {

    private DataAndWeight<AnyType>[] input;
    private MyMinHeap<MyLinkedBinaryTree<AnyType>> heap;

    public MyHuffmanTree(DataAndWeight<AnyType>[] input){
        super();
        this.input = input;
        this.heap = new MyMinHeap<>(new MyComparator());
        buildMinHeap();
        create();
    }

    private class MyComparator implements Comparator<MyLinkedBinaryTree<AnyType>>{

        @Override
        public int compare(MyLinkedBinaryTree<AnyType> o1, MyLinkedBinaryTree<AnyType> o2) {
            return o1.getRoot().getWeight() - o2.getRoot().getWeight();
        }
    }

    // 根据权值构建一个最小二叉树堆
    private void buildMinHeap(){
        for(int i = 0; i < input.length; i++){
            MyLinkedBinaryTree<AnyType> tree = new MyLinkedBinaryTree<>();
            tree.getRoot().setData(input[i].getData());
            tree.getRoot().setWeight(input[i].getWeight());
            heap.insert(tree);
        }
    }

    // 创建一个哈夫曼树
    private void create(){
        int heapSize = heap.getSize();
        // 对最小堆中的二叉树进行heapSize-1次合并
        for(int i = 1; i < heapSize; i++){
            // 从最小堆中弹出两棵树，然后进行合并，新树的根节点中权值是子树权值之和
            MyLinkedBinaryTree<AnyType> newTree = new MyLinkedBinaryTree<>();
            newTree.getRoot().setLeft(heap.deleteMin().getRoot());
            newTree.getRoot().setRight(heap.deleteMin().getRoot());
            newTree.getRoot().setWeight(newTree.getRoot().getLeft().getWeight() + newTree.getRoot().getRight().getWeight());
            newTree.getRoot().setData(null);
            // 将新树插入到最小堆中
            heap.insert(newTree);
        }
        // 将根节点指向已经建立好的哈夫曼树
        setRoot(heap.deleteMin().getRoot());
    }

    // 先序遍历
    @Override
    public void preOrderTraversal(){
        preOrderTraversal(getRoot());
    }

    @Override
    public void preOrderTraversal(Node<AnyType> root){
        if(root != null){
            if(root.getData() != null){
                System.out.println("data: " + root.getData() + ", weight: " + root.getWeight());
            }
            preOrderTraversal(root.getLeft());
            preOrderTraversal(root.getRight());

        }
    }


}
