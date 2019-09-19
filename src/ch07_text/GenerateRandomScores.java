package ch07_text;

import java.util.Random;

public class GenerateRandomScores {

    private GenerateRandomName nameGenerator;
    private Random random;
    private SubjectGenerator subjectGenerator;

    public GenerateRandomScores(GenerateRandomName nameGenerator, SubjectGenerator subjectGenerator) {
        this.nameGenerator = nameGenerator;
        random = new Random(System.currentTimeMillis());
        this.subjectGenerator = subjectGenerator;
    }

    public Student[] generate(int number){
        Student[] students = new Student[number];
        for(int i = 0; i < number; i++){
            students[i] = new Student(nameGenerator.generate(), random.nextInt(100));
            students[i].setSubject(subjectGenerator.generate());
        }
        return students;
    }
}
