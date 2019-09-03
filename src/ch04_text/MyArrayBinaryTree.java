package ch04_text;

/**
 * 顺序存储结构的二叉树
 * 适合完全二叉树
 */
public class MyArrayBinaryTree<AnyType> {

    private AnyType[] data;
    private AnyType blank; // 虚节点
    private static int DEFAULT_SIZE = 4;
    private int size;

    public MyArrayBinaryTree(AnyType blank){
        creat();
        this.blank = blank;
    }

    public void creat(){
        data = (AnyType[]) new Object[DEFAULT_SIZE];
        size = 0;
    }

    public boolean isFull(){
        return size == data.length - 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void ensureCapacity(int newSize){
        if(size > newSize){
            return;
        }
        AnyType[] temp = (AnyType[]) new Object[newSize];
        for(int i = 0; i <= size; i++){
            temp[i] = data[i];
        }
        data = temp;
    }

    public void insert(AnyType x){
        if(isFull()){
            ensureCapacity(data.length * 2);
        }
        data[++size] = x;
    }

    public AnyType delete(){
        if(isEmpty()){
            return null;
        }
        return data[size--];
    }

    public int find(AnyType x){
        for(int i = 1; i < data.length; i++){
            if(data[i].equals(x)){
                return i;
            }
        }
        return -1;
    }

    public AnyType getParent(AnyType x){
        int position = find(x);
        if(position < 1){
            System.out.println("不存在该元素：" + x);
            return null;
        }
        int parentIndex = position / 2;
        if(parentIndex == 0){
            // 该节点是根节点
            return null;
        }
        return data[parentIndex];
    }

    public AnyType getLeftChild(AnyType x){
        int position = find(x);
        if(position < 1){
            System.out.println("不存在该元素：" + x);
            return null;
        }
        int leftChildIndex = position * 2;
        if(leftChildIndex > size){
            System.out.println("不存在该元素的左儿子");
            return null;
        }
        return data[leftChildIndex];
    }

    public AnyType getRightChild(AnyType x){
        int position = find(x);
        if(position < 1){
            System.out.println("不存在该元素：" + x);
            return null;
        }
        int rightChildIndex = position * 2 + 1;
        if(rightChildIndex > size){
            System.out.println("不存在该元素的右儿子");
            return null;
        }
        return data[rightChildIndex];
    }

    // 将x作为node的左儿子插入在树中
    public void insertLeftChild(AnyType node, AnyType x){
        int position = find(node);
        if(position < 1){
            System.out.println("不存在该节点：" + node);
            return;
        }
        int targetPosition = position * 2;
        if(targetPosition > data.length){
            ensureCapacity(data.length * 2);
        }
        // 如果已经有左儿子，则替换数据
        if(data[targetPosition] != null){
            data[targetPosition] = node;
        }
        // 如果没有左儿子，则需要填充虚节点
        int start = targetPosition - 1;
        do{
            data[start--] = blank;
        }while(data[start] == null);
        data[targetPosition] = x;
        size = targetPosition;
    }

    // 将x作为node的右儿子插入在树中
    public void insertRightChild(AnyType node, AnyType x){
        int position = find(node);
        if(position < 1){
            System.out.println("不存在该节点：" + node);
            return;
        }
        int targetPosition = position * 2 + 1;
        if(targetPosition > data.length){
            ensureCapacity(data.length * 2);
        }
        // 如果已经有右儿子，则替换数据
        if(data[targetPosition] != null){
            data[targetPosition] = node;
        }
        // 如果没有左儿子，则需要填充虚节点
        int start = targetPosition - 1;
        do{
            data[start--] = blank;
        }while(data[start] == null);
        data[targetPosition] = x;
        size = targetPosition;
    }
}
