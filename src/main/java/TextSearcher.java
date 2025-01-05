/**
 * Implementing a strategy pattern for text searching / pattern matching.
 * <ul>
 *  Implemented Algorithms:
 *  <li>Naive search </li>
 *  <li>Advanced naive search - Using only the counterInText (V1 )</li>
 *  <li>Advanced naive search - Similar to the naive search (V2) </li>
 *  <li>Boyer Moore Algorithm </li>
 * </ul>
 */
public interface TextSearcher {
    int occurences(String pattern);
}
