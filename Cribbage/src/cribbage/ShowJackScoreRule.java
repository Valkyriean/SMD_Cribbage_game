package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class ShowJackScoreRule implements IScoreRule {


    @Override
    public int getScore(Hand hand) {
        Card starter = hand.getLast();
        ArrayList<Card> cards = hand.getCardList();
        int score = 0;
        for (Card c : cards){
            if (c.getRank().equals(Cribbage.Rank.JACK) && c.getSuitId() == starter.getSuitId()){
                score += ScoreAdapter.getInstance().loadScore("jack");
            }
        }
        return score;
    }
}
