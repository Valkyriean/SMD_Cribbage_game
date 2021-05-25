package cribbage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;
import cribbage.Cribbage.Rank;

public class PlayRunsScore implements IScoreRule{
    @Override
	public int getScore(Hand hand) {
		int runsCount = 1, score = 0;
		ArrayList<Integer> sequence = new ArrayList<>();
		int i = hand.getNumberOfCards()-1;
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
			System.out.println("Runs +3");
			score = 3;
			break;
		case 4:
			System.out.println("Runs +4");

			score = 4;
			break;
		case 5:
			System.out.println("Runs +5");

			score = 5;
			break;
		case 6:
			System.out.println("Runs +6");

			score = 6;
			break;
		case 7:
			System.out.println("Runs +7");

			score = 7;
			break;
		}
		return score;
    }
}
