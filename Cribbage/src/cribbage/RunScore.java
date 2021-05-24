package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class RunScore implements IScoreRule{
    @Override
    public int getScore(Hand start, ArrayList<Card> cards) {
        if (cards.size() !=5){
            cards.add(start.getCardList().get(0));
        }
        cards.sort(Comparator.comparingInt(Card::getRankId));
        int [] runs = new int[5];
        int count = 1;
        int slow  = 0;
        while (slow < 5){
            int run = 1;
            int max = 1;
            for (int j = slow + 1; j < 5; j++){
                if (cards.get(slow).getRankId() == cards.get(j).getRankId()-run){
                    run += 1;
                } else if(cards.get(slow).getRankId() == cards.get(j).getRankId()){
                    continue;
                }
                if (run >= max){
                    max = run;
                }
            }
            runs[slow] = max;
            slow ++;
        }
        int max = 2;
        for (int k = 0; k < 5; k++){
            if (runs[k] > max){
                count = 1;
                max = runs[k];
            } else if (runs[k] == max){
                count += 1;
            }
        }
        return max > 2? count*max : 0;
    }

}
