package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.HashMap;

public class FlushScore implements IScoreRule{

    @Override
    public int getScore(Hand hand) {
        Card start = hand.getLast();
        ArrayList<Card> cards = hand.getCardList();
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
                return ScoreAdapter.getInstance().loadScore("flush4");
            case 5:
                return ScoreAdapter.getInstance().loadScore("flush5");
            default:
                return 0;
        }
    }
}
