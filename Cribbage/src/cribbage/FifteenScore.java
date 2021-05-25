package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.Comparator;

public class FifteenScore implements IScoreRule {

    @Override
    public int getScore(Hand hand){
        ArrayList<Card> cards = hand.getCardList();
        int i = 0;
        int score = 0;
        while (i < 5){
            int sum = 0;
            for (int j = i + 1; j < 5; j ++){
                Cribbage.Rank rank1 = (Cribbage.Rank)cards.get(i).getRank();
                Cribbage.Rank rank2 = (Cribbage.Rank)cards.get(j).getRank();
                sum = rank1.value + rank2.value;
                if (sum == 15){
                    score += 2;
                }
            }
            i ++;
        }
        return score;
    }
}
