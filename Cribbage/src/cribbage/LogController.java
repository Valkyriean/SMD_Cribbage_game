package cribbage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import ch.aplu.jcardgame.Card;
import cribbage.Cribbage.Rank;
import cribbage.Cribbage.Suit;


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

    public static LogController getInstance(){
        if (instance == null){
            instance = new LogController();
        }
        return instance;
    }

    public void closeFile(){
        try {
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void logScore(int player, String event ,int score) {
        scores[player] += score;
        try {
            fw.write("score,P" + player + "," + scores[player] + "," + score + "," + event + "\n");
            fw.flush();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void logScore(int player, String event, int score, ArrayList<Card> cards) {
        scores[player] += score;
        try {
            String cardlist= "[" + cards.stream().map(this::canonical).collect(Collectors.joining(",")) + "]";
            fw.write("score,P" + player + "," + scores[player] + "," + score + "," + event +"," + cardlist + "\n");
            fw.flush();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void logSeed(int content){
        try {
            fw.write("seed," + content + "\n");
            fw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logPlayer(String content, int player){
        try {
            fw.write(content + ",P" + player + "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logDealDiscard(String type, int player, String canonical){
        try {
            fw.write(type + ",P" + player + ',' + canonical + "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void logStarter(String canonical){
        try {
            fw.write("starter," + canonical + "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logPlay(int player, int score, String card){
        try {
            fw.write("play, P" + player + ',' + score + ',' + card + "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logShow(int player, String card, String canonical){
        try {
            fw.write("show,P" + player + ',' + card + '+' + canonical + "\n");
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String canonical(Card c) { return canonical((Rank) c.getRank()) + canonical((Suit) c.getSuit()); }

    String canonical(Suit s) { return s.toString().substring(0, 1); }

    String canonical(Rank r) {
        switch (r) {
            case ACE:case KING:case QUEEN:case JACK:case TEN:
                return r.toString().substring(0, 1);
            default:
                return String.valueOf(r.value);
        }
    }

}
