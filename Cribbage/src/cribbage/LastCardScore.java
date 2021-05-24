package cribbage;

import ch.aplu.jcardgame.Hand;

public class LastCardScore implements IScoreRule{
    @Override
    public int getScore(Hand cards, int playerId) {
        return 0;
    }
}
