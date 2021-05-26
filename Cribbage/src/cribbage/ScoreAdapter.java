package cribbage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
/** search the score of each event from properties file
 * */
public class ScoreAdapter {
    private static ScoreAdapter instance = null;
    final static Properties scoreProperties;
    static {
        scoreProperties = new Properties();
        try {
            FileReader inStream = new FileReader("score.properties");
            scoreProperties.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /** get the singleton ScoreAdapter
     * */
    public static ScoreAdapter getInstance(){
        if (instance == null){
            instance = new ScoreAdapter();
        }
        return instance;
    }
    /** search the score in the property file according to event type
     * @param key event type
     * @return the score according to event
     * */
    public int loadScore(String key){
        String score = scoreProperties.getProperty(key);
        return Integer.parseInt(score);
    }
}
