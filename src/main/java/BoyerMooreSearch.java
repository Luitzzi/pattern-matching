import org.jetbrains.annotations.NotNull;

/**
 * Pattern searching algorithm implementing the bad character heuristic. <br>
 * Preprocesses the pattern to determine shifts, allowing it to skip sections of the text,
 * enabling sublinear time complexity.
 * <ul>
 *  Possible alphabets:
 *  <li>Only numbers</li>
 *  <li>Upper case letters</li>
 *  <li>Lower case letters</li>
 *  <li>All letters</li>
 *  <li>Numbers and all letters</li>
 *  <li>Ascii</li>
 *  The special characters ' ' and ',' are always at the second to last and last position except in the ascii alphabet.
 * </ul>
 *
 * <ul>
 *  Time complexity:
 *  <li>text length = n; pattern length = m; alphabet length = ∑;</li>
 *  <li>To validate the text = O(n)</li>
 *  Method occurrences:
 *  <li>preparation effort = O(∑ + m)</li>
 *  <li>bc = O(n / m) ; wc = O(n * m) ; ac = O(n / m) </li>
 * </ul>
 */
public class BoyerMooreSearch implements TextSearcher {
    @NotNull
    private String text;
    private Alphabet alphabetType;
    private int sizeOfAlphabet;
    private int[] shiftTable;

    public enum Alphabet {onlyNumbers, upperCaseLetters, lowerCaseLetters, allLetters, numbersAndAllLetters, ascii}

    /**
     * The alphabets containing the letters also include the space at position 26 and comma at position 27.
     * @param text
     * @param alphabetType
     */
    public BoyerMooreSearch(String text, Alphabet alphabetType) {
        setText(text, alphabetType);
        calcSizeOfAlphabet();
        initShiftTable();
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
                if (pattern.length() - counterInPattern > shiftTable[getIndex(text.charAt(counterInText))]) {
                    /* Went more steps left (down the text) then the jump distance from shiftAlphabet would be.
                       -> Jump all steps back and one step further */
                    counterInText += pattern.length() - counterInPattern;
                }
                else {
                    counterInText += shiftTable[getIndex(text.charAt(counterInText))];
                }
                // Reset counterInPattern
                counterInPattern = pattern.length() - 1;
            }
        }

        return occurrences;
    }

    public void visualiseComparisons(String pattern) throws InterruptedException {
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
            char currentCharInPattern = pattern.charAt(counterInPattern);
            output = calcNumSpaces(String.valueOf(currentCharInPattern),counterInText);
            lastJumpLength = counterInText - prevTextPos;
            prevTextPos = counterInText;
            System.out.print("\r" + output + " jumpsize: " + lastJumpLength + " occurrences: " + occurrences);
            Thread.sleep(10000);

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
                if (pattern.length() - counterInPattern > shiftTable[getIndex(text.charAt(counterInText))]) {
                    /* Went more steps left (down the text) then the jump distance from shiftAlphabet would be.
                       -> Jump all steps back and one step further */
                    counterInText += pattern.length() - counterInPattern;
                }
                else {
                    counterInText += shiftTable[getIndex(text.charAt(counterInText))];
                }
                // Reset counterInPattern
                counterInPattern = pattern.length() - 1;
            }
        }

    }

    public @NotNull String getText() {
        return text;
    }

    public void setText(String text, Alphabet alphabetType) {
        setAlphabetType(alphabetType);
        if (isTextValid(text)) {
            this.text = text;
        }
        else throw new IllegalArgumentException("The text consists of characters that are not included in the alphabet!");
    }

    public Alphabet getAlphabetType() {
        return alphabetType;
    }

    public void setAlphabetType(Alphabet alphabetType) {
        if (alphabetType != null) {
            this.alphabetType = alphabetType;
        }
        else throw new IllegalArgumentException("An alphabet needs to be chosen. Null is not valid.");
    }

    public int getSizeOfAlphabet() {
        return sizeOfAlphabet;
    }

    public void calcSizeOfAlphabet() {
        switch (alphabetType) {
            case onlyNumbers -> sizeOfAlphabet = 10 + 2;
            case lowerCaseLetters -> sizeOfAlphabet = 26 + 2;
            case upperCaseLetters -> sizeOfAlphabet = 26 + 2;
            case allLetters -> sizeOfAlphabet = 2 * 26 + 2;
            case numbersAndAllLetters -> sizeOfAlphabet = 10 + 2 * 26 + 2;
            case ascii -> sizeOfAlphabet = 128;
            default -> throw new IllegalArgumentException("Alphabet type is missing.");
        }
    }

    public int[] getShiftTable() {
        return shiftTable;
    }

    public void initShiftTable() {
        shiftTable = new int[sizeOfAlphabet];
    }

    public boolean isTextValid(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (!isCharValid(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isCharValid(char c) {
        if (c == ' ' || c == ',') {
            return true;
        }
        else {
            switch (alphabetType) {
                case onlyNumbers -> {
                    return checkForOnlyNumbers(c);
                }
                case upperCaseLetters -> {
                    return checkForUpperCaseLetters(c);
                }
                case lowerCaseLetters -> {
                    return checkForLowerCaseLetters(c);
                }
                case allLetters -> {
                    return checkForAllLetters(c);
                }
                case numbersAndAllLetters -> {
                    return checkForNumbersAndAllLetters(c);
                }
                case ascii -> {
                    return checkForAscii(c);
                }
                default -> {
                    return false;
                }
            }
        }
    }

    private boolean checkForOnlyNumbers(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean checkForUpperCaseLetters(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private boolean checkForLowerCaseLetters(char c) {
        return c >= 'a' && c <= 'z';
    }

    private boolean checkForAllLetters(char c) {
        return checkForUpperCaseLetters(c) || checkForLowerCaseLetters(c);
    }

    private boolean checkForNumbersAndAllLetters(char c) {
        return checkForOnlyNumbers(c) || checkForUpperCaseLetters(c) || checkForLowerCaseLetters(c);
    }

    private boolean checkForAscii(char c) {
        return c >= 0 && c <= 127;
    }

    /**
     * Maps the ASCII value of a char to the position in the alphabet.
     * Special cases ' ' and ',' are always on the second to last and last position in alphabet.
     * General order is: numbers ; upperCase ; lowerCase ; specialChars
     * (Of course only one alphabet would be the most effective solution)
     * @param charInPattern character from the text
     * @return index in the alphabet
     */
    public int getIndex(char charInPattern) {
        if (alphabetType == Alphabet.ascii) {
            return charInPattern;
        }
        else if (charInPattern == ' ') {
            return sizeOfAlphabet - 2;
        }
        else if (charInPattern == ',') {
            return sizeOfAlphabet - 1;
        }
        else {
            if (charInPattern >= '0' && charInPattern <= '9') {
                // Number
                return charInPattern - '0';
            } else if (charInPattern >= 'A' && charInPattern <= 'Z') {
                // Upper case
                if (alphabetType == Alphabet.numbersAndAllLetters) {
                    return charInPattern - 'A' + 10;
                }
                else {
                    return charInPattern - 'A';
                }
            } else {
                // Lower case
                if (alphabetType == Alphabet.allLetters) {
                    return charInPattern - 'a' + 26;
                }
                else if (alphabetType == Alphabet.numbersAndAllLetters) {
                    return charInPattern - 'a' + 10 + 26;
                }
                else {
                    return charInPattern - 'a';
                }
            }
        }
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
            shiftTable[i] = patternLength;
        }
        // Edit the shiftAlphabet array for all chars occurring in the pattern
        for (int i = 0; i < patternLength; i++) {
            shiftTable[getIndex(pattern.charAt(i))] = patternLength - i - 1;
        }
    }

    /**
     * Method used for the visualisation.
     * Concatenate so many spaces as the counterInText has advanced with the current char in the pattern
     * @param currentCharInPattern current char in the pattern that is compared with the text
     * @param positionInText
     * @return String consisting of positionInText many spaces and the char that is currently compared to the text
     */
    private String calcNumSpaces(String currentCharInPattern, int positionInText) {
        String space = " ";
        for (int i = 0; i < positionInText; i++) {
            currentCharInPattern = space + currentCharInPattern;
        }
        return currentCharInPattern;
    }

    /**
     * Method used for the visualisation.
     * Calculate for each char in the text the corresponding shift size from the shift table.
     * Is printed above the text to visualise the shifts.
     * @return the text but instead of the chars with the shift sizes
     */
    private String calcTextInShiftNums() {
        int[] shiftNums = new int[text.length()];
        for (int i = 0; i < text.length(); i++) {
            shiftNums[i] = shiftTable[getIndex(text.charAt(i))];
        }

        StringBuilder textInShiftNums = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            textInShiftNums.append(shiftNums[i]);
        }
        return textInShiftNums.toString();
    }
}
