package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class PlayCompositeScore implements IScoreRule{
    ArrayList<IScoreRule> rules = new ArrayList<>();

    @Override
    public int getScore(Hand hand) {
        int score = 0;
        for (IScoreRule rule: rules){
            score += rule.getScore(hand);
        }
        return score;
    }

    public PlayCompositeScore (){
        rules.add(new PlayPairScore());
        rules.add(new PlayRunsScore());
    }
}
