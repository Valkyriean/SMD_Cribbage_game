package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

/** Calculate the score of fifteen score rule
 * */
public class FifteenScoreRule implements IScoreRule {

    /**
     * calculate the score of cards in the hand that can sum to 15
     * @param hand  the cards in the player's hand
     * @param player playerId
     * @return the score of fifteen rule
     */
    @Override
    public int getScore(Hand hand, int player){
        Card stater = hand.getLast();
        ArrayList<Card> cards = (ArrayList<Card>)hand.getCardList().clone();
        cards.remove(stater);
        cards.sort((o1,o2)->o1.getValue() - o2.getValue());
        cards.add(stater);
        int [] sum = {0};
        ArrayList<Card> temp = new ArrayList<>();

        DFS(player, 15, cards, sum, temp);
        return sum[0];
    }

    // find all the combination that can sum to 15
    private void DFS (int player, int target, ArrayList<Card> cards,int[] sum, ArrayList<Card> temp){
        // sum to 15 write to log and record the score
        if (target == 0){
            sum[0] += ScoreAdapter.getInstance().loadScore("fifteen");
            LogController.getInstance().logScore(player, "fifteen", 
            ScoreAdapter.getInstance().loadScore("fifteen"), temp);
        }
        if (target < 0){
            return;
        }

        // find all combination
        for (int i = 0; i < cards.size(); i++){
            ArrayList<Card> newCards = new ArrayList<>();
            for (int j = i + 1; j < cards.size(); j++){
                newCards.add(cards.get(j));
            }
            temp.add(cards.get(i));
            Cribbage.Rank rank = (Cribbage.Rank)cards.get(i).getRank();
            int score = rank.value;
            DFS(player, target - score, newCards, sum, temp);
            temp.remove(cards.get(i));
        }

    }
}
