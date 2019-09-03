package ch01_practice;

import java.util.ArrayList;

public class GenericCollection<AnyType> {

    ArrayList<AnyType> list;
    int size;

    public GenericCollection(AnyType[] objects){
        list = new ArrayList<>();
        for(int i = 0; i < objects.length; i++){
            list.add(objects[i]);
        }
        size = list.size();
    }

    public GenericCollection(){
        makeEmpty();
    }

    public void makeEmpty(){
        list = new ArrayList<>();
        size = 0;
    }

    public void insert(AnyType obj){
        list.add(obj);
        size++;
    }

    public AnyType remove(int kth){
        if(kth >= size){
            return null;
        }
        size--;
        return list.remove(kth);
    }

    public boolean isPresent(AnyType obj){
        for(int i = 0; i < list.size(); i++){
            if(obj.equals(list.get(i))){
                return true;
            }
        }
        return false;
    }

    public int size(){
        return list.size();
    }

    public void printOut(){
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    static class Person{
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString(){
            return "name: " + name + ", age: " + age;
        }


        @Override
        public boolean equals(Object obj) {
            Person person = (Person) obj;
            if(this.name.equals(person.name) && this.age == person.age){
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args){
        Person[] persons = {
                new Person("张三", 12),
                new Person("李四", 13),
                new Person("王五", 15),
                new Person("赵六", 14)
        };

        GenericCollection container = new GenericCollection(persons);
        container.printOut();
        System.out.println("-------------------------");

        container.insert(new Person("张龙", 16));
        container.insert(new Person("赵虎", 17));
        container.printOut();
        System.out.println("-------------------------");

        container.makeEmpty();
        container.printOut();
        System.out.println("-------------------------");

        container.insert(new Person("张三", 12));
        container.insert(new Person("李四", 13));
        container.insert(new Person("王五", 15));
        container.insert(new Person("赵六", 16));
        System.out.println(container.size());
        container.printOut();
        System.out.println("-------------------------");

        container.remove(1);
        container.printOut();
        System.out.println("-------------------------");

        System.out.println(container.isPresent(new Person("王五", 15)));
    }
}
