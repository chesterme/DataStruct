package ch01_text;

import java.util.Comparator;

public class TestProgram {

    public static <AnyType> AnyType findMax(AnyType[] arr, Comparator<? super AnyType> cmp){
        int maxIndex = 0;
        for(int i = 1; i < arr.length; i++){
            // if(arr[i].compareTo(arr[maxIndex) > 0) 有什么区别
            if(cmp.compare(arr[i], arr[maxIndex]) > 0){
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    class CaseInsensitiveCompare implements Comparator<String>{
        public int compare(String lhs, String rhs){
            return lhs.compareToIgnoreCase(rhs);
        }
    }

    public static void main(String[] args){
        String[] arr = {"ZEBRA", "alligator", "crocodile"};
        System.out.println(findMax(arr, new TestProgram().new CaseInsensitiveCompare()));
    }
}
