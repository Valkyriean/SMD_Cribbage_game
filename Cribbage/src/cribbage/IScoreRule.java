package cribbage;


import ch.aplu.jcardgame.Hand;




public interface IScoreRule {
    int getScore(Hand hand,int player);
}
