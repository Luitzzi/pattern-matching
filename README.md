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
![Naive Search visualised](/images/naive_search.gif)<br/><br/>

* **Advanced Naive Search V1**<br/>
![Advanced Naive Search V1 visualised](/images/advanced_naive_search_v1.gif)<br/><br/>

* **Advanced Naive Search V2**<br/>
![Advanced Naive Search V2 visualised](/images/advanced_naive_search_v2.gif)<br/><br/>

* **Boyer Moore**<br/>
![Boyer Moore visualised](/images/boyer_moore.gif)<br/><br/>


![Static Badge](https://img.shields.io/badge/Author-Luis_Gerlinger-blue)