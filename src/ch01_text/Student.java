package ch01_text;

public class Student extends Person implements Comparable{

    private int age;
    private String name;

    public Student(int age, String name) {
        super("中国");
        this.age = age;
        this.name = name;
    }

    public void sayHello(){
        System.out.println("hello");
    }

    @Override
    public int compareTo(Object o) {
        Student other = (Student) o;
        return this.age - other.age;
    }

    public String toString(){
        return "name: " + this.name + ", age: " + this.age;
    }
}
