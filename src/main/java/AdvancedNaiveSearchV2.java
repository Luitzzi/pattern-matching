import java.util.HashSet;
import java.util.Set;

/**
 * Pattern searching algorithm with the dependency that all characters in the pattern have to be different.
 * The core function is similar to the NaiveSearch. However, if a comparison fails it doesn't only go one step further,
 * it goes over all matched characters -> counterInText +=  counterInPattern - 1.
 *
 * <ul>
 *  Time complexity:
 *  <li>text length = n; pattern length = m; </li>
 *  <li>bc,wc,ac = O(n)</li>
 * <ul>
 */
public class AdvancedNaiveSearchV2 implements TextSearcher{
    String text;

    public AdvancedNaiveSearchV2(String text) {
        this.text = text;
    }

    @Override
    public int occurences(String pattern) {
        if (!isDependencySatisfied(pattern)) {
            throw new IllegalArgumentException ("Dependency is not satisfied! At least two characters are similar in the pattern.");
        }
        else {
            int occurrences = 0;

            for (int counterInText = 0; counterInText <= (text.length() - pattern.length()); counterInText++) {
                boolean match = true;
                for (int counterInPattern = 0; counterInPattern < pattern.length(); counterInPattern++) {
                    if (text.charAt(counterInText + counterInPattern) != pattern.charAt(counterInPattern)) {
                        match = false;
                        // Jump over all matched characters
                        if (counterInPattern > 0) {
                            // The one is subtracted because the for-loop will increment counterInText too
                            counterInText += counterInPattern - 1;
                        }
                        break;
                    }
                }
                if (match) {
                    occurrences++;
                }
            }
            return occurrences;
        }
    }

    public void visualiseComparisons(String pattern) throws InterruptedException {
        System.out.println(text);
        String output = pattern;
        String space = " ";

        int occurrences = 0;

        for (int counterInText = 0; counterInText <= (text.length() - pattern.length()); counterInText++) {
            boolean match = true;

            for (int counterInPattern = 0; counterInPattern < pattern.length(); counterInPattern++) {
                System.out.print("\r" + output + " comparisons: " + counterInPattern + "; occurrences: " + occurrences);
                Thread.sleep(500);
                if (text.charAt(counterInText + counterInPattern) != pattern.charAt(counterInPattern)) {
                    match = false;
                    if (counterInPattern > 0) {
                        counterInText += counterInPattern - 1;
                        for (int i = 0; i < counterInPattern - 1; i++) {
                            output = space + output;
                        }
                    }
                    break;
                }
            }
            if (match) {
                occurrences++;
            }

            output = space + output;
        }
    }

    /**
     * Check if the dependency that all characters in the pattern are unique is satisfied.
     * @param pattern
     * @return true if all patterns are unique. False if characters are duplicate
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
