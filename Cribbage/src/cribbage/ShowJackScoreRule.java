package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
/** calculate the score according the jack score rule
 * */
public class ShowJackScoreRule implements IScoreRule {
    // type of even
    private static final String event = "jack";
    private static final String stage = "show";
    /**
     * calculate the score of cards in the hand is Jack and same suit to starter
     * @param hand  the cards in the player's hand
     * @param player playerId
     * @return the score of jack rule
     */
    @Override
    public int getScore(Hand hand, int player) {
        Card starter = hand.getLast();
        ArrayList<Card> cards = hand.getCardList();
        int total = 0;
        int score = 0;
        // check whether the card is jack and same suit to the starter or not
        for (Card c : cards){
            if (c.equals(starter)){
                continue;
            }
            if (c.getRank().equals(Cribbage.Rank.JACK) && c.getSuit().equals(starter.getSuit())){
                score = ScoreAdapter.getInstance().loadScore(stage+event);
                ArrayList<Card> cardList = new ArrayList<>();
                cardList.add(c);
                LogController.getInstance().logScore(player, event, score,cardList);
                total += score;
            }
        }
        return total;
    }
}
