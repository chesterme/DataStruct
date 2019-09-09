package ch04_text;

public class MySimpleSetTest {

    public static void main(String[] args){

        MySimpleSet set = new MySimpleSet();
        set.union(2,3);
        System.out.println(set.find2(3));
        for(int i = 1; i <= 20; i++){
            set.union2(i, i + 1);
        }
        System.out.println(set.isInSameSet(2, 3));

    }

}
