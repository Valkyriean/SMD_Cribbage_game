package cribbage;

/** singleton score factory create different type of score rules
 * */
public class ScoreFactory {
    static ScoreFactory instance = null;

    /** get ScoreFactory singleton
     * */
    public static ScoreFactory getInstance(){
        if (instance == null){
            instance = new ScoreFactory();
        }
        return instance;
    }

    /** get the different score rules instance
     * @param r the type of score rules
     * @return the instance of score rules
     * */
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
            	return new PlayScoreRule("jack");
            case REACHFIFTEEN:;
            	return new PlayScoreRule("fifteen");
            case REACHTHIRTYONE:
            	return new PlayScoreRule("thirtyone");
            case SHOWJACK:
                return new ShowJackScoreRule();
            case GO:
            	return new PlayScoreRule("go");
            default:
                return null;
        }
    }
}
