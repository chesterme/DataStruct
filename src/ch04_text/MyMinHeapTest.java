package ch04_text;

public class MyMinHeapTest {

    public static void main(String[] args){

        MyMinHeap<Integer> heap = new MyMinHeap<>(new MyIntegerComparator());
        for(int i = 1; i <= 20; i++){
            heap.insert(i);
        }

        heap.printAll();
        for(int i = 1; i <= 20; i++){
            System.out.println("current min: " +heap.deleteMix());
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
