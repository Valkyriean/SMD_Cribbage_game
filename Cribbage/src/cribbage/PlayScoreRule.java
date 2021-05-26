package cribbage;

import ch.aplu.jcardgame.Hand;

public class PlayScoreRule implements IScoreRule{
	private final String event;
	
	public PlayScoreRule(String event) {
		this.event = event;
	}
	
	@Override
	public int getScore(Hand hand, int player) {
		int score = ScoreAdapter.getInstance().loadScore(event);
		LogController.getInstance().logScore(player, event, score);
		return score;
	}
}