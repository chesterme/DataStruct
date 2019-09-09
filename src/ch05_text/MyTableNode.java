package ch05_text;

/**
 * 散列表单个元素的结构
 * @param <AnyType>
 */
public class MyTableNode<AnyType> {

    private AnyType data; // 单元素中保存的数据
    private MyHashEntryType info; // 单元素中的状态

    public MyTableNode(AnyType data, MyHashEntryType info) {
        this.data = data;
        this.info = info;
    }

    public AnyType getData() {
        return data;
    }

    public void setData(AnyType data) {
        this.data = data;
    }

    public MyHashEntryType getInfo() {
        return info;
    }

    public void setInfo(MyHashEntryType info) {
        this.info = info;
    }
}
