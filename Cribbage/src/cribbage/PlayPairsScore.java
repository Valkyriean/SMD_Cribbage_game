package cribbage;

import java.util.ArrayList;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class PlayPairsScore implements IScoreRule{
    @Override
	public int getScore(Hand hand) {
    	int i = hand.getNumberOfCards()-1;
		int pairCount = 1, score = 0;
		while(i>0 && hand.get(i).getRank()== hand.get(i-1).getRank()) {
			pairCount++;
			i--;
		}
		switch (pairCount) {
			case 2:
				System.out.println("Pair +2");
				score += ScoreAdapter.getInstance().loadScore("pair2");
				break;
			case 3:
				System.out.println("Pair +6");
				score += ScoreAdapter.getInstance().loadScore("pair3");;
				break;
			case 4:
				System.out.println("Pair +12");
				score += ScoreAdapter.getInstance().loadScore("pair4");;
				break;
		}
		return score;
    }
}
