package cribbage;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class CompositeScore implements IScoreRule{
    ArrayList<IScoreRule> rules = new ArrayList<>();

    @Override
    public int getScore(Hand start, ArrayList<Card> cards) {
        int score = 0;
        for (IScoreRule rule: rules){
            score += rule.getScore(start,cards);
        }
        return score;
    }

    public CompositeScore (){
        rules.add(new FifteenScore());
        rules.add(new RunScore());
        rules.add(new PairsScore());
        rules.add(new FlushScore());
    }
}
