package ch01_text;

public class FindMaxDemo {

    public static Comparable findMax(Comparable[] arr){
        int maxIndex = 0;
        for(int i = 1; i < arr.length; i++){
            if(arr[maxIndex].compareTo(arr[i]) < 0){
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    public static void main(String[] args){
        Student[] arr1 = {
                new Student(12, "张三"),
                new Student(15, "李四"),
                new Student(14, "王五")
        };
        System.out.println(findMax(arr1));
    }
}
