package cribbage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ScoreFactory {
    static ScoreFactory instance = null;
    public static ScoreFactory getInstance(){
        if (instance == null){
            instance = new ScoreFactory();
        }
        return instance;
    }

    public IScoreRule getScoreRule (Rules r){
        switch (r) {
            case PLAYRUNS:
                return new PlayRunsScore();
            case PLAYPAIRS:
                return new PlayPairsScore();
            case PLAYCOMPOSITE:
                return new CompositeScore(true);
            case FLUSH:
                return new FlushScore();
            case SHOWCOMPOSITE:
                return new CompositeScore(false);
            case FIFTEEN:
                return new FifteenScore();
            case SHOWRUNS:
                return new ShowRunsScore();
            case SHOWPAIRS:
                return new ShowPairsScore();
            case JACK:
            	return new JackScore();
            case REACHFIFTEEN:;
            	return new ReachFifteenScore();
            case REACHTHIRTYONE:
            	return new ReachThirtyoneScore();
            case GO:
            	return new GoScore();
            default:
                return null;
        }
    }
}
