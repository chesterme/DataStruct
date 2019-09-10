package ch05_text;

public class MyOpenAddressingHashTableTest {

    public static void main(String[] args){

        int tableSize = 9;
        MyHash<Integer> myHash = new MyIntegerHash();
        MyOpenAddressingHashTable<Integer> hashTable = new MyOpenAddressingHashTable<>(tableSize, myHash);
        int[] input = {47, 7, 29, 11, 9, 84, 54, 20, 30};
        for(int i = 0; i < input.length; i++){
            hashTable.insert(input[i]);
        }
        for(int i = 0; i < input.length; i++){
            System.out.println("关键字：" + input[i] + "，在散列表中的位置为：" + hashTable.find(input[i]));
        }

    }

}
