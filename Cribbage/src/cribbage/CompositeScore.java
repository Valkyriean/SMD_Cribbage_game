package cribbage;

import ch.aplu.jcardgame.Hand;

import java.util.ArrayList;

public class CompositeScore implements IScoreRule{
    ArrayList<IScoreRule> rules = new ArrayList<>();
    @Override
    public int getScore(Hand cards, int playerId) {
        return 0;
    }
}
