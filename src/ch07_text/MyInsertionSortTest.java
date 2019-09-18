package ch07_text;

import ch03_text.MyLinkedList;

import java.io.*;
import java.util.Scanner;

public class MyInsertionSortTest {

    public static void main(String[] args) throws IOException {

        double beforeTime = System.currentTimeMillis();
        FileReader reader = new FileReader(new File("resources/randomDigital.txt"));
        Scanner scanner = new Scanner(reader);
        MyLinkedList<Integer> list = new MyLinkedList<>();
        while(scanner.hasNext()){
            list.add(scanner.nextInt());
        }

        Object[] objects = list.toArray();
        Integer[] input = new Integer[objects.length];
        for(int i = 0; i < input.length; i++){
            input[i] = (Integer)objects[i];
        }

        MyInsertionSort<Integer> insertionSort = new MyInsertionSort<>(input, new IntegerComparator());
        insertionSort.insertSort();

        PrintStream out = new PrintStream(new File("resources/sortOutput.txt"));
        Integer[] result = insertionSort.getInput();
        for(int i = 0; i < result.length; i++){
            out.printf("%5d", result[i]);
            if(i % 10 == 0){
                System.out.println();
            }
        }

        out.println("\n+++++++++++++++++++++++++");
        double afterTime = System.currentTimeMillis();
        out.println("一共花费的时间为：" + (afterTime - beforeTime) / 1000 + " 秒");

    }

}
