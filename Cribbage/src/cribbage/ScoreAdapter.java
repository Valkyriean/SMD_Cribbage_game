package cribbage;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

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
    public static ScoreAdapter getInstance(){
        if (instance == null){
            instance = new ScoreAdapter();
        }
        return instance;
    }
    public int loadScore(String key){
        String score = scoreProperties.getProperty(key);
        return Integer.parseInt(score);
    }
}
