package ch04_text;

import java.util.Comparator;

/**
 * 二叉搜索树
 */
public class MyBinarySearchTree<AnyType> extends  MyLinkedBinaryTree<AnyType>{

    private Comparator<? super AnyType> comparator;

    public MyBinarySearchTree(Comparator<? super AnyType> comparator){
        super();
        this.comparator = comparator;
    }

    // 从二叉搜索树中查找关键字X的节点，递归查找函数
    public Node<AnyType> find(AnyType x){
        return find(getRoot(), x);
    }
    private Node<AnyType> find(Node<AnyType> root, AnyType x){
        // 查找失败，则返回null
        if(root == null) return null;
        //  如果子树的根节点的值比关键字x大，则说明关键字x只能出现在子树的左侧
        if(comparator.compare(root.getData(), x) > 0){
            return find(root.getLeft(), x);
        }
        // 如果子树的根节点的值比关键字x小，则索命关键字只能出现在子树的右侧
        else if(comparator.compare(root.getData(), x) < 0){
            return find(root.getRight(), x);
        }else{
            return root;
        }
    }

    // 从二叉搜索树中查找关键字X的节点，非递归查找函数
    public Node<AnyType> findNotRecursive(AnyType x){
        return findNotRecursive(getRoot(), x);
    }

    private Node<AnyType> findNotRecursive(Node<AnyType> root, AnyType x){
        Node<AnyType> temp = root;
        while(temp != null){
            if(comparator.compare(temp.getData(), x) > 0){
                temp = temp.getLeft();
            }
            else if(comparator.compare(temp.getData(), x) < 0){
                temp = temp.getRight();
            }else{
                break;
            }
        }
        // 当找到时返回找到的节点，否则返回null
        return temp;
    }

    // 查找二叉搜索树上的最小节点
    public Node<AnyType> findMix(){
        return findMin(getRoot());
    }
    private Node<AnyType> findMin(Node<AnyType> root){
        // 如果是空的二叉树，则返回null
        if(root == null){
            return null;
        }
        // 如果已经到达二叉树的最左端，则直接返回该节点
        else if(root.getLeft() == null){
            return root;
        }
        // 否则从下一个左子树开始查找
        else{
            return findMin(root.getLeft());
        }
    }

    public Node<AnyType> findMinNotRecursive(){
        return findMinNotRecursive(getRoot());
    }

    private Node<AnyType> findMinNotRecursive(Node<AnyType> root){
        Node<AnyType> tempTree = root;
        if(tempTree != null){
            while(tempTree.getLeft() != null){
                tempTree = tempTree.getRight(); // 从左分支一直往下，直到最左端点
            }
        }
        return tempTree;
    }

    // 查找二叉树中最大元素
    public Node<AnyType> findMax(){
        return findMax(getRoot());
    }

    private Node<AnyType> findMax(Node<AnyType> root){
        if(root == null){
            return null;
        }
        else if(root.getRight() == null){
            return root;
        }
        else return findMax(root.getRight());
    }

    public Node<AnyType> findMaxNotRecursive(){
        return findMaxNotRecursive(getRoot());
    }

    private Node<AnyType> findMaxNotRecursive(Node<AnyType> root){
        Node<AnyType> tempTree = root;
        if(tempTree != null){
            while(tempTree.getRight() != null){ // 从右分支开始，一直往下直到最右端点
                tempTree = tempTree.getRight();
            }
        }
        return tempTree;
    }

    public Node<AnyType> insert(AnyType x){
        return insert(getRoot(), x);
    }

    private Node<AnyType> insert(Node<AnyType> root, AnyType x){
        // 如果原树为空，生成并返回一个节点的二叉搜索树
        if(root == null){
            root = new Node<AnyType>(x, null, null);
        }
        else if((root.getLeft() == null && root.getRight() == null && root.getData() == null)){
            root.setData(x);
            root.setLeft(null);
            root.setRight(null);
        }
        // 开始查找要插入的元素
        else{
            // 如果子树的根节点的值比插入元素大，说明该元素应该插入在子树的左子树中
            if(comparator.compare(root.getData(), x) > 0){
                root.setLeft(insert(root.getLeft(), x));
            }
            else if(comparator.compare(root.getData(), x) < 0){
                root.setRight(insert(root.getRight(), x));
            }
            // 如果该元素已经存在于子树中，则什么也不用做
            else ;
        }
        return root;
    }

    // 从二叉树中删除某个元素
    public Node<AnyType> delete(AnyType x){
        return delete(getRoot(), x);
    }

    private Node<AnyType> delete(Node<AnyType> root, AnyType x){
        Node<AnyType> tempNode = null;

        if(root == null){
            System.out.println("要删除的元素不存在");
        }
        else{
            // 如果元素x的值小于根节点的值，则从左子树开始删除
            if(comparator.compare(x, root.getData()) < 0){
                root.setLeft(delete(root.getLeft(), x));
            }
            else if(comparator.compare(x, root.getData()) >0){
                // 从右子树开始删除
                root.setRight(delete(root.getRight(), x));
            }
            // 该root就是要删除的节点
            else{
                // 如果被删除的节点有左右两个子节点
                if(root.getRight() != null && root.getLeft() != null){
                    // 从右子树中找出最小的元素填充到被删除的节点中
                    tempNode = findMin(root.getRight());
                    root.setData(tempNode.getData());
                    // 从右子树中删除最小元素
                    root.setRight(delete(root.getRight(), tempNode.getData()));
                }
                // 被删除的节点只有一个或无子节点
                else{
                    tempNode = root;
                    // 只有右节点或无子节点
                    if(root.getLeft() == null){
                        root = root.getRight();
                    }
                    else{
                        root = root.getLeft();
                    }
                }
            }
        }
        return root;
    }
}
