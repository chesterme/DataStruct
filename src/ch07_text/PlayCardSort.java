package ch07_text;

/**
 * 排序扑克牌
 */
public class PlayCardSort {

    private static final int MAX_LENGTH = 15;
    private PlayCardList list;
    private PlayCardList[] buckets;

    public PlayCardSort(PlayCardList list){
        this.list = list;
        // 建立一个空白的桶列
        buckets = new PlayCardList[MAX_LENGTH];
        for(int i = 0; i < buckets.length; i++){
            buckets[i] = new PlayCardList();
            buckets[i].getHeader().setData(new PlayCard(i, -1));
        }
    }

    /**
     * 获取排序的比较元素
     * @param card
     * @param i
     * @return
     */
    private int getElement(PlayCard card, int i){
        if(i == 1){
            return card.getNumber();
        }
        else{
            return card.getType();
        }
    }

    public void sort(){
        int element;
        for(int i = 0; i < 2; i++){
            // 遍历输入元素
            while(!list.isEmpty()){
                PlayCardNode node = list.deleteFromHead();
                element = getElement(node.getData(), i);
                buckets[element].insertFromTail(node.getData());
            }

            // 将桶列中的内容复制到list中
            for(int j = 0; j < MAX_LENGTH; j++){
                while(!buckets[j].isEmpty()){
                    PlayCardNode node = buckets[j].deleteFromHead();
                    list.insertFromTail(node.getData());
                }
            }
        }
    }

    public PlayCardList getList() {
        return list;
    }
}
