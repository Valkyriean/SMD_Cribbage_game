package cribbage;

import ch.aplu.jcardgame.Hand;

public class ReachThirtyoneScoreRule implements IScoreRule{
	private final static String event = "thirtyone";
	@Override
	public int getScore(Hand hand, int player) {
		int score = ScoreAdapter.getInstance().loadScore(event);
		LogController.getInstance().logScore(player, event, score);
		return score;
	}

}
