package cribbage;

import ch.aplu.jcardgame.Hand;

public class StarterScore implements IScoreRule{
    @Override
    public int getScore(Hand cards, int playerId) {
        return 0;
    }
}
