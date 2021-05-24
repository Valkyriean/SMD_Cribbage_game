package cribbage;


import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.Comparator;

public class PairsScore {

    public int getScore(Hand start, ArrayList<Card> cards) {
        if(cards.size() != 5){
            cards.add(start.getCardList().get(0));
        }
        cards.sort(Comparator.comparingInt(Card::getRankId));
        int [] pair = new int[5];
        pair[0] = 1;
        int i = 1;
        while (i < 5){
            if (cards.get(i-1).getRankId() == cards.get(i).getRankId()){
                pair[i] += pair[i-1] + 1;
            } else {
                pair[i] = 1;
            }
            i++;
        }
        int maxpair = 1;
        for (int j = 0; j < 5; j++){
            if (pair[j] != 1 && pair[j] > maxpair){
                maxpair = pair[j];
            }
        }

        switch (maxpair){
            case 2:
                return 2;
            case 3:
                return 6;
            case 4:
                return 12;
            default:
                return 0;
        }
    }
}
