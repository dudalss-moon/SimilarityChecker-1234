public class SimilarityChecker {

    public static final int MAX_SCORE = 60;

    public int getLengthScore(String fistString, String secondString) {
        if (isSameLength(fistString, secondString)) {
            return MAX_SCORE;
        }

        if (isDoubleleLength(fistString, secondString)) {
            return 0;
        }

        return getLengthScoreForPartialMatched(fistString, secondString);
    }

    private boolean isDoubleleLength(String fistString, String secondString) {
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

    public int getAlphabetScore(String firstAlphabet, String secondAlphabet) throws RuntimeException {
        checkValidArgument(firstAlphabet);
        checkValidArgument(secondAlphabet);

        int[] array = new int[24];
        for (char ch : firstAlphabet.toCharArray()) {
            // only first
            array[ch - 65] = 1;
        }
        for (char ch : secondAlphabet.toCharArray()) {
            if (array[ch - 65] == 0) {
                // only second
                array[ch - 65] = 2;
            } else if (array[ch - 65] == 1) {
                // both
                array[ch - 65] = 3;
            }
        }

        int firstCount = 0;
        int secondCount = 0;
        int bothCount = 0;
        for (int i = 0; i < 24; i++) {
            if (array[i] == 1) {
                firstCount++;
            } else if (array[i] == 2) {
                secondCount++;
            } else if (array[i] == 3) {
                bothCount++;
            }
        }
        if (bothCount > 0 && firstCount == 0 && secondCount == 0) {
            return 40;
        } else if (bothCount == 0) {
            return 0;
        } else {
            double score = (double) bothCount / (firstCount + secondCount + bothCount) * 40;
            return (int) score;
        }

//        int firstAlphabetCount = getAlphabetCount(getBoolCheckedAlphabet(firstAlphabet));
//        int secondAlphabetCount = getAlphabetCount(getBoolCheckedAlphabet(secondAlphabet));
//        if (firstAlphabetCount == secondAlphabetCount) {
//            return 40;
//        }
//        return 0;
    }

    private int getAlphabetCount(boolean[] alphabetArray) {
        int alphabetCount = 0;
        for (int i = 0; i < 24; i++) {
            if (alphabetArray[i]) {
                alphabetCount++;
            }
        }
        return alphabetCount;
    }

    private boolean[] getBoolCheckedAlphabet(String alphabetString) {
        boolean[] checkedArray = new boolean[24];
        for (char ch : alphabetString.toCharArray()) {
            checkedArray[ch - 65] = true;
        }
        return checkedArray;
    }

    private void checkValidArgument(String str) {
        for (char ch : str.toCharArray()) {
            if (ch < 'A' || ch > 'Z') {
                throw new RuntimeException();
            }
        }
    }
}
