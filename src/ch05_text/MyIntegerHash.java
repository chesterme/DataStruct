package ch05_text;

public class MyIntegerHash implements MyHash<Integer> {

    @Override
    public int hash(Integer key, int tableSize) {
        // 使用除留余数法
        return key % tableSize;
    }
}
