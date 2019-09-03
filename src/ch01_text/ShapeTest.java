package ch01_text;

import java.util.ArrayList;
import java.util.Collection;

public class ShapeTest {

    public static double totalArea(Shape[] str){
        double total = 0.0;
        for(Shape shape : str){
            total += shape.getArea();
        }
        return total;
    }

    // 它不能就收Shape的子类作为参数传入
    public static double totalAreaAnother(Collection<Shape> arr){
        double total = 0;
        for(Shape shape : arr){
            total += shape.getArea();
        }
        return total;
    }

    // 它可以接受Shape的子类作为参数传入
    public static double totalAreaCorrect(Collection<? extends Shape> arr){
        double total = 0;
        for(Shape shape : arr){
            total += shape.getArea();
        }
        return total;
    }

    public static void main(String[] args){
        Shape[] arr = {
                new Circle(12),
                new Rectangle(3, 4)
        };
        System.out.println(totalArea(arr));

        Circle[] cs = {
                new Circle(12),
                new Circle(13)
        };
        System.out.println(totalArea(cs)); //ok, 数组可以接受能向上转型的参数

        Collection<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(12));
        shapes.add(new Rectangle(3, 4));
        System.out.println(totalAreaAnother(shapes));

        Collection<Circle> circles = new ArrayList<>();
        circles.add(new Circle(12));
        circles.add(new Circle(13));
//        System.out.println(totalAreaAnother(circles)); // error: 编译错误，类型不匹配
        System.out.println(totalAreaCorrect(circles));
    }

}

