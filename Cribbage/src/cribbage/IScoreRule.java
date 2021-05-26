package cribbage;


import ch.aplu.jcardgame.Hand;



/** Calculate the score of cards in the player's hand
 * */
public interface IScoreRule {
    // return the score based on the score rule
    int getScore(Hand hand,int player);
}
