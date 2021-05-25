package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class CompositeScoreRule implements IScoreRule{
    ArrayList<IScoreRule> rules = new ArrayList<>();

    @Override
    public int getScore(Hand hand) {
        int score = 0;
        for (IScoreRule rule: rules){
            int temp = rule.getScore(hand);
            score += temp;
            System.out.println(rule.getClass()+" "+"score :" + temp);
        }
        return score;
    }

    public CompositeScoreRule(boolean play){
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
