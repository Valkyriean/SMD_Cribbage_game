package cribbage;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;
/** combine all the score rule together and calculate the total score
 * */
public class CompositeScoreRule implements IScoreRule{
    private ArrayList<IScoreRule> rules;


    /**
     * calculate the score of cards in the hand
     * @param hand  the cards in the player's hand
     * @param player playerId
     * @return the total score of cards
     */
    @Override
    public int getScore(Hand hand, int player) {
        int score = 0;
        for (IScoreRule rule: rules){
            int temp = rule.getScore(hand, player);
            score += temp;
        }
        return score;
    }

    /** Construct the composite score rule in different stage
     * @param play the stage of game
     * */
    public CompositeScoreRule(boolean play){
        rules = new ArrayList<>();
        // only consider runs and pairs in play stage
        if (play){
            rules.add(ScoreFactory.getInstance().getScoreRule(Rules.PLAYPAIRS));
            rules.add(ScoreFactory.getInstance().getScoreRule(Rules.PLAYRUNS));
        } else {
            rules.add(ScoreFactory.getInstance().getScoreRule(Rules.FIFTEEN));
            rules.add(ScoreFactory.getInstance().getScoreRule(Rules.SHOWRUNS));
            rules.add(ScoreFactory.getInstance().getScoreRule(Rules.SHOWPAIRS));
            rules.add(ScoreFactory.getInstance().getScoreRule(Rules.FLUSH));
            rules.add(ScoreFactory.getInstance().getScoreRule(Rules.SHOWJACK));
        }

    }
}
