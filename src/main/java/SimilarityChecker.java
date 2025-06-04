public class SimilarityChecker {

    public static final int MAX_SCORE = 60;

    public int getLengthScore(String fistString, String secondString) {
        if (isSameLength(fistString, secondString)) {
            return MAX_SCORE;
        }

        if (isTowMultipleLength(fistString, secondString)) {
            return 0;
        }

        return getLengthScoreForPartialMatched(fistString, secondString);
    }

    private boolean isTowMultipleLength(String fistString, String secondString) {
        int gapCount = Math.abs(fistString.length() - secondString.length());
        int minCount = Math.min(fistString.length(), secondString.length());

        if (minCount <= gapCount) {
            return true;
        }
        return false;
    }

    private boolean isSameLength(String fistString, String secondString) {
        return fistString.length() == secondString.length();
    }

    private int getLengthScoreForPartialMatched(String fistString, String secondString) {
        int gapCount = Math.abs(fistString.length() - secondString.length());
        int minCount = Math.min(fistString.length(), secondString.length());

        double rate = 1 - (gapCount / (double) minCount);
        int score = (int) (rate * MAX_SCORE);
        return score;
    }
}
