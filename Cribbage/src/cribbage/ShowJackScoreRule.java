package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class ShowJackScoreRule implements IScoreRule {

    private static final String event = "jack";

    @Override
    public int getScore(Hand hand, int player) {
        Card starter = hand.getLast();
        ArrayList<Card> cards = hand.getCardList();
        int total = 0;
        int score = 0;
        for (Card c : cards){
            if (c.getRank().equals(Cribbage.Rank.JACK) && c.getSuit().equals(starter.getSuit())){
                score = ScoreAdapter.getInstance().loadScore(event);
                ArrayList<Card> cardList = new ArrayList<>();
                cardList.add(c);
                LogController.getInstance().logScore(player, event, score,cardList);
                total += score;
            }
        }
        return total;
    }
}
