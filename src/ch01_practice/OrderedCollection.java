package ch01_practice;

import java.util.ArrayList;

public class OrderedCollection<AnyType extends Comparable<? super AnyType>> {

    private ArrayList<AnyType> list;
    private int size;

    public OrderedCollection(){
        list = new ArrayList<>();
        size = 0;
    }

    public OrderedCollection(AnyType[] objs){
        list = new ArrayList<>();
        for(int i = 0; i < objs.length; i++){
            list.add(objs[i]);
        }
        size = list.size();
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void makeEmpty(){
        list = new ArrayList<>();
        size = 0;
    }

    public int size(){
        return size;
    }

    public void insert(AnyType object){
       list.add(object);
       size++;
    }

    public AnyType remove(int kth){
        AnyType result = list.remove(kth);
        size--;
        return result;
    }

    public boolean isPresent(Object obj){
        for(int i = 0; i < size; i++){
            if(list.get(i).equals(obj)){
                return true;
            }
        }
        return false;
    }

    public void printOut(){
        if(size == 0){
            System.out.println("collection is empty");
            return;
        }
        for(int i = 0; i < size; i++){
            System.out.println(list.get(i));
        }
    }

    public AnyType findMax(){
        AnyType max = list.get(0);
        for(int i = 1; i < size; i++){
            if(list.get(i).compareTo(max) > 0){
                max = list.get(i);
            }
        }
        return max;
    }

    public AnyType findMin(){
        AnyType min = list.get(0);
        for(int i = 1; i < size; i++){
            if(list.get(i).compareTo(min) < 0){
                min = list.get(i);
            }
        }
        return min;
    }

    static class Person implements Comparable{
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
        public boolean equals(Object obj){
            Person person = (Person) obj;
            if(this.name.equals(person.name) && this.age == person.age){
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(Object o) {
            Person person = (Person)o;
            return this.age - person.age;
        }
    }


    public static void main(String[] args){
        Person[] persons = {
                new Person("张三", 12),
                new Person("李四", 13),
                new Person("王五", 15),
                new Person("赵六", 14)
        };
        OrderedCollection container = new OrderedCollection(persons);
        container.printOut();
        System.out.println("-------------------------");

        container.insert(new Person("张龙", 16));
        container.insert(new Person("赵虎", 17));
        container.printOut();
        System.out.println("max: " + container.findMax());
        System.out.println("min: " + container.findMin());
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
