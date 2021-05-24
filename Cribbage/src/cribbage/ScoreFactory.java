package cribbage;

public class ScoreFactory {
    static ScoreFactory instance = null;

    public static ScoreFactory getInstance(){
        if (instance == null){
            instance = new ScoreFactory();
        }
        return instance;
    }

//    public IScoreRule getScoreRule (Rules r){
//        switch (r) {
//            case RUNS:
//                return new RunScore();
////            case PAIRS:
////                return new PairsScore();
//            case FLUSH:
//                return new FlushScore();
//            case COMPOSITE:
//                return new CompositeScore();
//            case LASTCARD:
//                return new LastCardScore();
//            default:
//                return null;
//        }
//    }
}
