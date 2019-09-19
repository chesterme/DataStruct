package ch07_text;

import java.util.Random;

public class PlayCardListTest {

    public static void main(String[] args){

        Random numberGenerator = new Random(System.currentTimeMillis());
        Random typeGenerator = new Random(System.currentTimeMillis());

        int length = 14;
        PlayCardList list = new PlayCardList();
        for(int i = 0; i < length; i++){
            list.insert(i + 1, new PlayCard(numberGenerator.nextInt(12) + 1, typeGenerator.nextInt(3) + 1));
        }

        for(PlayCardNode card : list){
            System.out.println(card.getData());
        }
    }

}
