package ch01_text;

/**
 * 使用基本类型的包装类
 * 比如：int -> Integer
 *       double -> Double
 */
public class WrapperDemo {

    public static void main(String[] args){
        MemoryCell m = new MemoryCell();

        m.write(new Integer(24));
        Integer result = (Integer) m.read();
        System.out.println(result.intValue());
    }

}
