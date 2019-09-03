package ch01_text;

public class GenericMemoryCellTest {

    public static void main(String[] args){

        GenericMemoryCell m = new GenericMemoryCell<String>();
//         m.setStoredValue(12); // error: java.lang.Integer cannot be cast to java.lang.String
        m.setStoredValue("hello");
        String result = (String) m.getStoredValue();
        System.out.println(result);

        GenericMemoryCell<Integer> cell1 = new GenericMemoryCell<>();
        cell1.setStoredValue(4);
        Object cell = cell1;
        GenericMemoryCell<String> cell2 = (GenericMemoryCell<String>) cell;
//        String s = cell2.getStoredValue(); // error, java.lang.Integer cannot be cast to java.lang.String

    }

}
