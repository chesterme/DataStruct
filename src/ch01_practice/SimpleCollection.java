package ch01_practice;

public class SimpleCollection {

    Object[] storeValues;
    int size;
    int DEFAULT_SIZE = 3;

    public SimpleCollection(){
        makeEmpty();
    }

    public SimpleCollection(Object[] storeValues){
        this.storeValues = storeValues;
        size = storeValues.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void makeEmpty(){
        storeValues = new Object[DEFAULT_SIZE];
        size = 0;
    }

    public int size(){
        return size;
    }

    public void insert(Object object){
        if(size >= storeValues.length){
            Object[] temp = storeValues;
            storeValues = new Object[temp.length * 2];
            for(int i = 0; i < temp.length; i++){
                storeValues[i] = temp[i];
            }
        }
        storeValues[size++] = object;
    }

    public Object remove(int kth){
        if(kth > size){
            return null;
        }
        Object result = storeValues[kth - 1];
        for(int i = kth; i < size; i++){
            storeValues[i - 1] = storeValues[i];
        }
        storeValues[size - 1] = null;
        size--;
        return result;
    }

    public boolean isPresent(Object obj){
        for(int i = 0; i < size; i++){
            if(storeValues[i].equals(obj)){
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
            System.out.println(storeValues[i]);
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
        public boolean equals(Object obj){
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
        SimpleCollection container = new SimpleCollection(persons);
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
