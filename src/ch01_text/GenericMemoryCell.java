package ch01_text;

/**
 * 简单的泛型类
 * 这里包括了一个类型参数，当创建它的实例时，可以使用具体的类型，比如：String, Integer等
 * 好处是：当类型不匹配时会产生一个编译错误
 */
public class GenericMemoryCell<AnyType> {

    private AnyType storedValue;

    public AnyType getStoredValue() {
        return storedValue;
    }

    public void setStoredValue(AnyType storedValue) {
        this.storedValue = storedValue;
    }
}
