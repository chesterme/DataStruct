package ch03_text;

public class Person implements Comparable<Person>{

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }


    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Person)obj).name);
    }

    @Override
    public String toString() {
        return "name: " + name + "; age: " + age;
    }
}
