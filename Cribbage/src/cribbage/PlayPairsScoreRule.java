package cribbage;

import ch.aplu.jcardgame.Hand;

/**
 * calculate the score of cards in the hand
 * that can form pairs in play stage
 */
public class PlayPairsScoreRule implements IScoreRule{

	/**
	 * calculate the score of cards in the hand
	 * that can form pairs in play stage
	 * @param hand  the cards in the player's hand
	 * @param player playerId
	 * @return the score of pairs rule
	 */
    @Override
	public int getScore(Hand hand, int player) {
    	int i = hand.getNumberOfCards()-1;
		int pairCount = 1, score = 0;
		String event = null;
		while(i>0 && hand.get(i).getRank()== hand.get(i-1).getRank()) {
			pairCount++;
			i--;
		}
		// check the tyep of pairs formed
		switch (pairCount) {
			case 2:
				event = "pair2";;
				break;
			case 3:
				event = "pair3";
				break;
			case 4:
				event = "pair4";
				break;
		}
		// write the event into log file if pairs formed
		if(event != null) {
			score = ScoreAdapter.getInstance().loadScore(event);;
			LogController.getInstance().logScore(player, event, score);
		}
		return score;
    }
}
