package ch05_text;

public class MyIdentifyNumberHash implements MyHash<String> {

    /**
     * 选取身份证号码中的第6、10、14、16、17、18位参与hash计算
     * @param key，18位身份证号码
     * @param tableSize，散列表的大小
     * @return 身份证在散列表的hash值
     */
    @Override
    public int hash(String key, int tableSize) {
        char[] keys = key.toCharArray();
        int result = 0;
        int[] indexs = {5, 9, 13, 15, 16};
        for(int i = 0; i < indexs.length; i++){
            result = result * 10 + keys[indexs[i]] - '0';
        }
        if (keys[17] == 'x') {
            result = result * 10 + 10;
        }
        else{
            result = result * 10 + keys[17] - '0';
        }
        return result % tableSize;
    }
}
