package ch07_text;

import ch03_text.MyLinkedList;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MySimpleSelectionSortTest {

    public static void main(String[] args) throws IOException {

        double beforeTime = System.currentTimeMillis();
        File file = new File("resources/randomDigital.txt");
        FileReader reader = new FileReader(file);
        Scanner scanner = new Scanner(reader);
        int value;
        MyLinkedList<Integer> list = new MyLinkedList<>();
        while(scanner.hasNext()){
            value = scanner.nextInt();
            list.add(value);
        }

        // 这里没有办法把一个Object数组转化成一个Integer数组
        // 需要转化Object数组中的每一个元素
        Object[] objs = list.toArray();
        Integer[] input = new Integer[objs.length];
        for(int i = 0; i < input.length; i++){
            input[i] = (Integer)objs[i];
        }

//        double beforeTime = System.currentTimeMillis();
        MySimpleSelectionSort<Integer> selectionSort = new MySimpleSelectionSort<>(input, new IntegerComparator());
        selectionSort.sort();
        Integer[] result = selectionSort.getInput();
//        for(int i = 0; i < result.length; i++){
//            System.out.printf("%5d", result[i]);
//            if(i % 10 == 0){
//                System.out.println();
//            }
//        }

        System.out.println("\n+++++++++++++++++++++++++");
        double afterTime = System.currentTimeMillis();
        System.out.println("一共花费的时间为：" + (afterTime - beforeTime) / 1000 + " 秒");
    }

}
