package ch03_text;

import java.util.Comparator;

/**
 * 多项式
 */
public class Polynomial{

    private String input; // 输入的字符串
    private String[] handledInput; // 预处理的字符串
    private MyLinkedList<Struct> list; // 保存多项式中的内容

    // 多项式每一项中的系数和指数
    class Struct{
        private double coefficient;
        private double exponent;

        public Struct(double coefficient, double exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }

        @Override
        public String toString() {
            return coefficient + "x^" + exponent;
        }
    }

    public Polynomial(String input){
        this.input = input;
        list = new MyLinkedList<>();
        handleInput();
        generateAList();
    }

    public MyLinkedList<Struct> getList() {
        return list;
    }

    // 预处理字符串
    public void handleInput(){
        handledInput = input.trim().split("(\\s)*\\+(\\s)*");
    }

    // 构建一个链表来表示多项式
    public void generateAList(){
        // 遍历预处理后的字符串
        for(int i = 0; i < handledInput.length; i++){
            String[] temp = handledInput[i].split("(\\s)*x\\^(\\s)*");
            list.insert(1, new Struct(Double.valueOf(temp[0]), Double.valueOf(temp[1])));
        }
        // 从小到大排列
        list.sort(new StructComparator());
    }

    private class StructComparator implements Comparator<Struct>{

        @Override
        public int compare(Struct o1, Struct o2) {
            return o1.exponent - o2.exponent > 0 ? 1 : (o1.exponent - o2.exponent == 0 ? 0 : -1);
        }
    }


    // 多项式相加
    public MyLinkedList<Struct> add(Polynomial target){
        MyLinkedList<Struct> result = new MyLinkedList<>();
        MyLinkedList<Struct> targetList = target.list;
        StructComparator comparator = new StructComparator();
        int i = 1, j = 1;
        while(i <= list.size() && j <= targetList.size()){
            Struct struct1 = list.findKth(i);
            Struct struct2 = targetList.findKth(j);
            int flag = comparator.compare(struct1, struct2);
            if(flag < 0){
                result.insert(1, struct1);
                i++;
            }else if(flag == 0){
                double v = struct1.coefficient + struct2.coefficient;
                if(v != 0){
                    result.insert(1, new Struct(v, struct1.exponent));
                }
                i++; j++;
            }else{
                result.insert(1, struct2);
                j++;
            }
        }
        while(i <= list.size()){
            result.insert(1, list.findKth(i++));
        }
        while(j <= list.size()){
            result.insert(1, targetList.findKth(j++));
        }

        return result;
    }
}
