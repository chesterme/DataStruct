package ch04_text;

public class DataAndWeight<AnyType> {

    private AnyType data;
    private int weight;

    public DataAndWeight(AnyType data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public AnyType getData() {
        return data;
    }

    public void setData(AnyType data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
