package ch06_text;

import java.util.Comparator;

public class MyEdgeNodeComparator implements Comparator<MyEdgeNode> {

    @Override
    public int compare(MyEdgeNode o1, MyEdgeNode o2) {
        return o1.getWeight() - o2.getWeight();
    }
}
