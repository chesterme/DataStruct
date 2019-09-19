package ch07_text;

import java.util.Random;

public class SubjectGenerator {

    private String[] subjects = "程序设计 离散数学 高等数学1 高等数学2 数据结构 线性代数 汇编语言 操作系统 数据库 计算机组成原理 编译原理 计算机网络".trim().split("\\s+");
    private Random random;

    public SubjectGenerator(){
        random = new Random(System.currentTimeMillis());
    }

    public String generate(){
        int index = random.nextInt(subjects.length);
        return subjects[index];
    }

}
