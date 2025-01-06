import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SearchTest {
    private TextSearcher textSorter;

    public enum SortingAlgorithm {naive, advancedNaiveV1, advancedNaiveV2, boyerMoore};

    public void setup(SortingAlgorithm sortingAlgorithm) {
        String text = "ALGORITHMEN UND DADATENSTRUKTUREN";
        switch (sortingAlgorithm) {
            case naive -> textSorter = new NaiveSearch(text);
            case advancedNaiveV1 -> textSorter = new AdvancedNaiveSearchV1(text);
            case advancedNaiveV2 -> textSorter = new AdvancedNaiveSearchV2(text);
            case boyerMoore -> textSorter = new BoyerMooreSearch(text, BoyerMooreSearch.Alphabet.upperCaseLetters);
        }
    }

    @Test
    public void test_Naive_Search() {
        setup(SortingAlgorithm.naive);
        String pattern1 = "DATEN";
        String pattern2 = "T";
        String pattern3 = "EN";

        // One Occurence
        int occurences1 = textSorter.occurences(pattern1);
        assertEquals(1,occurences1);

        // Multiple occurences
        int occurences2 = textSorter.occurences(pattern2);
        assertEquals(4,occurences2);

        // Multiple occurence & at the end
        int occurences3 = textSorter.occurences(pattern3);
        assertEquals(3, occurences3);
    }

    @Test
    public void test_Advanced_Naive_Search_V1() {
        setup(SortingAlgorithm.advancedNaiveV1);
        String pattern1 = "DATEN";
        String pattern2 = "T";
        String pattern3 = "EN";
        String pattern4 = "AA";

        // One Occurence
        int occurences1 = textSorter.occurences(pattern1);
        assertEquals(1,occurences1);

        // Multiple occurences
        int occurences2 = textSorter.occurences(pattern2);
        assertEquals(4,occurences2);

        // Multiple occurence & at the end
        int occurences3 = textSorter.occurences(pattern3);
        assertEquals(3, occurences3);

        // Pattern with duplicates -> Error
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            textSorter.occurences(pattern4);
        });
        System.out.println(exception.getMessage());
    }

    @Test
    public void test_Advanced_Naive_Search_V2() {
        setup(SortingAlgorithm.advancedNaiveV2);
        String pattern1 = "DATEN";
        String pattern2 = "T";
        String pattern3 = "EN";
        String pattern4 = "AA";

        // One Occurence
        int occurences1 = textSorter.occurences(pattern1);
        assertEquals(1,occurences1);

        // Multiple occurences
        int occurences2 = textSorter.occurences(pattern2);
        assertEquals(4,occurences2);

        // Multiple occurence & at the end
        int occurences3 = textSorter.occurences(pattern3);
        assertEquals(3, occurences3);

        // Pattern with duplicates -> Error
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            textSorter.occurences(pattern4);
        });
        System.out.println(exception.getMessage());
    }

    @Test
    public void test_BoyerMoore_Search() {
        setup(SortingAlgorithm.boyerMoore);
        String pattern1 = "DATEN";
        String pattern2 = "T";
        String pattern3 = "EN";

        // One Occurence
        int occurences1 = textSorter.occurences(pattern1);
        assertEquals(1,occurences1);

        // Multiple occurences
        int occurences2 = textSorter.occurences(pattern2);
        assertEquals(4,occurences2);

        // Multiple occurence & at the end
        int occurences3 = textSorter.occurences(pattern3);
        assertEquals(3, occurences3);
    }
}