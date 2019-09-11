package ch05_text;

public class StringCounterTest {

    public static void main(String[] args){

        int tableSize = 100;
        String fileName = "resources/inception.srt";
        StringCounter stringCounter = new StringCounter(tableSize, new MyStringHash(), fileName);
        double percent = 0.1;
        stringCounter.main(percent);

    }

}
