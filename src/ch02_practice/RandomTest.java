package ch02_practice;

import java.util.Random;

public class RandomTest {

    // 打印数组
    public static void printArray(int[] input){
        for(int i = 0; i < input.length; i++){
            System.out.print(input[i] + ", ");
        }
        System.out.println();
    }

    // 生成一个数组项不重复的数组
    public static int[] generateNotRedundancyArray(int size, int minimum, int maximum){
        int[] result = new int[size];
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < size; i++){
            int value = 0;
            // 生成一个不重复的数组元素
            do{
                // 例如，需要生成一个10~100之间的数，random.nexInt(100)得到[0, 100)之间的数
                // random.nextInt(100) % 91得到[0, 91)之间的数
                // [0, 91) + 10得到[10,101)之间的数
                value = random.nextInt(maximum) % (maximum - minimum + 1) + minimum;
            }while(isPresence(result, value, i + 1));
            result[i] = value;
        }
        return result;
    }

    // 生成一个不重复的数组
    public static int[] generateNotRedundancyArray2(int size, int minimum, int maximum){
        int result[] = new int[size];
        boolean[] used = new boolean[maximum + 1];

        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < size; i++){
            int value = 0;
            do{
                value = random.nextInt(maximum) % (maximum - minimum + 1) + minimum;
            }while(used[value]);
            used[value] = true;
            result[i] = value;
        }

        return result;
    }

    // 检查某个元素是否在数组中
    public static boolean isPresence(int[] target, int key, int size){
        for(int i= 0; i < size; i++){
            if(target[i] == key){
                return true;
            }
        }
        return false;
    }

    // 生成一个不重复的数组
    public static int[] generateNotRedundancyArray3(int size, int minimum, int maximum){
        int[] result = new int[size + 1];
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i <= size; i++){
            result[i] = i + 1;
        }
        for(int i = 0; i < size; i++){
            int temp = result[i];
            int index = randInt(random, 1, i + 1);
            result[i] = result[index];
            result[index] = temp;
        }
        return result;
    }

    public static int randInt(Random random, int minimum, int maximum){
        return random.nextInt(maximum) % (maximum - minimum + 1) + minimum;
    }

    public static void main(String[] args){
        int[] array = generateNotRedundancyArray(10, 5, 14);
        printArray(array);

        int[] array2 = generateNotRedundancyArray2(10, 5, 14);
        printArray(array2);

        int[] array3 = generateNotRedundancyArray3(10, 5, 14);
        printArray(array3);

    }

}
