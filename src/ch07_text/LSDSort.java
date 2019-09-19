package ch07_text;

/**
 * 次位优先法基数排序
 */
public class LSDSort {

    private IntegerList list; // 排序前保存输入源，排序后保存结果
    private IntegerList[] lists; // 基数桶
    private static final int RADIX = 10; // 基数
    private static final int MAX_DIGIT = 4; // 输入整数的最大位数

    public LSDSort(){
        // 构建一个大小为10的链表数组
        lists = new IntegerList[RADIX];
        for(int i = 0; i < RADIX; i++){
            lists[i] = new IntegerList();
            lists[i].getHeader().setKey(i);
        }
    }

    /**
     * 获取整数以RADIX为进位的x的第d位上的数
     * @param X，整数
     * @param D，位数
     * @return 获取以RADIX为进位的整数x的第d位上的数
     */
    private int getDigit(int X, int D){
        int d = 0; // 保存余数
        int i;
        for(i = 1; i <= D; i++){
            d = X % RADIX;
            X /= RADIX;
        }
        return d;
    }

    public void sort(int[] inputs){
        // 获取输入源
        list = new IntegerList();
        for(int i = 0; i < inputs.length; i++){
            list.add(inputs[i]);
        }

        IntegerNode temp = null;
        IntegerList tempList = null;
        int digit = 0;
        // 排序
        // 对输入整数的每一位进行桶排序
        for(int i = 1; i <= MAX_DIGIT; i++){
            // 一次桶排序
            while(!list.isEmpty()){
                // 获取当前元素
                temp = list.deleteFromHead();
                // 获取当前元素的当前位
                digit = getDigit(temp.getKey(), i);
                // 将该元素放置在对应的桶中
                lists[digit].insertFromTailor(temp.getKey());
            }

            // 将桶中的内容放置在列表中
            for(int j = 0; j < RADIX; j++){
                tempList = lists[j];
                while(!tempList.isEmpty()){
                    list.insertFromTailor(tempList.deleteFromHead().getKey());
                }
            }
        }
    }

    public IntegerList getList() {
        return list;
    }
}
