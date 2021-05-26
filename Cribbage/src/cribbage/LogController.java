package cribbage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import ch.aplu.jcardgame.Card;
import cribbage.Cribbage.Rank;
import cribbage.Cribbage.Suit;

/** write the event into the log
 * */
public class LogController {
    private static LogController instance = null;
    private static FileWriter fw;
    private static int[] scores = new int[] {0,0};
    static {
        File log= new File ("cribbage.log");
        if (log.exists()) {
            try {
                fw = new FileWriter(log,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                log.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fw = new FileWriter(log);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /** get the LogController singleton
     * */
    public static LogController getInstance(){
        if (instance == null){
            instance = new LogController();
        }
        return instance;
    }

    /** close the file writer
     * */
    public void closeFile(){
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** write the event into the log file in the play stage
     * @param player playerId
     * @param event type of score event
     * @param score score get from cards
     * */
    public void logScore(int player, String event ,int score) {
        scores[player] += score;
        try {
            fw.write("score,P" + player + "," + scores[player] + "," + score + "," + event + "\n");
            fw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** write the event into the log file in the show stage
     * @param player playerId
     * @param event type of score event
     * @param score score get from cards
     * @param cards the combination of cards which can reward the score
     * */
    public void logScore(int player, String event, int score, ArrayList<Card> cards) {
        scores[player] += score;
        cards.sort(new Comparator<Card>(){
            @Override
            public int compare(Card c1, Card c2){
                Rank r1 = (Rank) c1.getRank();
                Rank r2 = (Rank) c2.getRank();
                int order1 = r1.order;
                int order2 = r2.order;
                if (order1 - order2 == 0){
                    return c1.getSuitId() - c2.getSuitId();
                } else {
                    return order1 - order2;
                }
            }
        });
        try {
            String cardlist= "[" + cards.stream().map(this::canonical).collect(Collectors.joining(",")) + "]";
            fw.write("score,P" + player + "," + scores[player] + "," + score + "," + event +"," + cardlist + "\n");
            fw.flush();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /** write the game seed into log file
     * @param content seed number
     * */
    public void logSeed(int content){
        try {
            fw.write("seed," + content + "\n");
            fw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** write the player name into log file
     * @param content player name
     * @param player playerId
     * */
    public void logPlayer(String content, int player){
        try {
            fw.write(content + ",P" + player + "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** write the deal to each player
     *  and players' discard cards into log file
     * @param type deal or discard
     * @param player playerId
     * @param canonical the string that can represent the card information
     * */
    public void logDealDiscard(String type, int player, String canonical){
        try {
            fw.write(type + ",P" + player + ',' + canonical + "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** write the starter card into log
     * @param canonical the string that can represent the card information
     * */
    public void logStarter(String canonical){
        try {
            fw.write("starter," + canonical + "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** write the card play dealt into log file
     * @param player playerId
     * @param  card dealt card
     * */
    public void logPlay(int player, int score, String card){
        try {
            fw.write("play,P" + player + ',' + score + ',' + card + "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** write the card list in the show stage into log file
     * @param player playerId
     * @param card starter card
     * @param canonical the string that can represent the card information
     * */
    public void logShow(int player, String card, String canonical){
        try {
            fw.write("show,P" + player + ',' + card + '+' + canonical + "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // process the card information to string
    private String canonical(Card c) { return canonical((Rank) c.getRank()) + canonical((Suit) c.getSuit()); }

    // process the card's suit to string
    private String canonical(Suit s) { return s.toString().substring(0, 1); }

    // process the card's rank to string
    private String canonical(Rank r) {
        switch (r) {
            case ACE:case KING:case QUEEN:case JACK:case TEN:
                return r.toString().substring(0, 1);
            default:
                return String.valueOf(r.value);
        }
    }

}
