package ch03_text;


public class MyArrayListTest {

    public static void main(String[] args){
        MyArrayList<Person> list = new MyArrayList<>();
        System.out.println("线性表的大小为：" + list.size());

        list.insert(1, new Student("张三", 19, "计算机系"));
        list.insert(2, new Student("李四", 18, "计算机系"));
        list.insert(3, new Student("王五", 19, "数学系"));
        list.insert(1, new Student("赵六", 19, "数学系"));
        list.insert(1, new Student("张龙", 21, "计算机系"));
        list.insert(1, new Student("赵虎", 22, "计算机系"));
        list.insert(1, new Student("展昭", 24, "计算机系"));
        System.out.println("线性表的大小为：" + list.size());
        System.out.println("线性表的内容为：");
        list.printAll();

        int deleteIndex = 3;
        System.out.println("删除第" + deleteIndex + "个元素后，线性表的内容为：");
        list.delete(deleteIndex);
        list.printAll();

        int findIndex = 2;
        System.out.println("线性表中第" + findIndex + "个元素的内容为：");
        System.out.println(list.findKth(findIndex));

        Student student = new Student("张三", 19, "计算机系");
        System.out.println("线性表中是否存在以下元素：");
        System.out.println(student);

        if(list.find(student) == -1){
            System.out.println("不存在该元素：" + student);
        }else{
            System.out.println("存在该元素：" + student + ", 位序为：" + list.find(student));
        }


        // 排序
        list.sort(new PersonComparator());
        System.out.println("按照年龄排序后的结果为：");
        list.printAll();
    }

}
