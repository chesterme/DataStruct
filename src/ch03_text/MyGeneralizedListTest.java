package ch03_text;

public class MyGeneralizedListTest {

    public static void main(String[] args){

        MyGeneralizedList<Student> list = new MyGeneralizedList<>();
        list.insert(1, new MyGeneralizedList<>());
        list.insert(2, new Student("张三", 18, "计算机系"));
        list.insert(3, new Student("李四", 19, "计算机系"));
        MyGeneralizedList<Student> list1 = new MyGeneralizedList<>();
        list1.insert(1, new Student("王五", 20, "数学系"));
        list1.insert(2, new Student("赵六", 20, "数学系"));
        list1.insert(3, new Student("张龙", 19, "数学系"));
        list.insert(4, list1);
        MyGeneralizedList<Student> list2 = new MyGeneralizedList<>();
        list2.insert(1, new Student("赵虎", 19, "物理系"));
        MyGeneralizedList<Student> list3 = new MyGeneralizedList<>();
        list3.insert(1, new Student("张无忌", 20, "文学系"));
        list3.insert(2, new Student("杨逍", 21, "文学系"));
        list2.insert(2, list3);
        list2.insert(3, new Student("韦一笑", 20, "物理系"));
        list.insert(5, list2);

        list.printAll();

    }

}
