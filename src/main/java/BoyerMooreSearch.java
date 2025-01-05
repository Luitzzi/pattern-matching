/**
 * Pattern searching algorithm implementing the bad character heuristic. <br>
 * Preprocesses the pattern to determine shifts, allowing it to skip sections of the text,
 * enabling sublinear time complexity.
 *
 * <p>
 * Time complexity:
 *  <ul>
 *      <li>text length = n; pattern length = m; alphabet length = ∑;</li>
 *      <li>preparation effort = O(∑ + m)</li>
 *      <li>bc = O(n / m) ; wc = O(n * m) ; ac = O(n / m) </li>
 *  </ul>
 * </p>
 */
public class BoyerMooreSearch implements TextSearcher {
    private String text;
    int sizeOfAlphabet;
    private int[] shiftAlphabet;

    public BoyerMooreSearch(String text) {
        // Default Alphabet used -> All upper case characters, space and comma
        this.text = text;
        sizeOfAlphabet = 28;
        shiftAlphabet = new int[sizeOfAlphabet];
    }

    @Override
    public int occurences(String pattern) {
        initShiftAlphabet(pattern);

        int occurrences = 0;
        int counterInText = pattern.length() - 1;
        int counterInPattern = pattern.length() - 1;

        while (counterInText < text.length()) {
            if (text.charAt(counterInText) == pattern.charAt(counterInPattern)) {
                // Match
                if (counterInPattern == 0) {
                    occurrences++;
                    counterInText += pattern.length();
                    counterInPattern = pattern.length() - 1;
                }
                else {
                    counterInText--;
                    counterInPattern--;
                }
            }
            else {
                // Jump to next position
                if (pattern.length() - counterInPattern > shiftAlphabet[getIndex(text.charAt(counterInText))]) {
                    /* Went more steps left (down the text) then the jump distance from shiftAlphabet would be.
                       -> Jump all steps back and one step further */
                    counterInText += pattern.length() - counterInPattern;
                }
                else {
                    counterInText += shiftAlphabet[getIndex(text.charAt(counterInText))];
                }
                // Reset counterInPattern
                counterInPattern = pattern.length() - 1;
            }
        }

        return occurrences;
    }

    /**
     * <p>
     * Calculates the shift amount for each char in the alphabet, which allows to skip over characters in the algorithm.
     * All chars with no occurrence in the pattern have the shift amount of the pattern length. The whole pattern can
     * advance over these letters.
     * </p>
     * <p>
     * The chars in the pattern get assigned with their position in the pattern in reverse.
     * This aligns the char in the text directly with its correspondent letter in the pattern, the first possible position
     * where a whole match is possible.
     * </p>
     * @param pattern
     */
    private void initShiftAlphabet(String pattern) {
        int patternLength = pattern.length();
        // Assign all chars of the alphabet the default shift width of the patternLength
        for (int i = 0; i < sizeOfAlphabet; i++) {
            shiftAlphabet[i] = patternLength;
        }
        // Edit the shiftAlphabet array for all chars occurring in the pattern
        for (int i = 0; i < patternLength; i++) {
            shiftAlphabet[getIndex(pattern.charAt(i))] = patternLength - i - 1;
        }
    }

    /**
     * Maps the ASCII value of a char to the position in the alphabet.
     * @param charInPattern
     * @return index in the alphabet
     */
    private int getIndex(char charInPattern) {
        return switch (charInPattern) {
            case ' ' -> 26;
            case ',' -> 27;
            default -> (int) charInPattern - 65;
        };
    }

    public int visualiseComparisons(String pattern) throws InterruptedException {
        initShiftAlphabet(pattern);

        String textInShiftNums = calcTextInShiftNums();
        System.out.println(textInShiftNums);
        System.out.println(text);
        String cursor = "^";
        String output;
        boolean first = true;

        int occurrences = 0;
        int counterInText = pattern.length() - 1;
        int counterInPattern = pattern.length() - 1;

        int prevTextPos = counterInText;
        int lastJumpLength;

        while (counterInText < text.length()) {
            char currentPatternChar = pattern.charAt(counterInPattern);
            output = calcNumSpaces(String.valueOf(currentPatternChar),counterInText);
            lastJumpLength = counterInText - prevTextPos;
            prevTextPos = counterInText;
            System.out.print("\r" + output + " jumpsize: " + lastJumpLength);
            Thread.sleep(2500);

            char c = text.charAt(counterInText);
            if (text.charAt(counterInText) == pattern.charAt(counterInPattern)) {
                // Match
                if (counterInPattern == 0) {
                    occurrences++;
                    counterInText += pattern.length();
                    counterInPattern = pattern.length() - 1;
                }
                else {
                    counterInText--;
                    counterInPattern--;
                }
            }
            else {
                // Jump to next position
                if (pattern.length() - counterInPattern > shiftAlphabet[getIndex(text.charAt(counterInText))]) {
                    /* Went more steps left (down the text) then the jump distance from shiftAlphabet would be.
                       -> Jump all steps back and one step further */
                    counterInText += pattern.length() - counterInPattern;
                }
                else {
                    counterInText += shiftAlphabet[getIndex(text.charAt(counterInText))];
                }
                // Reset counterInPattern
                counterInPattern = pattern.length() - 1;
            }
        }

        return occurrences;
    }

    private String calcNumSpaces(String output, int positionInText) {
        String space = " ";
        for (int i = 0; i < positionInText; i++) {
            output = space + output;
        }
        return output;
    }

    private String calcTextInShiftNums() {
        int[] shiftNums = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            shiftNums[i] = shiftAlphabet[getIndex(text.charAt(i))];
        }

        StringBuilder textInShiftNums = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            textInShiftNums.append(shiftNums[i]);
        }
        return textInShiftNums.toString();
    }
}
