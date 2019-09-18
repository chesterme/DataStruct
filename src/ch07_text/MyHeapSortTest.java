package ch07_text;

import ch03_text.MyLinkedList;

import java.io.*;
import java.util.Scanner;

public class MyHeapSortTest {

    public static void main(String[] args) throws IOException {

        double beforeTime = System.currentTimeMillis();
        FileReader reader = new FileReader(new File("resources/randomDigital.txt"));
        Scanner scanner = new Scanner(reader);
        MyLinkedList<Integer> list = new MyLinkedList<>();
        while(scanner.hasNext()){
            list.add(scanner.nextInt());
        }

        File output = new File("resources/sortOutput.txt");
        if(!output.exists()){
            output.createNewFile();
        }
        PrintStream out = new PrintStream(output);

        Object[] objects = list.toArray();
        Integer[] input = new Integer[objects.length];
        for(int i = 0; i < input.length; i++){
            input[i] = (Integer) objects[i];
        }

        MyHeapSort<Integer> heapSort = new MyHeapSort(input, new IntegerComparator());
        heapSort.heapSort();

        Integer[] result = heapSort.getInput();
        for(int i = 0; i < result.length; i++){
            out.printf("%5d", result[i]);
            if(i % 10 == 0){
                out.println();
            }
        }

        out.println("\n+++++++++++++++++++++++++");
        double afterTime = System.currentTimeMillis();
        out.println("一共花费的时间为：" + (afterTime - beforeTime) / 1000 + " 秒");
    }

}
