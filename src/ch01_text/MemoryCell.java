package ch01_text;

/**
 * 传统形式上的泛型
 * 缺点是：
 */
public class MemoryCell {

    private Object storedValue;

    public Object read(){
        return storedValue;
    }

    public void write(Object storedValue){
        this.storedValue = storedValue;
    }
}
