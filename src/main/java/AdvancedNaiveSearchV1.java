import java.util.HashSet;
import java.util.Set;

/**
 * Pattern searching algorithm with the dependency that all characters in the pattern have to be different.
 * Goes in linear time over the text and compares every position with the corresponding letter in the pattern.
 * It keeps track of this letter in the positionInPattern variable.
 *
 * <ul>
 *  Time complexity:
 *  <li>text length = n; pattern length = m; </li>
 *  <li>bc,wc,ac = O(n)</li>
 * <ul>
 */
public class AdvancedNaiveSearchV1 implements TextSearcher {
    String text;

    public AdvancedNaiveSearchV1(String text) {
        this.text = text;
    }

    @Override
    public int occurences(String pattern) {
        if (!isDependencySatisfied(pattern)) {
            throw new IllegalArgumentException ("Dependency is not satisfied! At least two characters are similar in the pattern.");
        }
        else {
            int occurences = 0;
            int positionInPattern = 0;
            for (int counterInText = 0; counterInText < text.length(); counterInText++) {
                if (text.charAt(counterInText) != pattern.charAt(positionInPattern)) {
                    // No Match
                    positionInPattern = 0;
                }
                if (text.charAt(counterInText) == pattern.charAt(positionInPattern)) {
                    // Match
                    if (positionInPattern == pattern.length() - 1) {
                        // Whole pattern matched
                        occurences++;
                        positionInPattern = 0;
                    } else {
                        positionInPattern++;
                    }
                }
            }
            return occurences;
        }
    }

    public void visualiseComparisons(String pattern) throws InterruptedException {
        System.out.println(text);
        String output = "^";
        String space = " ";

        int occurences = 0;
        int positionInPattern = 0;

        for (int counterInText = 0; counterInText < text.length(); counterInText++) {
            System.out.print("\r" + output + " compared with " + pattern.charAt(positionInPattern) + "; occurences: " + occurences);
            Thread.sleep(1000);

            if (text.charAt(counterInText) != pattern.charAt(positionInPattern)) {
                // No Match
                positionInPattern = 0;
                System.out.print("\r" + output + " compared with " + pattern.charAt(positionInPattern) + "; occurences: " + occurences);
                Thread.sleep(1000);
            }
            if (text.charAt(counterInText) == pattern.charAt(positionInPattern)) {
                // Match
                if (positionInPattern == pattern.length() - 1) {
                    // Whole pattern matched
                    occurences++;
                    positionInPattern = 0;
                } else {
                    positionInPattern++;
                }
            }

            output = space + output;
        }

    }

    /**
     * Check if the dependency that all characters in the pattern are unique is satisfied.
     * @param pattern
     * @return true if all patterns are unique, false if at least two characters are duplicate
     */
    private boolean isDependencySatisfied(String pattern) {
        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            char currentChar = pattern.charAt(i);
            if (seen.contains(currentChar)) {
                return false;
            }
            seen.add(currentChar);
        }
        return true;
    }
}
