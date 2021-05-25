package cribbage;

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
                return new PlayRunsScoreRule();
            case PLAYPAIRS:
                return new PlayPairsScoreRule();
            case PLAYCOMPOSITE:
                return new CompositeScoreRule(true);
            case FLUSH:
                return new FlushScoreRule();
            case SHOWCOMPOSITE:
                return new CompositeScoreRule(false);
            case FIFTEEN:
                return new FifteenScoreRule();
            case SHOWRUNS:
                return new ShowRunsScoreRule();
            case SHOWPAIRS:
                return new ShowPairsScoreRule();
            case PLAYJACK:
            	return new PlayJackScoreRule();
            case REACHFIFTEEN:;
            	return new ReachFifteenScoreRule();
            case REACHTHIRTYONE:
            	return new ReachThirtyoneScoreRule();
            case SHOWJACK:
                return new ShowJackScoreRule();
            case GO:
            	return new GoScoreRule();
            default:
                return null;
        }
    }
}
