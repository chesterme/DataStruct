package ch04_text;

public class MyMaxHeapTest {

    public static void main(String[] args){

        MyMaxHeap<Integer> heap = new MyMaxHeap<>(new MyIntegerComparator());
        for(int i = 1; i <= 20; i++){
            heap.insert(i);
        }
//        heap.deleteMax();
        heap.printAll();
        for(int i = 1; i <= 20; i++){
            System.out.println("current max: " +heap.deleteMax());
            heap.printAll();
        }

        Integer[] input = new Integer[21];
        for(int i = 1; i < input.length; i++){
            input[i] = i;
        }
        input[0] = Integer.MAX_VALUE;
        heap.buildHeap(input);
        heap.printAll();
    }

}
