package ch04_text;

import ch03_text.MyLinkedList;
import ch03_text.PersonComparator;
import ch03_text.Student;

/**
 * 二分查找
 */
public class BinarySearch{

    public static void main(String[] args){

        MyLinkedList<Student> list = new MyLinkedList<>();
        list.insert(1, new Student("张三", 19, "计算机系"));
        list.insert(1, new Student("李四", 20, "计算机系"));
        list.insert(1, new Student("王五", 21, "计算机系"));
        list.insert(1, new Student("赵六", 19, "计算机系"));

        Student key = new Student("赵六", 19, "计算机系");

        PersonComparator comparator = new PersonComparator();
        list.sort(comparator);

        int left, mid, right;
        left = 1;
        right = list.size();

        while(left <= right){
            mid = (left + right) / 2;
            if(comparator.compare(key, list.findKth(mid)) < 0){
                right = mid - 1; // 调整右边界
            }
            else if(comparator.compare(key, list.findKth(mid)) > 0){
                left = mid + 1; // 调整左边界
            }
            else{
                System.out.println(mid); // 查找成功，返回该元素的下标
                return;
            }
        }
        System.out.println("查找不成功");
    }

}
