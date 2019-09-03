package ch03_text;

public class MyArrayQueueTest {

    public static void main(String[] args){

        MyArrayQueue<Student> queue = new MyArrayQueue<>();
        queue.enqueue(new Student("张三", 19, "计算机系"));
        queue.enqueue(new Student("李四", 18, "计算机系"));
        queue.enqueue(new Student("王五", 20, "计算机系"));
        System.out.println("队列大小为：" + queue.size());
        System.out.println("出队：" + queue.dequeue());
        System.out.println("出队：" + queue.dequeue());
        queue.enqueue(new Student("赵六", 20, "计算机系"));
        queue.enqueue(new Student("张龙", 20, "计算机系"));
        queue.enqueue(new Student("赵虎", 20, "计算机系"));
        queue.enqueue(new Student("展昭", 20, "计算机系"));
        System.out.println("队列大小为：" + queue.size());
        System.out.println("所有内容出队: ");
        queue.printAll();

    }

}
