![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)<br/>
![All algorithms visualised](/images/all.gif)<br/>
Welcome to MyPatternMatchers - A Collection of pattern matching algorithms

---

## Algorithms
text length = n; pattern length = m;
* **NaiveSearch**<br/>
    Compares the text with the pattern using two hard coded for-loops.<br/>
    -> Time complexity: bc, ac = θ(n); wc = θ(n * m)<br/><br/>

* **AdvancedNaiveSearch V1 and V2**<br/>
    If all characters in the pattern are different, it is possible to jump over certain letters in the text.<br/>
    There are two implementations of the algorithm:<br/>
    V1 has only one 'pointer' going over the text directly performing the jumps.
    On the other hand is V2 very similar to NaiveSearch with a 'pointer' in the text and the pattern.
    It jumps when a match or mismatch occurs.<br/>
    -> Time complexity: bc, ac, wc =  θ(n)<br/><br/>

* **BoyerMooreSearch with bad-character heuristic**<br/>
    Preprocesses the pattern to determine shifts which allows it to skip sections of the text.<br/>
    Implements multiple alphabet variations:<br/>
    Only numbers, uppercase, lowercase, all letters, numbers and all letters, ascii<br/>
    -> Time complexity: preparation - θ(∑ + m); bc, ac = θ(n / m); wc = θ(n * m)<br/><br/>

## Visualisation
* **Naive Search** <br/>
![Naive Search visualised](/images/naive_search.gif)<br/>
Pattern = "DATEN"; Text = "ALGORITHMEN UND DATENSTRUKTUREN"<br/>
Variables: 
  * comparisons -> Displays the number of comparisons the inner for-loop performed.<br/>
  * occurences -> How often the pattern occurs in the text.<br/><br/>

* **Advanced Naive Search V1**<br/>
![Advanced Naive Search V1 visualised](/images/advanced_naive_search_v1.gif)<br/>
Pattern = "DATEN"; Text = "DATEN DATES"<br/>
Only one pointer goes over the text and keeps track of the letter from the pattern that needs to be compared to the text.<br/>
Variables:
    * compared with \<letter\> -> Letter from the pattern that is compared.<br/>
    * occurences -> How often the pattern occurs in the text.<br/><br/>

* **Advanced Naive Search V2**<br/>
![Advanced Naive Search V2 visualised](/images/advanced_naive_search_v2.gif)<br/><br/>
Pattern = "DATEN"; Text = "DATEN DATABCDE"<br/>
The text-pointer jumps after comparing DATEN over all previously compared letters. Same with DAT later.
Variables:
    * comparisons -> Displays the number of comparisons the inner for-loop performed.<br/>
    * occurences -> How often the pattern occurs in the text.<br/><br/>

* **Boyer Moore**<br/>
![Boyer Moore visualised](/images/boyer_moore.gif)<br/><br/>
Pattern = "DATEN"; Text = "ALGORITHMEN UND DATENSTRUKTUREN"<br/>
The numbers above the text display the jumpsize of the corresponding letter.
Variables:
    * \<letter\> -> The letter from the pattern that is currently compared to the text.
    * jumpsize -> Displays the size of the previous jump. It is similar to the jumpsize number above the last compared letter.<br/>
    * occurences -> How often the pattern occurs in the text.<br/><br/>


![Static Badge](https://img.shields.io/badge/Author-Luis_Gerlinger-blue)