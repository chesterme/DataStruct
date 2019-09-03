package ch03_text;

public class Student extends Person{

    private String department;

    public Student(String name, int age, String department) {
        super(name, age);
        this.department = department;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString() + "; department: " + department;
    }
}
