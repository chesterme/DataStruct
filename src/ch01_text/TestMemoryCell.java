package ch01_text;

public class TestMemoryCell {

    public static void main(String[] args){
        MemoryCell m = new MemoryCell();
        m.write("27");
        String result =(String)m.read();
        System.out.println(result);
    }

}
