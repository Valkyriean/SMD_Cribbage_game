package cribbage;


import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowPairsScoreRule implements IScoreRule{

    @Override
    public int getScore(Hand hand, int player) {
        String event;
        ArrayList<Card> cards = (ArrayList<Card>)hand.getCardList().clone();
        ArrayList<Card> cardList;
        cards.sort((o1,o2)->o1.getValue() - o2.getValue());
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
