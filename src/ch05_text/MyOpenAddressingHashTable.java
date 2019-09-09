package ch05_text;

/**
 * 散列表
 */
public class MyOpenAddressingHashTable<AnyType> {

    private static final int MAX_SIZE = 100000; // 允许开辟的散列表最大长度
    private int tableSize; // 散列表最大长度
    private MyTableNode<AnyType>[] tables;
    private MyHash<AnyType> myHash;

    public MyOpenAddressingHashTable(int tableSize, MyHash<AnyType> myHash){
        this.myHash = myHash;
        create(tableSize);
    }

    // 创建一个散列表
    private void create(int tableSize){
        // 返回一个大于tableSize但不超过MAX_SIZE的最小素数
        tableSize = nextPrime(tableSize);
        tables = (MyTableNode<AnyType>[]) new Object[tableSize];
        // 初始化散列表中元素的内容
        for(int i = 0; i < tableSize; i++){
            tables[i].setData(null);
            tables[i].setInfo(MyHashEntryType.EMPTY);
        }
    }

    // 返回一个大于tableSize但不超过MAX_SIZE的最小素数
    private int nextPrime(int tableSize){
        int i;
        // 从下一个大于tableSize的奇数开始
        int p = tableSize % 2 == 0 ? tableSize + 1 : tableSize + 2;

        while(p <= MAX_SIZE){
            for(i = (int)Math.sqrt(p); i > 2; ){
                // p不是素数
                if(p % i == 0){
                    break;
                }
            }
            // 如果i==2，表明p是素数
            if(i == 2){
                break;
            }
            // 否则，查找下一个奇数
            else{
                p += 2;
            }
        }
        return p;
    }

    // 从散列表中查找某个元素
    // 可能返回一个空元素的下标、该元素的下标
    public int find(AnyType x){
        int currentPosition, newPosition;
        int collisionCounter = 0; // 记录冲突次数

        // 通过散列函数找到关键字key对应的存储位置
        currentPosition = newPosition = myHash.hash(x, tableSize);
        // 如果该位置上的元素不为空，并且不是想要找的元素时，即发生冲突时
        while(!tables[newPosition].getData().equals(x) && tables[newPosition].getInfo() != MyHashEntryType.EMPTY){
            // 统计冲突次数
            collisionCounter++;
            // 如果是奇数次冲突
            if(collisionCounter % 2 == 1){
                newPosition = currentPosition + (collisionCounter + 1) * (collisionCounter + 1) / 4;
                if(newPosition >= tableSize){
                    newPosition %= tableSize;
                }
            }
            else{
                newPosition = currentPosition - collisionCounter * collisionCounter / 4;
                while(newPosition < 0){
                    newPosition += tableSize;
                }
            }
        }
        return newPosition;
    }

    // 判断一个元素x是否存在于散列表中
    public boolean isExist(AnyType x){
        int index = find(x);
        // 如果该元素不是一个合法的元素
        if(tables[index].getInfo() != MyHashEntryType.LEGITIMATE){
            return false;
        }
        return true;
    }


    // 将一个元素x插入到散列表中
    public boolean insert(AnyType x){
        int position = find(x);
        // 检查元素x是否存在于表中
        if(tables[position].getInfo() != MyHashEntryType.LEGITIMATE){
            tables[position].setData(x);
            tables[position].setInfo(MyHashEntryType.LEGITIMATE);
            return true;
        }
        else{
            System.out.println("键值已经存在");
            return false;
        }
    }
}
