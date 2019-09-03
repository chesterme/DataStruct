package ch01_practice;

import java.util.Comparator;

public class RectangleTest {

    public <AnyType> AnyType findMax(AnyType[] input, Comparator<? super AnyType> cmp){
        int maxIndex = 0;
        for(int i = 1; i < input.length; i++){
            if(cmp.compare(input[i], input[maxIndex]) > 0){
                maxIndex = i;
            }
        }
        return input[maxIndex];
    }


    static class Rectangle{
        private double length;
        private double width;

        public Rectangle(double length, double width) {
            this.length = length;
            this.width = width;
        }

        public double getLength() {
            return length;
        }

        public void setLength(double length) {
            this.length = length;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        private double getArea(){
            return width * length;
        }

        private double getPerimeter(){
            return 2 * (width + length);
        }

        @Override
        public String toString(){
           return "length: " + length + ", width: " + width;
        }
    }

    static class AreaCompare implements Comparator<Rectangle>{
        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            return o1.getArea() - o2.getArea() > 0 ? 1 : -1;
        }
    }

    static class PerimeterCompare implements Comparator<Rectangle>{
        @Override
        public int compare(Rectangle o1, Rectangle o2) {
            return o1.getPerimeter() - o2.getPerimeter() > 0 ? 1 : -1;
        }
    }

    public static void main(String[] args){
        Rectangle[] rectangles = {
                new Rectangle(1.0, 2.0),
                new Rectangle(4.0, 2.0),
                new Rectangle(1.0, 3.0),
                new Rectangle(1.4, 2.0),
                new Rectangle(7.0, 6.0)
        };
        RectangleTest test = new RectangleTest();
        System.out.println("maximum area: " + test.findMax(rectangles, new AreaCompare()));
        System.out.println("maximum perimeter: " + test.findMax(rectangles, new PerimeterCompare()));
    }
}
