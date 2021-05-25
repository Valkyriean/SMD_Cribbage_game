package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class CompositeScore implements IScoreRule{
    ArrayList<IScoreRule> rules = new ArrayList<>();

    @Override
    public int getScore(Hand hand) {
        int score = 0;
        for (IScoreRule rule: rules){
            int temp = rule.getScore(hand);
            score += temp;
        }
        return score;
    }

    public CompositeScore (){
        rules.add(ScoreFactory.getInstance().getScoreRule(Rules.FIFTEEN));
        rules.add(ScoreFactory.getInstance().getScoreRule(Rules.RUNS));
        rules.add(ScoreFactory.getInstance().getScoreRule(Rules.PAIRS));
        rules.add(ScoreFactory.getInstance().getScoreRule(Rules.FLUSH));
    }
}
