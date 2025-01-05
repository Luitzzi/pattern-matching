/**
 * Pattern searching algorithm.
 * Goes over the whole text and compares at every position in a seperate for loop if all characters of the pattern
 * match with the corresponding letters in the text.
 *
 * <ul>
 *  Time complexity:
 *  <li>text length = n; pattern length = m; </li>
 *  <li>bc = O(n) ; wc O(n * m) ; ac O(n) </li>
 * </ul>
 */
public class NaiveSearch implements TextSearcher {
    protected String text;

    public NaiveSearch(String text) {
        this.text = text;
    }

    @Override
    public int occurences(String pattern) {
        int occurences = 0;
        for (int counterInText = 0; counterInText <= (text.length() - pattern.length()); counterInText++) {
            boolean match = true;
            for (int counterInPattern = 0; counterInPattern < pattern.length(); counterInPattern++) {
                if (text.charAt(counterInText + counterInPattern) != pattern.charAt(counterInPattern)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                occurences++;
            }
        }
        return occurences;
    }

    public void visualiseComparisons(String pattern) throws InterruptedException {
        System.out.println(text);
        String output = pattern;
        String space = " ";

        int occurences = 0;
        for (int counterInText = 0; counterInText <= (text.length() - pattern.length()); counterInText++) {
            boolean match = true;
            for (int counterInPattern = 0; counterInPattern < pattern.length(); counterInPattern++) {
                System.out.print("\r" + output + " comparisons: " + (counterInPattern + 1) + "; occurences: " + occurences);
                Thread.sleep(500);

                if (text.charAt(counterInText + counterInPattern) != pattern.charAt(counterInPattern)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                occurences++;
            }
            output = space + output;
        }
    }
}
