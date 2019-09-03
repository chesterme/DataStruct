package ch01_text;

public class MemoryCellTest {

    public static void main(String[] args){
        MemoryCell m = new MemoryCell();
        Person[] arr = new Employee[2];
        Employee e1 = new Employee("小明", 2000);
        Student s1 = new Student(12, "张三");
        arr[0] = e1;
//        arr[1] = s1; // error

    }

}
