package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
import java.util.List;

public class FifteenScoreRule implements IScoreRule {

    @Override
    public int getScore(Hand hand){
        ArrayList<Card> cards = hand.getCardList();
        int [] sum = {0};
        ArrayList<Card> temp = new ArrayList<>();
        ArrayList<ArrayList<Card>> out = new ArrayList<>();
        DFS(15, cards, sum, temp, out);
        return sum[0];
    }

    public void DFS (int target, ArrayList<Card> cards,int[] sum,
                     ArrayList<Card> temp ,ArrayList<ArrayList<Card>> out){
        if (target == 0){
            sum[0] += ScoreAdapter.getInstance().loadScore("fifteen");
            out.add(temp);
        }
        if (target < 0){
            return;
        }

        for (int i = 0; i < cards.size(); i++){
            ArrayList<Card> newCards = new ArrayList<>();
            for (int j = i + 1; j < cards.size(); j++){
                newCards.add(cards.get(j));
            }
            temp.add(cards.get(i));
            Cribbage.Rank rank = (Cribbage.Rank)cards.get(i).getRank();
            int score = rank.value;
            DFS(target - score, newCards, sum, temp, out);
            temp.remove(cards.get(i));
        }

    }
}
