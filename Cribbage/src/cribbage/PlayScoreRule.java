package cribbage;

import ch.aplu.jcardgame.Hand;
/**
 * calculate the score of cards in the hand
 * that sum to special mark in play stage
 */
public class PlayScoreRule implements IScoreRule{
	private final String event;

	/** Construct score rule with different type of event
	 * @param event event type
	 * */
	public PlayScoreRule(String event) {
		this.event = event;
	}

	/**
	 * calculate the score of cards in the hand
	 * that sum to special mark in play stage
	 * @param hand  the cards in the player's hand
	 * @param player playerId
	 * @return the score in play stage
	 */
	@Override
	public int getScore(Hand hand, int player) {
		int score = ScoreAdapter.getInstance().loadScore(event);
		LogController.getInstance().logScore(player, event, score);
		return score;
	}
}