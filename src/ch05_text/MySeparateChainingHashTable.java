package ch05_text;

/**
 * 散列表
 * 使用分离链接法来处理冲突问题
 * @param <AnyType>
 */
public class MySeparateChainingHashTable<AnyType> {

    private int tableSize; // 最大表长
    private MyLinkedTableNode<AnyType>[] headers; // 一维数组，其中每个元素存储一个链表的表头
    private MyHash<AnyType> myHash;

    public MySeparateChainingHashTable(int tableSize, MyHash<AnyType> myHash){
        this.myHash = myHash;
        this.tableSize = nextPrime(tableSize);
        create();
    }

    // 创建一个空白的散列表
    private void create(){
        headers = new MyLinkedTableNode[tableSize];
        for(int i = 0; i < headers.length; i++){
            headers[i] = new MyLinkedTableNode<>(null, null);
        }
    }

    // 返回一个大于size的最小素数
    private int nextPrime(int size){
        int i, p = size % 2 == 0 ? size + 1 : size + 2;
        while(true){
            for(i = (int)Math.sqrt(p); i > 2; i--){
                if(p % i == 0){
                    // 说明p不是素数
                    break;
                }
            }
            if(i == 2){
                // 说明p是素数
                break;
            }else{
                // 查找下一个奇数
                p += 2;
            }
        }
        return p;
    }

    // 从散列表中查找元素x，若找到则返回该节点，否则返回null
    public MyLinkedTableNode<AnyType> find(AnyType x){
        MyLinkedTableNode<AnyType> tempNode;
        int hashIndex = myHash.hash(x, tableSize);
        // 获取hash值对应链表的第一个节点元素
        tempNode = headers[hashIndex].getNext();
        // 遍历该链表
        while(tempNode != null){
            if(!tempNode.getData().equals(x)){
                tempNode = tempNode.getNext();
            }
            else{
                break;
            }
        }
        return tempNode;
    }

    // 判断散列表中是否存在元素x
    public boolean isExist(AnyType x){
        return find(x) == null ? false : true;
    }

    // 在散列表中插入一个元素x
    public boolean insert(AnyType x){
        // 如果散列表中不存在该元素，则插入
        if(!isExist(x)){
            // 获取hash值
            int insertIndex = myHash.hash(x, tableSize);
            MyLinkedTableNode<AnyType> tempNode = new MyLinkedTableNode<>(x, null);
            MyLinkedTableNode<AnyType> header = headers[insertIndex];
            tempNode.setNext(header.getNext());
            header.setNext(tempNode);
            return true;
        }
        else{
            System.out.println("键值已经存在");
            return false;
        }
    }

    // 在散列表中删除元素x
    public MyLinkedTableNode<AnyType> delete(AnyType x){
        if(isExist(x)){
            int deleteIndex = myHash.hash(x, tableSize);
            MyLinkedTableNode<AnyType> previousNode = headers[deleteIndex];
            MyLinkedTableNode<AnyType> currentNode = headers[deleteIndex].getNext();
            while(!currentNode.getData().equals(x)){
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            previousNode.setNext(currentNode.getNext());
            return currentNode;
        }
        else{
            System.out.println("散列表不存在该元素，无法删除");
            return null;
        }
    }

    // 打印散列表
    public void printAll(){
        for(int i = 0; i < tableSize; i++){
            if(headers[i].getNext() != null){
                System.out.println("下标为：" + i + " 的链表元素为：");
                MyLinkedTableNode<AnyType> tempNode = headers[i].getNext();
                while(tempNode != null){
                    System.out.println(tempNode);
                    tempNode = tempNode.getNext();
                }
            }
        }
    }
}
