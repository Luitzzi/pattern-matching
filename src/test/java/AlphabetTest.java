import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlphabetTest {

    @Test
    public void test_ValidateText() {
        String number = "1234 ,";
        String lower = "abc ,";
        String upper = "ABC ,";
        String allLetters = "abcABC ,";
        String numAndLetters = "123abcABC ,";
        String ascii = "*&aA1 ,";

        BoyerMooreSearch.Alphabet alphabet = BoyerMooreSearch.Alphabet.onlyNumbers;
        BoyerMooreSearch textSearch = new BoyerMooreSearch(number,alphabet);
        // isTextValid needs to be public
        /*
        assertTrue(textSearch.isTextValid(number));
        assertFalse(textSearch.isTextValid(lower));
        assertFalse(textSearch.isTextValid(upper));
        assertFalse(textSearch.isTextValid(allLetters));
        assertFalse(textSearch.isTextValid(numAndLetters));
        assertFalse(textSearch.isTextValid(ascii));

        alphabet = BoyerMooreSearch.Alphabet.lowerCaseLetters;
        textSearch.setAlphabetType(alphabet);
        assertFalse(textSearch.isTextValid(number));
        assertTrue(textSearch.isTextValid(lower));
        assertFalse(textSearch.isTextValid(upper));
        assertFalse(textSearch.isTextValid(allLetters));
        assertFalse(textSearch.isTextValid(numAndLetters));
        assertFalse(textSearch.isTextValid(ascii));

        alphabet = BoyerMooreSearch.Alphabet.upperCaseLetters;
        textSearch.setAlphabetType(alphabet);
        assertFalse(textSearch.isTextValid(number));
        assertFalse(textSearch.isTextValid(lower));
        assertTrue(textSearch.isTextValid(upper));
        assertFalse(textSearch.isTextValid(allLetters));
        assertFalse(textSearch.isTextValid(numAndLetters));
        assertFalse(textSearch.isTextValid(ascii));

        alphabet = BoyerMooreSearch.Alphabet.allLetters;
        textSearch.setAlphabetType(alphabet);
        assertFalse(textSearch.isTextValid(number));
        assertTrue(textSearch.isTextValid(allLetters));
        assertFalse(textSearch.isTextValid(numAndLetters));
        assertFalse(textSearch.isTextValid(ascii));

        alphabet = BoyerMooreSearch.Alphabet.numbersAndAllLetters;
        textSearch.setAlphabetType(alphabet);
        assertTrue(textSearch.isTextValid(numAndLetters));
        assertFalse(textSearch.isTextValid(ascii));

        alphabet = BoyerMooreSearch.Alphabet.ascii;
        textSearch.setAlphabetType(alphabet);
        assertTrue(textSearch.isTextValid(ascii));
         */
    }

    @Test
    public void test_GetIndex() {
        String number = "1239 ,";
        String lower = "abc ,";
        String upper = "ABC ,";
        String allLetters = "abzABZ ,";
        String numAndLetters = "129abzABZ ,";
        String ascii = "*&ab24 ,";

        BoyerMooreSearch.Alphabet alphabet = BoyerMooreSearch.Alphabet.onlyNumbers;
        BoyerMooreSearch textSearcher = new BoyerMooreSearch(number,alphabet);
        for (int i = 0; i < 6; i++) {
            System.out.print(textSearcher.getIndex(number.charAt(i)));
        }

        System.out.println();

        alphabet = BoyerMooreSearch.Alphabet.allLetters;
        textSearcher = new BoyerMooreSearch(allLetters,alphabet);
        for (int i = 0; i < 8; i++) {
            System.out.print(textSearcher.getIndex(allLetters.charAt(i)) + " ");
        }

        System.out.println();

        alphabet = BoyerMooreSearch.Alphabet.numbersAndAllLetters;
        textSearcher = new BoyerMooreSearch(numAndLetters,alphabet);
        for (int i = 0; i < 11; i++) {
            System.out.print(textSearcher.getIndex(numAndLetters.charAt(i)) + " ");
        }

        System.out.println();

        alphabet = BoyerMooreSearch.Alphabet.ascii;
        textSearcher = new BoyerMooreSearch(ascii,alphabet);
        for (int i = 0; i < 8; i++) {
            System.out.print(textSearcher.getIndex(ascii.charAt(i)) + " ");
        }
    }
}
