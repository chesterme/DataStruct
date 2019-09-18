package ch07_text;

import ch03_text.MyLinkedList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class MyQuickSortTest {

    public static void main(String[] args) throws FileNotFoundException {

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

        MyQuickSort<Integer> quickSort = new MyQuickSort<>(input, new IntegerComparator());
        quickSort.quickSort();

        PrintStream out = new PrintStream(new File("resources/sortOutput.txt"));
        Integer[] result = quickSort.getInput();
        for(int i = 0; i < result.length; i++){
            out.printf("%5d\t", result[i]);
            if(i % 10 == 0){
                out.println();
            }
        }

        out.println("\n+++++++++++++++++++++++++");
        double afterTime = System.currentTimeMillis();
        out.println("一共花费的时间为：" + (afterTime - beforeTime) / 1000 + " 秒");


    }

}
