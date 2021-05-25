package cribbage;

import ch.aplu.jcardgame.Hand;

public class PlayPairsScoreRule implements IScoreRule{
    @Override
	public int getScore(Hand hand, int player) {
    	int i = hand.getNumberOfCards()-1;
		int pairCount = 1, score = 0;
		String event = null;
		while(i>0 && hand.get(i).getRank()== hand.get(i-1).getRank()) {
			pairCount++;
			i--;
		}
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
		if(event != null) {
			score = ScoreAdapter.getInstance().loadScore(event);;
			LogController.getInstance().logScore(player, event, score);
		}
		return score;
    }
}
