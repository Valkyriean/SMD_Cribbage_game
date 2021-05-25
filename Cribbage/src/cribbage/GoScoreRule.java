package cribbage;

import ch.aplu.jcardgame.Hand;

public class GoScoreRule implements IScoreRule{
	private final static String event = "go";
	
	@Override
	public int getScore(Hand hand, int player) {
		int score = ScoreAdapter.getInstance().loadScore(event);
		LogController.getInstance().logScore(player, event, score);
		return score;
	}
}