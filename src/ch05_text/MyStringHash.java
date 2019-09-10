package ch05_text;


/**
 * 字符串散列计算
 * 将字符串中前3位作为关键字
 */
public class MyStringHash extends MySimpleStringHash {

    /**
     *
     * @param key, 假定字符串长度不超过8，由大小写字母和空格符组成
     * @param tableSize，散列表的大小
     * @return
     */
    @Override
    public int hash(String key, int tableSize) {
        char[] chars = key.toCharArray();
        int result = 0;
        for(int i = 2; i >= 0; i--){
            if(isUpperCase(chars[i])){
                result = result * 27 + toLowerCase(chars[i]) - 'a';
            }
            else{
                result = result * 27 + chars[i] - 'a';
            }
        }
        return result % tableSize;
    }

    // 将大写字母转化成小写字母
    public char toLowerCase(char c){
        return (char)('a' + (c - 'A'));
    }
}
