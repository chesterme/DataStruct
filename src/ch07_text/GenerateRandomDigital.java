package ch07_text;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateRandomDigital {

    public static void main(String[] args) throws IOException {

        File file = new File("resources/randomDigital.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter write = new FileWriter(file);

        int infinity = 10000000;
        int value = 0;
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < infinity; i++){
            value = random.nextInt(Integer.MAX_VALUE);
            if(value % 3 == 0){
                value = value *  -1;
            }
            write.write(Integer.toString(value));
            write.write("\n");
        }
        write.flush();
    }

}
