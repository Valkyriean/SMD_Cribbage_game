package cribbage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage.Rank;

public class PlayRunsScoreRule implements IScoreRule{
    @Override
	public int getScore(Hand hand, int player) {
		int runsCount = 1, score = 0;
		ArrayList<Integer> sequence = new ArrayList<>();
		int i = hand.getNumberOfCards()-1;
		String event = null;
		while(i>=0) {
			Rank r = (Rank) hand.get(i).getRank();
			sequence.add(r.order);
			Collections.sort(sequence);
			Set<Integer> seqSet = new HashSet<Integer>(sequence);
			if(seqSet.size() < sequence.size()) {
				break;
			}
			if(sequence.get(sequence.size()-1)-sequence.get(0) == sequence.size()-1) {
				runsCount = sequence.size();
			}
			i--;
		}

		switch (runsCount) {
		case 3:
			event = "run3";
			break;
		case 4:
			event = "run4";
			break;
		case 5:
			event = "run5";
			break;
		case 6:
			event = "run6";
			break;
		case 7:
			event = "run7";
			break;
		}
		if(event != null) {
			score = ScoreAdapter.getInstance().loadScore(event);
			LogController.getInstance().logScore(player, event, score);
		}
		return score;
    }
}
