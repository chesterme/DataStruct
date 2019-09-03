package ch03_text;

public class MyLinkedStackTest {

    public static void main(String[] args){

        MyLinkedStack<Student> stack = new MyLinkedStack<>();
        stack.push(new Student("张三", 19, "计算机系"));
        stack.push(new Student("李四", 18, "计算机系"));
        stack.push(new Student("王五", 20, "计算机系"));
        System.out.println("栈的大小为：" + stack.size());

        Student student1 = stack.pop();
        System.out.println(student1);
        System.out.println("栈的大小为：" + stack.size());

        stack.push(new Student("赵六", 20, "数学系"));
        stack.printAll();
        System.out.println("栈的大小为：" + stack.size());

    }

}
