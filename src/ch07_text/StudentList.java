package ch07_text;

public class StudentList {

    private StudentNode header;
    private StudentNode tailor;
    private int size;

    public StudentList(){
        create();
    }

    private void create(){
        header = new StudentNode(null, null);
        tailor = new StudentNode(null, null);
        header.setNext(tailor);
        size = 0;
    }

    public boolean isEmpty(){
        if(header.getNext() == tailor){
            return true;
        }
        return false;
    }

    public int getSize(){
        return size;
    }

    public void add(Student student){
        insert(1, student);
    }

    public void insert(int i, Student student){
        if(i < 1 || i > size + 1){
            return;
        }
        int index = 0;
        StudentNode temp = header;
        while(index < i - 1){
            temp = temp.getNext();
            index++;
        }
        StudentNode node = new StudentNode(student, null);
        node.setNext(temp.getNext());
        temp.setNext(node);
        size++;
    }

    public StudentNode delete(int i){
        if(i < 1 || i > size){
            return null;
        }
        int index = 0;
        StudentNode temp = header;
        while(index < i - 1){
            temp = temp.getNext();
            index++;
        }
        StudentNode deleteNode = temp.getNext();
        temp.setNext(deleteNode.getNext());
        size--;
        return deleteNode;
    }

    public StudentNode getHeader() {
        return header;
    }

    public void setHeader(StudentNode header) {
        this.header = header;
    }

    public StudentNode getTailor() {
        return tailor;
    }
}
