package ch07_text;

/**
 * 成绩排序
 */
public class ScoresSort {

    private StudentList[] lists;
    private static final int MAX_LENGTH = 101; // 成绩范围

    public ScoresSort(){
        // 构建一个链表数组，每个头节点代表一个分数
        lists = new StudentList[MAX_LENGTH];
        for(int i = 0; i < MAX_LENGTH; i++){
            lists[i] = new StudentList();
            lists[i].getHeader().setStudent(new Student(null, i));
        }
    }

    /**
     * 桶排序
     * @param students，输入源
     */
    public void sort(Student[] students){
        for(int i = 0; i < students.length; i++){
            lists[students[i].getScore()].add(students[i]);
        }
    }

    /**
     * 输出经排序后的学生信息
     */
    public void printAll(){
        StudentNode header;
        StudentNode tailor;
        for(int i = 0; i < MAX_LENGTH; i++){
            System.out.println("分数为" + i + "的同学有：");
            header = lists[i].getHeader();
            tailor = lists[i].getTailor();
            while(header.getNext() != tailor){
                Student student = header.getNext().getStudent();
                System.out.println("姓名为： " + student.getName() + ", 分数为：" + student.getScore() + ", 学科为：" + student.getSubject());
                header = header.getNext();
            }
            System.out.println("++++++++++++++++++++++++++++++");
        }
    }


    public static void main(String[] args){
        ScoresSort sort = new ScoresSort();
        GenerateRandomScores scoreGenerator = new GenerateRandomScores(new GenerateRandomName(), new SubjectGenerator());
        int number = 10000;
        Student[] students = scoreGenerator.generate(number);
        sort.sort(students);
        sort.printAll();
    }
}
