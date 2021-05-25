package cribbage;

import ch.aplu.jcardgame.Hand;

public class ReachThirtyoneScore implements IScoreRule{
	@Override
	public int getScore(Hand hand) {
		return ScoreAdapter.getInstance().loadScore("thirtyone");
	}
}
