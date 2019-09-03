package ch01_text;

public class Employee extends Person {

    private double salary;
    private String name;

    public Employee(String name, double salary) {
        super("中国");
        this.salary = salary;
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }
}
