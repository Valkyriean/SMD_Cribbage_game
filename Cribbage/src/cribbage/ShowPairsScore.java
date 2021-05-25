package cribbage;


import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowPairsScore implements IScoreRule{
    @Override
    public int getScore(Hand hand) {
        ArrayList<Card> cards = hand.getCardList();
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
        int score = 0;
        for (Integer key : map.keySet()){
            if (map.get(key) > 1){
                score += map.get(key) * (map.get(key) - 1);
            }
        }
        return  score;
    }

}
