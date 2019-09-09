package ch05_text;

/**
 * 字符串散列计算
 * 将字符串中的字符值相加，得到的和作为该字符的关键字
 */
public class MySimpleStringHash implements MyHash<String> {

    /**
     * 返回key在散列表中对应的hash值
     * @param key, 假定字符串长度不超过8，由数字、大小写字母和下划线组成
     * @param tableSize，散列表的大小
     * @return key在散列表中对应的hash值
     */
    @Override
    public int hash(String key, int tableSize) {
        char[] chars = key.toCharArray();
        int result = 0;

        for(int i = 0; i < chars.length; i++){
            if(isUpperCase(chars[i])){
                result += (int)(chars[i] - 'A');
            }
            else if(isLowerCase(chars[i])){
                result += (int)(chars[i] - 'a');
            }
            else if(isDigital(chars[i])){
                result += (int)(chars[i] - '0');
            }
            else{
                result += (int)(chars[i] - '_');
            }
        }
        return result % tableSize;
    }

    // 判断一个字符是否是大写字母
    public boolean isUpperCase(char c){
        if((int)c - 65 >= 0 && (int)c - 65 <= 90){
            return true;
        }
        return false;
    }

    // 判断一个字符是否是小写字母
    public boolean isLowerCase(char c){
        if((int)c - 97 >= 0 && (int)c - 97 <= 122){
            return true;
        }
        return false;
    }

    // 判断一个字符是否是数字
    public boolean isDigital(char c){
        if((int)c - 48 >= 0 && (int)c - 48 <= 57){
            return true;
        }
        return false;
    }
}
