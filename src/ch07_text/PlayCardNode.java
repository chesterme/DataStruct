package ch07_text;

/**
 * 扑克牌节点
 */
public class PlayCardNode {

    private PlayCard data;
    private PlayCardNode next;
    private PlayCardNode previous;

    public PlayCardNode(PlayCard data, PlayCardNode next, PlayCardNode previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }

    public PlayCard getData() {
        return data;
    }

    public void setData(PlayCard data) {
        this.data = data;
    }

    public PlayCardNode getNext() {
        return next;
    }

    public void setNext(PlayCardNode next) {
        this.next = next;
    }

    public PlayCardNode getPrevious() {
        return previous;
    }

    public void setPrevious(PlayCardNode previous) {
        this.previous = previous;
    }
}
