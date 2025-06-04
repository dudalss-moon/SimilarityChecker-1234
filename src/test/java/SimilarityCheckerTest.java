import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimilarityCheckerTest {

    private SimilarityChecker similarityChecker;

    @Test
    @DisplayName("글자수가 같으면 : ASD, DSA = 60점")
    void sameCountString() {
        assertEquals(60, similarityChecker.getLengthScore("ADS", "DSA"));
    }

    @BeforeEach
    void setUp() {
        similarityChecker = new SimilarityChecker();
    }

    @Test
    @DisplayName("글자수가 2배 이상 차이 : A, BB = 0 점")
    void over2MultipleCountString() {
        assertEquals(0, similarityChecker.getLengthScore("A", "AB"));
    }

    @Test
    @DisplayName("글자수가 2배 이상 차이 : A, BB = 0 점")
    void over2MultipleCountStringCase2() {
        assertEquals(0, similarityChecker.getLengthScore("AAA", "ABABABAB"));
    }

    @Test
    @DisplayName("부분 점수 GAP(긴문자열 - 짧은 문자열)에 따라 점수 - AAABB, BAA")
    void getPartialScore() {
        assertEquals(20, similarityChecker.getLengthScore("AAABB", "BAA"));
    }

    @Test
    @DisplayName("부분 점수 GAP(긴문자열 - 짧은 문자열)에 따라 점수 - AA, AAE")
    void getPartialScoreAnother() {
        assertEquals(30, similarityChecker.getLengthScore("AA", "AAE"));
    }
}