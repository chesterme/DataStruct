package ch04_text;

import java.util.Comparator;

/**
 * 平衡树
 */
public class MyBalancedBinaryTree<AnyType> extends MyLinkedBinaryTree<AnyType> {

    private Comparator<? super AnyType> comparator; // 节点数据比较器
    private static final int ALLOW_IMBALANCE = 1; // 允许节点的左右子树的高度相差1

    public MyBalancedBinaryTree(Comparator<? super AnyType> comparator){
        super();
        this.comparator = comparator;
    }

    // 在二叉树中插入一个节点
    public void insert(AnyType x){
        setRoot(insert(getRoot(), x));
    }

    private Node<AnyType> insert(Node<AnyType> root, AnyType x){
        // 如果节点为空，则根据数据创建一个新的节点
        if(root == null){
            return new Node<AnyType>(x, null, null);
        }
        // 如果只有树的根节点，则设置树的根节点
        else if(root.getData() == null && root.getLeft() == null && root.getRight() == null){
            root.setData(x);
        }
        // 如果当前节点不为空，则将插入元素x与当前节点中的数据进行比较，确定将元素x插入在当前节点的左子树还是右子树
        else{
            int compareResult = comparator.compare(x, root.getData());
            // 如果插入元素x比当前节点的数据大，则将x插入在当前节点的右子树中
            if(compareResult > 0){
                root.setRight(insert(root.getRight(), x));
            }
            // 如果插入的元素x比当前节点的数据小，则将x插入在当前节点的左子树中
            else if(compareResult < 0){
                root.setLeft(insert(root.getLeft(), x));
            }
            // 如果相等，则是冗余的，不进行处理
            else{
                ;
            }
        }
        // 最后需要对插入的元素进行平衡操作
        return balance(root);
    }

    // 返回当前节点的高度
    private int height(Node<AnyType> currentNode){
        return currentNode == null ? -1 : currentNode.getHeight();
    }

    // 平衡操作
    private Node<AnyType> balance(Node<AnyType> root){
        if(root == null){
            return root;
        }
        int leftHeight = height(root.getLeft());
        int rightHeight = height(root.getRight());
        // 如果节点的左子树高度比右子树高度大于1，即发生不平衡的地方出现在左子树中
        if(height(root.getLeft()) - height(root.getRight()) > ALLOW_IMBALANCE){
            // 如果左子树的左子树高度比其右子树高度高或者相等，执行一次单旋转，将节点的左子树升高，当前节点降低
            if(height(root.getLeft().getLeft()) >= height(root.getLeft().getRight())){
                root = rotateWithLeftChild(root);
            }
            // 如果左子树的左子树高度比其右子树高度低，则需要执行一次双旋转
            else{
                root = doubleWithLeftChild(root);
            }
        }
        // 如果发生不平衡的地方出现在右子树
        else if(height(root.getRight()) - height(root.getLeft()) > ALLOW_IMBALANCE){
            // 如果右子树的右子树高度比其左子树高度高或者相等，则需要使用右子树执行一次单旋转，将右子树升高，当前节点降低
            if(height(root.getRight().getRight()) - height(root.getRight().getLeft()) >= ALLOW_IMBALANCE){
                root = rotateWithRightChild(root);
            }
            // 如果右子树的左子树高度比其右子树高度高，则需要使用右子树执行一次双旋转，将右子树的左节点升高，当前节点降低
            else{
                root = doubleWithRightChild(root);
            }
        }
        // 更新当前节点的高度
        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);
        return root;
    }

    // 用节点的左子树进行单旋转，并返回当前子树的根节点
    private Node<AnyType> rotateWithLeftChild(Node<AnyType> root){
        // 将左节点升高，当前节点降低
        Node<AnyType> leftChild = root.getLeft();
        root.setLeft(leftChild.getRight());
        leftChild.setRight(root);
        // 更改对应的高度
        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);
        leftChild.setHeight(Math.max(height(leftChild.getLeft()), height(leftChild.getRight())) + 1);
        return leftChild;
    }

    // 使用节点的右子树进行一次单旋转，并返回当前子树的根节点
    private Node<AnyType> rotateWithRightChild(Node<AnyType> root){
        // 将右节点升高，当前节点降低
        Node<AnyType> rightChild = root.getRight();
        root.setRight(rightChild.getLeft());
        rightChild.setLeft(root);
        // 更改对应的高度
        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);
        rightChild.setHeight(Math.max(height(rightChild.getLeft()), height(rightChild.getRight())) + 1);
        return rightChild;
    }

    // 用左子树进行一次双旋转
    private Node<AnyType> doubleWithLeftChild(Node<AnyType> root){
        // 将当前节点的左子树视为一个整体，使用其右子树进行一次单旋转，调整其高度
        root.setLeft(rotateWithRightChild(root.getLeft()));
        // 把当前节点和调整后的左子树视为一个整体，使用其左子树进行一次单旋转，调整其高度
        return rotateWithLeftChild(root);
    }

    // 使用右子树进行一次双旋转
    private Node<AnyType> doubleWithRightChild(Node<AnyType> root){
        // 将当前节点的右子树视为一个整体，使用其左子树进行一次单旋转，调整其高度
        root.setRight(rotateWithLeftChild(root.getRight()));
        // 将整个子树视为一个整体，使用其右子树进行一次单旋转，调整高度
        return rotateWithRightChild(root);
    }

    // 从二叉树中删除某个元素
    public Node<AnyType> delete(AnyType x){
        return delete(getRoot(), x);
    }

    private Node<AnyType> delete(Node<AnyType> root, AnyType x){
        Node<AnyType> tempNode = null;

        if(root == null){
//            System.out.println("要删除的元素不存在");
            return null;
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
        return balance(root);
    }

    // 查找二叉搜索树上的最小节点
    public Node<AnyType> findMin(){
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

    // 查找二叉树中是否存在某个元素x
    public boolean isContain(AnyType x){
        Node<AnyType> tempTree = getRoot();
        boolean result = false;
        while(tempTree != null && tempTree.getData() != null){
            int compareResult = comparator.compare(x, tempTree.getData());
            if(compareResult > 0){
                tempTree = tempTree.getRight();
            }else if(compareResult < 0){
                tempTree = tempTree.getLeft();
            }else{
                result = true;
                break;
            }
        }
        return result;
    }
}
