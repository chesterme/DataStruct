package ch01_text;

import java.util.Collection;

/**
 * 静态泛型方法
 */
public class GenericStatic{

    public static <AnyType> boolean contains(AnyType[] arr, AnyType x){
        for(AnyType item : arr){
            if(item.equals(x)){
                return true;
            }
        }
        return false;
    }

    public static <AnyType extends Comparable<AnyType>> AnyType findMax(AnyType[] arr){
        AnyType max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i].compareTo(max)> 0){
                max = arr[i];
            }
        }
        return max;
    }

    // 假设< ? super AnyType>表示T类型，那么T是AnyType的超类
    public static <AnyType extends Comparable< ? super AnyType>> AnyType findMaxImprove(AnyType[] arr){
        AnyType max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i].compareTo(max)> 0){
                max = arr[i];
            }
        }
        return max;
    }

    static class Circle implements Comparable{
        private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public int compareTo(Object o) {
            return (int)(radius - ((Circle)o).radius);
        }

        public String toString(){
            return "radius: " + radius;
        }
    }


    public static void main(String[] args){
        String[] arr = {
                "123",
                "456",
                "789",
                "000"
        };
        System.out.println(GenericStatic.contains(arr, "789"));


        Circle[] circles = {
                new Circle(2),
                new Circle(4),
                new Circle(3),
                new Circle(9),
                new Circle(4),
        };
        System.out.println(findMax(circles));
    }

}
