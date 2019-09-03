package ch03_text;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        // 按照person实例的年龄排序
        return o1.getAge() - o2.getAge();
    }
}
