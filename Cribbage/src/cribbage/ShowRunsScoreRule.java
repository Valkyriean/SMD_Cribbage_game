package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage.Rank;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;

public class ShowRunsScoreRule implements IScoreRule{
    @Override
    public int getScore(Hand hand, int player) {
        ArrayList<Card> cards = (ArrayList<Card>) hand.getCardList().clone();
        cards.sort(new Comparator<Card>(){
            @Override
            public int compare(Card c1, Card c2){
                Rank r1 = (Rank) c1.getRank();
                Rank r2 = (Rank) c2.getRank();
                int order1 = r1.order;
                int order2 = r2.order;
                if (order1 - order2 == 0){
                    return c1.getSuitId() - c2.getSuitId();
                } else {
                    return order1 - order2;
                }
            }
        });

        ArrayList<ArrayList<Card>> runs = new ArrayList<>();
        for(int i = 0; i < cards.size()-1; i++){
            Rank r1 = (Rank) cards.get(i).getRank();
            Rank r2 = (Rank) cards.get(i+1).getRank();
            int dif = r2.order - r1.order;
            if (i==0){
                runs.clear();
                ArrayList<Card> temp = new ArrayList<>();
                temp.add(hand.get(i));
                runs.add(temp);
            }
            if (dif > 1){
                if(runs.get(0).size() >= 3 || i > 3) {
                    break;
                }
                runs.clear();
                ArrayList<Card> temp = new ArrayList<>();
                temp.add(hand.get(i+1));
                runs.add(temp);
                
            } else if (dif == 1){
                for (int j = 0; j < runs.size(); j++){
                    runs.get(j).add(cards.get(i+1));
                }
            } else if (dif == 0){
                int s = runs.size();
                for (int j = 0; j < s; j++){
                    ArrayList<Card> temp = (ArrayList<Card>) runs.get(j).clone();
                    temp.set(temp.size()-1, cards.get(i+1));
                    runs.add(temp);
                } 
            }
        }
        if (runs.get(0).size() >= 3){
            for (int i = 0; i<runs.size();i++){
                LogController.getInstance().logScore(player, "run"+runs.get(i).size(), runs.get(i).size(), runs.get(i));
            }
            return runs.size() * runs.get(0).size();
        }else {
            return 0;
        }
        
     
    }
}
