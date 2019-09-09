package ch05_text;

public interface MyHash<AnyType> {

    int hash(AnyType key, int tableSize);

}
