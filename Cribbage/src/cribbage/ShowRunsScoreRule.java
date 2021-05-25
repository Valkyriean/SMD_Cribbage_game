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
                int order1 = r1.value;
                int order2 = r2.value;
                if (order1 - order2 == 0){
                    return c1.getSuitId() - c2.getSuitId();
                } else {
                    return order1 - order2;
                }
            }

        });
        // int [] runs = new int[5];
        // int count = 1;
        // int slow  = 0;

        // while (slow < 5){
        //     int run = 1;
        //     int max = 1;
        //     for (int j = slow + 1; j < 5; j++){
        //         if (cards.get(slow).getRankId() == cards.get(j).getRankId()-run){
        //             run += 1;
        //         } else if(cards.get(slow).getRankId() == cards.get(j).getRankId()){
        //             continue;
        //         }
        //         if (run >= max){
        //             max = run;
        //         }
        //     }
        //     runs[slow] = max;
        //     slow ++;
        // }
        // int max = 2;
        // int index = 0;
        // for (int k = 0; k < 5; k++){
        //     if (runs[k] > max){
        //         count = 1;
        //         index = k;
        //         max = runs[k];
        //     } else if (runs[k] == max){
        //         count += 1;
        //     }
        // }
        // cards.sort((o1,o2)->o1.getValue() - o2.getValue());

        // switch (max){
        //     case 3:
        //         return ScoreAdapter.getInstance().loadScore("run3") * count;
        //     case 4:
        //         return ScoreAdapter.getInstance().loadScore("run4") * count;
        //     case 5:
        //         return ScoreAdapter.getInstance().loadScore("run5") * count;
        //     default:
        //         return 0;
        // }
        


        ArrayList<ArrayList<Card>> temp = new ArrayList<>();
        boolean first = true;
        for(int i = 0; i < hand.getNumberOfCards()-1; i++){
            Rank r1 = (Rank) hand.get(i).getRank();
            Rank r2 = (Rank) hand.get(i+1).getRank();
            int dif = r2.order - r1.order;
            if (i==0){
                temp.clear();
                ArrayList<Card> tem2 = new ArrayList<>();
                tem2.add(hand.get(i));
                temp.add(tem2);  
            }
            if (dif > 1){
                if(temp.get(0).size() >= 3 || i > 3) {
                    break;
                }
                temp.clear();
                ArrayList<Card> tem2 = new ArrayList<>();
                tem2.add(hand.get(i+1));
                temp.add(tem2);  
                
            } else if (dif == 1){
                for (int j = 0; j < temp.size(); j++){
                    temp.get(j).add(hand.get(i+1));
                }
            } else if (dif == 0){
                for (int j = 0; j < temp.size(); j++){
                    ArrayList<Card> tem2 = (ArrayList<Card>) temp.get(j).clone();
                    tem2.set(tem2.size()-1, hand.get(i+1));
                    temp.add(tem2);
                } 
            }
        }
        if (temp.get(0).size() >= 3){
            return temp.size() * temp.get(0).size();
        }else {
            return 0;
        }
        
     
    }
}
