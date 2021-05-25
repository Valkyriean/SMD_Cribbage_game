package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.HashMap;

public class FlushScoreRule implements IScoreRule{

    @Override
    public int getScore(Hand hand, int player) {
        String event = null;
        int score = 0;
        Card start = hand.getLast();
        ArrayList<Card> cards = (ArrayList<Card>) hand.getCardList().clone();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (Card c : cards){
            if (c.equals(start)){
                continue;
            }
            if (!map.containsKey(c.getSuitId())){
                map.put(c.getSuitId(),1);
            } else {
                map.put(c.getSuitId(), map.get(c.getSuitId()) + 1);
            }
        }

        int max = 0;
        for (Integer key : map.keySet()){
            if (map.get(key)>3){
                max = map.get(key);
                if (map.containsKey(start.getSuitId())){
                    max += 1;
                }
            }
        }
        switch (max){
            case 4:
                cards.remove(start);
                cards.sort((o1,o2)->o1.getValue() - o2.getValue());
                event = "flush4";
                break;
            case 5:
                cards.sort((o1,o2)->o1.getValue() - o2.getValue());
                event  = "flush5";
            default:
                break;
        }

        if (event != null){
            score = ScoreAdapter.getInstance().loadScore(event);
            LogController.getInstance().logScore(player, event, score, cards);
        } 
        
        return score;
    
    }
}
