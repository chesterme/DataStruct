package ch07_text;

import java.util.Iterator;

/**
 * 扑克牌双向列表
 */
public class PlayCardList implements Iterable<PlayCardNode> {

    private PlayCardNode header;
    private PlayCardNode tailor;
    private int size;

    public PlayCardList(){
        // 构建一个空的双向列表
        header = new PlayCardNode(null, null, null);
        tailor = new PlayCardNode(null, null, null);
        header.setNext(tailor);
        header.setPrevious(tailor);
        tailor.setNext(header);
        tailor.setPrevious(header);
        size = 0;
    }

    public boolean isEmpty(){
        return header.getNext() == tailor;
    }

    public int getSize(){
        return size;
    }

    public void insertFromHead(PlayCard playCard){
        PlayCardNode insertNode = new PlayCardNode(playCard, null, null);
        insertNode.setNext(header.getNext());
        header.getNext().setPrevious(insertNode);
        insertNode.setPrevious(header);
        header.setNext(insertNode);
        size++;
    }

    public void insertFromTail(PlayCard playCard){
        PlayCardNode insertNode = new PlayCardNode(playCard, null, null);
        insertNode.setNext(tailor);
        tailor.getPrevious().setNext(insertNode);
        insertNode.setPrevious(tailor.getPrevious());
        tailor.setPrevious(insertNode);
        size++;
    }

    public void insert(int index, PlayCard playCard){
        if(index < 1 || index > size + 1){
            return;
        }
        int count = 0;
        PlayCardNode temp = header;
        while(count < index){
            temp = temp.getNext();
            count++;
        }
        PlayCardNode preNode = temp.getPrevious();
        PlayCardNode insertNode = new PlayCardNode(playCard, null, null);
        insertNode.setNext(temp);
        temp.setPrevious(insertNode);
        insertNode.setPrevious(preNode);
        preNode.setNext(insertNode);
        size++;
    }

    public PlayCardNode deleteFromHead(){
        PlayCardNode deleteNode = header.getNext();
        header.setNext(deleteNode.getNext());
        deleteNode.getNext().setPrevious(header);
        size--;
        deleteNode.setPrevious(null);
        deleteNode.setNext(null);
        return deleteNode;
    }

    public PlayCardNode deleteFromTail(){
        PlayCardNode deleteNode = tailor.getPrevious();
        deleteNode.getPrevious().setNext(tailor);
        tailor.setPrevious(deleteNode.getPrevious());
        size--;
        deleteNode.setPrevious(null);
        deleteNode.setNext(null);
        return deleteNode;
    }

    public PlayCardNode delete(int index){
        if(index < 1 || index > size){
            return null;
        }
        int count = 0;
        PlayCardNode tempNode = header;
        while(count < index){
            tempNode = tempNode.getNext();
            count++;
        }
        PlayCardNode preNode = tempNode.getPrevious();
        preNode.setNext(tempNode.getNext());
        tempNode.getNext().setPrevious(preNode);
        size--;
        tempNode.setNext(null);
        tempNode.setPrevious(null);
        return tempNode;
    }

    public PlayCardNode getHeader() {
        return header;
    }

    public void setHeader(PlayCardNode header) {
        this.header = header;
    }

    public PlayCardNode getTailor() {
        return tailor;
    }

    public void setTailor(PlayCardNode tailor) {
        this.tailor = tailor;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Iterator<PlayCardNode> iterator() {
        return new PlayCardListIterator();
    }

    class PlayCardListIterator implements Iterator<PlayCardNode>{

        PlayCardNode tempNode = header.getNext();

        @Override
        public boolean hasNext() {
            return tempNode != tailor;
        }

        @Override
        public PlayCardNode next() {
            PlayCardNode result = tempNode;
            tempNode = tempNode.getNext();
            return result;
        }
    }

}
