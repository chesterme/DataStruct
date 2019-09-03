package ch01_text;

public class BoxDemo {

    public static void main(String[] args){
        GenericMemoryCell<Integer> m = new GenericMemoryCell<>();
        m.setStoredValue(37);
        int value = m.getStoredValue();
        System.out.println(value);
    }

}
