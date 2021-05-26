package cribbage;


import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * calculate the score of cards in the hand that can form pairs
 */
public class ShowPairsScoreRule implements IScoreRule{
    /**
     * calculate the score of cards in the hand that can form pairs
     * @param hand  the cards in the player's hand
     * @param player playerId
     * @return the score of pairs rule
     */
    @Override
    public int getScore(Hand hand, int player) {

        String event;
        ArrayList<Card> cards = (ArrayList<Card>)hand.getCardList().clone();
        ArrayList<Card> cardList;

        // find frequency of same value
        HashMap<Integer,Integer> map = new HashMap<>();
        int i = 0;
        while (i < 5){
            int id = cards.get(i).getRankId();
            if (map.containsKey(id)){
                map.put(id,map.get(id) + 1);
            } else {
                map.put(id,1);
            }
            i ++;
        }
        int total = 0;
        int score = 0;
        for (Integer key : map.keySet()){
            // find all the pairs in hand and write this event into log file
            switch (map.get(key)){
                case 2:
                    event = "pair2";
                    score = ScoreAdapter.getInstance().loadScore(event);
                    cardList = findPairs(key, cards);
                    LogController.getInstance().logScore(player, event, score, cardList);
                    total += score;
                    break;
                case 3:
                    event = "pair3";
                    score = ScoreAdapter.getInstance().loadScore(event);
                    cardList = findPairs(key, cards);
                    LogController.getInstance().logScore(player, event, score, cardList);
                    total += score;
                    break;
                case 4:
                    event = "pair4";
                    score = ScoreAdapter.getInstance().loadScore(event);
                    cardList = findPairs(key, cards);
                    LogController.getInstance().logScore(player, event, score, cardList);
                    total += score;
                    break;
                default:
                    break;
            }
        }
        return  total;
    }

    // find  pairs in the card according to the rankId
    private ArrayList<Card> findPairs(int RankId, ArrayList<Card> cards){
        ArrayList<Card> cardList = new ArrayList<>();
        for (Card c : cards){
            if (c.getRankId() == RankId){
                cardList.add(c);
            }
        }
        return cardList;

    }

}
