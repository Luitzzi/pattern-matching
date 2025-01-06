public class Main {
    public static void main(String[] args) throws Exception{
        //visualiseNaive();
        //visualiseAdvancedNaiveV1();
        //visualiseAdvancedNaiveV2();
        //visualiseBoyerMoore();
        altklausur();
    }

    public static void visualiseNaive() throws Exception{
        String text = "ALGORITHMEN UND DATENSTRUKTUREN";
        String pattern = "DATEN";
        NaiveSearch textSearcher = new NaiveSearch(text);
        textSearcher.visualiseComparisons(pattern);
    }

    public static void visualiseAdvancedNaiveV1() throws Exception{
        String text = "DATEDATEN ALGORITHMEN UND DATENSTRUKTUREN";
        String pattern = "DATEN";
        AdvancedNaiveSearchV1 textSearcher = new AdvancedNaiveSearchV1(text);
        textSearcher.visualiseComparisons(pattern);
    }

    public static void visualiseAdvancedNaiveV2() throws Exception{
        String text = "DATEDATEN ALGORITHMEN UND DATENSTRUKTUREN";
        String pattern = "DATEN";
        AdvancedNaiveSearchV2 textSearcher = new AdvancedNaiveSearchV2(text);
        textSearcher.visualiseComparisons(pattern);
    }

    public static void visualiseBoyerMoore() throws Exception{
        String text = "ALGORITHMEN UND DATENSTRUKTUREN";
        String pattern = "DATEN";
        BoyerMooreSearch textSearcher = new BoyerMooreSearch(text, BoyerMooreSearch.Alphabet.upperCaseLetters);
        textSearcher.visualiseComparisons(pattern);
    }

    public static void altklausur() throws Exception{
        String text = "363645636363645632136";
        String pattern = "456321";
        BoyerMooreSearch textSearcher = new BoyerMooreSearch(text, BoyerMooreSearch.Alphabet.onlyNumbers);
        textSearcher.visualiseComparisons(pattern);
    }
}
