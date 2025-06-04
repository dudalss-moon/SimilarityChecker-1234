import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityCheckerTest {
    @Test
    @DisplayName("글자수가 같으면 : ASD, DSA = 60점")
    void sameCountString() {
        SimilarityChecker similarityChecker = new SimilarityChecker();
        int score = 60;

        int actual = similarityChecker.getLengthScore("ADS", "DSA");

        assertEquals(score, actual);
    }

    @Test
    @DisplayName("글자수가 2배 이상 차이 : A, BB = 0 점")
    void over2MultipleCountString() {
        // AAABB, BAA : 부분점수
        // AA, AAE : 부분 점수
        // Gap : A (긴문자열) - B(짧은 문자열)

        SimilarityChecker similarityChecker = new SimilarityChecker();
        int score = 0;

        int actual = similarityChecker.getLengthScore("A", "AB");

        assertEquals(score, actual);
    }

    @Test
    @DisplayName("부분 점수 GAP(긴문자열 - 짧은 문자열)에 따라 점수 - AAABB, BAA")
    void getPartialScore() {
        // AAABB, BAA : 부분점수
        SimilarityChecker similarityChecker = new SimilarityChecker();
        int score = 20;

        int actual = similarityChecker.getLengthScore("AAABB", "BAA");

        assertEquals(score, actual);
    }

    @Test
    @DisplayName("부분 점수 GAP(긴문자열 - 짧은 문자열)에 따라 점수 - AA, AAE")
    void getPartialScoreAnother() {
        // AA, AAE : 부분 점수
        SimilarityChecker similarityChecker = new SimilarityChecker();
        int score = 30;

        int actual = similarityChecker.getLengthScore("AA", "AAE");

        assertEquals(score, actual);
    }
}