package ch04_text;

import ch03_text.MyLinkedQueue;
import ch03_text.MyLinkedStack;

/**
 * 链式存储结构的二叉树
 */
public class MyLinkedBinaryTree<AnyType> {

    // 使用内部类表示节点
    class Node<AnyType>{
        private AnyType data;
        private Node<AnyType> left; // 指向左儿子
        private Node<AnyType> right; // 指向右儿子
        private int pushCounter; // 入栈次数

        public Node(AnyType data, Node<AnyType> left, Node<AnyType> right) {
            this.data = data;
            this.left = left;
            this.right = right;
            pushCounter = 0;
        }

        public int getPushCounter() {
            return pushCounter;
        }

        public void setPushCounter(int pushCounter) {
            this.pushCounter = pushCounter;
        }

        public AnyType getData() {
            return data;
        }

        public void setData(AnyType data){
            this.data = data;
        }

        public Node<AnyType> getLeft(){
            return this.left;
        }

        public void setLeft(Node<AnyType> left){
            this.left = left;
        }

        public Node<AnyType> getRight(){
            return this.right;
        }

        public void setRight(Node<AnyType> right){
            this.right = right;
        }
    }

    private Node<AnyType> root; // 指向树的根节点
    private int size; // 书的大小
    private static String NULLNODE = "0"; // 标识空节点

    public MyLinkedBinaryTree(){
        create();
    }

    public Node<AnyType> getRoot(){
        return root;
    }

    private void create(){
        root = new Node<>(null, null, null);
        size = 0;
    }

    public boolean isEmpty(){
        return root.data == null && root.getRight() == null && root.getLeft() == null;
    }

    // 中序遍历递归实现
    public void inOrderTraversal(){
        inOrderTraversal(root);
    }
    public void inOrderTraversal(Node<AnyType> root){
        if(root != null){
            inOrderTraversal(root.left);
            System.out.println(root.data);
            inOrderTraversal(root.right);
        }
    }

    // 中序遍历非递归实现
    public void inOrderTraversalNotRecursive(){
        inOrderTraversalNotRecursive(root);
    }
    public void inOrderTraversalNotRecursive(Node<AnyType> root){
        Node<AnyType> tempTree = root;
        MyLinkedStack<Node<AnyType>> stack = new MyLinkedStack<>();
        while(tempTree != null || !stack.isEmpty()){
            System.out.println(tempTree.data);
            // 将子树的所有左儿子入栈
            while(tempTree != null){
                stack.push(tempTree);
                tempTree = tempTree.left;
            }
            // 当左分支无法访问时，从栈中弹出一个节点，以他作为一个子树的根节点，继续访问他的左子树
            tempTree = stack.pop();
            System.out.println(tempTree.data);
            tempTree = tempTree.right;
        }
    }

    // 先序遍历
    public void preOrderTraversal(){
        preOrderTraversal(root);
    }
    public void preOrderTraversal(Node<AnyType> root){
        if(root != null){
            System.out.println(root.data);
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }
    // 先序遍历非递归实现
    public void preOrderTraversalNotRecursive(){
        preOrderTraversalNotRecursive(root);
    }
    public void preOrderTraversalNotRecursive(Node<AnyType> root){
        Node<AnyType> tempTree = root;
        MyLinkedStack<Node<AnyType>> stack = new MyLinkedStack<>();
        while(tempTree != null || !stack.isEmpty()){
            // 从子树的根节点出发，将其左儿子入栈
            while(tempTree != null){
                System.out.println(tempTree.data);
                stack.push(tempTree);
                tempTree = tempTree.left;
            }
            // 此时指向最左边的左儿子
            tempTree = stack.pop();
            // 以该元素的右儿子作为下次遍历的子树
            tempTree = tempTree.right;
        }
    }

    // 后序遍历
    public void postOrderTraversal(){
        postOrderTraversal(root);
    }
    public void postOrderTraversal(Node<AnyType> root){
        if(root != null){
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.println(root.data);
        }
    }
    // 后序遍历的非递归实现
    public void postOrderTraversalNotRecursive(){
        postOrderTraversalNotRecursive(root);
    }
    public void postOrderTraversalNotRecursive(Node<AnyType> root){
        Node<AnyType> tempTree = root;
        MyLinkedStack<Node<AnyType>> stack = new MyLinkedStack<>();
        while(tempTree != null || !stack.isEmpty()){
            // 从子树的根节点出发，将其左儿子入栈
            while(tempTree != null){
                stack.push(tempTree);
                tempTree.pushCounter++;
                tempTree = tempTree.left;
            }
            // 此时指向最左边的左儿子
            tempTree = stack.pop();
            // 因为最左端节点的右子树还没有访问，所以还不能把该节点从栈中弹出，因此需要入栈
            // 当第二次从栈中弹出时，其左子树和右子树已经访问了，因此可以从栈中弹出并打印
            if(tempTree.pushCounter == 2){
                System.out.println(tempTree);
            }else{
                stack.push(tempTree);
                tempTree.pushCounter++;
            }
            // 以该元素的右儿子作为下次遍历的子树
            tempTree = tempTree.right;
        }
    }

    // 层次遍历
    public void levelOrderTraversal(){
        levelOrderTraversal(root);
    }

    public void levelOrderTraversal(Node<AnyType> root){
        Node<AnyType> tempTree = root;
        MyLinkedQueue<Node<AnyType>> queue = new MyLinkedQueue<>();
        // 若树为空，则直接返回
        if(tempTree == null){
            return;
        }
        // 将子树的根节点入队
        queue.enqueue(tempTree);
        // 如果队列不为空
        while(!queue.isEmpty()){
            // 出队一个元素
            Node<AnyType> node = queue.dequeue();
            // 访问这个元素
            System.out.println(node.data);
            // 如果该元素的左右子树不为空，则将其左右子树的根节点入队
            if(node.left != null){
                queue.enqueue(node.left);
            }
            if(node.right != null){
                queue.enqueue(node.right);
            }
        }
    }

    // 输出二叉树中所有的叶子节点
    public void preOrderPrintLeaves(){
        preOrderPrintLeaves(root);
    }

    public void preOrderPrintLeaves(Node<AnyType> root){
        Node<AnyType> tempTree = root;
        if(tempTree != null){
            // 如果是叶子节点，则打印
            if(tempTree.left == null && tempTree.right == null){
                System.out.println(tempTree.data);
            }
            preOrderPrintLeaves(tempTree.left);
            preOrderPrintLeaves(tempTree.right);
        }
    }

    // 返回二叉树的高度
    public int getHeight(){
        return getHeight(root);
    }
    public int getHeight(Node<AnyType> root){
        int leftHeight, rightHeight, maxHeight;
        Node<AnyType> tempTree = root;
        // 一个节点的高度等于其左右子树高度的最大值+1
        if(tempTree != null){
            leftHeight = getHeight(tempTree.left);
            rightHeight = getHeight(tempTree.right);
            maxHeight = leftHeight > rightHeight ? leftHeight : rightHeight;
            return maxHeight + 1;
        }
        // 空树的高度为0
        else{
            return 0;
        }
    }

    //基于层序遍历生成一个二叉树
    public void createInLevelOrder(AnyType[] input){
        Node<AnyType> tempTree = root;
        MyLinkedQueue<Node<AnyType>> queue = new MyLinkedQueue<>();

        if(input[0] == null){
            return;
        }
        // 将树的根节点入队
        tempTree.data = input[0];
        queue.enqueue(tempTree);

        int inputIndex = 1; // 输入数据的起始下标
        while(!queue.isEmpty()){
            tempTree = queue.dequeue(); // 从队列中移除当前节点
            // 然后从输入数据中读取两个输入，作为当前节点的左右节点，并将这两个节点入队
            // 从输入数据中读入一个数据，作为tempTree节点的左节点，将该左节点入队，作为下一次处理的节点
            if(((String)input[inputIndex]).equals(NULLNODE)){
                // 如果输入的是空节点，则它没有左儿子
                tempTree.left = null;
            }
            else{
                tempTree.left = new Node<>(input[inputIndex], null, null);
                queue.enqueue(tempTree.left);
            }
            inputIndex++;
            if(inputIndex >= input.length){
                break;
            }

            // 从输入数据中读入一个数据，作为tempTree节点的右节点，将该右节点入队，作为下一次处理的节点
            if(((String)input[inputIndex]).equals(NULLNODE)){
                // 如果输入的是空节点，则它没有右儿子
                tempTree.right = null;
            }
            else{
                tempTree.right = new Node<>(input[inputIndex], null, null);
                queue.enqueue(tempTree.right);
            }
            inputIndex++;
            if(inputIndex >= input.length){
                break;
            }
        }
    }

    // 创建单个节点的二叉树
    public void create(AnyType data){
        root.data = data;
        root.left = null;
        root.right = null;
    }

    // 合并两棵二叉树到根节点上
    public void merge(MyLinkedBinaryTree<AnyType> leftTree, MyLinkedBinaryTree<AnyType> rightTree){
        root.left = leftTree.root;
        root.right = rightTree.root;
    }

}
