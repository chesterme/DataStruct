package ch02_practice;

import java.util.Scanner;

/**
 * n位整数的加减法运算
 */
public class IntegerCalculation {

    // 处理整数相加
    public static void add(int a, int b){
        char[] aChars = handleDigital(a);
        char[] bChars = handleDigital(b);
        add(aChars, bChars);
    }

    // 处理字符串表示的整数相加
    public static void add(String a, String b){
        char[] aChars = handleString(a);
        char[] bChars = handleString(b);
        add(aChars, bChars);
    }

    // 处理字符串表示的整数相减
    public static void minus(String a, String b){
        char[] aChars = handleString(a);
        char[] bChars = handleString(b);
        char[] result = handleSubtraction(aChars, bChars);
//        reverseArray(result);
        printDigital(result);
    }


    // 处理字符串
    public static char[] handleString(String str){
        char[] c = str.toCharArray();
        if(c[0] != '-'){
            char[] result = new char[c.length + 1];
            for(int i = 0; i < c.length; i++){
                result[i + 1] = c[i];
            }
            result[0] = '+';
            return result;
        }
        return c;
    }

    // n位整数相加
    public static void add(char[] a, char[] b){
        reverseArray(a);
        reverseArray(b);

        // 判断符号
        int aSign = a[0] == '+' ? 0 : 1;
        int bSign = b[0] == '+' ? 0 : 2;

        // 根据符号进行运算
        int sign = aSign + bSign;
        char[] c = null;
        switch(sign){
            // 做加法预算
            case 0:
            case 3:
                c = handleAdd(a, b);
                break;
            // 做减法运算 b - a
            case 1:
                a[0] = '+';
                c = handleSubtraction(b , a);
                break;
            // a - b
            case 2:
                b[0] = '+';
                c = handleSubtraction(a, b);
                break;
            default:
                break;
        }
        printDigital(c);
    }

    // 将数字转换成字符
    public static char[] handleDigital(int a){
        String str = String.valueOf(a);
        char[] b = str.toCharArray();
        char[] c = null;
        if(a > 0){
            int cLength = b.length+ 1;
            c = new char[cLength];
            for(int i = 0; i < cLength - 1; i ++){
                c[i + 1] = b[i];
            }
            c[0] = '+';
        }else{
            c = b;
        }
        return c;
    }

    //处理加法运算
    public static char[] handleAdd(char[] a, char[] b){
        int aLength = a.length;
        int bLength = b.length;
        int maxLength = aLength > bLength ? aLength : bLength;

        int cLength = maxLength + 1;
        char[] c = new char[cLength];

        // 处理符号相同的情况
        if(a[0] == '+' && b[0] == '+'){
            c[0] = '+';
        }else if(a[0] == '-' && b[0] == '-'){
            c[0] = '-';
        }

        // 初始化进位为0
        int carry = 0;
        for(int i = 1; i < cLength; i++){
            if(i < aLength && i < bLength){
                int aInt = a[i] - '0';
                int bInt = b[i] - '0';
                int cInt = (aInt + bInt + carry) % 10;
                // 当前位的进位
                carry = (aInt + bInt + carry) / 10;
                c[i] = (char)(cInt + '0');
            }
            else if(i < aLength && i >= bLength){
                int aInt = a[i] - '0';
                int cInt = (aInt + carry) % 10;
                carry = (aInt + carry) / 10;
                c[i] = (char)(cInt + '0');
            }
            else if(i >= aLength && i < bLength){
                int bInt = b[i] - '0';
                int cInt = (bInt + carry) % 10;
                carry = (bInt + carry) / 10;
                c[i] = (char)(cInt + '0');
            }else{
                c[i] = (char)(carry + '0');
            }
        }
        if(c[cLength - 1] == '0'){
            char[] result = new char[cLength - 1];
            for(int i = 0; i < result.length; i++){
                result[i] = c[i];
            }
            reverseArray(result);
            return result;
        }else{
            reverseArray(c);
            return c;
        }
    }

    // 处理减法运算
    public static char[] handleSubtraction(char[] a, char[] b){
        char[] result = null;
        //情况1： a - b
        if(a[0] == '+' && b[0] == '+'){
            result = handleMinus(a, b);
        }
        //情况2： -a - b ==> -a + -b
        else if(a[0] == '-' && b[0] == '+'){
            b[0] = '-';
            result = handleAdd(a, b);
        }
        //情况3：a - -b ==> a + b
        else if(a[0] == '+' && b[0] == '-'){
            b[0] = '+';
            result = handleAdd(a, b);
        }
        //情况4：-a - -b ==> -a + b ==> b - a
        else{
            result = handleMinus(b, a);
        }
        return result;
    }

    // 比较a与b的大小
    public static boolean isBigger(char[] a, char[] b){
        if(a.length > b.length){
            return true;
        }else if(a.length == b.length){
            for(int i = a.length - 1; i >= 0; i++){
                if(a[i] > b[i]){
                    return true;
                }
            }
        }
        return false;
    }

    // 处理a - b的情况
    public static char[] handleMinus(char[] a, char[] b){
        // 处理a < b的情况
        if(!isBigger(a, b)){
            char[] result = handleMinus(b, a);
            result[0] = '-';
            return result;
        }

        // 处理a > b的情况
        int cLength = a.length;
        char[] c = new char[cLength];

        int carry = 0;
        for(int i = 1; i < cLength; i++){
            if(i < a.length && i < b.length){
                int aInt = a[i] - '0';
                int bInt = b[i] - '0';
                int cInt = aInt + carry - bInt;
                if(cInt < 0){
                    cInt = cInt + 10;
                    carry = -1;
                }else{
                    carry = 0;
                }
                c[i] = (char)(cInt + '0');
            }
            if(i < a.length && i >= b.length){
                int aInt = a[i] - '0' + carry;
                if(aInt < 0){
                    carry = -1;
                    aInt += 10;
                }else{
                    carry = 0;
                }
                c[i] = (char)(aInt + '0');
            }
        }
        c[0] = '+';
        if(c[cLength - 1] == '0'){
            char[] result = new char[cLength - 1];
            for(int i = 0; i < result.length; i++){
                result[i] = c[i];
            }
            c = result;
        }
        reverseArray(c);
        return c;
    }

    // 打印字符
    public static void printDigital(char[] a){
        if(a[0] == '-'){
            System.out.print("-");
        }
        for(int i = 1; i < a.length; i++){
            System.out.print(a[i]);
        }
        System.out.println();
    }

    // 反转字符串
    public static void reverseArray(char[] a){
        int i = 1, j = a.length - 1;
        char temp;
        while(i < j){
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++;
            j--;
        }
    }

    // 判断是不是数字
    public static boolean isDigital(char c){
        char[] digital = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for(int i = 0; i < digital.length; i++){
            if(digital[i] == c){
                return true;
            }
        }
        return false;
    }

    // 判断是不是负数
    public static boolean isNegative(char c){
        if(c == '-'){
            return true;
        }
        return false;
    }


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String a = scanner.next();
        String b = scanner.next();
//        add(a, b);
        minus(a, b);
    }
}
