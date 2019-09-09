package ch05_text;

/**
 * 散列表中元素的状态
 */
public enum MyHashEntryType {

    LEGITIMATE, // 该元素是一个合法元素
    EMPTY,      // 该元素是一个空元素
    DELETED     // 该元素已经被删除

}
