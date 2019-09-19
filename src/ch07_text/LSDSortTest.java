package ch07_text;

import java.util.Random;

public class LSDSortTest {

    public static void main(String[] args){

//        int[] input = {64, 8, 216, 512, 27, 729, 0, 1, 343, 125};

        Random random = new Random(System.currentTimeMillis());
        int length = 1000;
        int[] input = new int[length];
        for(int i = 0; i < input.length; i++){
            input[i] = random.nextInt(10000);
        }

        LSDSort sort = new LSDSort();
        sort.sort(input);
        IntegerList list = sort.getList();
        list.printAll();
    }

}
