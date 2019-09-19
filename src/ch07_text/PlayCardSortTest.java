package ch07_text;

import java.util.Random;

public class PlayCardSortTest {

    public static void main(String[] args){

        Random numberGenerator = new Random(System.currentTimeMillis());
        Random typeGenerator = new Random(System.currentTimeMillis());

        int length = 52;
        PlayCardList list = new PlayCardList();
        boolean flag;
        PlayCard tempCard;
        for(int i = 0; i < length; i++){
            do{
                tempCard = new PlayCard(numberGenerator.nextInt(13) + 2, typeGenerator.nextInt(4) + 1);
                flag = false;
                for(PlayCardNode node : list){
                    if(node.getData() != null && node.getData().isSame(tempCard)){
                        flag = true;
                        break;
                    }
                }
            }while(flag);
//            list.insertFromHead(tempCard);
            list.insert(i + 1, tempCard);
        }

        System.out.println("输入为：");
        int count = 1;
        for(PlayCardNode card : list){
            System.out.println("count: " + count + ", " + card.getData());
            count++;
        }

        System.out.println("+++++++++++++++++++++++++");

        System.out.println("按照从小到大排序为：");
        PlayCardSort cardSort = new PlayCardSort(list);
        cardSort.sort();
        PlayCardList result = cardSort.getList();

        count = 1;
        for(PlayCardNode card : result){
            System.out.println("count: " + count + ", " +  card.getData());
            count++;
        }

    }

}
