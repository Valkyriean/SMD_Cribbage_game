package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.Comparator;

public class FlushScore implements IScoreRule{

    @Override
    public int getScore(Hand hand) {
        ArrayList<Card> cards = hand.getCardList();
        int i = 1;
        int [] flush = new int[5];
        flush[0] = 1;
        while (i < 5){
            if (cards.get(i-1).getSuitId() == cards.get(i).getSuitId()){
                flush[i] += flush[i-1] + 1;
            } else {
                flush[i] = 1;
            }
            i++;
        }
        int maxflush = 1;
        for (int j = 0; j < 5; j++){
            if (flush[j] != 1 && flush[j] > maxflush){
                maxflush = flush[j];
            }
        }
        return maxflush > 3 ? maxflush : 0;
    }
}
