package ch05_text;

public class MySeparateChainingHashTableTest {

    public static void main(String[] args){

        int[] input = {47, 7, 29, 11, 16, 92, 22, 8, 3, 50, 37, 89, 21};
        int tableSize = 13;
        MySeparateChainingHashTable<Integer> hashTable = new MySeparateChainingHashTable<>(tableSize, new MyIntegerHash());
        for(int i = 0; i < input.length; i++){
            hashTable.insert(input[i]);
        }
        hashTable.printAll();
        System.out.println("========================");
        for(int i = 0; i < input.length; i++){
            hashTable.delete(input[i]);
            System.out.println("删除元素：" + input[i] + " 后：");
            hashTable.printAll();
            System.out.println("========================");
        }

    }

}
