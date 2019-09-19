package ch07_text;

public class StudentNode {

    private Student student;
    private StudentNode next;

    public StudentNode(Student student, StudentNode next) {
        this.student = student;
        this.next = next;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public StudentNode getNext() {
        return next;
    }

    public void setNext(StudentNode next) {
        this.next = next;
    }
}
